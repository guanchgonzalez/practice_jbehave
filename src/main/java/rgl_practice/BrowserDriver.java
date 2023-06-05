package rgl_practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserDriver {
  
  private static WebDriver browDriver;
  private static WebElement res4w;

  public synchronized static WebDriver getCurrentDriver() {
    System.out.println("Instantiating browser");
    if (browDriver == null) {
      try {
        String webdriver = System.getProperty("browser", "chrome");
        System.out.println("Selected driver: " + webdriver);

        // Launch browser
        FactoryBrowser(webdriver);
      }
      finally {
        Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
      }
    }
    return browDriver;
  }

  private static void FactoryBrowser (String webdriver) {
    if ("chrome".equals(webdriver)) {
      String[] optionList = {
        "test-type",
        "disable-popups-blocking",
        "incognito",
      };
      // Instantiate ChromeOptions
      WebDriverManager.chromedriver().setup();
      ChromeOptions options = new ChromeOptions();
      options.addArguments(optionList);
      // Launch Chrome browser
      browDriver = new ChromeDriver(options);
    } else {
      throw new UnsupportedOperationException("Browser " + webdriver + " not supported");
    }
  }

  private static class BrowserCleanup implements Runnable {
    public void run() {
      System.out.println("Closing browser class");
      close();
    }
  }

  public static void close() {
    try {
      if (browDriver != null) {
        browDriver.quit();
        browDriver = null;
      }
      System.out.println("Closed browser");
    } 
    catch (UnreachableBrowserException e) {
      System.out.println("Please close the browser by yourself");
    }
  }

  public static WebElement loadPage(String url, String clickableElem) {
    System.out.println("Entering into " + url);
    browDriver.get(url);
    // Accept Google cookies when needed
    if (browDriver.findElement(By.id("L2AGLb")) != null) {
      browDriver.findElement(By.id("L2AGLb")).click();
    }
    res4w = new WebDriverWait(browDriver, Duration.ofSeconds(10))
                  .until(ExpectedConditions.elementToBeClickable(By.id(clickableElem)));
    return res4w;
  }

}
