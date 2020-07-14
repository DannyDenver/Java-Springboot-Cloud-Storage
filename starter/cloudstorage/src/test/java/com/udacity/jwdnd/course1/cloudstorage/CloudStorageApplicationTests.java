package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
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
		baseUrl = baseUrl = "http://localhost:" + this.port;
		this.driver = new ChromeDriver();
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

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

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
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
		Assertions.assertEquals("Home", driver.getTitle());

		HomePage homepage = new HomePage(driver);
		homepage.logout();

		Assertions.assertEquals("Login", driver.getTitle());
		driver.get(baseUrl + "/home");

		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void createEditDeleteNote() throws InterruptedException {
		String title = "test";
		String description = "description of note";

		HomePage homePage = new HomePage(driver);

		WebDriverWait wait = new WebDriverWait(driver, 4, 500);
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#nav-notes']")));

		WebElement tab = driver.findElement(By.xpath("//a[@href='#nav-notes']"));

		tab.click();
		Thread.sleep(500);

		WebDriverWait wait2 = new WebDriverWait(driver, 10, 500);
		WebElement button2 = wait2.until(ExpectedConditions.elementToBeClickable(By.id("showNoteModalButton")));

		homePage.showNotesModal();
		WebDriverWait wait3 = new WebDriverWait(driver, 4, 2000);
		wait2.until(webDriver -> webDriver.findElement(By.id("noteModal")));

		homePage.createNote(title, description);

		WebDriverWait wait4 = new WebDriverWait(driver, 4);
		wait3.until(webDriver -> webDriver.findElement(By.id("results-view")));

		Assertions.assertEquals("Result", driver.getTitle());

		driver.get(baseUrl + "/home");
		WebDriverWait wait5 = new WebDriverWait(driver, 4);
		wait4.until(webDriver -> webDriver.findElement(By.className("noteTitle")));

		homePage = new HomePage(driver);

		WebElement tab2 = driver.findElement(By.xpath("//a[@href='#nav-notes']"));

		tab2.click();

		WebElement note = new WebDriverWait(driver, 2).until(driver -> driver.findElement(By.className("noteTitle")));

		Thread.sleep(2000);

		Assertions.assertEquals(title, homePage.geFirstNoteTitle());
		Assertions.assertEquals(description, homePage.getFirstNoteDescription());

		String editedTitle = "test2";
		String editedDescription = "description of note edit";

		homePage.editNote(editedTitle, editedDescription);

		WebDriverWait wait6 = new WebDriverWait(driver, 4, 500);
		wait6.until(webDriver -> webDriver.findElement(By.id("results-view")));

		Assertions.assertEquals("Result", driver.getTitle());
		Thread.sleep(5000);

		driver.get(baseUrl + "/home");

		homePage = new HomePage(driver);

		homePage.showNotes();

		WebElement tab3 = driver.findElement(By.xpath("//a[@href='#nav-notes']"));

		tab3.click();

		WebElement note2 = new WebDriverWait(driver, 2).until(driver -> driver.findElement(By.className("noteTitle")));

		Thread.sleep(2000);//

		Assertions.assertEquals(editedTitle, homePage.geFirstNoteTitle());
		Assertions.assertEquals(editedDescription, homePage.getFirstNoteDescription());
		Thread.sleep(2000);

		homePage.deleteNote();

		driver.get(baseUrl + "/home");
		Thread.sleep(2000);

		homePage = new HomePage(driver);

		HomePage finalHomePage = homePage;
		Assertions.assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> finalHomePage.getFirstNoteDescription());
	}

	@Test
	public void createViewEditDeleteCredential() throws InterruptedException {
		String url = "google.com";
		String username = "dan";
		String password = "isawesome44";

		HomePage homePage = new HomePage(driver);

		WebDriverWait wait = new WebDriverWait(driver, 4, 500);
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#nav-credentials']")));

		button.click();
		Thread.sleep(500);

		homePage.showNewCredentialModal();
		Thread.sleep(2000);

		homePage.addCredential(url, username, password);

		Thread.sleep(2000);
		Assertions.assertEquals("Result", driver.getTitle());

		driver.get(baseUrl + "/home");

		HomePage homePage2 = new HomePage(driver);

		WebDriverWait wait2 = new WebDriverWait(driver, 4, 500);
		WebElement navCredentialsButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#nav-credentials']")));

		navCredentialsButton2.click();

		Thread.sleep(1000);

		Assertions.assertEquals(homePage2.getFirstCredentialUsername(), username);
		Assertions.assertEquals(homePage2.getFirstCredentialUrl(), url);
		Assertions.assertNotEquals(homePage2.getFirstCredentialEncryptedPassword(), password);

		homePage2.showCredentialModal();
		Thread.sleep(1000);

		Assertions.assertEquals(homePage2.getUnencryptedPassword(), password);
		Thread.sleep(2000);

		String newUsername = "dantheman";

		homePage2.editCredentialUsername(newUsername);

		Thread.sleep(500);
		Assertions.assertEquals("Result", driver.getTitle());

		driver.get(baseUrl + "/home");

		HomePage homePage3 = new HomePage(driver);

		WebDriverWait wait3 = new WebDriverWait(driver, 4, 500);
		WebElement navCredentialsButton3 = wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#nav-credentials']")));

		navCredentialsButton3.click();

		Thread.sleep(1000);

		Assertions.assertEquals(homePage3.getFirstCredentialUsername(), newUsername);

		homePage3.deleteCredential();

		Thread.sleep(500);
		Assertions.assertEquals("Result", driver.getTitle());

		driver.get(baseUrl + "/home");

		Assertions.assertThrows(org.openqa.selenium.NoSuchElementException.class, () -> homePage3.getFirstCredentialUrl());
	}
}
