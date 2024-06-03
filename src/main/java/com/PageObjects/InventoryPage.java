package com.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage extends BasePage {

    By byProducts = By.cssSelector("div[class='header_secondary_container'] [class='title']");
    By byBackPack = By.id("add-to-cart-sauce-labs-backpack");
    By byCartContainer = By.id("shopping_cart_container");



    String textValue="";

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getText() {
        WebElement text = driver.findElement(byProducts);
        textValue= text.getText();
        return textValue;
    }

    public void addToCart(){
        wait =new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement back=driver.findElement(byBackPack);
        wait.until(ExpectedConditions.elementToBeClickable(back));
        back.click();
    }


    public void GoToCartContainer(){
        wait =new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement cart=driver.findElement(byCartContainer);
        wait.until(ExpectedConditions.elementToBeClickable(cart));
        cart.click();
    }




}
