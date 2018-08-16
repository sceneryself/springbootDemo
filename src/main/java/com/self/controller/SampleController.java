package com.self.controller;

import com.self.model.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class SampleController {
    private static Logger log = LoggerFactory.getLogger(SampleController.class);

    @GetMapping("/")
    public String index() {
        log.info("reqeust index");
        return "hello world";

    }

    @GetMapping("/{id}")
    public String request(@PathVariable int id, @RequestParam("name") String name) {
        return "success:" + id + "; name:" + name;
    }

    @PostMapping("/aispeech/callcenter/warn")
    public String warnInfo(@RequestBody Info info) {
        log.info("get warn info");
        log.info("maxChannelCount=" + info.getMaxchannelcount() + ", curChannelCount=" + info.getCurchannelcount() + ",msg=" + info.getMsg());
        return "{\"status\":\"SUCCESS\"}";
    }
}
