package com.self.controller;

import com.self.dao.UserInfoRepository;
import com.self.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class UserDataController {
    private static Logger log = LoggerFactory.getLogger("UserDataController");

    @Autowired
    private UserInfoRepository userInfoRepository;

    @GetMapping("/user/datas")
    public List<UserInfo> getUserInfos(@RequestParam String type, @RequestParam String startTime, @RequestParam String endTime) {
        log.info("request data: {},{},{}", type, startTime, endTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date start = sdf.parse(startTime);
            Date end = sdf.parse(endTime);
            return userInfoRepository.findByTypeAndLogTimeBetween(type, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/user/data/{sendId}")
    public UserInfo getUserInfo(@PathVariable String sendId, @RequestParam String type) {
        log.info("request data: {},{}", type, sendId);
        return userInfoRepository.getBySendIdAndType(sendId, type);
    }

    @GetMapping("/user/datas/{sendId}")
    public List<UserInfo> getUserInfo(@PathVariable String sendId) {
        log.info("request data: {}", sendId);
        return userInfoRepository.findBySendId(sendId);
    }
}
