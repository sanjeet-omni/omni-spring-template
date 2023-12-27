package com.saison.omni.omnispringtemplate.event;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.saison.omni.ehs.EhsHelper;
import com.saison.omni.ehs.EventConstants;
import com.saison.omni.ehs.MessageCategory;
import com.saison.omni.ehs.dto.ListenerRegistrationUnit;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EhsClientUtil {

    //If using omni logger which is part of commons. If not, please add your own logger implementation.
    private static final Logger logger = LoggerFactory.getLogger(EhsClientUtil.class);

    @Value("${service.ehs.url}")
    String eventUrl;

    @Value("${spring.application.name}")
    String applicationName;

    @Autowired
    Gson gson;

    public static final Map<String, MessageCategory> EVENTS = ImmutableMap.<String, MessageCategory>builder()
            // put event name with category as direcct - example: .put("event.name", MessageCategory.DIRECT)
            .build();

    public List<ListenerRegistrationUnit> getListenerRegistrationUnit() {
        List<ListenerRegistrationUnit> registrationUnits = new ArrayList<>();
        EVENTS.forEach((s, messageCategory) -> {
            ListenerRegistrationUnit unit = new ListenerRegistrationUnit(s, messageCategory, null);
            registrationUnits.add(unit);
        });
        return registrationUnits;
    }

    @PostConstruct
    public void executeRegistration() {
        logger.info("passing the following values to ehsHandler :{},{}", eventUrl, applicationName);
        try {
            EhsHelper ehsHelper = new EhsHelper(eventUrl, applicationName);
            boolean isRegistered = ehsHelper.registerConsumer(getListenerRegistrationUnit());
            if (!isRegistered) {
                logger.error("Error in registration of service.");
            }
        } catch (Exception e) {
            logger.info("e");
            e.printStackTrace();
        }
    }

    public void sendEventUtility(String eventType, Object object, MessageCategory category) {
        EhsHelper ehsHelper = new EhsHelper(eventUrl, applicationName);
        Map<String, Object> attributes = new HashMap<>(4);
        attributes.put(EventConstants.EVENT_METADATA_EVENT_TYPE, eventType);
        attributes.put(EventConstants.REG_METADATA_MESSAGE_TYPE, category);
        attributes.put(EventConstants.EVENT_METADATA_SOURCE, applicationName);
        attributes.put(EventConstants.PAYLOAD_METADATA_DESTINATION, "internal");
        logger.info("sending event: {}, {}", eventType, object);
        ehsHelper.sendEvent(gson.toJson(object), attributes);
    }
}