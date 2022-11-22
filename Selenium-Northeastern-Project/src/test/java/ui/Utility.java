package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Utility {
    WebDriver driver;

    public Properties getProperties() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("target/generated-test-sources/test-annotations/testdata.properties");
        properties.load(fileInputStream);
        return properties;
    }

    public WebDriver launchBrowser() throws IOException {

        Properties properties = this.getProperties();
        String browser = properties.getProperty("browser");

        if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        return driver;
    }



    public void getScreenShot(String fileName) throws IOException{
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
        Date date = new Date();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("/Users/bhavinbhatia/BB_Documents/Projects/selenium/intelij-selenium" +
                "/Selenium-Northeastern-Project/screenshots/"+fileName+"-"+dateFormat.format(date)+".png"));
    }
}
