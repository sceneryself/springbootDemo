package com.test.excel.http;

import com.alibaba.fastjson.JSONObject;
import com.test.util.ExcelUtil;
import com.test.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LoadExcelToHttpTest {
    private static Logger log = LoggerFactory.getLogger(LoadExcelToHttpTest.class);

    private static final String url = "";

    public static void importIntentByPath(String path) {
        log.info("importIntentByPath");
        List<String[]> list = new ArrayList<>();
        importIntentByPath(path, list);
        for (int i = 0; i < list.size(); i++) {
            String[] strArray = list.get(i);
            for (int j = 0; j < strArray.length; j++) {
                log.info("request: {}", strArray[j]);
                JSONObject q = new JSONObject();
                q.put("content", strArray[j]);
                q.put("from", "idea1");
                q.put("textId", "helloworld");

                String resp = HttpUtils.invokePost(url, q.toJSONString());
                log.info("response: {}", resp);
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
