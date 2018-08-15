package com.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.service.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateServiceImpl implements RestTemplateService {
    Logger log = LoggerFactory.getLogger(RestTemplateServiceImpl.class);

    @Autowired
    public RestTemplate restTemplate;

    @Value("{$spring.service.url}")
    private String service_url;

    @Override
    public String getInfo(String url) {
        JSONObject req = new JSONObject();
        req.put("test", "hello world");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity httpEntity = new HttpEntity(req, headers);
        String respContenet = restTemplate.postForObject(service_url, httpEntity, String.class);
        if (respContenet != null){
            JSONObject jsonObject = JSON.parseObject(respContenet);
            log.debug(jsonObject.toJSONString());
        }
        return respContenet;
    }
}
