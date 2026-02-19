package demo.screenshots;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import static org.openqa.selenium.support.locators.RelativeLocator.with;
public class RelativeLocator {
@Test
	
	public void demoRelativeLocators() {
	final WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://the-internet.herokuapp.com/login");
	WebElement usernameLabel=driver.findElement(By.cssSelector("label[for='username']"));
	WebElement passwordLabel=driver.findElement(By.cssSelector("label[for='password']"));
	
	WebElement usernameInput=driver.findElement(with(By.tagName("input")).below(usernameLabel));
	WebElement passwordInput=driver.findElement(with(By.tagName("input")).below(passwordLabel));

	usernameInput.sendKeys("tomsmith");
	passwordInput.sendKeys("SuperSecretPassword!");
	
	driver.findElement(By.cssSelector("button[type='submit']")).click();
	

}
}