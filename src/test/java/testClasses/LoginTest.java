package testClasses;


import org.testng.annotations.Test;
import org.testng.Assert;
import pageObject.LoginPage;
import pageObject.OtpVerificationPage;
import pageObject.HomePage;
import utilities.DataProviders;

public class LoginTest extends BaseClass {
	
	
	
	@Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
	public void DataProvider_Login(String username,String pass)
	{
		logger.info("..........Starting Verify_Login...........");
		try {
		logger.info("Starting Verify_Login_Functionality");
		LoginPage lg= new LoginPage(driver);
		logger.info("Enter valid credential");
		lg.setUsername(username);
		lg.setPass(pass);
		String captcha =lg.getCapcha();
		lg.setCaptcha(captcha);
		lg.clickLoging();
		OtpVerificationPage otp= new OtpVerificationPage(driver);
		otp.setOtp("5678");
		otp.clickValidateButton();
		
		}catch(Exception e)
		{
			logger.error("Test Failed");
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
		
		logger.info("..........Finished Verify_Login...........");
		
	}
	
	
	
	
}
