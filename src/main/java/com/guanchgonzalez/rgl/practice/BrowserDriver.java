package com.guanchgonzalez.rgl.practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserDriver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserDriver.class);

    private static WebDriver webDriver;

    public static synchronized WebDriver getCurrentDriver() {
        if (webDriver != null) {
            return webDriver;
        }

        return initCurrentDriver();
    }

    private static WebDriver initCurrentDriver() {
        LOGGER.info("Instantiating browser");
        if (webDriver != null) {
            return webDriver;
        }

        String webDriverName = System.getProperty("browser", "chrome");
        LOGGER.info("Selected driver: {}", webDriverName);

        // Launch browser
        initWebDriver(webDriverName);

        Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));

        return webDriver;
    }

    private static void initWebDriver(String webDriverName) {
        if (!"chrome".equals(webDriverName)) {
            throw new UnsupportedOperationException(String.format("Browser %s not supported", webDriverName));
        }

        String[] optionList = { "test-type", "disable-popups-blocking", "incognito" };

        // Instantiate ChromeOptions
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments(optionList);

        // Launch Chrome browser
        webDriver = new ChromeDriver(options);
    }

    private static class BrowserCleanup implements Runnable {

        public void run() {
            close();
        }
    }

    public static void close() {
        try {
            LOGGER.info("Closing browser class");

            if (webDriver != null) {
                webDriver.quit();
                webDriver = null;
            }

            LOGGER.info("Closed browser");
        } catch (UnreachableBrowserException e) {
            LOGGER.error("Please close the browser by yourself", e);
        }
    }

    public static WebElement loadPage(String url, String clickableElem) {
        LOGGER.info("Entering into {}", url);

        webDriver.get(url);

        // Accept Google cookies when needed
        if (webDriver.findElement(By.id("L2AGLb")) != null) {
            webDriver.findElement(By.id("L2AGLb")).click();
        }
        WebElement res4w = new WebDriverWait(webDriver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(By.id(clickableElem)));

        return res4w;
    }
}
