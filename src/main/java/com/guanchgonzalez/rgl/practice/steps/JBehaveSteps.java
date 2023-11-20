package com.guanchgonzalez.rgl.practice.steps;

import com.guanchgonzalez.rgl.practice.BrowserDriver;
import java.time.Duration;
import java.util.List;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JBehaveSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(JBehaveSteps.class);

    private final WebDriver rglBrowser = BrowserDriver.getCurrentDriver();

    private boolean status = false;

    @Given("A Chrome browser with Google Home Page searching for $inTex")
    public void givenAChromeBrowserWithGoogleHomePageSearchingFor(String inText) {
        LOGGER.info("Starting browser");

        // Start browser
        String googleHomePage = "https://www.google.com";
        String searchBox = "APjFqb";
        BrowserDriver.loadPage(googleHomePage, searchBox);

        // Search for inText
        // BrowserDriver.getCurrentDriver();
        WebElement searchText = rglBrowser.findElement(By.id(searchBox));
        searchText.sendKeys(inText);
        searchText.sendKeys(Keys.RETURN);
    }

    @When("Response includes $outText")
    public void whenResponseIncludes(String outText) {
        LOGGER.info("Getting response");

        String resClass = "v7W49e";
        new WebDriverWait(rglBrowser, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(By.className(resClass)));
        String textRes = "//h3";
        List<WebElement> results = rglBrowser.findElements(By.xpath(textRes));
        List<String> texts = results.stream().map(WebElement::getText).toList();
        status = texts.contains(outText);

        // status = rglBrowser.findElements(By.xpath(textRes)).toString().contains(outText);
        LOGGER.info("Results:{}", texts);
    }

    @Then("Status is $status")
    public void thenStatusIs(String outText) {
        LOGGER.info("Test status");
        BrowserDriver.close();
        Assert.assertTrue(status);
    }
}
