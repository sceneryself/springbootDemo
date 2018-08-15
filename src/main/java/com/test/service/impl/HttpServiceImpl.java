package com.test.service.impl;

import com.test.service.HttpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HttpServiceImpl implements HttpService {

    @Value("{$spring.service.url}")
    private String service_url;

    @Override
    public String getInfo(String url) {
        return null;
    }
}
