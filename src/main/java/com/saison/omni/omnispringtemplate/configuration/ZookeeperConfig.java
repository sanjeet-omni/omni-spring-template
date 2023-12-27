package com.saison.omni.omnispringtemplate.configuration;

import org.springframework.cloud.zookeeper.config.ZookeeperConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperConfig {

    @Bean
    public ZookeeperConfigProperties zookeeperConfigProperties() {
      return new ZookeeperConfigProperties();
    }
}
