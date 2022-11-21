package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.tracing.Status;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.internal.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

import static org.testng.Assert.assertEquals;

public class SeleniumTest {

    WebDriver driver;


    @BeforeTest //Runs the test before all the Test that has been defined
    public void loginToApplication(){
        System.out.println("Login into the Application");
    }
    @AfterTest //Runs the test after all the Test that has been defined
    public void logoutFromApplication(){
        System.out.println("logout from the application");
    }
    @BeforeMethod // Runs the method before every test
    public void launchBrowser() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("target/generated-test-sources/test-annotations/testdata.properties");
        properties.load(fileInputStream);
        String browser = properties.getProperty("browser");

        if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
    }
    public void getScreenShot(String fileName) throws IOException{
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
        Date date = new Date();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("/Users/bhavinbhatia/BB_Documents/Projects/selenium/intelij-selenium" +
                "/Selenium-Northeastern-Project/screenshots/"+fileName+"-"+dateFormat.format(date)+".png"));
    }

    @Test(priority = 1, description = "Scenario 1: Add options in “My Favorites” and verify if it works or not ")
    public void scenarioOne() throws IOException, InterruptedException {
        System.out.println("Test One");
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("target/generated-test-sources/test-annotations/testdata.properties");
        properties.load(fileInputStream);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10000));

        //Open NEU Portal on Chrome
        driver.get("https://me.northeastern.edu/");

        //login to Northeastern
        WebElement buttonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/div/form/div[1]/div[2]/div\t")));
        buttonElement.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

        //Before Login SS
        getScreenShot("Scenario1/Before Login");

        //Enter the login credentials (username)
        driver.findElement(By.id("username")).sendKeys(properties.getProperty("username"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

        //Enter the login credentials (password)
        driver.findElement(By.id("password")).sendKeys(properties.getProperty("password"));
        Thread.sleep(1000);

        //click on login button
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("_eventId_proceed")))).click();
        Thread.sleep(5000);

        //Click on Yes
        driver.findElement(By.xpath("/html/body/div/form/div/div/div[2]/div[1]/div/div/div/div/div/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/input")).click();
        Thread.sleep(3000);

        //click on Lets Go
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/button[2]/span/span/span")).click();
        Thread.sleep(3000);

        //After Login SS
        getScreenShot("Scenario1/After Login");

        // Click on resources
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[2]/div[2]/div/div[2]/div/div[3]/div/div/div/span[4]/a")).click();
        Thread.sleep(2000);

        //Click on Academic Classes & Registration
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[2]/div[3]/section/article/div[1]/div/div/div/div[1]/div/div/div/div/div/div/div/div/div/div/div/div/div[1]/div[2]/div/div[1]/div/p")).click();
        Thread.sleep(2000);

        //add to favourites Course Description
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[2]/div[3]/section/article/div[1]/div/div/div/div[1]/div/div/div/div/div/div/div/div/div/div/div/div/div[2]/div/div/div[1]/div/div[10]/div/i")).click();
        Thread.sleep(2000);

        //add to favourites My Transcripts
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[2]/div[3]/section/article/div[1]/div/div/div/div[1]/div/div/div/div/div/div/div/div/div/div/div/div/div[2]/div/div/div[1]/div/div[21]/div/i")).click();
        Thread.sleep(2000);

        //After adding to fav
        getScreenShot("Scenario1/After Fav");

    }

    @Test(priority = 2, description = "Scenario 2: Delete the option from “My Favorites” ")
    public void scenarioTwo() throws IOException, InterruptedException {
        System.out.println("Test Two");

        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("target/generated-test-sources/test-annotations/testdata.properties");
        properties.load(fileInputStream);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30000));

        //Open NEU Portal on Chrome
        driver.get("https://me.northeastern.edu/");

        //login to Northeastern
        WebElement buttonElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/div/form/div[1]/div[2]/div\t")));
        buttonElement.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

        //Before Login SS
        getScreenShot("Scenario2/Before Login");

        //Enter the login credentials (username)
        driver.findElement(By.id("username")).sendKeys(properties.getProperty("username"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

        //Enter the login credentials (password)
        driver.findElement(By.id("password")).sendKeys(properties.getProperty("password"));
        Thread.sleep(1000);

        //click on login button
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("_eventId_proceed")))).click();

        Thread.sleep(10000);

