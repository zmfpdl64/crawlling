package com.example.demo.service;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverFactory {
    public static ChromeDriver of() {
        ChromeOptions opt = new ChromeOptions();
//        opt.addArguments("--headless");
        opt.addArguments("--disable-gpu");
        opt.addArguments("--window-size=1920,1080");
        opt.addArguments("--start-maximized");
        opt.addArguments("--disable-extensions");
        opt.addArguments("--ignore-certificate-erros");
        return new ChromeDriver(opt);
    }
}
