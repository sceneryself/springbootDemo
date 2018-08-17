package com.self.util;

import com.alibaba.fastjson.JSONObject;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TestLocalDateTime {
    public static void main(String[] args) {
        LocalTime lts = LocalTime.of(9, 46, 31);
        LocalTime lte = LocalTime.of(10, 9, 4);

        System.out.println();
        System.out.println(lts.toSecondOfDay());
        System.out.println(LocalTime.ofSecondOfDay(lte.toSecondOfDay() - lts.toSecondOfDay()));


        LocalTime counts = LocalTime.of(2, 40, 59);
        long per = counts.toSecondOfDay() / 762;
        System.out.println(LocalTime.ofSecondOfDay(per));

        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(ldt.format(dtf));



        JSONObject jsonResp = new JSONObject();

        jsonResp.get("self");

    }
}
