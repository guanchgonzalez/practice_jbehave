package com.guanchgonzalez.rgl.practice.steps;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import com.guanchgonzalez.rgl.practice.BrowserDriver;
import java.time.Duration;
import java.util.List;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JBehaveSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(JBehaveSteps.class);

    private WebDriver webDriver;

    private boolean status = false;

    @Given("A Chrome browser with Google Home Page searching for $inTex")
    public void givenAChromeBrowserWithGoogleHomePageSearchingFor(String inText) {
        LOGGER.info("Starting browser");

        // Start browser
        String googleHomePage = "https://www.google.com";
        String searchBox = "APjFqb";
        webDriver = BrowserDriver.loadPage(googleHomePage);

        // Search for inText
        WebElement searchTextElement = new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(elementToBeClickable(id(searchBox)));
        searchTextElement.click();
        searchTextElement.sendKeys(inText);

        String resultClassName = "aajZCb";
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(presenceOfElementLocated(className(resultClassName)));
        searchTextElement.sendKeys(Keys.RETURN);
    }

    @When("Response includes $outText")
    public void whenResponseIncludes(String outText) {
        LOGGER.info("Getting response");

        String resClass = "v7W49e";
        new WebDriverWait(webDriver, Duration.ofSeconds(20)).until(presenceOfElementLocated(className(resClass)));

        String textRes = "//h3";
        List<WebElement> results = webDriver.findElements(xpath(textRes));
        List<String> texts = results.stream().map(WebElement::getText).toList();
        status = texts.contains(outText);

        LOGGER.info("Results:{}", texts);
    }

    @Then("Status is $status")
    public void thenStatusIs(String outText) {
        LOGGER.info("Test status");
        BrowserDriver.close();
        Assert.assertTrue(status);
    }
}
