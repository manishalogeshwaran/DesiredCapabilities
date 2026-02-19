package com.trainee.com;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CookiesDemoTest {

	private WebDriver driver;
	@BeforeMethod
	public void setup() {
		driver=(WebDriver) new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		
	}
	@AfterMethod(alwaysRun=true)
		public void teardown() {
			
		}
	
		//Exception invalid because of the Invalid domain exception
	//https:www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/Cookie.html
		@Test
		public void addAndDeleteCookies_demo() {
			driver.get("https://the-internet.herokuapp.com/");
			Cookie myCookie=new Cookie("traineeCookie", "Manisha123");
			driver.manage().addCookie(myCookie);
			Cookie fetched =driver.manage().getCookieNamed("traineeCookie");
			Assert.assertNotNull(fetched,"Cookie was NOT added");
			Assert.assertEquals(fetched.getValue(),"Manisha123","Cookie value mismatch!");
			System.out.println("Added Cookie :"+fetched);
			Set<Cookie> all =driver.manage().getCookies();
			System.out.println("Total cookies now: "+all.size());
			for(Cookie c:all) {
				System.out.println("Cookie -> "+c.getName() + " = "+c.getValue());
			}
			driver.manage().deleteCookieNamed("traineeCookie");
			Cookie afterDelete=driver.manage().getCookieNamed("traineeCookie");
			Assert.assertNull(afterDelete,"Cookie was NOT deleted!");
			System.out.println("Deleted Cookie traineeCookie");
			driver.manage().deleteAllCookies();
			Assert.assertEquals(driver.manage().getCookies().size(), 0 ,"All Cookies were not deleted!");
			System.out.println("Deleted All Cookies");
			System.out.println("---- Cookie Details ----");
		    System.out.println("Name      : " + fetched.getName());
		    System.out.println("Value     : " + fetched.getValue());
		    System.out.println("Domain    : " + fetched.getDomain());     
		    System.out.println("Path      : " + fetched.getPath());       
		    System.out.println("Secure    : " + fetched.isSecure());      
		    System.out.println("HttpOnly  : " + fetched.isHttpOnly());    
		    System.out.println("Expiry    : " + fetched.getExpiry());     
		    System.out.println("SameSite  : "+ fetched.getSameSite());
			
			
		}

		 
		        
		    }

	

