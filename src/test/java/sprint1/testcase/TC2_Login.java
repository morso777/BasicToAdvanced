package sprint1.testcase;

import com.PageObjects.BaseTest;
import com.PageObjects.Constants;
import com.PageObjects.InventoryPage;
import com.PageObjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC2_Login extends BaseTest {

    /**
     * Page Objects
     **/
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @BeforeMethod()
    public void beforeMethod() {
        loginPage = new LoginPage(driver);
        inventoryPage =new InventoryPage(driver);
    }

    @Test(priority = 1)
    public void TC2_Login (){
        log.info("Login");
        loginPage.Login();
    }

    @Test(priority = 2)
    public void TC2_Login_UserName (){
        log.info("Login");
        loginPage.LoginUserName("standard_user","secret_sauce");
    }

    @Test(priority = 3)
    public void TC2_Login_Constants ()  {
        log.info("Login");
        loginPage.LoginConstants(Constants.userNameStandard, Constants.password);
        String actualText =inventoryPage.getText();
        Assert.assertEquals(actualText, "Products");
    }

}
