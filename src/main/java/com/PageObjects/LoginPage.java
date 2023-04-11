package com.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

    String password ="secret_sauce";
    String userName="standard_user";

    By byUserName= By.id("user-name");
    By byPassword= By.id("password");
    By byLogin=By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void Login (){
        WebElement userName= driver.findElement(byUserName);
        userName.sendKeys("standard_user");
        WebElement password= driver.findElement(byPassword);
        password.sendKeys("secret_sauce");
        WebElement login= driver.findElement(byLogin);
        login.click();
    }


}
