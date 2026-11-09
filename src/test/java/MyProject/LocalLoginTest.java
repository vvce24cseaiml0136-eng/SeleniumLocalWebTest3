package MyProject;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class LocalLoginTest {
	 WebDriver driver;

	    // Open the login page before every test
	    @BeforeMethod
	    public void setUp() {

	        driver = new ChromeDriver();

	        driver.manage().window().maximize();

	        String page = new File("webpage/login.html")
	                .getAbsoluteFile()
	                .toURI()
	                .toString();

	        driver.get(page);
	    }

	    // Test Case 1: Valid Login
	    @Test
	    public void validLoginTest() {

	        driver.findElement(By.id("username"))
	                .sendKeys("student");

	        driver.findElement(By.id("password"))
	                .sendKeys("12345");

	        driver.findElement(By.id("loginButton"))
	                .click();

	        String message = driver.findElement(By.id("message"))
	                .getText();

	        System.out.println("Valid Login: " + message);

	        Assert.assertEquals(message, "Login Successful");
	    }

	    // Test Case 2: Invalid Login
	    @Test
	    public void invalidLoginTest() {

	        driver.findElement(By.id("username"))
	                .sendKeys("wrong");

	        driver.findElement(By.id("password"))
	                .sendKeys("wrong");

	        driver.findElement(By.id("loginButton"))
	                .click();

	        String message = driver.findElement(By.id("message"))
	                .getText();

	        System.out.println("Invalid Login: " + message);

	        Assert.assertEquals(
	                message,
	                "Invalid Username or Password"
	        );
	    }

	    // Test Case 3: Empty Login
	    @Test
	    public void emptyLoginTest() {

	        driver.findElement(By.id("loginButton"))
	                .click();

	        String message = driver.findElement(By.id("message"))
	                .getText();

	        System.out.println("Empty Login: " + message);

	        Assert.assertEquals(
	                message,
	                "Invalid Username or Password"
	        );
	    }

	    // Close browser after every test
	    @AfterMethod
	    public void tearDown() {

	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}