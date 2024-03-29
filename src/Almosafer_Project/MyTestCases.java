package Almosafer_Project;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
	@Test(enabled = false)
	public void CheckTheLanguage() {
		String Acteuallanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(Acteuallanguage, "en");
	}
	@Test(enabled = false)
	public void CheckTheCaruncy() {
		String ActeualCaruncy = driver.findElement(By.xpath("//button[normalize-space()='SAR']")).getText();
		Assert.assertEquals(ActeualCaruncy, "SAR");
	}
	@Test(enabled = false)
	public void CheckTheNumberofPhone() {
		WebElement NumberOfPhone = driver.findElement(By.cssSelector("a[class='sc-hUfwpO bWcsTG'] strong"));
		String ActualNumberOfPhone = NumberOfPhone.getText();
		String ExpectedNumberOfPhone = "+966554400000";
		Assert.assertEquals(ActualNumberOfPhone, ExpectedNumberOfPhone);
	}
	@Test(enabled = false)
	public void CheckQitafLogo() {
		WebElement Footer = driver.findElement(By.tagName("footer"));
		boolean QitafLogoIsDespaly = Footer.findElement(By.xpath("//div[@class='sc-fihHvN eYrDjb']")).isDisplayed();
		Assert.assertEquals(QitafLogoIsDespaly, true);
	}
	@Test(enabled = false)
	public void CheckHotelTapIsNotSelected() {
		WebElement HotelTap = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualValue = HotelTap.getAttribute("aria-selected");
		Assert.assertEquals(ActualValue, "false");
	}
	@Test(enabled = false)
	public void ChangeTheLangugeOfTheWebsite() throws InterruptedException {
		String[] MyWebsite = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		Random rand = new Random();
		int RandomNumber = rand.nextInt(MyWebsite.length);
		driver.get(MyWebsite[RandomNumber]);
		Thread.sleep(2000);
		String MyWebsiteURL = driver.getCurrentUrl();
		if (MyWebsiteURL.contains("ar")) {
			String Acteuallanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			Assert.assertEquals(Acteuallanguage, "ar");
		} else if (MyWebsiteURL.contains("en")) {
			String Acteuallanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
			Assert.assertEquals(Acteuallanguage, "en");
		}
	}
	@Test(enabled = false)
	public void CheckTheDateOfTheWebsite() {
		LocalDate ExpectedReturnDate = LocalDate.now().plusDays(2);
		LocalDate ExpectedDepatureDate = LocalDate.now().plusDays(1);
		int ExpectedDayOfTheReturn = ExpectedReturnDate.getDayOfMonth();
		int ExpectedDayOfTheDepature = ExpectedDepatureDate.getDayOfMonth();
		WebElement ActualReturnDateOnWebsite = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']"));
		WebElement ActualDepatureDateOnWebsite = driver
				.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']"));
        Assert.assertEquals(Integer.parseInt(ActualDepatureDateOnWebsite.getText()), ExpectedDayOfTheDepature);
        Assert.assertEquals(Integer.parseInt(ActualReturnDateOnWebsite.getText()), ExpectedDayOfTheReturn);
	}
	@Test(enabled = false)
	public void HotelTabSwitch() throws InterruptedException {
		String[] MyWebsite = { "https://www.almosafer.com/ar", "https://www.almosafer.com/en" };
		String [] ArabicCities = {"دبي","جدة"};
		String [] EnglishCities = {"Dobi","Jeddah","Riyadh"};
		Random rand = new Random();
		int RandomNumber = rand.nextInt(MyWebsite.length);
		int RandomArabicCities = rand.nextInt(ArabicCities.length);
		int RandomEnglishCities = rand.nextInt(EnglishCities.length);
		driver.get(MyWebsite[RandomNumber]);
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();
		Thread.sleep(5000);
		if(driver.getCurrentUrl().contains("ar")) {
			WebElement SearchBarForHotel = driver.findElement(By.cssSelector("input[placeholder='البحث عن فنادق أو وجهات']"));
			SearchBarForHotel.sendKeys(ArabicCities[RandomArabicCities]+Keys.ENTER);
			WebElement Results = driver.findElement(By.cssSelector(".phbroq-4.UzzIN.AutoComplete__List"));
			driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-hotels\"]/div/div/div/div[4]/button")).click();
			Thread.sleep(10000);
			WebElement mySelectElement = driver
					.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
			Select selector = new Select(mySelectElement);
			selector.selectByIndex(rand.nextInt(2));
			Thread.sleep(10000);

			String resultsFound = driver
					.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText();
			Assert.assertEquals(resultsFound.contains("وجدنا"), true);
			driver.findElement(By.xpath("//button[contains(text(),'الأقل سعراً')]")).click();
			Thread.sleep(3000);

		}else {
			WebElement SearchBarForHotel = driver.findElement(By.cssSelector("input[placeholder='Search for hotels or places']"));
		    SearchBarForHotel.sendKeys(EnglishCities[RandomEnglishCities]+Keys.ENTER);
		    WebElement Results = driver.findElement(By.cssSelector(".phbroq-4.UzzIN.AutoComplete__List"));
			driver.findElement(By.xpath("//*[@id=\"uncontrolled-tab-example-tabpane-hotels\"]/div/div/div/div[4]/button")).click();
			Thread.sleep(10000);
			WebElement mySelectElement = driver
					.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
			Select selector = new Select(mySelectElement);
			selector.selectByIndex(rand.nextInt(2));
			Thread.sleep(10000);
			String resultsFound = driver
					.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText();
			Assert.assertEquals(resultsFound.contains("found"), true);
			driver.findElement(By.xpath("//button[normalize-space()='Lowest price']")).click();
			Thread.sleep(3000);
		}
		WebElement rightSection = driver.findElement(By.xpath("//div[@class='sc-htpNat KtFsv col-9']"));
		List<WebElement> Prices = rightSection.findElements(By.className("Price__Value"));
		int LowestPrice = 0;
		int HighestPrice = 0;
		for (int i = 0; i < Prices.size(); i++) {
			LowestPrice = Integer.parseInt(Prices.get(0).getText());
			HighestPrice = Integer.parseInt(Prices.get(Prices.size() - 1).getText());
			Assert.assertEquals(LowestPrice < HighestPrice, true);
		}
		System.out.println(LowestPrice + " this is the lowest price ");
		System.out.println(HighestPrice + " this is the highest price ");
	}
	@AfterTest
	public void MyAfterTest() {
	}
}