package com.spring.springselenium.StepDefinitions;

import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.Utilities.AllureResultCleaner;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class CucumberHooks {
    @LazyAutowired
    private ApplicationContext applicationContext;
    @Autowired
    protected WebDriver driver;

    @BeforeAll
    public static void before_or_after_all() throws IOException {
        AllureResultCleaner.cleanUpAllureResultDirectory();
    }
    @AfterStep
    public void afterStep(Scenario scenario) throws IOException, InterruptedException {
        if(scenario.isFailed()){
            scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", scenario.getName());
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }
    @After
    public void afterScenario(){
        this.applicationContext.getBean(WebDriver.class).quit();
        }
   }


