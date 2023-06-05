package rgl_practice.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Alias;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import rgl_practice.BrowserDriver;

public class JBehaveSteps {

  private WebDriver rglBrowser = BrowserDriver.getCurrentDriver();
  private WebElement rglRes;

  @Given("A Chrome browser with Google Home Page searching for $inTex") 
  public void givenAChromeBrowserWithGoogleHomePageSearchingFor(String inText) {
    System.out.println("Starting browser");

    // Start browser
    String googleHomePage = "https://www.google.com";
    String visibleElem = "realbox";
    rglRes = BrowserDriver.loadPage(googleHomePage, visibleElem);

    // Search for inText
    BrowserDriver.getCurrentDriver().findElement(By.id("input")).sendKeys(inText);
    // rglBrowser.findElement(By.id("input")).sendKeys(inText);
  }

  @When("Response includes $outText")
  @Alias("Response not includes $outText")
  public void whenResponseIncludes(String outText) {
    System.out.println("Getting response");
    //chromeBrowser.responseContains(outText);
  }

  @Then("Status is OK for $outText")
  public void thenStatusIsOk(String outText) {
    System.out.println("Validating OK status");
    // Assert.assertTrue(chromeBrowser.getStatus(outText));
  }
  
  @Then("Status is NOK for $outText")
  public void thenStatusIsNOK(String outText) {
    System.out.println("Validating NOK status");
    // Assert.assertFalse(chromeBrowser.getStatus(outText));
  }

}
