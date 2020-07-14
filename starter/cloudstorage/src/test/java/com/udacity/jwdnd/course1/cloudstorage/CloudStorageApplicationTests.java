package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

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
	public void createNoteVerifyExists() {
		String title = "test";
		String description = "description of note";

		HomePage homePage = new HomePage(driver);

		WebDriverWait wait = new WebDriverWait(driver, 1000);
		WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("nav-notes-tab")));

		homePage.navigateToNotesTab();

		WebElement marker2 = wait.until(webDriver -> webDriver.findElement(By.id("showNoteModalButton")));

		homePage.showNoteModal();

		WebElement marker3= wait.until(webDriver -> webDriver.findElement(By.id("noteModal")));

		homePage.createNote(title, description);
		driver.get(baseUrl + "/home");

		homePage = new HomePage(driver);

		Assertions.assertEquals(title, homePage.geFirstNoteTitle());
		Assertions.assertEquals(description, homePage.getFirstNoteDescription());
	}



}
