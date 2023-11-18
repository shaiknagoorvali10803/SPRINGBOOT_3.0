package com.spring.springselenium.Utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.Configuraion.annotation.Page;
import com.spring.springselenium.Configuraion.service.ScreenshotService;
import com.spring.springselenium.StepDefinitions.ScenarioContext;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Page
public class ScreenshotUtils {
    public static Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);
    private static Map<Integer,ScenarioContext> contextMap = new HashMap<>();
    @Autowired
    WebDriver driver;
    @Autowired
    ScenarioContext scenarioContext;
    @LazyAutowired
    private ScreenshotService screenshotService;


    @PostConstruct
    private void init(){
        PageFactory.initElements(this.driver, this);
        contextMap.put(driver.hashCode(),scenarioContext);
    }

    public void insertScreenshot(String screenshotTitle){
        if(!scenarioContext.getScenario().isFailed() && scenarioContext.getScenario() !=null ){
            try{
                scenarioContext.getScenario().attach(this.screenshotService.getScreenshot(), "image/png", screenshotTitle);
            }
            catch (Exception e){
                logger.error("failed to add screenshot because scenario already failed");
            }
        }
    }
    public void insertScreenshot1(){
        ExtentCucumberAdapter.getCurrentStep().log(Status.PASS, MarkupHelper.createLabel("screenshot", ExtentColor.GREEN),MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshotBase64()).build());
    }
    public void addLog(String text){
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, text);
        ExtentCucumberAdapter.getCurrentStep().log(Status.PASS,MarkupHelper.createCodeBlock(text));
        ExtentCucumberAdapter.getCurrentStep().log(Status.PASS,MarkupHelper.createLabel(text,ExtentColor.BROWN));
    }
    public void addLog(Object object){
        ExtentCucumberAdapter.getCurrentStep().log(Status.PASS,MarkupHelper.createOrderedList(object));
    }

    public void addJsonLog(Object object){
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,MarkupHelper.createJsonCodeBlock(object));
    }
    public String getScreenshotBase64() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        String screenshotAs = ts.getScreenshotAs(OutputType.BASE64);
        return screenshotAs;
    }
}
