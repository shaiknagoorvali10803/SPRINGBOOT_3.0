package com.spring.springselenium.StepDefinitions;

import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.Configuraion.service.ScreenshotService;
import com.spring.springselenium.Utilities.AllureResultCleaner;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.context.ApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class CucumberHooks {
    @LazyAutowired
    private ScreenshotService screenshotService;
    @LazyAutowired
    private ApplicationContext applicationContext;
    @LazyAutowired
    ScenarioContext scenarioContext;
    @BeforeAll
    public static void before_or_after_all() throws IOException {
        AllureResultCleaner.cleanUpAllureResultDirectory();
    }
    @Before
    public void settingScenario(Scenario scenario) {
        this.applicationContext.getBean(ScenarioContext.class).setScenario(scenario);
    }
    @AfterStep
    public void afterStep(Scenario scenario) throws IOException, InterruptedException {
        if(scenario.isFailed()){
            scenario.attach(this.screenshotService.getScreenshot(), "image/png", scenario.getName());
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(this.screenshotService.getScreenshot()));
        }
    }
    @After
    public void afterScenario(){
        this.applicationContext.getBean(WebDriver.class).quit();
        }
   }


