package test.practice.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Alias;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.practice.BrowserDriver;
// import test.practice.ChromeBrowser;

public class JBehaveSteps {
  
  // private ChromeBrowser chromeBrowser = new ChromeBrowser();

  @Given("A Chrome Browser with $googleHomePage and search for $inTex") 
  public void givenAChromeBrowserWithAndSearchFor(String googleHomePage, String inText) {
    System.out.println("Starting browser");

    // Start browser
    BrowserDriver.getCurrentDriver().get(googleHomePage);

    // Accept Google cookies when needed
    Assert.assertTrue(BrowserDriver.getCurrentDriver().findElement(By.id("L2AGLb")).isDisplayed());

    // Search for inText
    WebElement searchBox = BrowserDriver.getCurrentDriver().findElement(By.name("q"));
    searchBox.sendKeys(inText);
    searchBox.submit();

  }

  
  @When("A response includes $outText")
  @Alias("A response not includes $outText")
  public void whenAResponseIncludes(String outText) {
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
