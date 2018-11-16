package ExceptionHandlingDemo;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ExceptionHandlingWithSeleniumDemo {

	static WebDriver driver;

	@Test
	public void testCase1() throws InterruptedException, NoSuchElementException {

		System.setProperty("webdriver.chrome.driver", "./exefiles/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get("http://127.0.0.1/login.do");
		driver.findElement(By.name("username")).sendKeys("admin");
		System.out.println(" --> UserId entered");
		driver.findElement(By.name("pwd")).sendKeys("manager");
		System.out.println(" --> Password entered");

		try {
			WebElement actitimeLogo = driver.findElement(By.xpath("//img[contains(@src,'timer.gif')]"));
			Boolean flag = actitimeLogo.isDisplayed();
			if (flag == true) {
				System.out.println(" --> Logo is displayed properly");
			} else {
				throw new NoSuchElementException(
						"this logo element is not present in the webpage, please riase a bug !!");
			}
		} catch (Exception e) {
			System.out.println(" --> Exception Found");
			System.out.println(e);
			System.out.println(" --> Exception handled");
		}

		finally {
			driver.findElement(By.id("loginButton")).click();
			System.out.println(" --> Logged In Sucessfully");
		}
		Thread.sleep(6000);
		driver.quit();

	}
}
