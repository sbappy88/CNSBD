package com;

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
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

import util.InitData;
import util.Logger;
import util.MainInitData;
import util.ScriptsMgmt;
import configuration.LoginlInitData;

public class WebTest {


    public static WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    protected static LoginlInitData loginData = null;
    private MainInitData loginInitdata;
    private ScriptsMgmt scriptMgmt;
    private Logger logger;

    //public static String[] requisitionDOCNO =null;

    @Before
    public void setUp() {
	System.setProperty("webdriver.gecko.driver","C:\\geckodriver.exe");
        WebDriverManager.firefoxdriver().setup();
	driver = new FirefoxDriver();
	js = (JavascriptExecutor) driver;
	vars = new HashMap<String, Object>();

        //Please uncomment if wants to test in Chrome browser
        // System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();

        driver.manage().window().maximize();
	    
        //Test case id: TC_01 verify Open the web aplication
	String url= "http://123.200.20.20:5302/";
	driver.get(url);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void main () throws Exception{

        try {

            // Initialize data
            MainInitData.preCheckExec();
            loginInitdata = MainInitData.getInstance();
            LoginlInitData.preCheckExec();
            loginData = LoginlInitData.getInstance();
            scriptMgmt = ScriptsMgmt.getInstance();
            logger = Logger.getInstance();
            InitData.setScriptName("com.WebTest");
            scriptMgmt.logHeader(InitData.scriptName);

            // Execute all test case Sequentially
            CircularSearch(); // Test Case ID: TC_02
	    JobApplyNow(); // Test Case ID: TC_03, TC_06, TC_07 and TC_08
	    BasicInformation(); // Test Case ID: TC_09, TC_10
	    Address(); // Test Case ID: TC_11
	    Education(); // Test Case ID: TC_12
	    JobExperience(); // Test Case ID: TC_13
	    Certificate(); // Test Case ID: TC_14
            Complete(); // Test Case ID: TC_15

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
    driver.findElement(By.cssSelector("#circularMst_filter .form-control")).sendKeys("14/2023");
    driver.findElement(By.cssSelector("#circularMst_filter .form-control")).sendKeys(Keys.ENTER);
    Assert.assertTrue(driver.getCurrentUrl().contains("14/2023"));

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
    driver.findElement(By.linkText("14/2023")).click();
	  
    //Switch between browser tabs using Selenium WebDriver
    driver.getWindowHandles().forEach(tab->driver.switchTo().window(tab));
    assertTrue(driver.findElement(By.id("circularDtl")).isDisplayed());

   //Click on the Action icon
    driver.findElement(By.cssSelector(".bx-show")).click();
    //Click on the Apply Now button
    driver.findElement(By.cssSelector("strong")).click();
    //Click on the Continue without Login button
    driver.findElement(By.linkText("Continue without Login")).click();

  }//End of JobApplyNow


  /**
       * Function Name: BasicInformation
       * Description: This function will enter the applicant basic information like NID, DOB, Mobile no etc.
       * Test Case reference number: TC_09, TC_10
       * Parameters: None
       *
  */
  public void BasicInformation() throws Exception{
    // ENTER National ID
    driver.findElement(By.id("national_id")).click();
    driver.findElement(By.id("national_id")).sendKeys("8231771135");
    driver.findElement(By.id("national_id")).sendKeys(Keys.ENTER);

    // NID FILE UPLOADING ....
    driver.findElement(By.id("national_id_attachment")).click();
    driver.findElement(By.id("national_id_attachment")).sendKeys("C:\\WebTest\\image\\NID.jpg");

    // ENTER Date of Birth
    driver.findElement(By.id("date_of_birth")).sendKeys("23-08-1995");
    driver.findElement(By.id("date_of_birth")).sendKeys(Keys.ENTER);

    // CLICK ON VERIFY NID BUTTON
    driver.findElement(By.id("nid_verification")).click();
    Thread.sleep(3000);

    JavascriptExecutor js = (JavascriptExecutor)d; //Scrolling using JavascriptExecutor
    js.executeScript("window.scrollBy(0,380)");//Scroll Down to file upload button
    Thread.sleep(3000);

    //ENTER Father Name
    driver.findElement(By.id("father_name")).click();
    driver.findElement(By.id("father_name")).sendKeys("Father Name");
    //ENTER Mother Name
    driver.findElement(By.id("mother_name")).click();
    driver.findElement(By.id("mother_name")).sendKeys("Mother Name");

    driver.findElement(By.id("mobile")).click();
    driver.findElement(By.id("mobile")).sendKeys("01911111111");

    //assertThat(driver.switchTo().alert().getText(), is("Your One-Time Password is 5078. Please enter the password to login."));
    //Trim OTP info from the alert box
    String otp = driver.switchTo().alert().getText();
    String otp = s.substring(27,31);
    Alert alert = driver.switchTo().alert();
	alert.accept();
	//Enter OTP value for match
	driver.findElement(By.id("otp")).click();
	driver.findElement(By.id("otp")).sendKeys(otp);
	Alert alert2 = driver.switchTo().alert();
	alert2.accept();
	//assertThat(driver.switchTo().alert().getText(), is("OTP Match"));

	//Enter email info
	driver.findElement(By.id("email")).click();
	driver.findElement(By.id("email")).sendKeys("TEST@TEST.COM");
    driver.findElement(By.id("email")).sendKeys(Keys.ENTER);

    //Select religion from the list
    driver.findElement(By.id("religion")).click();
	{
	      WebElement dropdown = driver.findElement(By.id("religion"));
	      dropdown.findElement(By.xpath("//option[. = 'ISLAM']")).click();
	}
    driver.findElement(By.cssSelector("#religion > option:nth-child(2)")).click();
    Thread.sleep(3000);

    //Upload photo
    driver.findElement(By.id("photo")).click();
	driver.findElement(By.id("photo")).sendKeys("C:\\WebTest\\image\\Photo.jpg");
	Thread.sleep(3000);
	//Upload signature
	driver.findElement(By.id("signature")).click();
    driver.findElement(By.id("signature")).sendKeys("C:\\WebTest\\image\\Signature.png");
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

	//ENTER applicant distric info
	driver.findElement(By.id("permanent_district")).click();
	{
	      WebElement dropdown = driver.findElement(By.id("permanent_district"));
	      dropdown.findElement(By.xpath("//option[. = 'DHAKA']")).click();
	}

	//select district from the list
	driver.findElement(By.cssSelector("#permanent_district > option:nth-child(2)")).click();
	{
	      WebElement element = driver.findElement(By.linkText("3"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	}

	//ENTER applicant Thana/Upazila info
	driver.findElement(By.id("select2-permanent_thana-container")).click();
	driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("ramna");
    driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);

	//ENTER applicant post code info
	driver.findElement(By.id("permanent_post_office_name")).click();
    driver.findElement(By.id("permanent_post_office_name")).sendKeys("1217");

    //ENTER applicant post code info
    driver.findElement(By.id("permanent_post_code")).click();
	driver.findElement(By.id("permanent_post_code")).sendKeys("1217");
    driver.findElement(By.id("permanent_post_code")).sendKeys(Keys.ENTER);

    //ENTER applicant detail address info
    driver.findElement(By.id("permanent_address")).click();
	driver.findElement(By.id("permanent_address")).sendKeys("H#4, R#11, SECTION#6, MIRPUR, DHAKA-1216");
    driver.findElement(By.id("permanent_address")).sendKeys(Keys.ENTER);

    // CLICK ON CHECKBOX Same As Permanent Address
    driver.findElement(By.cssSelector(".custom-checkbox:nth-child(1) > .custom-control-label")).click();

    // CLICK ON NEXT BUTTON
    driver.findElement(By.cssSelector("#enable_after_district_verification > .btn")).click();
    Thread.sleep(3000);

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
      driver.findElement(By.id("education_0_exam")).click();
      {
        WebElement dropdown = driver.findElement(By.id("education_0_exam"));
        dropdown.findElement(By.xpath("//option[. = 'HSC']")).click();
      }
      driver.findElement(By.cssSelector("#education_0_exam > option:nth-child(2)")).click();

      //Select the subject
      driver.findElement(By.id("select2-education_0_subject-container")).click();
	  driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("science");
      driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);

      //Select the Institution/Board
      driver.findElement(By.id("select2-education_0_exam_body-container")).click();
	  driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("comilla");
      driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);

      //Select the Passing Year
      driver.findElement(By.id("education_0_passing_year")).click();
      driver.findElement(By.id("education_0_passing_year")).clear();
	  driver.findElement(By.id("education_0_passing_year")).sendKeys("1992");
      driver.findElement(By.id("education_0_passing_year")).sendKeys(Keys.ENTER);

      //Select the Result type
      driver.findElement(By.id("select2-education_0_result_type-container")).click();
	  driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("gpa");
      driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);

      //Select the Passing result number
      driver.findElement(By.id("education_0_result")).click();
	  driver.findElement(By.id("education_0_result")).sendKeys("4.5");
      driver.findElement(By.id("education_0_result")).sendKeys(Keys.ENTER);

      //Enter the 2nd education level from here
      driver.findElement(By.id("education_1_exam")).click();
	  {
	        WebElement dropdown = driver.findElement(By.id("education_1_exam"));
	        dropdown.findElement(By.xpath("//option[. = 'SSC']")).click();
	  }
     driver.findElement(By.cssSelector("#education_1_exam > option:nth-child(2)")).click();

	 //Select the subject
	 driver.findElement(By.id("select2-education_1_subject-container")).click();
	 driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("science");
     driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);

