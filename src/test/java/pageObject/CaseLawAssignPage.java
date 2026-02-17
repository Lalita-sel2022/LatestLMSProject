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
import org.testng.Assert;

public class CaseLawAssignPage {
     WebDriver driver;
     
     public CaseLawAssignPage(WebDriver driver)
     {
    	 this.driver=driver;
    	 PageFactory.initElements(driver, this);
     }
     
     @FindBy(xpath="//span[normalize-space()='Law']")
 	public WebElement lawNode;
 	
 	@FindBy(xpath="//ul/li/input[@class='c_box l_box  ']")
 	public List<WebElement> allLawCheckboxes;
 	
 	@FindBy(xpath="//div[@class='region_ap_to']/button[@id='save']")
 	public WebElement topSaveScrollbar;
 	
 	@FindBy(xpath="(//div[@class='region_ap_to']//button[@id='save'])[1]")
 	public WebElement law_Save_Button;
 
 	@FindBy(xpath="//div[@class='modal-footer']/button[contains(text(),'OK')]")
 	public WebElement confirmationButton;
 	
 	@FindBy(xpath="//div[@class='chosen-container chosen-container-multi chosen-with-drop chosen-container-active']")
 	public WebElement standardTagField;
 	
 	@FindBy(xpath="//div[@class='chosen-drop']/ul/li")
 	public List<WebElement> standardTagOptions;
 	
 	@FindBy(xpath="//li[contains(@class,'active')]/a[text()='Law/Provision']")
 	public WebElement lawMappingHeader;
 	
 	@FindBy(xpath="//h4[contains(text(),'Case law mapped successfully.')]")
 	public WebElement popupMessage;
	
 	public void selectAllUncheckedLaws() {

 	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
 	    JavascriptExecutor js = (JavascriptExecutor) driver;

 	    // Expand Law node
 	    wait.until(ExpectedConditions.elementToBeClickable(lawNode)).click();;
// 	    js.executeScript("arguments[0].click();", lawNode);

 	    // Wait for checkbox presence
 	    List<WebElement> checkboxes = wait.until(
 	        ExpectedConditions.visibilityOfAllElements(allLawCheckboxes)
 	    );

 	    // Select all unchecked
 	    for (WebElement cb : checkboxes) {

 	        if (!cb.isSelected()) {

 	            js.executeScript(
 	                "arguments[0].scrollIntoView({block:'center'});", cb
 	            );

 	            js.executeScript("arguments[0].click();", cb);
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
    
    public boolean isLawMappingPageDisplayed()
    {
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    	return wait.until(ExpectedConditions.visibilityOf(lawMappingHeader))
                .isDisplayed();
    }
    
    public void validateLawMappingSuccessPopup() {
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(popupMessage));

        String actualMessage = popupMessage.getText();
        String expectedMessage = "Case law mapped successfully.";

        Assert.assertEquals(actualMessage, expectedMessage,
                "Law mapping success message is incorrect");
    }
    
}
