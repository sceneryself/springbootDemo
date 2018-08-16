package com.self.tools.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class MyScheduleTask {
    private static Logger log = LoggerFactory.getLogger(MyScheduleTask.class);

    public void scheduleTest() {
        log.info("scheduleTest start");
    }
}
