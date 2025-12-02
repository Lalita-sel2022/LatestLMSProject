package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OtpVerificationPage 
{
	public WebDriver driver;
	
	public OtpVerificationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="validate_otp")
	public WebElement txtOTP;
	
	@FindBy(id="verify_otp")
	public WebElement OTPButton;
	
	public void setOtp(String otp)
	{
		txtOTP.sendKeys(otp);
	}
	
	public void clickValidateButton()
	{
		OTPButton.click();
	}
	
	

}
