package com.example.demo.service;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverFactory {
    public static ChromeDriver of(){
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--headless=new");
        opt.addArguments("--start-maximized");
        opt.addArguments("--disable-gpu");
        opt.addArguments("--window-size=1920,1080");
        opt.addArguments("--incognito");
        opt.addArguments("--disable-extensions");
        opt.addArguments("--ignore-certificate-errors");
        opt.addArguments("lang=ko");
        ChromeDriver chromeDriver = new ChromeDriver(opt);
        return chromeDriver;
    }
}
