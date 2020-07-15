package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    @FindBy(id="logout-button")
    private WebElement logoutButton;

    @FindBy(id="nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id="nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(xpath="//a[@href='#nav-notes']")
    private WebElement showNotesLink;

    @FindBy(id = "showNoteModalButton")
    private WebElement showNoteModalButton;

    @FindBy(id="note-title")
    private WebElement noteTitleInput;

    @FindBy(id="note-description")
    private WebElement noteDescriptionInput;

    @FindBy(id="noteSubmit")
    private WebElement noteSubmitButton;

    @FindBy(className="noteTitle")
    private WebElement firstNoteTitle;

    @FindBy(className="noteDescription")
    private WebElement firstNoteDescription;

    @FindBy(className = "edit-note")
    private WebElement editNoteButton;

    @FindBy(className = "delete-note")
    private WebElement deleteNoteButton;

    @FindBy(id="showCredentialModal")
    private WebElement showCredentialModalButton;

    @FindBy(id="credential-url")
    private WebElement credentialUrlInput;

    @FindBy(id="credential-username")
    private WebElement credentialUsernameInput;

    @FindBy(id="credential-password")
    private WebElement credentialPasswordInput;

    @FindBy(id="credentialSubmit")
    private WebElement submitCredentialButton;

    @FindBy(className = "credential-url")
    private WebElement firstCredentialUrl;

    @FindBy(className = "credential-username")
    private WebElement firstCredentialUsername;

    @FindBy(className = "credential-password")
    private WebElement firstCredentialEncryptedPassword;

    @FindBy(className = "edit-credential")
    private WebElement editCredentialButton;

    @FindBy(className = "delete-credential")
    private WebElement deleteCredentialButton;

    private WebDriver driver;

    private JavascriptExecutor jse;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        logoutButton.click();
    }

    public void showCredentials() {
        jse.executeScript("document.getElementById('nav-credentials-tab').click()");

    }

    public void showNotes() {
        jse.executeScript("document.getElementById('nav-notes-tab').click()");
    }

    public void showNotesModal() {
        jse.executeScript("document.getElementById('showNoteModalButton').click()");
    }

    public void createNote(String title, String description)  {
        jse.executeScript("document.getElementById('note-title').value=\"" + title + "\";"); //.value = "+ title + ";
        jse.executeScript("document.getElementById('note-description').value=\"" + description + "\";");
        jse.executeScript("document.getElementById('noteSubmit').click()");
    }

    public String getFirstNoteDescription() {
        return this.firstNoteDescription.getText();
    }

    public String geFirstNoteTitle()
    {
        return firstNoteTitle.getText();
    }

    public String getFirstCredentialUrl() {
        return firstCredentialUrl.getText();
    }

    public String getFirstCredentialUsername() {
        return firstCredentialUsername.getText();
    }

    public String getFirstCredentialEncryptedPassword() {
        return firstCredentialEncryptedPassword.getText();
    }

    public void editNote(String title, String description)  {
        editNoteButton.click();
        jse.executeScript("document.getElementById('note-title').value=\"" + title + "\";"); //.value = "+ title + ";
        jse.executeScript("document.getElementById('note-description').value=\"" + description + "\";");
        jse.executeScript("document.getElementById('noteSubmit').click()");
    }

    public void deleteNote() {
        deleteNoteButton.submit();
    }

    public void showNewCredentialModal() {
        showCredentialModalButton.click();
    }

    public void addCredential(String url, String username, String password) {
        jse.executeScript("document.getElementById('showCredentialModal').click()");
        jse.executeScript("document.getElementById('credential-url').value=\"" + url + "\";"); //.value = "+ title + ";
        jse.executeScript("document.getElementById('credential-username').value=\"" + username + "\";");

        jse.executeScript("document.getElementById('credential-password').value=\"" + password + "\";");
        jse.executeScript("document.getElementById('credentialSubmit').click()");

    }

    public void showCredentialModal() {
        editCredentialButton.click();
    }

    public void editCredentialUsername(String username) throws InterruptedException {
        jse.executeScript("document.getElementById('credential-username').value=\"" + username + "\";");
        jse.executeScript("document.getElementById('credentialSubmit').click()");

    }

    public String getUnencryptedPassword() {
        return credentialPasswordInput.getAttribute("value");
    }

    public void deleteCredential() {
        deleteCredentialButton.submit();
    }
}
