package com.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{

    String pwd ="";
    String password="secret_sauce";
    String userName="";
    String userNameStandard="standard_user";

    By byUserName= By.cssSelector("input[data-test='username']");
    By byPassword= By.id("password");
    By byLogin=By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void Login(){
        WebElement userName= driver.findElement(byUserName);
        userName.sendKeys(userNameStandard);
        WebElement pwd= driver.findElement(byPassword);
        pwd.sendKeys(password);
        WebElement login= driver.findElement(byLogin);
        login.click();
    }

    public void LoginUserName(String userName, String pwd){
        WebElement uN= driver.findElement(byUserName);
        uN.sendKeys(userName);
        WebElement p= driver.findElement(byPassword);
        p.sendKeys(pwd);
        WebElement login= driver.findElement(byLogin);
        login.click();
    }

    public void LoginConstants(String userName, String pwd){
        WebElement userNames= driver.findElement(byUserName);
        userNames.sendKeys(userName);
        WebElement password= driver.findElement(byPassword);
        password.sendKeys(pwd);
        WebElement login= driver.findElement(byLogin);
        login.click();
    }



    public void loginNegative (){
        WebElement userName= driver.findElement(byUserName);
        userName.sendKeys("s");
        WebElement password= driver.findElement(byPassword);
        password.sendKeys("secret_sauce");
        WebElement login= driver.findElement(byLogin);
        login.click();
    }


}
