package rgl_practice.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import rgl_practice.BrowserDriver;

public class JBehaveSteps {

  private WebDriver rglBrowser = BrowserDriver.getCurrentDriver();
  private WebElement rglRes;
  private boolean status = false;

  @Given("A Chrome browser with Google Home Page searching for $inTex") 
  public void givenAChromeBrowserWithGoogleHomePageSearchingFor(String inText) {
    System.out.println("Starting browser");

    // Start browser
    String googleHomePage = "https://www.google.com";
    String acceptAll = "APjFqb";
    rglRes = BrowserDriver.loadPage(googleHomePage, acceptAll);

    // Search for inText
    rglBrowser.findElement(By.id(acceptAll)).sendKeys(inText);
  }

  @When("Response includes $outText")
  public void whenResponseIncludes(String outText) {
    System.out.println("Getting response");
    String resClass = "v7W49e";
    rglRes = new WebDriverWait(rglBrowser, Duration.ofSeconds(10))
                   .until(ExpectedConditions.presenceOfElementLocated(By.className(resClass)));
    status = rglRes.toString().contains(outText);
  }

  @Then("Status is $status")
  public void thenStatusIs(String outText) {
    System.out.println("Test status");
    BrowserDriver.close();
    Assert.assertTrue(status);
  }

}
