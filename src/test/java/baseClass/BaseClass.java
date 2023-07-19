package baseClass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	public ResourceBundle rb;

	@BeforeClass(groups= {"master","regression","sanity"})
	@Parameters({ "browser" })
	public void setup(String br) {

		// Loading Config properties
		rb = ResourceBundle.getBundle("config");

		// Logging
		logger = LogManager.getLogger(this.getClass());

		// Driver
		// WebDriverManager.chromedriver().setup();
		if (br.equals("chrome")) {
			System.setProperty("Webdriver.chrome.driver", "G:\\SeleniumDeriver\\chromedriver_win32\\chromedriver");
			driver = new ChromeDriver();
			logger.info("chrome broser is selected");
		} else if (br.equals("edge")) {
			System.setProperty("Webdriver.edge.driver", "G:\\SeleniumDeriver\\edgedriver_win64\\msedgedriver");
			driver = new EdgeDriver();
			logger.info("Edge broser is selected");
		} else if (br.equals("firefox")) {
			System.setProperty("Webdriver.fireox.driver",
					"G:\\SeleniumDeriver\\geckodriver-v0.33.0-win-aarch64\\geckodriver");
			driver = new FirefoxDriver();
			logger.info("Firefox broser is selected");
		}

		// Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@AfterClass(groups= {"master","regression","sanity"})
	public void tearDown() {
		driver.quit();
	}

	public String randomestring() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}

	public int redomeNumber() {
		String genratednumber = RandomStringUtils.randomNumeric(5);
		return (Integer.parseInt(genratednumber));
	}
		
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
	}
	
}
