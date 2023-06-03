package test.practice;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeBrowser {
  /**
   * @param args
   * @throws InterruptedException
   */
  public static void main(String[] args) throws InterruptedException {
    String URL = "https://www.google.com/";
    String[] optionList = {
      "test-type",
      "disable-popups-blocking",
      "incognito",
    };

    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments(optionList);

    // Instantiate a ChromeDriver class
    String rootPath = System.getProperty("user.dir");
    String driverPath = "/webdriver/chromedriver";
    System.setProperty("webdriver.chrome.driver", rootPath + driverPath);

    WebDriver driver = new ChromeDriver(options);
    driver.get(URL);
    driver.findElement(By.id("L2AGLb")).click();
    
    WebElement searchBox = driver.findElement(By.name("q"));
    searchBox.sendKeys("JBehave");

    WebDriverWait w2res = new WebDriverWait(driver, Duration.ofSeconds(5));
    w2res.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul")));
    searchBox.submit();
    Thread.sleep(10000);
    driver.quit();
  }
}
