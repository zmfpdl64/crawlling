package com.example.demo.controller;

import com.example.demo.domain.entity.Hola;
import com.example.demo.respository.HolaRepsitory;
import com.example.demo.service.Crawlling;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@EnableScheduling
@Slf4j
@RestController
public class UserController {

    private final Crawlling crawlling;
    private final HolaRepsitory holaRepsitory;

    @GetMapping("/")
    public ResponseEntity<Page<Hola>> home(@RequestParam("content")String content, Pageable pageable) throws JsonProcessingException {
        log.info("connect homepage");

        Page<Hola> byKeyWord = holaRepsitory.findByKeyWord(content, pageable);

        for(Hola h : byKeyWord){
            log.info("{} {} {}", h.getId(), h.getPostname(), h.getContent());
        }
        return ResponseEntity.ok().body(byKeyWord);
    }

//    @Scheduled(fixedRate = 1000)
//    public void test(){
//        log.info("scheduled test");
//    }

//    @Scheduled(fixedRate = 30_000)
//    public void crawlling(){
//        log.info("starg crallwing");
//        crawlling.getHolaPostData();
//    }
}
