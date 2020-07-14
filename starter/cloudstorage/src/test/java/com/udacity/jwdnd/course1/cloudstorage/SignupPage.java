package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id="inputFirstName")
    private WebElement firstNameInput;

    @FindBy(id="inputLastName")
    private WebElement lastNameInput;

    @FindBy(id="inputUsername")
    private WebElement inputUsername;

    @FindBy(id="inputPassword")
    private WebElement inputPassword;

    @FindBy(id="submit-button")
    private WebElement submitButton;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void submit() {
        submitButton.submit();
    }

    public void enterFirstName(String name) {
        firstNameInput.sendKeys(name);
    }

    public void enterLastName(String name) {
        lastNameInput.sendKeys(name);
    }

    public void enterUsername(String name) {
        inputUsername.sendKeys(name);
    }

    public void enterPassword(String name) {
        inputPassword.sendKeys(name);
    }

    public void signup(String userName, String password, String firsName, String lastName) {
        inputUsername.sendKeys(userName);
        inputPassword.sendKeys(password);
        lastNameInput.sendKeys(lastName);
        firstNameInput.sendKeys(firsName);
        this.submit();
    }
}
