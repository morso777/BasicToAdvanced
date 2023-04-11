package com.PageObjects;


import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import com.PageObjects.TestData;
import io.restassured.internal.http.Status;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.*;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class BaseTest {

    //protected ExtentTest test;
    private String testName;
    //protected PageManager pageManager;
    protected static WebDriver driver;
    public String browserFlag;
    public String onGrid;
    public String host;
    public String port;
    public static int ieCountCurrent = 0;
    public static int firefoxCountCurrent = 0;
    public static int chromeCountCurrent = 0;
    public static int safariCountCurrent = 0;
    public static String browserName = "Chrome";
    protected Log log = LogFactory.getLog(this.getClass());

    public String outputExcelFileName;

    public String actualResult;
    public HashMap<String, String> expected;
    //public CustomAssertion customAssertion;
    private final String description = " This is a simple test from complex factory";

    static Properties PROPERTIES_RESOURCES = SystemUtil.loadPropertiesResources("/testdata_common.properties");
    //TestListener testListener = new TestListener();

    public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();

    public String projectPath = System.getProperty("user.dir");
    String os = System.getProperty("os.name");


    public static Properties properties;
    public String url;


    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        log.info("running TEST Case:" + this.getClass().getName());//print test case name
    }



    @BeforeMethod(alwaysRun = true)
    @Parameters({"envTest"})
    public void beforeMethod(Method caller, String envTest) throws Exception {
        selectEnvironment(envTest);
        selectBrowser("chrome");
        getChromeVersion ();
        String[] classes = caller.getDeclaringClass().getName().split("\\.");
        String className = classes[classes.length - 1];
        driver.manage().window().maximize();
        testName = browserFlag + "-" + className + "-" + caller.getName();
        //test = ComplexReportFactory.getTest(testName, className, description);
        //test.log(Status.PASS, "Test Started!");
        //customAssertion = new CustomAssertion(driver, test);
        //pageManager = new PageManager(driver, browserFlag, test);
        driver.get(url);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method caller)  {
        driver.close();
        driver.quit();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        //ComplexReportFactory.closeReport();
    }


    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() throws Exception {
        //outputExcelFileName = ExcelUtil.setupExcelOutput();

    }



    private void selectBrowser(String browser) throws MalformedURLException {
        log.info(os);
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions op = new ChromeOptions();
            if (properties.getProperty("incognitoMode").equals("true")) {
                op.addArguments("--incognito");
            }
            if (properties.getProperty("headlessMode").equals("true")) {
                op.addArguments("window-size=1920x1080");
                op.addArguments("--no-sandbox");
                op.addArguments("--disable-dev-shm-usage");
                op.addArguments("--disable-extensions");
                op.addArguments("--start-maximized");
                op.addArguments("disable-infobars");
                op.addArguments("--disable-notifications");
                op.addArguments("--remote-allow-origins=*");
            }
            System.setProperty("webdriver.chrome.driver", projectPath + "\\lib\\chromedriver.exe");
            driver = new ChromeDriver(op);

            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.BROWSER, Level.ALL);
            op.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            FirefoxProfile profile = new FirefoxProfile();
            firefoxOptions.setProfile(profile);
            System.setProperty("webdriver.gecko.driver", projectPath + "\\lib\\geckodriver.exe");
            driver = new FirefoxDriver(firefoxOptions);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void selectEnvironment(String envTest) {
        if (envTest.equals("DEV")) {
            properties = SystemUtil.loadPropertiesResources("properties_dev.properties");
        } else if (envTest.equals("UAT")) {
            properties = SystemUtil.loadPropertiesResources("properties_uat.properties");
        }
        url = properties.getProperty("url");
    }

    protected WebDriver getDriver() {
        return driver;
    }

    public String getBrowser() {
        return this.browserFlag;
    }





    private void initParams(String browserFlagO) {
        browserFlag = browserFlagO;
        actualResult = null;
        expected = new HashMap<String, String>();
        log.info("browserFlag=" + browserFlag);
    }

    private void setUpChrome() throws Exception {
        System.setProperty("webdriver.chrome.driver", projectPath + "\\lib\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
    }



    public void getChromeVersion (){
        Capabilities cap = ((ChromeDriver) driver).getCapabilities();
        log.info("Chrome Version is: " +cap.getBrowserVersion());
    }


}
