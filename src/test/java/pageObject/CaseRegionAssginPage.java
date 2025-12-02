package pageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CaseRegionAssginPage 
{
	WebDriver driver;
	public CaseRegionAssginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ul[@class='region_tree_box']/li/span")
	public WebElement parentRegion;
	
	@FindBy(xpath="//input[@class='c_box']")
	public List<WebElement> resionCheckboxs;
	
	@FindBy(xpath="(//button[@id='save region_button'])[1]")
	public WebElement region_Save_Button;
	
	@FindBy(xpath="//button[text()='OK']")
	public WebElement okConfirmationButton;
	
	
	
    public void selectAllRegions() throws InterruptedException
    {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.elementToBeClickable(parentRegion)).click();
//		((JavascriptExecutor)driver).executeScript("arguments[0].click();", parentRegion);
		Thread.sleep(2000);
		for(int i = 0; i < resionCheckboxs.size(); i++)
		{
			WebElement cb = resionCheckboxs.get(i);
		    if (cb.isDisplayed() && cb.isEnabled()) 
		    {
		        cb.click();
			
			}
		    Thread.sleep(2000);
		}
		
    }
    public void clickResionSaveButton()
    {
    	region_Save_Button.click();
    }
    
    public void clickConfirmationOk()
    {
    	okConfirmationButton.click();
    }
    
    

}
