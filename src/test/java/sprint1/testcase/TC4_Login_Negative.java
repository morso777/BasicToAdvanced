package sprint1.testcase;

import com.PageObjects.BaseTest;
import com.PageObjects.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC4_Login_Negative extends BaseTest {

    /**
     * Page Objects
     **/
    LoginPage loginPage;


    @BeforeMethod()
    public void beforeMethod() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void TC4_Login (){
        log.info("Login");
        loginPage.loginNegative();
    }

}
