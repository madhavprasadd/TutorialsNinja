package base;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.Utilities;

public class Base {
	protected Properties prop;
	public Properties dataProp;
	public Base() throws IOException {
		 prop=new Properties();
		 dataProp=new Properties();
		 File datafile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\testdata\\testdata.properties");
		 FileInputStream datafis=new FileInputStream(datafile);
		 dataProp.load(datafis);
		File file=new File(System.getProperty("user.dir")+"\\src\\main\\java\\config\\config.properties");
		
		
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);
		
		
	}
	
	WebDriver driver;
	public WebDriver intializeBrowser(String browserName) {
		
		driver=new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT));
    	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_WAIT));
    	driver.get(prop.getProperty("url"));
    	return driver;
	}

}
