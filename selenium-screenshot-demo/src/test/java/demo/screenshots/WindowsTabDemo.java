package demo.screenshots;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WindowsTabDemo {
@Test
	
	public void newTabAndSwitch() throws InterruptedException  {
	final WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://the-internet.herokuapp.com/");
	String parent=driver.getWindowHandle();
	System.out.println();
	driver.switchTo().newWindow(WindowType.TAB);
	driver.get("https://the-internet.herokuapp.com/");
	System.out.println("Now in the new tab title:" + driver.getTitle());
	driver.switchTo().window(parent);
	System.out.println("Back to parent title:"+driver.getTitle());
	Thread.sleep(6000);
	

}

}