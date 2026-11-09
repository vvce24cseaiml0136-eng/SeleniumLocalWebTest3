
package MyProject;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LocalLoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        ChromeOptions options = new ChromeOptions();

        // Required for GitHub Actions
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);

        String page = new File("webpage/login.html")
                .getAbsoluteFile()
                .toURI()
                .toString();

        driver.get(page);
    }

    @Test
    public void validLoginTest() {

        driver.findElement(By.id("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("12345");

        driver.findElement(By.id("loginButton")).click();

        String message = driver.findElement(By.id("message")).getText();

        Assert.assertEquals(message, "Login Successful");
    }

    @Test
    public void invalidLoginTest() {

        driver.findElement(By.id("username")).sendKeys("wrong");
        driver.findElement(By.id("password")).sendKeys("wrong");

        driver.findElement(By.id("loginButton")).click();

        String message = driver.findElement(By.id("message")).getText();

        Assert.assertEquals(
            message,
            "Invalid Username or Password"
        );
    }

    @Test
    public void emptyLoginTest() {

        driver.findElement(By.id("loginButton")).click();

        String message = driver.findElement(By.id("message")).getText();

        Assert.assertEquals(
            message,
            "Invalid Username or Password"
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
