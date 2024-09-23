package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

    public static ExtentReports generateExtentReport() throws IOException {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\config\\config.properties")) {
            prop.load(fis);
        } catch (IOException e) {
            System.err.println("Could not load properties file: " + e.getMessage());
            throw e; // Rethrow or handle it as needed
        }

        ExtentReports extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\test-output\\Extent-Report\\extentreport.html");

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("TutorialsNinja");
        sparkReporter.config().setDocumentTitle("TN-Automation Report");
        sparkReporter.config().setTimeStampFormat("yyyy/MM/dd HH:mm:ss"); // Adjusted format
        
        extentReports.attachReporter(sparkReporter);
        
        extentReports.setSystemInfo("Application Url", prop.getProperty("url"));
        extentReports.setSystemInfo("Browser", prop.getProperty("browser"));
        extentReports.setSystemInfo("Gmail", prop.getProperty("validemail"));
        extentReports.setSystemInfo("Owner", "Madhav");
        extentReports.setSystemInfo("User", System.getProperty("user.name"));
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));

        extentReports.flush();
        return extentReports;
    }
}
