package pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
	
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="Case_td")
	public WebElement caseDropdownMenu;
	
	@FindBy(xpath="//ul[@id='Case']/li/a[text()='Create Case']")
	public WebElement createCaseOption;
	
	@FindBy(xpath="//li[@class='nav-item dropdown']/a")
	public WebElement profileButton;
	
	@FindBy(xpath="//ul[@class='dropdown-menu show']/li[2]")
	public WebElement logoutOption;
	
	@FindBy(xpath="//button[text()='Yes']")
	public WebElement yesConfirmationButton;
	
	@FindBy(xpath="//ul[@id='Case']/li")
	public List<WebElement> caseMenuOptions;
	
	public boolean isCaseDisplayed()
	{
		try {
		return caseDropdownMenu.isDisplayed();
		}catch(Exception e)
		{
			return false;
		}
	}
	
	public void clickCaseMenu()
	{
		caseDropdownMenu.click();
	}
	
	public void clickProfile()
	{
		profileButton.click();
	}
	
	public void clickLogout()
	{
		logoutOption.click();
	}
	public void confirmationPopup()
	{
		yesConfirmationButton.click();
	}
	
	public void selectCaseMenuOptions(String caseOption)
	{
		for(WebElement menu:caseMenuOptions)
		{
			String optionValue = menu.getText().trim();
			System.out.println("Case Options"+optionValue);
			if(optionValue.equalsIgnoreCase(caseOption))
			{
				menu.click();
				break;
			}
		}
	}
	

	

}
