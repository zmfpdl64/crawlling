package com.example.demo.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class CatchCrawller {
    private final static String url = "https://www.catch.co.kr/NCS/RecruitSearch";
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = ChromeDriverFactory.of();
        Actions action = new Actions(driver);
        driver.get(url);
        WebElement element = driver.findElement(By.className("al1")).findElement(By.tagName("a"));
        System.out.println(element.getAttribute("outerHTML"));
//        String title = element.findElement(By.tagName("a")).getText();
        System.out.println(element.getText());
        System.out.println(driver.getCurrentUrl());

//        System.out.println(element);
        action.moveToElement(element)
//                    .click()
                .pause(Duration.ofMillis(1000))
                .clickAndHold()
                .pause(Duration.ofMillis(1000))
                .perform();
//.get(0).findElements(By.className("pointc_20"))
        List<WebElement> elements = driver.findElements(By.tagName("tbody"));
//        String salary = elements.get(4).getText();
//        for(WebElement e : elements){
//            e.findElement(By.className())
//            System.out.println(e.getText());
//        }
//        System.out.println(elements.get(0).getAttribute("outerHTML"));
        System.out.println(driver.getCurrentUrl());


//        String movetitle = element.findElement(By.tagName("a")).getText();

//        System.out.println(title + " : " );
//        System.out.println(title + " : " + salary);
//        List<WebElement> lists = driver.findElements(By.className("t1"));
//        for(WebElement element : lists){
//            action.moveToElement(element)
//                    .pause(Duration.ofMillis(100))
//                    .clickAndHold()
//                    .pause(Duration.ofMillis(100))
//                    .perform();
//            String title = driver.getTitle();
//            String title = driver.findElement(By.className("h2")).getText();
//            String salary = driver.findElement(By.className("pointc_20")).getText();
//            System.out.println(title + " : " + salary);
//        }

    }
}
