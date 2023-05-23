package com.example.demo.service;

import com.example.demo.domain.entity.Hola;
import com.example.demo.domain.Selenium;
import com.example.demo.respository.HolaRepsitory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;


@Service
@RequiredArgsConstructor
@Slf4j
public class Crawlling {
    private static String urlHola = "https://holaworld.io";
    private final HolaRepsitory holaRepsitory;


    public void getHolaPostData() {
        log.info("크롤링 시작 로그");
        Selenium set = new Selenium();
        WebDriver driver = set.getDriver();
        driver.get(urlHola);
        boolean flag = false;
        try {
            scroll((JavascriptExecutor) driver);  //전체스크롤
            String html = driver.getPageSource();
            Document doc = Jsoup.parse(html);
            Elements element = doc.select("#root > main > div.mainContent_appWrapper___CgAh > ul");
            int count = element.select(">a").size();
            for (int i = count; i > 1; i--) {
                if (i == count) {
                    scroll((JavascriptExecutor) driver);
                    driver.findElement(By.cssSelector("#root > main > div.mainContent_appWrapper___CgAh > ul > a:nth-child(2)")).click();
                    driver.navigate().back();
                }
                Elements eachPost = element.select("a:nth-child(" + i + ")");
                driver.findElement(By.cssSelector("#root > main > div.mainContent_appWrapper___CgAh > ul > a:nth-child(" + i + ")")).click();
                Thread.sleep(500);
                Document realPost = Jsoup.parse(driver.getPageSource());
                String link = driver.getCurrentUrl();
                String num = link.substring(27);
                driver.navigate().back();
                String content = realPost.select("#root > div.studyContent_wrapper__VVyNH > div > div").text();
                String talk = realPost.select("#root > div.studyContent_wrapper__VVyNH > div > div").select("a").attr("href");
                if (talk.length() > 200)
                    talk = talk.substring(0, 199);
                if (content.length() > 200)
                    content = content.substring(0, 199);
                String userName = realPost.select("#root > div.studyContent_wrapper__VVyNH > section.studyContent_postHeader__2Qu_y > div.studyContent_userAndDate__1iYDv > div.studyContent_user__1XYmH > div").text();
                String date = realPost.select("#root > div.studyContent_wrapper__VVyNH > section.studyContent_postHeader__2Qu_y > div.studyContent_userAndDate__1iYDv > div.studyContent_registeredDate__3lybC").text();
                date = standard(date);
                String postName = eachPost.select("h1").text();
                int views = Integer.parseInt(eachPost.select(" section > div.studyItem_viewsAndComment__1Bxpj > div:nth-child(1) > p").text());
                Hola hola = new Hola(num, postName, content, userName, date, link, null, views, talk);
                holaRepsitory.save(hola);
//                convertToPost.hola(hola);
                flag = true;
            }
            if (!flag)
                log.warn("불러올 글이 없습니다!");
            else
                log.info("홀라 크롤링 성공");
        } catch (StringIndexOutOfBoundsException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.close(); // 브라우저 종료
        }
    }

    private String standard(String date) {
        date = date.substring(0, 4) + '-' + date.substring(5, 7) + '-' + date.substring(8, 10) + 'T' + LocalDateTime.now().toLocalTime().toString().substring(0, 8);
        date = LocalDateTime.parse(date).toString();

        return date;
    }

    private void scroll(JavascriptExecutor driver) throws InterruptedException {
        var stTime = new Date().getTime(); //현재시간
        while (new Date().getTime() < stTime + 500) { //5초 동안 무한스크롤 지속
            Thread.sleep(300); //리소스 초과 방지
            driver.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }
    }


}