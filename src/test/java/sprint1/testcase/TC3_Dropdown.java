package sprint1.testcase;

import com.PageObjects.BaseTest;
import com.PageObjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC3_Dropdown extends BaseTest {


    /**
     * Page Objects
     **/
    LoginPage loginPage;


    @BeforeMethod()
    public void beforeMethod() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void TC3_Dropdown (){
        log.info("Login");
        loginPage.Login();
        log.info("click dropdown");
        WebElement staticDropdown = driver.findElement(By.cssSelector("button[data-testid='occupancy-config']"));
        Select dropdown = new Select(staticDropdown);
        dropdown.selectByIndex(0);
    }
}
