package test.practice;

import org.openqa.selenium.WebDriver;
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
      "-incognito",
      "--remote-allow-origins=*",
    };

    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments(optionList);

    // Instantiate a ChromeDriver class
    String rootPath = System.getProperty("user.dir");
    String driverPath = "/webdriver/chromedriver";
    System.setProperty("webdriver.chrome.driver", rootPath + driverPath);
    WebDriver driver = new ChromeDriver(options);
    Thread.sleep(3000);
    driver.get(URL);
    driver.quit();
  }
}
