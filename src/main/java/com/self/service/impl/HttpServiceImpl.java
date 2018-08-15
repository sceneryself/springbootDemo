package com.self.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.self.service.HttpService;
import com.self.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HttpServiceImpl implements HttpService {
    Logger log = LoggerFactory.getLogger(HttpServiceImpl.class);

    @Value("{$spring.service.url}")
    private String service_url;

    @Override
    public String getInfo(String url) {
        String respContenet = HttpUtils.invokePost(service_url, "");
        if (respContenet != null){
            JSONObject jsonObject = JSON.parseObject(respContenet);
            log.debug(jsonObject.toJSONString());
        }
        return respContenet;
    }
}
