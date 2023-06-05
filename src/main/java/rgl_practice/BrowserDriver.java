package rgl_practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;
// import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserDriver {
  
  private static WebDriver browDriver;

  public synchronized static WebDriver getCurrentDriver() {
    System.out.println("Instantiating browser");
    if (browDriver == null) {
      try {
        // Insert proxy location between browser and client
        // String rootPath = System.getProperty("user.dir");
        // String driverPath = "/webdriver/chromedriver";
        // System.setProperty("webdriver.chrome.driver", rootPath + driverPath);
        String webdriver = System.getProperty("browser", "chrome");
        // String wcd = System.getProperty("/webdriver/chromedriver");
        // System.out.print("webdriver.chrome.driver = " + wcd);
        // String webdriver = "chrome";
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
      System.out.println("Closing browser");
      close();
    }
  }

  public static void close() {
    try {
      getCurrentDriver().quit();
      browDriver = null;
      System.out.println("Closing browser");
    } 
    catch (UnreachableBrowserException e) {
      System.out.println("Please close the browser by yourself");
    }
  }

  public static void loadPage(String url) {
    System.out.println("Entering into " + url);
    getCurrentDriver().get(url);
    // browDriver.findElement(By.id("L2AGLb")).click();
  }

}
