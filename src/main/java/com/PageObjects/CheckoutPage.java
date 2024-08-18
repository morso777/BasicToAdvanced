package com.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage extends BasePage{
    String name="ABC";
    String lastName="MNO";
    String zipCode="123";
    String textItemPrice= "";

    By byFirstName = By.id("first-name");
    By byLastName = By.id("last-name");
    By byPostalCode = By.id("postal-code");
    By byContinueButton = By.id("continue");
    By byFinishButton = By.id("finish");
    By byTextThankyou = By.cssSelector("h2[class='complete-header']");
    By byBackHomeButton = By.id("back-to-products");
    By byTextItemPrice = By.cssSelector("div[class='inventory_item_price']");


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillOutInformation(){
        wait =new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement firstName=driver.findElement(byFirstName);
        wait.until(ExpectedConditions.elementToBeClickable(firstName));
        firstName.sendKeys(name);
        WebElement lName=driver.findElement(byLastName);
        wait.until(ExpectedConditions.elementToBeClickable(lName));
        lName.sendKeys(lastName);
        WebElement zCode=driver.findElement(byPostalCode);
        wait.until(ExpectedConditions.elementToBeClickable(zCode));
        zCode.sendKeys(zipCode);
        WebElement continueButton=driver.findElement(byContinueButton);
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
    }

    public void clickFinishButton(){
        wait =new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement finishButton=driver.findElement(byFinishButton);
        wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        finishButton.click();
    }

    public String getTextThankyou() {
        WebElement textThank = driver.findElement(byTextThankyou);
        textItemPrice= textThank.getText();
        return textItemPrice;
    }

    public void clickBackHomeButton(){
        wait =new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement backHomeButton=driver.findElement(byBackHomeButton);
        wait.until(ExpectedConditions.elementToBeClickable(backHomeButton));
        backHomeButton.click();
    }
    public String getItemPrice() {
        WebElement textPrice = driver.findElement(byTextItemPrice);
        textItemPrice= textPrice.getText();
        return textItemPrice;
    }


}
