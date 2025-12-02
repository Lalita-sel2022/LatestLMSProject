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

public class CaseLawAssignPage {
     WebDriver driver;
     
     public CaseLawAssignPage(WebDriver driver)
     {
    	 this.driver=driver;
    	 PageFactory.initElements(driver, this);
     }
     
     @FindBy(xpath="//ul[@class='taw_tree_box']/li[@class='parent_li']/span")
 	public WebElement parentLaw;
 	
 	@FindBy(xpath="//input[@class='c_box l_box ']")
 	public List<WebElement> allLaws;
 	
 	@FindBy(xpath="(//div[@class='region_ap_to'])[3]")
 	public WebElement topSaveScrollbar;
 	
 	@FindBy(xpath="(//div[@class='region_ap_to']//button[@id='save'])[1]")
 	public WebElement law_Save_Button;
 
 	@FindBy(xpath="//div[@class='modal-footer']/button[contains(text(),'OK')]")
 	public WebElement confirmationButton;
 	
 	@FindBy(xpath="//div[@class='chosen-container chosen-container-multi chosen-with-drop chosen-container-active']")
 	public WebElement standardTagField;
 	
 	@FindBy(xpath="//div[@class='chosen-drop']/ul/li")
 	public List<WebElement> standardTagOptions;
	
	public void selectAllUncheckedLaws() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(parentLaw)).click();
//		lawOption.click();
		for(WebElement law:allLaws)
		{
			if(!law.isSelected())
			{
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", law);
				Thread.sleep(500); // optional small wait
				law.click();
			}
		}
	}
    public void clickLawSaveButton()
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
      // Scroll div ke andar poora neeche tak
    	js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", topSaveScrollbar);
    	js.executeScript("arguments[0].click();", law_Save_Button);
//        law_Save_Button.click();
    }
    
    public void clickConfirmationButton()
    {
    	confirmationButton.click();
    }
    
    public void selectStandardTag(String... optionName)
    {
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.elementToBeClickable(standardTagField)).click();
		for(WebElement option:standardTagOptions)
		{
		String optionValue=option.getText().trim();
		 if (optionValue.equalsIgnoreCase(optionValue)) {
			 option.click();
             break;
		 }
	}}
    
}
