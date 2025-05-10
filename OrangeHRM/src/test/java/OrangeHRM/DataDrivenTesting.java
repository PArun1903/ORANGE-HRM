package OrangeHRM;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTesting {
	@Test(dataProvider = "Dynamic", dataProviderClass = ExcelDataProvider.class)
	public void login(String UserName, String Password) throws Exception{

		
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		try {
		Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys(UserName);
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(Password);
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Boolean Sl = false;
		Boolean Ul = false;

		if (driver.findElements(By.xpath("//p[@class='oxd-userdropdown-name']")).size() > 0 &&
		    driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).isDisplayed()) {
		    Sl = true;
		} else if (driver.findElements(By.xpath("//div[@role='alert']")).size() > 0 &&
		           driver.findElement(By.xpath("//div[@role='alert']")).isDisplayed()) {
		    Ul = true;
		}

		// Successful assertion
		if (Sl) {
		    System.out.println("Login Successful by using valid credentials");
		} else if (Ul) {
		    System.out.println("Login not successful, by invalid credentials");
		} else {
		    System.out.println("Neither username nor alert displayed — unexpected issue!");
		}

		driver.quit();
	}
	
	// Given name is used for data provider so we can use it in the test Observe 11th line
	// indices are given for which data should we send to the test so we can mention only 0 or 1 or both as per the test
	// parallel attribute is used for it should send data at time for parallel execution
	@DataProvider(name="loginTestData",indices = {0,1}, parallel = true)
	public Object[][] loginData() {
      Object [][] data = new Object[2][2];
      data[0][0]="Admin";
      data[0][1]="admin123";
      
      data[1][0]="Admin";
      data[1][1]="Admin123";
      
      return data;
	}

}
