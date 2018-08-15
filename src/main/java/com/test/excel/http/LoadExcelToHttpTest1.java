package com.test.excel.http;

import com.alibaba.fastjson.JSONObject;
import com.test.util.ExcelUtil;
import com.test.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
@Component
public class LoadExcelToHttpTest1 {
    private static Logger log = LoggerFactory.getLogger(LoadExcelToHttpTest1.class);

//    @Autowired
//    private RestTemplate restTemplate;

    public static void importIntentByPath(String path) {
        log.info("importIntentByPath");
        List<String[]> list = new ArrayList<>();
        importIntentByPath(path, list);
        for (int i=0; i<list.size(); i++){
            String[] strArray = list.get(i);
            for (int j=0; j<strArray.length; j++){
                log.info("request: {}", strArray[j]);
                JSONObject q = new JSONObject();
                q.put("content", strArray[j]);
                q.put("from", "idea");
                q.put("textId", "helloworld");

//                JSONObject json = restTemplate.postForEntity("", q, JSONObject.class).getBody();
//                log.info("response: {}", json);
            }
        }
    }

    private static void importIntentByPath(String path, List<String[]> list) {
        log.info("path to list");
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    importIntentByPath(file2.getAbsolutePath(), list);
                } else {
                    List<String[]> intent = ExcelUtil.readExcelListAllSheet(file2.getAbsolutePath(), ExcelUtil.COLUMN_LIST);
                    list.addAll(intent);
                }
            }
        } else {
            log.error("file can not find!," + path);
        }
    }

    public static void main(String[] args) {
        importIntentByPath("d:/test");
    }

}
