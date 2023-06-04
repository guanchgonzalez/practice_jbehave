package test.practice.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Alias;
import org.junit.Assert;
import test.practice.ChromeBrowser;

public class JBehaveSteps {
  
  // private ChromeBrowser chromeBrowser = new ChromeBrowser();

  @Given("A $chromeBrowser with the Google home page") 
  @Alias("I search for $inText")
  public void givenISearchFor(String inText) {
    ChromeBrowser chromeBrowser = new ChromeBrowser(inText);
  }

  @When("A response includes $outText")
  @Alias("A response not includes $outText")
  public void whenAResponseIncludes(String outText) {
    //chromeBrowser.responseContains(outText);
  }

  /**
  @Then("Status is OK for $outText")
  public void thenStatusIsOk(String outText) {
    Assert.assertTrue(chromeBrowser.getStatus(outText));
  }
  
  @Then("Status is NOK for $outText")
  public void thenStatusIsNOK(String outText) {
    Assert.assertFalse(chromeBrowser.getStatus(outText));
  }
  */
}
