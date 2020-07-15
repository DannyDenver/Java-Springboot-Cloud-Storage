package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.Duration;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	private String baseUrl;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach()
	{
		baseUrl = "http://localhost:" + this.port;
		this.driver = new ChromeDriver();
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	public void signupAndLoginUser() {
		String first = "Dan";
		String last = "Tay";
		String username = "dan";
		String password = "man";
		driver.get(baseUrl + "/signup");

		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(username, password, first, last);

		driver.get(baseUrl + "/login");
		LoginPage login = new LoginPage(driver);
		login.login(username, password);
	}

	@Test
	public void getLoginPage() {
		driver.get(baseUrl + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void getHomePageForbiddenByUnauthorizedUser() {
		driver.get(baseUrl + "/home");
		Assertions.assertNotEquals("Home", driver.getTitle());
	}

	@Test
	public void signupLoginHomePageLogoutSuccess() {
		signupAndLoginUser();

		Assertions.assertEquals("Home", driver.getTitle());

		HomePage homepage = new HomePage(driver);
		homepage.logout();

		Assertions.assertEquals("Login", driver.getTitle());
		driver.get(baseUrl + "/home");

		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void createEditDeleteNote() throws InterruptedException {
		signupAndLoginUser();

		String title = "test";
		String description = "description of note";

		HomePage homePage = new HomePage(driver);

		homePage.showNotes();

		homePage.showNotesModal();

		homePage.createNote(title, description);

		Assertions.assertEquals("Result", driver.getTitle());

		driver.get(baseUrl + "/home");

		homePage = new HomePage(driver);
		homePage.showNotes();


		Assertions.assertEquals(title, homePage.geFirstNoteTitle());
		Assertions.assertEquals(description, homePage.getFirstNoteDescription());

		String editedTitle = "test2";
		String editedDescription = "description of note edit";

		homePage.editNote(editedTitle, editedDescription);
		driver.get(baseUrl + "/home");

		homePage = new HomePage(driver);
		homePage.showNotes();

		WebDriverWait wait = new WebDriverWait(driver, 4, 500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("noteTitle")));

		Assertions.assertEquals(editedTitle, homePage.geFirstNoteTitle());
		Assertions.assertEquals(editedDescription, homePage.getFirstNoteDescription());

		homePage.deleteNote();

		driver.get(baseUrl + "/home");
		homePage = new HomePage(driver);
		homePage.showNotes();

		HomePage finalHomePage = homePage;
		Assertions.assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> finalHomePage.getFirstNoteDescription());
	}

	@Test
	public void createViewEditDeleteCredential() throws InterruptedException {
		signupAndLoginUser();

		String url = "google.com";
		String username = "dan";
		String password = "isawesome44";

		HomePage homePage = new HomePage(driver);

		homePage.showCredentials();

		homePage.addCredential(url, username, password);

		Assertions.assertEquals("Result", driver.getTitle());

		driver.get(baseUrl + "/home");

		homePage = new HomePage(driver);

		homePage.showCredentials();

		WebDriverWait wait = new WebDriverWait(driver, 4, 500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("credential-url")));

		Assertions.assertEquals(homePage.getFirstCredentialUsername(), username);
		Assertions.assertEquals(homePage.getFirstCredentialUrl(), url);
		Assertions.assertNotEquals(homePage.getFirstCredentialEncryptedPassword(), password);

		homePage.showCredentialModal();

		Assertions.assertEquals(homePage.getUnencryptedPassword(), password);

		String newUsername = "dantheman";

		homePage.editCredentialUsername(newUsername);

		Assertions.assertEquals("Result", driver.getTitle());

		driver.get(baseUrl + "/home");

		homePage = new HomePage(driver);

		homePage.showCredentials();

		wait = new WebDriverWait(driver, 4, 500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("credential-url")));

		Assertions.assertEquals(homePage.getFirstCredentialUsername(), newUsername);

		homePage.deleteCredential();

		Assertions.assertEquals("Result", driver.getTitle());

		driver.get(baseUrl + "/home");

		homePage.showCredentials();

		HomePage finalHomePage = new HomePage(driver);

		Assertions.assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> finalHomePage.getFirstCredentialUrl());
	}
}
