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
    ExtentTest currentTest;

    @Override
    public void onStart(ITestContext context) {
        try {
            report = ExtentReport.generateExtentReport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        currentTest = report.createTest(result.getName());
        currentTest.log(Status.INFO, result.getName() + " started execution");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        currentTest.log(Status.PASS, result.getName() + " successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
            File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Ensure the Screenshots directory exists
            File screenshotDir = new File(System.getProperty("user.dir") + "\\Screenshots\\");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }

            String dest = screenshotDir + "\\" + result.getName() + ".png";
            FileHandler.copy(srcScreenshot, new File(dest));

            currentTest.addScreenCaptureFromPath(dest);
            currentTest.log(Status.FAIL, result.getName() + " failed with exception: " + result.getThrowable().getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        currentTest.log(Status.SKIP, result.getName() + " is skipped due to: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        report.flush();
        System.out.println("Execution finished");
    }
}
