package com.example.demo.controller;

import com.example.demo.service.Crawlling;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@EnableScheduling
@Slf4j
@RestController
public class UserController {

    private final Crawlling crawlling;

    @GetMapping("/")
    public String home(){
        log.info("connect homepage");
        return "hello";
    }

//    @Scheduled(fixedRate = 1000)
//    public void test(){
//        log.info("scheduled test");
//    }

    @Scheduled(fixedRate = 10_000)
    public void crawlling(){
        log.info("starg crallwing");
        crawlling.getHolaPostData();
    }
}
