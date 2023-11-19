package com.spring.springselenium.StepDefinitions;

import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.PageClass.Homepage.HomePage;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest
public class HomeSteps {
    @LazyAutowired
    private HomePage homePage;
    @Autowired
    protected WebDriver driver;

    @Autowired
    protected WebDriverWait wait;

    @Autowired
    ScenarioContext scenarioContext;

    @PostConstruct
    private void init(){
        PageFactory.initElements(this.driver, this);
    }
    Scenario scenario;
    @Before
    public void settingScenario(Scenario scenario) {
        this.scenario=scenario;
        scenarioContext.setScenario(scenario);
        System.out.println("scenarion object in home page by : ==>"+ scenario );
    }


    @Given("I am Google Page")
    public void launchSite() {
        this.homePage.goTo();
         }

    @When("Search for the Word {string}")
    public void enterKeyword(String keyword) throws InterruptedException {
        this.homePage.search(keyword);
    }

}