    //Select the Institution/Board
    driver.findElement(By.cssSelector(".select2-container--open b")).click();
    driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("dhaka");
    driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);

    //Select the Passing Year
    driver.findElement(By.id("education_1_passing_year")).click();
    driver.findElement(By.id("education_1_passing_year")).clear();
    driver.findElement(By.id("education_1_passing_year")).sendKeys("1990");
    driver.findElement(By.id("education_1_passing_year")).sendKeys(Keys.ENTER);

    //Select the Passing result number
    driver.findElement(By.id("select2-education_1_result_type-container")).click();
	driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("gpa");
    driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);
    driver.findElement(By.id("education_1_result")).click();
	driver.findElement(By.id("education_1_result")).sendKeys("5");
    driver.findElement(By.id("education_1_result")).sendKeys(Keys.ENTER);

    // CLICK ON NEXT BUTTON
    driver.findElement(By.cssSelector("#nextButtonContainer > .btn")).click();
    Thread.sleep(3000);


  }//End Education



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
	driver.findElement(By.id("jobexperience_0_organization")).sendKeys("ERA INFOTECH LTD");
	driver.findElement(By.cssSelector(".select2-selection__placeholder")).click();

	//ENTER the Designation
	driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("engineer");
    driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);

    //ENTER the Job Start and End date
    driver.findElement(By.id("jobexperience_0_start_date")).click();
    driver.findElement(By.id("jobexperience_0_start_date")).sendKeys("2018-05-03");
    driver.findElement(By.id("jobexperience_0_start_date")).sendKeys(Keys.ENTER);
    driver.findElement(By.id("jobexperience_0_end_date")).click();
    driver.findElement(By.id("jobexperience_0_end_date")).sendKeys("2023-12-31");
    driver.findElement(By.id("jobexperience_0_end_date")).sendKeys(Keys.ENTER);

    //ENTER JOB ADDRESS
    driver.findElement(By.id("jobexperience_0_address")).click();
	driver.findElement(By.id("jobexperience_0_address")).sendKeys("24 PALTAN DHAKA");
    driver.findElement(By.id("jobexperience_0_address")).sendKeys(Keys.ENTER);

    // CLICK ON NEXT BUTTON
    driver.findElement(By.cssSelector(".jobExpNext")).click();

  }//End of Job Experience



  /**
      * Function Name: Certificate
      * Description: This function will enter the applicant Certificate info
      * Test Case reference number: TC_14
      * Parameters: None
      *
  */
  public void Certificate() throws Exception{

    //Select course from list
    driver.findElement(By.id("select2-training_0_course-container")).click();
    driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("DIPLOMA");
    driver.findElement(By.cssSelector(".select2-search__field")).sendKeys(Keys.ENTER);

    //Enter course duration
    driver.findElement(By.id("training_0_duration")).click();
    driver.findElement(By.id("training_0_duration")).sendKeys("2");
    driver.findElement(By.id("training_0_duration")).sendKeys(Keys.ENTER);

    //Select course duration (year/month) from list
    driver.findElement(By.id("training_0_type")).click();
    {
      WebElement dropdown = driver.findElement(By.id("training_0_type"));
      dropdown.findElement(By.xpath("//option[. = 'YEAR']")).click();
    }

   // CLICK ON NEXT BUTTON
   driver.findElement(By.cssSelector(".trainingExpNext")).click();


  }//END OF Certificate


  /**
      * Function Name: Complete
      * Description: This function will complete the applicant job application
      * Test Case reference number: TC_15
      * Parameters: None
      *
  */
  public void Complete() throws Exception{
	  // SELECT Declaration CHECKBOX
	  driver.findElement(By.cssSelector(".custom-checkbox:nth-child(2) > .custom-control-label")).click();

	  // CLICK ON COMPLETE BUTTON
	  driver.findElement(By.name("completeBtn")).click();

	  // CLICK ON CONFIRM BUTTON
	  driver.findElement(By.cssSelector(".swal2-confirm")).click();

	  //THIS IS PATH : /html/body/div[4]/div[2]/div/div[2]/div[1]/div/strong
	  assertThat(driver.findElement(By.cssSelector(".alert-static > strong")).getText(), is("Your login info is:\\\\nMobile:01911111111 Password: ip1rm3CD. Please save it and use in future.\\\\nAn E-mail and SMS should sent to your given e-mail and mobile number with same login credentials! Please check your e-mail spam box too!"));


  }//End of Complete



}//END
