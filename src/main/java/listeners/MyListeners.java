package listeners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.ExtentReport;

public class MyListeners implements ITestListener {
	
	ExtentReports report;
	@Override
	public void onStart(ITestContext context) {
		
		
			try { 
				report = ExtentReport.generateExtentReport();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}		//System.out.println("exceution of project started");
	

	@Override
	public void onTestStart(ITestResult result) {
		
		 ExtentTest test = report.createTest(result.getName());
		 test.log(Status.INFO,result.getName()+"started execution");
		//System.out.println(result.getName()+"is started execution");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTest test = report.createTest(result.getName());
		test.log(Status.PASS, result.getName()+"Sucessfully executed");
		//System.out.println(result.getName()+"is sucesfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		//to get the access to driver to this block the next line will do
		try {
			 driver=(WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File srcScrrenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String dest=System.getProperty("user.dir")+"\\Scrrenshots\\"+result.getTestName()+"\\.png";
//	try {
//			FileHandler.copy(srcScrrenshot, dest);
//		} catch (IOException e) {s
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(result.getThrowable());
		
		System.out.println(result.getName()+"is failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getName()+"is skiiped");
		System.out.println(result.getThrowable());
	}

	
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("execution fineshed");
		
	}
	
	

}
