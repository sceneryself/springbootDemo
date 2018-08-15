package com.self.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value=1)
@Component
public class CommandLineRunnerListenerImpl implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println(">>>> CommandLineRunnerListenerImpl...<<<<");
    }
}
