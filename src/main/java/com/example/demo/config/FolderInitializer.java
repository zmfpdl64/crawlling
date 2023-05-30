package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FolderInitializer implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${file.dir}")
    private String filepath;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        File folder = new File(filepath);
        if(!folder.exists()){
            folder.mkdirs();
        }
    }
}
