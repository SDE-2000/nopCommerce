package com.pages;

import org.openqa.selenium.By;

public class LoginPage {
    
    public final By emailInput = By.id("Email");
    public final By passwordInput = By.id("Password");
    public final By loginButton = By.cssSelector("button.button-1.login-button");
    public final By forgotPasswordLink = By.linkText("Forgot password?");
    
    // No methods, only locators
}
