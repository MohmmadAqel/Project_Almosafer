package Almosafer_Project;
import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class MyTestCases {
	String url = "https://www.almosafer.com/en";
	WebDriver driver = new ChromeDriver();
	@BeforeTest
	public void MyBeforeTest() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div/button[1]")).click();
	}
	@Test()
	public void CheckTheLanguage() {
		String Acteuallanguage =  driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(Acteuallanguage, "en");
	}
	@Test()
	public void CheckTheCaruncy() {
		String ActeualCaruncy = driver.findElement(By.xpath("//button[normalize-space()='SAR']")).getText();
		Assert.assertEquals(ActeualCaruncy, "SAR");
	}
	@Test()
	public void CheckTheNumberofPhone() {
		WebElement NumberOfPhone = driver.findElement(By.cssSelector("a[class='sc-hUfwpO bWcsTG'] strong"));
		String ActualNumberOfPhone = NumberOfPhone.getText();
		String ExpectedNumberOfPhone = "+966554400000";
		Assert.assertEquals(ActualNumberOfPhone, ExpectedNumberOfPhone);
		}
	@Test()
	public void CheckQitafLogo() {
		WebElement Footer = driver.findElement(By.tagName("footer"));
		boolean QitafLogoIsDespaly = Footer.findElement(By.xpath("//div[@class='sc-fihHvN eYrDjb']")).isDisplayed();
        Assert.assertEquals(QitafLogoIsDespaly, true);	
	}
	@Test()
	public void CheckHotelTapIsNotSelected() {
		WebElement HotelTap = driver.findElement (By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualValue = HotelTap.getAttribute("aria-selected");
		Assert.assertEquals(ActualValue, "false");
	}
	@Test()
	public void ChangeTheLangugeOfTheWebsite() throws InterruptedException {
		String [] MyWebsite= {"https://www.almosafer.com/en","https://www.almosafer.com/ar"};
		Random rand = new Random();
		int RandomNumber = rand.nextInt(MyWebsite.length);
		driver.get(MyWebsite[RandomNumber]);
		Thread.sleep(2000);
		String MyWebsiteURL = driver.getCurrentUrl();
		if(MyWebsiteURL.contains("ar")) {
			String Acteuallanguage =  driver.findElement(By.tagName("html")).getAttribute("lang");
			Assert.assertEquals(Acteuallanguage, "ar");
		}else if (MyWebsiteURL.contains("en")) {
				String Acteuallanguage =  driver.findElement(By.tagName("html")).getAttribute("lang");
				Assert.assertEquals(Acteuallanguage, "en");
				}
	}
	@AfterTest
	public void MyAfterTest() {
	}
}