package com.spring.springselenium.StepDefinitions;

import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.PageClass.Google.GooglePage;
import com.spring.springselenium.PageClass.Visa.VisaRegistrationPage;
import com.spring.springselenium.Utils.ScreenshotUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.testng.Assert;

import java.time.LocalDate;

public class VisaSteps {
    @Autowired
    protected WebDriver driver;

    @Autowired
    protected WebDriverWait wait;

    @LazyAutowired
    private VisaRegistrationPage registrationPage;
    @Autowired
    private TestUserDetails testUserDetails;

    @Autowired
    ScreenshotUtils screenshotUtils;
   @LazyAutowired
    private GooglePage googlePage;

    @LazyAutowired
    private VisaRegistrationPage visaRegistrationPage;

    @Autowired
    ScenarioContext scenarioContext;

    @Autowired
    public VisaSteps (TestUserDetails testUserDetails)
    {
        this.testUserDetails=testUserDetails;
    }

    @PostConstruct
    private void init(){
        PageFactory.initElements(this.driver, this);
    }
    @Given("I am on VISA registration form")
    public void launchSite() {
        this.driver.navigate().to("https://vins-udemy.s3.amazonaws.com/sb/visa/udemy-visa.html");
        System.out.println("Current Thread Number "+ Thread.currentThread().getThreadGroup() +"thread number"+ Thread.currentThread().getId());
        //screenshotUtils.insertScreenshot("screenshot");
        screenshotUtils.insertScreenshot1();
        //Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
         }

    @When("I select my from country {string} and to country {string}")
    public void selectCountry(String from, String to) {
        this.registrationPage.setCountryFromAndTo(from, to);
    }

    @And("I enter my dob as {string}")
    public void enterDob(String dob) {
        this.registrationPage.setBirthDate(LocalDate.parse(dob));
    }

    @And("I enter my name as {string} and {string}")
    public void enterNames(String fn, String ln) {
        this.registrationPage.setNames(fn, ln);
    }

    @And("I enter my contact details as {string} and {string}")
    public void enterContactDetails(String email, String phone) {
        this.registrationPage.setContactDetails(email, phone);
    }

    @And("I enter the comment {string}")
    public void enterComment(String comment) {
        this.registrationPage.setComments(comment);
    }

    @And("I submit the form")
    public void submit() {
        //screenshotUtils.insertScreenshot("screenshot");
        screenshotUtils.insertScreenshot1();
        //Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        this.registrationPage.submit();
        System.out.println("The Username from GoogleTest Class is:" + testUserDetails.getUserDetails().getUsername());
        System.out.println("The Username from GoogleTest Class is:" + testUserDetails.getUserDetails().getPassword());
        }

    @Then("I should see get the confirmation number")
    public void verifyConfirmationNumber() throws InterruptedException {
        boolean isEmpty = StringUtils.isEmpty(this.registrationPage.getConfirmationNumber().trim());
        //screenshotUtils.insertScreenshot("screenshot");
        screenshotUtils.insertScreenshot1();
        Assert.assertFalse(isEmpty);
        Thread.sleep(2000);
    }

   }
