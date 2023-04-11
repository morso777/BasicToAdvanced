package com.PageObjects;

import com.paulhammant.ngwebdriver.NgWebDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.logging.Level;

public class BasePage {

    public String priceF;
    public String hourF;
    public String yearF;
    public String year;
    public String location;
    public int priceValue;
    public int selectedValue;


    public String projectPath = System.getProperty("user.dir");
    protected WebDriver driver;
    public NgWebDriver ngWebDriver;
    Actions actions;

    public Wait<WebDriver> fluentWait;

    protected By pageLocator;

    public BasePage(WebDriver driver) {
        this.driver = driver;

        actions = new Actions(driver);
        fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

    }

    protected Log log = LogFactory.getLog(this.getClass());
    WebElement ele;
    WebElement ele2;
    WebElement ele3;
    WebDriverWait wait;
    JavascriptExecutor jse;
    JavascriptExecutor jse2;
    JavascriptExecutor jse3;
    //public CustomAssertion customAssertion;
    //protected ExtentTest test;

    public void logBrowser() {
        ChromeOptions browserOptions = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        browserOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        log.info(driver.manage().logs().get(LogType.BROWSER));
    }




    public void sleepByNSeconds(int secs) {

        try {
            Thread.sleep(secs * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public WebElement sleep(By element) {
        WebElement _element = fluentWait.until(driver -> driver.findElement(element));
        return _element;
    }

    /*** Waits for the browser to signal that the DOM is loaded. For dynamic pages, an extra wait may be necessary.   ***/
    public static void waitForPageLoad(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        });
    }

    public void detectContentDOM (){
        log.info("Detect DOM");
        var script = "window.addEventListener('DOMContentLoaded', (event) => {" +
                "    console.log('DOM fully loaded and parsed');" +
                "});"+
                "try{window.performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {};" +
                "return(parseInt(window.performance.timing.domContentLoadedEventEnd)-parseInt(window.performance.timing.navigationStart));}catch(e){alert(e);}";
        JavascriptExecutor executor =((JavascriptExecutor) driver);
        executor.executeScript(script,"");

    }


    public void moveToElement(WebElement element) {
        jse2 = (JavascriptExecutor) driver;
        jse2.executeScript("arguments[0].scrollIntoView()", element);
    }

    public void moveToElement2(WebElement element) {
        jse2 = (JavascriptExecutor) driver;
        jse2.executeScript("arguments[0].scrollIntoView(true)", element);
    }


    public void scrollDown() {
        jse3 = (JavascriptExecutor) driver;
        jse3.executeScript("window.scrollBy(0,500)");
    }

    public void scrollDownByValue(String value) {
        jse3 = (JavascriptExecutor) driver;
        jse3.executeScript("window.scrollBy(0,"+ value+ ")");
    }


    public void scrollDownToEnd() {
        log.info("Scroll Down to End");
        jse3 = (JavascriptExecutor) driver;
        jse3.executeScript("window.scrollBy(0, document.body.scrollHeight)");
    }

    public void focusElement(WebElement element) {
        String javaScript = "var evObj = document.createEvent('MouseEvents');"
                + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false," +
                " 0, null);"
                + "arguments[0].dispatchEvent(evObj);";

        ((JavascriptExecutor) driver).executeScript(javaScript, element);
    }


    public void clickElementHidden(WebElement element) {
        log.info("click Element Hidden");
        jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click();", element);
    }







}
