package com.spring.springselenium.Utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.spring.springselenium.Configuraion.annotation.Page;
import com.spring.springselenium.StepDefinitions.ScenarioContext;
import io.cucumber.java.Scenario;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

@Page
public class ScreenshotUtils {
    public static Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);
    private static Map<Integer,ScenarioContext> contextMap = new HashMap<>();
    @Autowired
    WebDriver driver;
    @Autowired
    private ApplicationContext context;
    @PostConstruct
    private void init(){
        PageFactory.initElements(this.driver, this);
    }

    public void insertScreenshot(String screenshotTitle){
    Scenario scenario=context.getBean(ScenarioContext.class).getScenario();
        System.out.println(scenario.hashCode());
        if(!scenario.isFailed() && scenario!=null ){
            try{
                scenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", screenshotTitle);
            }
            catch (Exception e){
                logger.error("failed to add screenshot because scenario already failed");
            }
        }
    }
    public void insertScreenshot1(){
        ExtentCucumberAdapter.getCurrentStep().log(Status.WARNING, MarkupHelper.createLabel("screenshot", ExtentColor.GREEN),MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshotBase64()).build());
    }
    public String getScreenshotBase64() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        String screenshotAs = ts.getScreenshotAs(OutputType.BASE64);
        return screenshotAs;
    }
    public void addTestLog(String Value){
        ExtentCucumberAdapter.getCurrentStep().log(Status.PASS,Value);
    }
}
