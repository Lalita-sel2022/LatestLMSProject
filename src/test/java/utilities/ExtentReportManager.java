package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener
{   public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	public String reportName;
	
	public void onStart(ITestContext context) {
	String timeStemp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	reportName ="Test-Report-"+timeStemp+".html";
	System.out.println("Report Path "+ reportName);
	sparkReporter= new ExtentSparkReporter("/home/dalam/eclipse-workspace/openCart/report/"+reportName);
	sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
	sparkReporter.config().setReportName("Lalita");
	sparkReporter.config().setTheme(Theme.DARK);
	
	extent= new ExtentReports();
	extent.attachReporter(sparkReporter);
	extent.setSystemInfo("Environment", "QA");
	extent.setSystemInfo("User Name", "Lalita");
	String os= context.getCurrentXmlTest().getParameter("os");
	extent.setSystemInfo("Operating System", os);
	String browser= context.getCurrentXmlTest().getParameter("browser");
	extent.setSystemInfo("Browser", browser);
	
	
	}
	public void onTestStart(ITestResult result) {
		 
		
	}
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		test.log(Status.PASS, result.getMethod().getMethodName()+"got successfully executed");
	}
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		test.log(Status.FAIL, result.getMethod().getMethodName()+" got failed");
		try {
			String screenshotPath=ScreenshotUtil.captureScreenshot(result.getMethod().getMethodName());
			test.addScreenCaptureFromPath(screenshotPath);
//	        test.fail("Screenshot of failure :", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		test.log(Status.SKIP, result.getMethod().getMethodName());
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
		
//		Get open report
		String pathOfExtentReport = System.getProperty("user.dir")+"/report/"+reportName;
		File extentReport = new File(pathOfExtentReport);
		
//		try {
//			Desktop.getDesktop().browse(extentReport.toURI());
//		}catch(Exception e) {
//			e.printStackTrace();
//			
//		}
		
	}
	
    


}
