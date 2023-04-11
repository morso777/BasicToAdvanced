package sprint1.testcase;

import com.PageObjects.BaseTest;
import com.PageObjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC2_Login extends BaseTest {

    /**
     * Page Objects
     **/
    LoginPage loginPage;


    @BeforeMethod()
    public void beforeMethod() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void TC2_Login (){
        log.info("Login");
        loginPage.Login();
    }

}
