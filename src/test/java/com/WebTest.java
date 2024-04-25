package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebTest {

    public static WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver","CD:\\Soft\\geckodriver:\\geckodriver.exe");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();

        //Please uncomment if wants to test in Chrome browser
//        System.setProperty("webdriver.chrome.driver","D:\\\\Soft\\\\Chrome\\\\chromedriver-win64\\\\chromedriver-win64\\\\chromedriver.exe");
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();

        driver.manage().window().maximize();

        //Test case id: TC_01 verify Open the web aplication
        String url= "http://123.200.20.20:5302/";
        driver.get(url);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /**
     * Function Name: main
     * Description: this is main function which will execute all test cases one by one
     * Parameters: None
     *
     */
    @Test
    public void main () throws Exception{

        try {

            // Initialize data

            // Execute all test case Sequentially
            Thread.sleep(5000);
            CircularSearch(); // Test Case ID: TC_02
            JobApplyNow(); // Test Case ID: TC_03, TC_06, TC_07 and TC_08
            BasicInformation(); // Test Case ID: TC_09, TC_10
            Address(); // Test Case ID: TC_11
            Education(); // Test Case ID: TC_12
            JobExperience(); // Test Case ID: TC_13
//            Certificate(); // Test Case ID: TC_14
//            Complete(); // Test Case ID: TC_15

        }catch (Exception e)
        {
            e.printStackTrace();
            //scriptMgmt.logFail_stop_sendmail(InitData.scriptName,e);
        }
    }//login


    /**
     * Function Name: CircularSearch
     * Description: this function will search for specific circular
     * Test Case reference number: TC_02
     * Parameters: None
     *
     */
    public void CircularSearch() throws Exception{
        driver.findElement(By.cssSelector("#circularMst_filter .form-control")).click();
        driver.findElement(By.cssSelector("#circularMst_filter .form-control")).sendKeys("99/2024");
        driver.findElement(By.cssSelector("#circularMst_filter .form-control")).sendKeys(Keys.ENTER);
        //Thread.sleep(5000);
        //Assert.assertTrue(driver.getCurrentUrl().contains("99/2024"));
        Thread.sleep(3000);
    }//End of Circular Search

    /**
     * Function Name: CircularSearch
     * Description: this function will click on a specific circular and then apply for job without login
     * Test Case reference number: TC_03, TC_06, TC_07 and TC_08
     * Parameters: None
     *
     */
    public void JobApplyNow() throws Exception{
        vars.put("window_handles", driver.getWindowHandles());
        driver.findElement(By.linkText("99/2024")).click();
        Thread.sleep(3000);

        //Switch between browser tabs using Selenium WebDriver
        driver.getWindowHandles().forEach(tab->driver.switchTo().window(tab));
        assertTrue(driver.findElement(By.id("circularDtl")).isDisplayed());

        //Click on the Action icon
        driver.findElement(By.linkText("Apply")).click();
        Thread.sleep(2000);
        //Click on the Apply Now button
        driver.findElement(By.cssSelector("strong")).click();
        Thread.sleep(2000);
        //Click on the Continue without Login button
        driver.findElement(By.linkText("Continue without Login")).click();
        Thread.sleep(3000);

    }//End of JobApplyNow

    /**
     * Function Name: BasicInformation
     * Description: This function will enter the applicant basic information like NID, DOB, Mobile no etc.
     * Test Case reference number: TC_09, TC_10
     * Parameters: None
     *
     */
    public void BasicInformation() throws Exception{

        JavascriptExecutor jscroll1 = (JavascriptExecutor)driver; // Scroll operation using Js Executor
        jscroll1.executeScript("window.scrollBy(0,100)"); // Scroll Down(+ve) upto browse option
        Thread.sleep(2000); // suspending execution for specified time period

        // ENTER National ID
        driver.findElement(By.id("national_id")).click();
        driver.findElement(By.id("national_id")).sendKeys("8231771135");
        driver.findElement(By.id("national_id")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        JavascriptExecutor jscroll2 = (JavascriptExecutor)driver; // Scroll operation using Js Executor
        jscroll2.executeScript("window.scrollBy(0,100)"); // Scroll Down(+ve) upto browse option
        Thread.sleep(2000);

        // NID FILE UPLOADING ....
        WebElement addFile = driver.findElement(By.id("national_id_attachment"));
        addFile.sendKeys("E:\\\\SelIDE\\\\WebTest\\\\image\\\\NID.jpg");// For setting a profile picture
        Thread.sleep(5000);

        // ENTER Date of Birth

        WebElement dob = driver.findElement(By.id("date_of_birth"));
        // cast the driver to JavascriptExecutor
        JavascriptExecutor js3 = (JavascriptExecutor) driver;
        js3.executeScript("arguments[0].value = arguments[1]",
                driver.findElement(By.id("date_of_birth")), "23-08-1995");
        Thread.sleep(2000);

        //WebElement dob = driver.findElement(By.id("date_of_birth"));
        dob.sendKeys("23081995");
        dob.sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        // CLICK ON VERIFY NID BUTTON
        driver.findElement(By.id("nid_verification")).click();
        Thread.sleep(3000);

        JavascriptExecutor jscroll3 = (JavascriptExecutor)driver; // Scroll operation using Js Executor
        jscroll3.executeScript("window.scrollBy(0,400)"); // Scroll Down(+ve) upto browse option
        Thread.sleep(2000);

        //ENTER Father Name
        driver.findElement(By.id("father_name")).click();
        driver.findElement(By.id("father_name")).sendKeys("Father Name");
        Thread.sleep(3000);
        JavascriptExecutor jscroll4 = (JavascriptExecutor)driver; // Scroll operation using Js Executor
        jscroll4.executeScript("window.scrollBy(0,400)"); // Scroll Down(+ve) upto browse option
        Thread.sleep(2000);
        //ENTER Mother Name
        driver.findElement(By.id("mother_name")).click();
        driver.findElement(By.id("mother_name")).sendKeys("Mother Name");
        Thread.sleep(3000);
        JavascriptExecutor jscroll5 = (JavascriptExecutor)driver; // Scroll operation using Js Executor
        jscroll5.executeScript("window.scrollBy(0,400)"); // Scroll Down(+ve) upto browse option
        Thread.sleep(2000);

        driver.findElement(By.id("mobile")).click();
        driver.findElement(By.id("mobile")).sendKeys("01911111114");
        Thread.sleep(5000);

        //assertThat(driver.switchTo().alert().getText(), is("Your One-Time Password is 5078. Please enter the password to login."));
        //Trim OTP info from the alert box
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        String otp = alertText.substring(26,31);
        System.out.println("alertText IS = " + alertText);
        System.out.println("OTP IS = " + otp);
        alert.accept();

        JavascriptExecutor jscroll6 = (JavascriptExecutor)driver; // Scroll operation using Js Executor
        jscroll6.executeScript("window.scrollBy(0,400)"); // Scroll Down(+ve) upto browse option
        Thread.sleep(3000);

        //Enter OTP value for match
        driver.findElement(By.id("otp")).click();
        driver.findElement(By.id("otp")).sendKeys(otp);
        Thread.sleep(4000);
        Alert alert2 = driver.switchTo().alert();
        alert2.accept();
        Thread.sleep(3000);
        //assertThat(driver.switchTo().alert().getText(), is("OTP Match"));

        //Enter email info
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("TEST@TEST.COM");
        driver.findElement(By.id("email")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        JavascriptExecutor jscroll7 = (JavascriptExecutor)driver; // Scroll operation using Js Executor
        jscroll7.executeScript("window.scrollBy(0,400)"); // Scroll Down(+ve) upto browse option
        Thread.sleep(3000);

        // Select Gender Male/Female/Others
        //driver.findElement(By.cssSelector(".col-md-9 .custom-control:nth-child(1) > .custom-control-label")).click();
        driver.findElement(By.cssSelector(".col-md-9 .custom-control:nth-child(2) > .custom-control-label")).click();
        Thread.sleep(3000);
        //driver.findElement(By.cssSelector(".col-md-9 .custom-control:nth-child(3) > .custom-control-label")).click();

        // Select MARITAL STATUS
        driver.findElement(By.id("marital_status")).click();
        {
            WebElement dropdown = driver.findElement(By.id("marital_status"));
            dropdown.findElement(By.xpath("//option[. = 'MARRIED']")).click();
        }
        Thread.sleep(3000);
        //Enter Spouse Name
        driver.findElement(By.id("spouse_name")).click();
        driver.findElement(By.id("spouse_name")).sendKeys("MARIA SULTANA");
        Thread.sleep(3000);

        JavascriptExecutor jscroll8 = (JavascriptExecutor)driver; // Scroll operation using Js Executor
        jscroll8.executeScript("window.scrollBy(0,400)"); // Scroll Down(+ve) upto browse option
        Thread.sleep(3000);

        //Select religion from the list
        driver.findElement(By.id("religion")).click();
        {
            WebElement dropdown = driver.findElement(By.id("religion"));
            dropdown.findElement(By.xpath("//option[. = 'ISLAM']")).click();
        }
        driver.findElement(By.cssSelector("#religion > option:nth-child(2)")).click();
        Thread.sleep(3000);

        //Upload photo
        WebElement addPhoto = driver.findElement(By.id("photo"));
        addPhoto.sendKeys("E:\\\\SelIDE\\\\WebTest\\\\image\\\\Photo.jpg");// For setting a profile picture
        Thread.sleep(3000);

        //Upload signature
        WebElement addSign = driver.findElement(By.id("signature"));
        addSign.sendKeys("E:\\\\SelIDE\\\\WebTest\\\\image\\\\Signature.jpg");// For setting a profile picture
        Thread.sleep(3000);

        JavascriptExecutor jscroll9 = (JavascriptExecutor)driver; // Scroll operation using Js Executor
        jscroll9.executeScript("window.scrollBy(0,400)"); // Scroll Down(+ve) upto browse option
        Thread.sleep(3000);

        // CLICK ON NEXT BUTTON
        driver.findElement(By.cssSelector(".justify-content-end:nth-child(1) > .btn")).click();
        Thread.sleep(3000);

    } //End BasicInformation

    /**
     * Function Name: Address
     * Description: This function will enter the applicant Permanent Address and Present Address
     * Test Case reference number: TC_11
     * Parameters: None
     *
     */
    public void Address() throws Exception{
        //ENTER applicant division info
        driver.findElement(By.id("permanent_division")).click();
        {
            WebElement dropdown = driver.findElement(By.id("permanent_division"));
            dropdown.findElement(By.xpath("//option[. = 'DHAKA']")).click();
        }
        driver.findElement(By.cssSelector("#permanent_division > option:nth-child(2)")).click();
        Thread.sleep(3000);

        //ENTER applicant distric info
        driver.findElement(By.id("permanent_district")).click();
        {
            WebElement dropdown = driver.findElement(By.id("permanent_district"));
            dropdown.findElement(By.xpath("//option[. = 'DHAKA']")).click();
        }
        Thread.sleep(3000);

        //select district from the list
        driver.findElement(By.cssSelector("#permanent_district > option:nth-child(2)")).click();
        {
            WebElement element = driver.findElement(By.linkText("3"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        }
        Thread.sleep(3000);

        //ENTER applicant Thana/Upazila info
        driver.findElement(By.id("select2-permanent_thana-container")).click();
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("ramna");
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        //ENTER applicant post code info
        driver.findElement(By.id("permanent_post_office_name")).click();
        driver.findElement(By.id("permanent_post_office_name")).sendKeys("1217");
        Thread.sleep(3000);

        //ENTER applicant post code info
        driver.findElement(By.id("permanent_post_code")).click();
        driver.findElement(By.id("permanent_post_code")).sendKeys("1217");
        driver.findElement(By.id("permanent_post_code")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        //ENTER applicant detail address info
        driver.findElement(By.id("permanent_address")).click();
        driver.findElement(By.id("permanent_address")).sendKeys("H#4, R#11, SECTION#6, MIRPUR, DHAKA-1216");
        driver.findElement(By.id("permanent_address")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        //ENTER applicant detail address info
        driver.findElement(By.id("permanent_address_bng")).click();
        driver.findElement(By.id("permanent_address_bng")).clear();
        driver.findElement(By.id("permanent_address_bng")).sendKeys("২০/২, রূপনগর আবাসিক এলাকা, ওয়ার্ড নং-২০(পার্ট)");
        driver.findElement(By.id("permanent_address_bng")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        // CLICK ON CHECKBOX Same As Permanent Address
        driver.findElement(By.cssSelector(".custom-checkbox:nth-child(1) > .custom-control-label")).click();

        JavascriptExecutor jscroll1 = (JavascriptExecutor)driver; // Scroll operation using Js Executor
        jscroll1.executeScript("window.scrollBy(0,400)"); // Scroll Down(+ve) upto browse option
        Thread.sleep(3000);

        // CLICK ON NEXT BUTTON
        driver.findElement(By.cssSelector("#enable_after_district_verification > .btn")).click();
        Thread.sleep(4000);

    }//End of Address

    /**
     * Function Name: Education
     * Description: This function will enter the applicant Permanent Address and Present Address
     * Test Case reference number: TC_12
     * Parameters: None
     *
     */
    public void Education() throws Exception{

        //Select the Education level
        //driver.findElement(By.id("select2-education_0_education_level-container")).click();


        driver.findElement(By.id("select2-education_0_education_level-container")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("ssc");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        driver.findElement(By.id("select2-education_0_exam-container")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("s.s.c");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        driver.findElement(By.id("select2-education_0_subject-container")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("science");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        driver.findElement(By.id("select2-education_0_exam_body-container")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("dhaka");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        driver.findElement(By.id("education_0_passing_year")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("education_0_passing_year")).clear();
        Thread.sleep(2000);
        driver.findElement(By.id("education_0_passing_year")).sendKeys("1992");
        Thread.sleep(3000);
        driver.findElement(By.id("education_0_passing_year")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        WebElement Element = driver.findElement(By.id("select2-education_0_result_type-container"));
        JavascriptExecutor jscroll1 = (JavascriptExecutor)driver; // Scroll operation using Js Executor
        jscroll1.executeScript("arguments[0].scrollIntoView();", Element);
        Thread.sleep(3000);

        driver.findElement(By.id("select2-education_0_result_type-container")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("1st");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);
        //End of First Education

        //Start Second Education
        driver.findElement(By.id("addEducationForm")).click();
        Thread.sleep(3000);

        WebElement Element2 = driver.findElement(By.cssSelector(".select2-selection__placeholder"));
        JavascriptExecutor jscroll2 = (JavascriptExecutor)driver; // Scroll operation using Js Executor
        jscroll2.executeScript("arguments[0].scrollIntoView();", Element2);
        Thread.sleep(3000);

        driver.findElement(By.cssSelector(".select2-selection__placeholder")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("hsc");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        driver.findElement(By.id("select2-education_1_exam-container")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("hsc");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        driver.findElement(By.id("select2-education_1_subject-container")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("science");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        driver.findElement(By.id("select2-education_1_exam_body-container")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("comilla");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        driver.findElement(By.id("education_1_passing_year")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("education_1_passing_year")).clear();
        driver.findElement(By.id("education_1_passing_year")).sendKeys("1994");
        Thread.sleep(3000);
        driver.findElement(By.id("education_1_passing_year")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        WebElement Element3 = driver.findElement(By.id("select2-education_1_result_type-container"));
        JavascriptExecutor jscroll3 = (JavascriptExecutor)driver; // Scroll operation using Js Executor
        jscroll3.executeScript("arguments[0].scrollIntoView();", Element3);
        Thread.sleep(3000);

        driver.findElement(By.id("select2-education_1_result_type-container")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("1st");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        //End of 2nd Education

        JavascriptExecutor jscroll4 = (JavascriptExecutor)driver; // Scroll operation using Js Executor
        jscroll4.executeScript("window.scrollBy(0,400)"); // Scroll Down(+ve) upto browse option
        Thread.sleep(3000);

        // CLICK ON NEXT BUTTON
        driver.findElement(By.cssSelector("#nextButtonContainer > .btn")).click();
        Thread.sleep(3000);

    }//End of Education

    /**
     * Function Name: JobExperience
     * Description: This function will enter the applicant Job Experience info
     * Test Case reference number: TC_13
     * Parameters: None
     *
     */
    public void JobExperience() throws Exception{

        //ENTER the Organization name
        driver.findElement(By.id("jobexperience_0_organization")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("jobexperience_0_organization")).sendKeys("ANX CORP");
        Thread.sleep(2000);
        driver.findElement(By.id("jobexperience_0_organization")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        //ENTER the Designation
//        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("engineer");
//        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);

        //ENTER the Job Start and End date
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("arguments[0].value = arguments[1]",
                driver.findElement(By.id("jobexperience_0_start_date")), "2018-05-03");
        Thread.sleep(3000);

        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("arguments[0].value = arguments[1]",
                driver.findElement(By.id("jobexperience_0_end_date")), "2023-12-03");
        Thread.sleep(3000);


        //ENTER JOB ADDRESS
        driver.findElement(By.id("jobexperience_0_address")).click();
        driver.findElement(By.id("jobexperience_0_address")).sendKeys("24 PALTAN DHAKA");
        driver.findElement(By.id("jobexperience_0_address")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        // CLICK ON NEXT BUTTON
        driver.findElement(By.cssSelector(".jobExpNext")).click();
        Thread.sleep(30000);

    }//End of Job Experience



}//end of WebTest
