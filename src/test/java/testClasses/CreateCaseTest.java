package testClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.CaseLawAssignPage;
import pageObject.CaseRegionAssginPage;
import pageObject.CreateCasePage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.OtpVerificationPage;

public class CreateCaseTest extends BaseClass
{
	
    @Test
	public void VerifyThatTheCaseIsSuccessfullyCreated() {
		
	logger.info("..........Starting Verify_Create_Case...........");
	try {
	HomePage hp= new HomePage(driver);
	logger.info("Validate case creation");
	hp.clickCaseMenu();
	logger.info("Select Create Case menu");
	hp.selectCaseMenuOptions("Create Case");
	CreateCasePage cp= new CreateCasePage(driver);
	 // Assertion 1: Create Case Page Loaded
    Assert.assertTrue(cp.isCreateCasePageDisplayed(),
            "Create Case page not loaded!");
    
    logger.info("Create Case page successfully loaded");
	logger.info("Select entity from entity dropdown");
	cp.selectEntity("Regression Entity");
	logger.info("Select Bulk Test Entity");
	cp.setCaption("Case 101");
	logger.info("Enter case name");
	cp.selectOwnerName("lalita Saini","User_Lalita 1");
	logger.info("Select Owner Names");
	cp.selectCaseStatus("Open");
	logger.info("Select case status");
	cp.selectCasePriority("High");
	logger.info("Select case Priority");
	cp.selectStartDate("17", "Feb", "2026");
	logger.info("Select case start date");
	cp.selectCaseGroup("Regression Child Case Group");
	logger.info("Select case group");
	cp.setCaseID("101");
	logger.info("Enter caseID");
	cp.selectRelevantPeriod("Financial Year","2025-2026");
	logger.info("Select Relevant Period");
	cp.setDescription("sjgdysd    shgfdtsfd     strdtysd");
	cp.setRegistrationNo("123456");
	logger.info("Enter registration No");
	cp.clickOnNextButton();
	logger.info("Click on next button");
	CaseLawAssignPage la=new CaseLawAssignPage(driver);
	Assert.assertTrue(la.isLawMappingPageDisplayed(),"Law Mapping tab not active after saving Basic Details");
	la.selectAllUncheckedLaws();
	logger.info("Select laws");
	la.clickLawSaveButton();
	logger.info("Click on save button");
	la.validateLawMappingSuccessPopup();
	la.clickConfirmationButton();
	CaseRegionAssginPage cr= new CaseRegionAssginPage(driver);
	cr.selectAllRegions();
	logger.info("Select regions");
	cr.clickResionSaveButton();
	logger.info("Click on save button");
	cr.clickConfirmationOk();
	logger.info("Confirm pop-up");

	}catch(Exception e)
	{
		logger.error("Test Failed");
		Assert.fail("Test failed due to exception: " + e.getMessage());
	}

	logger.info("..........Finished Verify_Login...........");
	}
    
//    @Test
//    public void VerifyThatTheCreatedCaseIsDisplayedInTheList() {
//    	
//    }

}
