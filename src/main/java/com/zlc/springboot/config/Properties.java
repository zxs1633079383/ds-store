package com.zlc.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "initcaches")
public class Properties {

    private final Map<String, Duration> initCaches = new HashMap<>();

    public Map<String, Duration> getInitCaches() {
        return initCaches;
    }
}
