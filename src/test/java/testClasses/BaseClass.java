package testClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.LoginPage;
import pageObject.OtpVerificationPage;

public class BaseClass {
	
public static WebDriver driver;
public Logger logger;
public static String projectPath= System.getProperty("user.dir");
public static Properties pro;
	
    
    
	@BeforeClass
	@Parameters("browser")
	public void startUp(String browser) throws IOException
	{
		pro= new Properties();
		FileInputStream fis = new FileInputStream(projectPath + "/src/test/resources/config.properties");
        pro.load(fis);
//		logger=LogManager.getLogger(this.getClass());
        logger = LogManager.getLogger(this.getClass());
        
		if(browser.equalsIgnoreCase(browser))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			String jenkins = System.getenv("JENKINS_HOME");
			
			System.out.println(jenkins);
			
			if (jenkins != null) {
			    // Jenkins settings
			    options.addArguments("--headless=new");
			    options.addArguments("--no-sandbox");
			    options.addArguments("--disable-dev-shm-usage");
			    options.addArguments("--remote-allow-origins=*");
			    options.addArguments("--window-size=1920,1080");
			} else {
			    // Local machine
			    options.addArguments("--start-maximized");
			}
			driver = new ChromeDriver(options);
//			driver= new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		System.out.println(pro.getProperty("url"));
		driver.get(pro.getProperty("url"));
	
	}

	@AfterClass
	public void teardown()
	{
//		driver.quit();
	}
	
	public String randomString()
	{
		String generateString = RandomStringUtils.randomAlphabetic(5);
		return generateString;
	}
	
	public String randomNumber()
	{
		String generateNumber = RandomStringUtils.randomNumeric(10);
		return generateNumber;
	}
	
	public String randomAlphaNumeric()
	{
		String generateString = RandomStringUtils.randomAlphabetic(3);
		String generateNumber = RandomStringUtils.randomNumeric(2);
		return (generateString+"@"+generateNumber);
	}
	
	
	  @BeforeMethod public void loginMethod() {
	  logger.info("..........Starting Verify_Login..........."); 
	  try {
	  logger.info("Starting Verify_Login_Functionality"); 
	  LoginPage lg= new LoginPage(driver); 
	  logger.info("Enter valid credential");
	  lg.setUsername(pro.getProperty("username"));
	  lg.setPass(pro.getProperty("password")); 
	  String captcha =lg.getCapcha();
	  lg.setCaptcha(captcha); 
	  lg.clickLoging(); 
	  OtpVerificationPage otp= new OtpVerificationPage(driver); 
	  otp.setOtp("5678"); 
	  otp.clickValidateButton(); 
	  }catch(Exception e) 
	  { 
	 logger.error("Test Failed");
	  Assert.fail("Test failed due to exception: " + e.getMessage()); }
	  
	  logger.info("..........Finished Verify_Login..........."); }
	 

}