//		Click on Yes
        driver.findElement(By.xpath("/html/body/div/form/div/div/div[2]/div[1]/div/div/div/div/div/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/input")).click();
        Thread.sleep(2000);

        //click on Lets Go
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/button[2]/span/span/span")).click();
        Thread.sleep(2000);

        //After Login SS
        getScreenShot("Scenario2/After Login");

        // Click on resources
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[2]/div[2]/div/div[2]/div/div[3]/div/div/div/span[4]/a")).click();
        Thread.sleep(2000);

        //Click on Academic Classes & Registration
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[2]/div[3]/section/article/div[1]/div/div/div/div[1]/div/div/div/div/div/div/div/div/div/div/div/div/div[1]/div[2]/div/div[1]/div/p")).click();

        //add to favourites Course Description
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[2]/div[3]/section/article/div[1]/div/div/div/div[1]/div/div/div/div/div/div/div/div/div/div/div/div/div[2]/div/div/div[1]/div/div[10]/div/i")).click();
        Thread.sleep(2000);

        //add to favourites My Transcripts
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[2]/div[3]/section/article/div[1]/div/div/div/div[1]/div/div/div/div/div/div/div/div/div/div/div/div/div[2]/div/div/div[1]/div/div[21]/div/i")).click();
        Thread.sleep(2000);

        //After adding to fav
        getScreenShot("Scenario2/After Fav");

    }

    @Test (priority = 3, description = "Scenario 3: Browse classes for the Spring 2023 Semester ")
    public void scenarioThree() throws InterruptedException, IOException {
        System.out.println("three");
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("/Users/bhavinbhatia/BB_Documents/Projects/selenium" +
                "/intelij-selenium/untitled/target/generated-test-sources/test-annotations/testdata.properties");
        properties.load(fileInputStream);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30000));
        driver.get("https://about.me.northeastern.edu/home/");

        //login to student Hub
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//*[@id=\"menu-item-menu-main-desktop-2483\"]/a/span")));
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));



        //Login Screen
        driver.findElement(By.xpath("//*[@id=\"bySelection\"]/div[2]/div/span")).click();
        driver.findElement(By.id("username")).sendKeys(properties.getProperty("username"));
        driver.findElement(By.id("password")).sendKeys(properties.getProperty("password"));
        driver.findElement(By.xpath("/html/body/section/div/div[1]/div/form/div[3]/button")).click();


        //Click on Yes
        driver.findElement(By.xpath("/html/body/div/form/div/div/div[2]/div[1]/div/div/div/div/div/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/input")).click();
        Thread.sleep(2000);

        //click on Lets Go
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/button[2]/span/span/span")).click();
        Thread.sleep(2000);
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("#spSiteHeader > div > div.headerRow-45 > div > div.adjacentTitleSubcell-54 > " +
                "div > div > div > span:nth-child(4) > a")).click();

        driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[1]" +
                "/div[2]/div/div[1]/div/p")).click();
        driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[2]" +
                "/div/div/div[1]/div/div[11]/div/div/a")).click();


        //Handeling Multiple windows
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        String parentWindow = iterator.next();
        String childWindow = iterator.next();
        driver.switchTo().window(childWindow);
        //After Login Screenshot
        getScreenShot("Scenario3/AfterLogin");

        // Click Browse classes link
        driver.findElement(By.xpath("//*[@id=\"classSearchLink\"]/span")).click();


        //Clicking on dropdown
        driver.findElement(By.id("select2-chosen-1")).click();
        //Type the Term which you wish to plan for
        driver.findElement(By.id("s2id_autogen1_search")).sendKeys("Spring 2023 Semester");
        Thread.sleep(2000);
        //Select the term
        driver.findElement(By.xpath("//div[@id=\"202330\"]")).click();
        getScreenShot("Scenario3/select_term");
        Thread.sleep(2000);

        //Click on Continue button
        driver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/div[3]/button")).click();
        Thread.sleep(2000);

        //Click on Search Button
        driver.findElement(By.xpath("/html/body/main/div[3]/div/div/div/div/div[1]/div[2]/div[2]/button")).click();
        getScreenShot("Scenario3/browse");
        Thread.sleep(2000);

    }

    @Test(priority = 4, description = "Scenario 4: Add the items to your cart in the NU Bookstore ")
    public void scenarioFour() throws IOException, InterruptedException {
        System.out.println("Scenario 4: Add the items to your cart in the NU Bookstore");
        driver.get("https://northeastern.bncollege.com/");
        driver.manage().window().fullscreen();
        Thread.sleep(5000);
        getScreenShot("Scenario4/original shopping site");
        Thread.sleep(2000);

        driver.findElement(By.id("bned_site_search")).sendKeys("hats" + "\n");
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div/div[8]/div[2]/div[3]/div/div[2]/" +
                "div[2]/div/div[2]/div[3]/a")).click();
        getScreenShot("Scenario4/hats");
        Thread.sleep(2000);

        driver.findElement(By.className("add-to-cart-container")).click();
        getScreenShot("Scenario4/checkout");
        Thread.sleep(2000);
        getScreenShot("Scenario4/checkout");

        driver.quit();


    }

    @Test(priority = 5, description = "Scenario 5: Create a plan for course registration")
    public void scenarioFive() throws IOException, InterruptedException {
        System.out.println("five");
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("/Users/bhavinbhatia/BB_Documents/Projects/selenium" +
                "/intelij-selenium/untitled/target/generated-test-sources/test-annotations/testdata.properties");
        properties.load(fileInputStream);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30000));
        driver.get("https://about.me.northeastern.edu/home/");

        //login to student Hub
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath
                ("//*[@id=\"menu-item-menu-main-desktop-2483\"]/a/span")));
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

        //Login Screen
        driver.findElement(By.xpath("//*[@id=\"bySelection\"]/div[2]/div/span")).click();
        driver.findElement(By.id("username")).sendKeys(properties.getProperty("username"));
        driver.findElement(By.id("password")).sendKeys(properties.getProperty("password"));
        getScreenShot("Scenario5/login");
        Thread.sleep(5000);
        //Get screenshot of login screen

        driver.findElement(By.xpath("/html/body/section/div/div[1]/div/form/div[3]/button")).click();


        //Click on Yes
        driver.findElement(By.xpath("/html/body/div/form/div/div/div[2]/div[1]/div/div/div/div/div/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/input")).click();
        Thread.sleep(2000);

        //click on Lets Go
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/button[2]/span/span/span")).click();
        Thread.sleep(2000);
        driver.manage().window().fullscreen();
        driver.findElement(By.cssSelector("#spSiteHeader > div > div.headerRow-45 > div > div.adjacentTitleSubcell-54 > " +
                "div > div > div > span:nth-child(4) > a")).click();
        getScreenShot("Scenario5/new one");
        driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[1]" +
                "/div[2]/div/div[1]/div/p")).click();
        driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[2]" +
                "/div/div/div[1]/div/div[11]/div/div/a")).click();


        //Handeling Multiple windows
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        String parentWindow = iterator.next();
        String childWindow = iterator.next();
        driver.switchTo().window(childWindow);


        //new registration window
        getScreenShot("Scenario5/Registration_window");
        driver.findElement(By.id("planningLink")).click();

        driver.findElement(By.xpath("//*[@id=\"select2-chosen-1\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"202330\"]")).click();


        driver.findElement(By.id("term-go")).click();
        driver.findElement(By.id("createPlan")).click();
        driver.findElement(By.xpath("//*[@id=\"s2id_txt_subject\"]")).click();

        driver.findElement(By.xpath("//*[@id=\"s2id_autogen1\"]")).sendKeys("Information Systems Program");

        driver.findElement(By.xpath("//*[@id=\"INFO\"]")).click();
        driver.findElement(By.id("search-go")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[6]/div/button[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[2]/td[6]/div/button[2]")).click();
        Thread.sleep(2000);
        getScreenShot("Scenario5/add plan");
        driver.findElement(By.xpath("//*[@id=\"saveButton\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"txt_planDesc\"]")).sendKeys("Test");
        driver.findElement(By.cssSelector("body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.course-details-dialog.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(2)")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"breadcrumbHeader\"]/a[4]")).click();
        getScreenShot("Scenario5/Test Plan");
    }

}
