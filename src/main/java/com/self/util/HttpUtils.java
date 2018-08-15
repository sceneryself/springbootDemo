package com.self.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class HttpUtils {
    public static String invokeGet(String url, Map<String, Object> getParam) {
        CloseableHttpClient client = HttpClients.custom().build();
        StringBuilder sb = new StringBuilder();
        sb.append(url).append("?");
        for (Map.Entry<String, Object> entry : getParam.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        HttpGet get = new HttpGet(sb.substring(0, sb.length() - 1));
        String responseContent = null;
        try {
            CloseableHttpResponse response = client.execute(get);
            responseContent = getResponseContent(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseContent;
    }

    public static String invokePost(String url, String postParam) {
        CloseableHttpClient client = HttpClients.custom().build();
        HttpPost post = new HttpPost(url);
        String responseContent = null;
        StringEntity entity = new StringEntity(postParam, "UTF-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        try {
            CloseableHttpResponse response = client.execute(post);
            responseContent = getResponseContent(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseContent;
    }

    public static String invokePostXml(String url, String postParam) {
        CloseableHttpClient client = HttpClients.custom().build();
        HttpPost post = new HttpPost(url);
        String responseContent = null;
        StringEntity entity = new StringEntity(postParam, "UTF-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("“application/x-www-form-urlencoded");
        post.setEntity(entity);
        try {
            CloseableHttpResponse response = client.execute(post);
            responseContent = getResponseContent(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseContent;
    }

    public static String invokePut(String url, String putParam) {
        CloseableHttpClient client = HttpClients.custom().build();
        HttpPut put = new HttpPut(url);
        String responseContent = null;
        StringEntity entity = new StringEntity(putParam, "UTF-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        put.setEntity(entity);
        try {
            CloseableHttpResponse response = client.execute(put);
            responseContent = getResponseContent(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseContent;
    }

    public static String invokeDelete(String url, String putParam) {
        CloseableHttpClient client = HttpClients.custom().build();
        HttpDelete delete = new HttpDelete(url);
        String responseContent = null;
        StringEntity entity = new StringEntity(putParam, "UTF-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        try {
            CloseableHttpResponse response = client.execute(delete);
            responseContent = getResponseContent(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseContent;
    }

    private static String getResponseContent(CloseableHttpResponse response) throws IOException {
        String responseContent = null;
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity responseEntity = response.getEntity();
            responseContent = EntityUtils.toString(responseEntity, "UTF-8");
        }
        return responseContent;
    }
}
