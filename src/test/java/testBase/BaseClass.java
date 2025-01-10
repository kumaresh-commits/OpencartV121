package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //log4j
import org.apache.logging.log4j.Logger; // log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger; // log4j
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression", "Master", "DataProvider" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass()); // LOG4J2

		//remote
		if (p.getProperty("execution_evn").equalsIgnoreCase("remote")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();

			// os
			if (os.equalsIgnoreCase("Windows")) 
			{
				capabilities.setPlatform(Platform.WIN10);
			} 
			else if (os.equalsIgnoreCase("Mac")) 
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else 
			{
				System.out.println("No matching os");
			}
			
			
			// browser
			switch (br.toLowerCase()) {
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "firefox":capabilities.setBrowserName("firefox");break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
			default:System.out.println("no matching browser");return;
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
		

		//local
		if (p.getProperty("execution_evn").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				System.out.println("Invalid browser name...");
				return;
			}

		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(p.getProperty("appurl"));

	}

	@AfterClass(groups = { "Sanity", "Regression", "Master", "DataProvider" })
	public void terDown() {
		driver.quit();
	}

	// It will generate random Strings.
	public String randomString() {
		String generateString = RandomStringUtils.randomAlphabetic(6);
		return generateString;
	}

	// It will generate random number.
	public String randomnum() {
		String gerenatenum = RandomStringUtils.randomNumeric(6);
		return gerenatenum;
	}

	// It will generate random AlphaNumaric.
	public String randomAlphaNumaric() {
		String generateString = RandomStringUtils.randomAlphabetic(6);
		String gerenatenum = RandomStringUtils.randomNumeric(3);
		return (generateString + "@" + gerenatenum);
	}

	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
