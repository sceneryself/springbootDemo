package com.self.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SchedulerConfiguration {
    private static Logger log = LoggerFactory.getLogger(SchedulerConfiguration.class);

    @Scheduled(cron = "* 1 * * * ?")
    public void cornProcess() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("Scheduled cron: " + sdf.format(new Date()));
    }

    @Scheduled(fixedRate = 59000)
    public void fixedRate() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("Scheduled fixedRate:" + sdf.format(new Date()));
    }

    @Scheduled(fixedDelay = 58000)
    public void fixedDelay() {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("Scheduled fixedDelay: " + sdf.format(new Date()));
    }
}
