package com.spring.springselenium.PageClass.Homepage;

import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.Configuraion.annotation.Page;
import com.spring.springselenium.PageClass.Base;
import com.spring.springselenium.StepDefinitions.ScenarioContext;
import com.spring.springselenium.Utils.ScreenshotUtils;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Page
public class HomePage {
    @Value("${application.url}")
    private String url;
    @FindBy(name = "q")
    private WebElement searchBox;
    @FindBy(name = "btnK")
    private List<WebElement> searchBtns;
    @LazyAutowired
    ScreenshotUtils screenshotUtils;
    @Autowired
    WebDriver driver;
    @Autowired
    WebDriverWait wait;

    @Autowired
    ScenarioContext scenarioContext;
    @PostConstruct
    private void init(){
        PageFactory.initElements(this.driver, this);
    }

    public void goTo(){
        this.driver.get(url);
    }

    public void search(final String keyword) throws InterruptedException {
        this.searchBox.sendKeys(keyword);
        screenshotUtils.insertScreenshot1(scenarioContext.getScenario(),"screenshot");
        this.searchBox.sendKeys(Keys.TAB);
        this.searchBtns
                .stream()
                .filter(e -> e.isDisplayed() && e.isEnabled())
                .findFirst()
                .ifPresent(WebElement::click);
        Thread.sleep(3000);
        screenshotUtils.insertScreenshot1(scenarioContext.getScenario(),"screenshot");
    }

}
