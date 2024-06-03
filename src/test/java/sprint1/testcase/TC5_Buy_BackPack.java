package sprint1.testcase;

import com.PageObjects.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC5_Buy_BackPack extends BaseTest {

    String actualText="";
    String textProduct="";
    String textThankYou="";
    String textItemPrice="";

    /**
     * Page Objects
     **/
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod()
    public void beforeMethod() {
        loginPage = new LoginPage(driver);
        inventoryPage =new InventoryPage(driver);
        cartPage =new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test(priority = 1)
    public void TC5_Buy_BackPack ()  {
        SoftAssert softAssert = new SoftAssert();
        log.info("Login");
        loginPage.LoginConstants(Constants.userNameStandard, Constants.password);
        actualText =inventoryPage.getText();
        softAssert.assertEquals(actualText, "Products");

        inventoryPage.addToCart();
        inventoryPage.GoToCartContainer();

        textProduct=cartPage.getTextProduct();
        softAssert.assertEquals(textProduct, "Sauce Labs Backpack");

        cartPage.clickCheckoutButton();
        checkoutPage.fillOutInformation();

        textProduct=cartPage.getTextProduct();
        softAssert.assertEquals(textProduct, "Sauce Labs Backpack");

        textItemPrice=checkoutPage.getItemPrice();
        softAssert.assertEquals(textItemPrice, "$29.99");

        checkoutPage.clickFinishButton();

        textThankYou=checkoutPage.getTextThankyou();
        softAssert.assertEquals(textThankYou, "Thank you for your order!");
        checkoutPage.clickBackHomeButton();

        actualText =inventoryPage.getText();
        softAssert.assertEquals(actualText, "Products");
        softAssert.assertAll();
    }
}
