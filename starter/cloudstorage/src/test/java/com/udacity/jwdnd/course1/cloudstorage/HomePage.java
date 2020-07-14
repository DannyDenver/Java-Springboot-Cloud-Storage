package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
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

    @FindBy(id="showNoteModalButton")
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

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        logoutButton.click();
    }

    public void navigateToNotesTab() {
        notesTab.click();
    }

    public void navigateToCredentialsTab() {
        credentialsTab.click();
    }

    public void showNoteModal() {
        showNoteModalButton.click();
    }

    public void createNote(String title, String description) {

        noteTitleInput.sendKeys(title);
        noteTitleInput.sendKeys(description);
        noteSubmitButton.click();
    }

    public String getFirstNoteDescription() {
        return this.firstNoteDescription.getText();
    }

    public String geFirstNoteTitle() {
        return this.firstNoteTitle.getText();
    }
}
