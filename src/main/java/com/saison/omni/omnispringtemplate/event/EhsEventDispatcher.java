package com.saison.omni.omnispringtemplate.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EhsEventDispatcher {

    private static final Logger logger = LoggerFactory.getLogger(EhsEventDispatcher.class);

    @Autowired
    ObjectMapper objectMapper;

}