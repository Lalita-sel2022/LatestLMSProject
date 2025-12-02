package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import testClasses.BaseClass;

public class ScreenshotUtil extends BaseClass{
	
	public static String captureScreenshot(String fileName) throws IOException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		String timestamp =formatter.format(new Date());
		System.out.println(formatter+"........"+timestamp);
		String screenshotPath = System.getProperty("user.dir")+"/Screenshots/"+fileName+"_"+timestamp+".png";
		
		TakesScreenshot screenshot = ((TakesScreenshot)driver);
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File(screenshotPath);
		FileUtils.copyFile(srcFile, dest);
		return screenshotPath;
	}

}
