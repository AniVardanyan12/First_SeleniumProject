package com.tesvan.tests;
import com.tesvan.pages.HomePage;
import com.tesvan.pages.JobPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JobTests {

    WebDriver driver;
    protected static HomePage home;
    protected static JobPage job;

    @BeforeMethod
    public void before()  {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        home = new HomePage(driver);
        job = new JobPage(driver);
        home.clickOnJobBtn();
        job.clickApplyBtn();


    }

    @Test
    public void testApplyForJobPage() {

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.tesvan.com/job#job_apply");
    }

    @Test
    public void testAllRequired1()  {

        job.fillNameField("");
        job.fillSurNameField("");
        job.fillEmailField("");
        job.fillPhoneField("");
        job.fillCityField("");
        job.clickNextBtn();

        List<String> fildName = Arrays.asList("Name", "Surname", "Email", "Phone", "City");

        for (int i = 0; i < job.getErrMessageLen(); i++) {

            String actualValMessage = job.getErrorMes(i);
            String expectedValMessage = fildName.get(i) + " field is required";
            Assert.assertEquals(actualValMessage, expectedValMessage);

        }
    }

    @Test
    public void testAllRequired2()  {

        job.fillNameField("nameExample");
        job.fillSurNameField("surnameExample");
        job.fillEmailField("example@gmail.com");
        job.fillPhoneField("0000");
        job.fillCityField("cityExample");
        job.clickNextBtn();
        job.fillEducationField("");
        job.fillCompanyField("");
        job.fillCourseField("");
        job.clearAttachCVField();
        job.clickApply();

        List<String> fildName1 = Arrays.asList("", "", "", "", "", "Education", "Company", "Course", "CV");
        for (int i = 5; i < job.getErrMessageLen(); i++) {

            String actualValMessage = job.getErrorMes(i);
            String expectedValMessage = fildName1.get(i) + " field is required";
            Assert.assertEquals(actualValMessage, expectedValMessage);

        }
    }


    @Test
    public void testDataSaved()  {

        job.fillNameField("nameExample");
        job.fillSurNameField("surnameExample");
        job.fillEmailField("example@gmail.com");
        job.fillPhoneField("0000");
        job.fillCityField("cityExample");
        job.clickNextBtn();
        job.clickBackBtn();

        for (int i = 0; i < job.getInputFieldsLen(); i++) {
            String actualstate = job.getInnerText(i);
            Assert.assertFalse(actualstate.isEmpty());

        }
    }

    @Test
    public void testEnglishLevel()  {

        job.fillNameField("nameExample");
        job.fillSurNameField("surnameExample");
        job.fillEmailField("example@gmail.com");
        job.fillPhoneField("0000");
        job.fillCityField("cityExample");
        job.clickNextBtn();
        job.isRBtn1Clickable();
        for (int i = 0; i < job.getRBtnsLen(); i++) {
            for (int j = 0; j != i && j < job.getRBtnsLen(); j++) {
                job.clickRBtns(i);
                WebElement rBtnSelected = job.retRBtnsInput(i);
                WebElement rBtnNotSelected = job.retRBtnsInput(j);
                Assert.assertTrue(rBtnSelected.isSelected());
                Assert.assertFalse(rBtnNotSelected.isSelected());
            }
        }
    }

    @Test
    public void testAttachInvalidCV()  {

        job.fillNameField("nameExample");
        job.fillSurNameField("surnameExample");
        job.fillEmailField("example@gmail.com");
        job.fillPhoneField("0000");
        job.fillCityField("cityExample");
        job.clickNextBtn();
        job.fillEducationField("eduExample");
        job.fillCompanyField("compExample");
        job.fillCourseField("courseExample");
        File file = new File("src/main/resources/videoupload.mov");
        job.attachCV(file.getAbsolutePath());
        job.clickApply();
        Assert.assertFalse(job.issuccessPopUpVisible());

    }

    @Test
    public void testValidSubmit()  {
        job.fillNameField("nameExample");
        job.fillSurNameField("surnameExample");
        job.fillEmailField("example@gmail.com");
        job.fillPhoneField("0000");
        job.fillCityField("cityExample");
        job.clickNextBtn();
        job.fillEducationField("eduExample");
        job.fillCompanyField("compExample");
        job.fillCourseField("courseExample");
        File file = new File("src/main/resources/CVupload.pdf");
        job.attachCV(file.getAbsolutePath());
        job.clickApply();
        Assert.assertTrue(job.issuccessPopUpVisible());
        job.clickOkBtn();
        Assert.assertTrue(job.isNameFieldVisible());

    }

    @AfterMethod
    public void close() {

        driver.close();

    }
}

