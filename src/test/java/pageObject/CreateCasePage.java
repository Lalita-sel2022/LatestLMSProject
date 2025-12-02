package pageObject;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCasePage {
	WebDriver driver;
	
	public CreateCasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[contains(text(),'Basic Details')]")
	public WebElement BasicDetailTab;
	
	@FindBy(xpath="//div[contains(text(),'Name of entity  e.g.  co.x')]")
	public WebElement dropdownEntityName;
	
	@FindBy(xpath="//div[@id='bs-select-1']/ul/li")
	public List<WebElement> entityDropdownOptions;
	
	@FindBy(id="case_title")
	public WebElement txtCaption;
	
	@FindBy(xpath="//div[contains(text(),'Nothing selected')]")
	public WebElement dropdownOwnerName;
	
	@FindBy(xpath="//div[@id='bs-select-11']/ul/li")
	public List<WebElement> ownerNameDropdownOptions;
	
	@FindBy(xpath="//div[contains(text(),'Current status of case - open/closed  e.g.  open')]")
	public WebElement dropdownCaseStatus;
	
	@FindBy(xpath="//div[@id='bs-select-3']/ul/li")
	public List<WebElement> caseStatusDropdownOptions;
	
	@FindBy(xpath="//div[contains(text(),'Not Assigned')]")
	public WebElement dropdownPriority;
	
	@FindBy(xpath="//div[@id='bs-select-4']/ul/li")
	public List<WebElement> priorityDropdownOptions;
	
	@FindBy(id="case_start_date2")
	public WebElement startDateField;
	
	@FindBy(xpath="//div[contains(text(),'Select case group')]")
	public WebElement dropdownCaseGroup;
	
	@FindBy(xpath="//div[@id='bs-select-5']/ul/li")
	public List<WebElement> caseGroupDropdownOptions;
	
	@FindBy(id="rel_period_opt")
	public WebElement relevantPeriodDropdown;
	
	@FindBy(xpath="//div[contains(text(),'Select year')]")
	public WebElement selectYearDropdown;
	
	@FindBy(xpath="//div[@id='bs-select-6']/ul/li")
	public List<WebElement> selectYearDropdownOptions;
	
	@FindBy(id="rel_period")
	public WebElement txtPeriod;
	
	@FindBy(id="case_id")
	public WebElement txtCaseID;
	
	@FindBy(xpath="//textarea[@name='case_desc']")
	public WebElement txtDescription;
	
	@FindBy(xpath="//input[@id='case_tax_registeration_no']")
	public WebElement txtRegistrationNo;
	
	@FindBy(id="case_start_date2")
	public WebElement caseStartDateField;
	
	@FindBy(xpath="//span[@class='ui-datepicker-month']")
	public WebElement caseStartDate_month;
	
	@FindBy(xpath="//select[@class='ui-datepicker-year']")
	public WebElement caseStartDate_year;
	
	@FindBy(xpath="//table[@class=\"ui-datepicker-calendar\"]/tbody/tr/td[not(contains(@class,'ui-datepicker-other-month'))]")
	public List<WebElement> caseStartDate_dates;
	
	@FindBy(xpath="//a[@class='ui-datepicker-prev ui-corner-all']")
	public WebElement caseStartDate_preButton;
	
	@FindBy(id="next")
	public WebElement nextButton;

	public boolean isCreateCasePageDisplayed() {
		try {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(BasicDetailTab));
        return BasicDetailTab.isDisplayed();
	    } catch (Exception e) {
	        return false;   // element nahi mila
	    }
	}
	
	public void selectEntity(String entityName) 
	{   
		dropdownEntityName.click();
		
		for(WebElement option:entityDropdownOptions)
		{
			String optionValue = option.getText().trim();
			if(optionValue.equalsIgnoreCase(entityName))
			{
				option.click();
				break;
			}
		}
   }
	
	public void setCaption(String caption) {
		txtCaption.sendKeys(caption);
	}
	// Method to select multiple owners
	public void selectOwnerName(String... ownerNames) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(dropdownOwnerName));
		element.click();
//	    dropdownOwnerName.click();

	    for (String nameToSelect : ownerNames) {
	        for (WebElement option : ownerNameDropdownOptions) {
	            String optionValue = option.getText().trim();

	            if (optionValue.equalsIgnoreCase(nameToSelect)) {
//	            	Thread.sleep(500);
	                option.click();
	                break; // move to next owner
	            }
	        }
	    }
	    Actions actions = new Actions(driver);
	    actions.moveByOffset(10, 10).click().perform();
	}
	
	public void selectCaseStatus(String caseStatus) 
	{
		dropdownCaseStatus.click();
		
		for(WebElement option:caseStatusDropdownOptions)
		{
			String optionValue = option.getText().trim();
			if(optionValue.equalsIgnoreCase(caseStatus))
			{
				option.click();
				break;
			}
		}
   }
	
	public void selectCasePriority(String priority) 
	{
		dropdownPriority.click();
		
		for(WebElement option:priorityDropdownOptions)
		{
			String optionValue = option.getText().trim();
			if(optionValue.equalsIgnoreCase(priority))
			{
				option.click();
				break;
			}
		}
   }
	
	public void selectCaseGroup(String case_group) 
	{
		dropdownCaseGroup.click();
		
		for(WebElement option:caseGroupDropdownOptions)
		{
			String optionValue = option.getText().trim();
			if(optionValue.equalsIgnoreCase(case_group))
			{
				option.click();
				break;
			}
		}
   }
	
	public void selectStartDate(String targetDay,String targetMonth,String targetYear) {
		String currentMonth;
		caseStartDateField.click();
//		Select Year
		Select selectYear = new Select(caseStartDate_year);
		selectYear.selectByVisibleText(targetYear);
//		Get current month
		currentMonth=caseStartDate_month.getText();
//		Navigate month
		while(!currentMonth.equalsIgnoreCase(targetMonth))
		{
			caseStartDate_preButton.click();
			currentMonth = caseStartDate_month.getText();
		}
//		Select date
			for (WebElement date : caseStartDate_dates) {
			    if (date.getText().equals(targetDay)) {
			        date.click();
			        break;
			    }
		}
	}
	
	public void setCaseID(String caseID)
	{
		txtCaseID.sendKeys(caseID);
	}
	 
	public void selectRelevantPeriod(String relevantType, String inputValue) {
	    Select select = new Select(relevantPeriodDropdown);
	    select.selectByVisibleText(relevantType);
	    
	    if (relevantType.equalsIgnoreCase("Period")) {
	        // Text field visible hogi
	    	txtPeriod.sendKeys(inputValue);
	        
	    } else if (relevantType.equalsIgnoreCase("Assessment Year") || relevantType.equalsIgnoreCase("Financial Year")) {
	    	selectYearDropdown.click();
	    	for(WebElement year:selectYearDropdownOptions) {
	    		String yearValue = year.getText();
	    		if(yearValue.equals(inputValue))
	    		{
	    			year.click();
	    		}
	    	}
	        } 
	    Actions actions = new Actions(driver);
	   actions.moveByOffset(5, 5).click().perform();
	}

	public void setDescription(String description)
	{
		txtDescription.sendKeys(description);
	}
	public void setRegistrationNo(String registerNo)
	{
		txtRegistrationNo.sendKeys(registerNo);
	}
	public void clickOnNextButton()
	{
		nextButton.click();
	}

}
