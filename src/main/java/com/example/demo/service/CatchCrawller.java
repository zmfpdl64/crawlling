package com.example.demo.service;

import com.example.demo.domain.entity.CatchEntity;
import com.example.demo.respository.CatchRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CatchCrawller {
    private static final String url = "https://www.catch.co.kr/NCS/RecruitInformation";
    private static final String it_tag = "filter_category2";
    private static final String find_company = "al1";
    private static final String tbody = "tbody";
    private static final String employ_info = "pointc_20";
    private final CatchRepository catchRepository;
      public void getCatchPost(){
        //드라이버 초기화
        WebDriver driver = ChromeDriverFactory.of();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        Actions actions = new Actions(driver);


        driver.get(url);
        //굵직한 태그 찾기
        List<WebElement> childs = driver.findElement(By.className(it_tag)).findElements(By.tagName("a"));

        WebElement more = driver.findElement(By.className(it_tag)).findElement(By.tagName("button"));

        wait.until(ExpectedConditions.elementToBeClickable(more));
        actions.clickAndHold(more).click(more).pause(1L).perform();
        // it 버튼 찾기
        Optional<WebElement> it = childs.stream().filter(i -> i.getText().contains("IT")).findFirst();
        WebElement target = null;
        if(it.isPresent()){
            target = it.get();
        }


        wait.until(ExpectedConditions.elementToBeClickable(target));
        // 더보기 버튼 누르기
        actions.moveToElement(target).click(target).pause(Duration.ofMillis(2000)).perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(find_company)));
        // 회사 소개 url 찾기
        List<WebElement> list = driver.findElements(By.cssSelector("td > a"));
        List<String> links = new ArrayList<>();
        for(WebElement e : list) {
            //검색할 주소 찾기
            links.add(e.getAttribute("href"));
        }
        for(String link : links){
            driver.get(link);
            WebElement title = driver.findElement(By.className("tit")).findElement(By.className("h2")).findElement(By.tagName("a"));

            WebElement e = driver.findElement(By.tagName(tbody));
            List<WebElement> element = e.findElements(By.className(employ_info));
            wait.until(ExpectedConditions.visibilityOf(e));

            List<String> info = element.stream().map(WebElement::getText).toList();
            CatchEntity catchEntity = CatchEntity.of(title.getText(), info.get(0), info.get(1), info.get(2), info.get(3), info.get(4), info.get(5));
            catchRepository.save(catchEntity);

            driver.navigate().back();
        }
        /**
         * 검색할 주소에서
         * 1. 경력
         * 2. 학력
         * 3. 모집부문
         * 4. 고용형태
         * 5. 연봉
         * 6. 근무지역
         * 위의 정보들을 크롤링 해온다.
         */

    }
    public Page<CatchEntity> getPost(Pageable pageable){
        return catchRepository.findAll(pageable);
    }
}

