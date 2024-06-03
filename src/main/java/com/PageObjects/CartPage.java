package com.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage{

    String textProduct= "";
    By byTextBackPack = By.id("item_4_title_link");
    By byCheckoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getTextProduct() {
        WebElement text = driver.findElement(byTextBackPack);
        textProduct= text.getText();
        return textProduct;
    }

    public void clickCheckoutButton(){
        wait =new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement checkoutButton=driver.findElement(byCheckoutButton);
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutButton.click();
    }
}
