package com.test.service.impl;

import com.test.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateServiceImpl implements RestTemplateService {
    @Autowired
    public RestTemplate restTemplate;

    @Value("{$spring.service.url}")
    private String service_url;

    @Override
    public String getInfo(String url) {
        return null;
    }
}
