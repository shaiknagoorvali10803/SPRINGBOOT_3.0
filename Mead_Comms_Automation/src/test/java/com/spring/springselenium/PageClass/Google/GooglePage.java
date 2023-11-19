package com.spring.springselenium.PageClass.Google;

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
public class GooglePage{

    @LazyAutowired
    ScreenshotUtils screenshotUtils;

    @Value("${application.url}")
    private String url;
    @FindBy(name = "q")
    private WebElement searchBox;
    @FindBy(css = "div.g")
    private List<WebElement> results;

    @FindBy(name = "btnK")
    private List<WebElement> searchBtns;

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
        this.driver.navigate().to(url);
    }
    public void search(final String keyword){
        this.searchBox.sendKeys(keyword);
        screenshotUtils.insertScreenshot1(scenarioContext.getScenario(),"screenshot");
        this.searchBox.sendKeys(Keys.TAB);
        this.searchBtns
                .stream()
                .filter(e -> e.isDisplayed() && e.isEnabled())
                .findFirst()
                .ifPresent(WebElement::click);
    }
    public int getCount(){

        return this.results.size();


    }
    public boolean isAt() {
        return this.wait.until((d) -> this.searchBox.isDisplayed());
    }

}
