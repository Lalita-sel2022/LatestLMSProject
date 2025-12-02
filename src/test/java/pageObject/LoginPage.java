package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="email")
	public WebElement txtUserName;
	
	@FindBy(id="password")
	public WebElement txtPassword;
	
	@FindBy(id="captcha")
	public WebElement txtCaptcha;
	
	@FindBy(xpath="//button[@name='sign_in']")
	public WebElement btnLogin;
	
	@FindBy(id="newCaptcha")
	public WebElement captcha;
	
	public void setUsername(String username)
	{
		txtUserName.sendKeys(username);
	}
	
	public void setPass(String password)
	{
		txtPassword.sendKeys(password);
	}
	
	public String getCapcha()
	{ try {
		return captcha.getAttribute("value");
	}catch(Exception e)
	{
		return e.getMessage();
	}
	}
	
	public void setCaptcha(String captcha)
	{
		txtCaptcha.sendKeys(captcha);
	}
	
	public void clickLoging()
	{
		btnLogin.click();
	}

}
