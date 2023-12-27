package com.saison.omni.omnispringtemplate.event;

import com.saison.omni.ehs.EhsClientListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class EhsClientListenerRegister {
    @Autowired
    EhsEventDispatcher dispatcher;

    @Bean
    EhsClientListener<EhsEventDispatcher> ehsClientListener() {
        return new com.saison.omni.ehs.EhsClientListener<>(dispatcher);
    }

}
