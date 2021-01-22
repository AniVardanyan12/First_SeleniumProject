package com.tesvan.tests;
import com.tesvan.pages.HomePage;
import com.tesvan.pages.RequestDemoPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class RequestDemoTests {

    WebDriver driver;
    protected static HomePage home;
    protected static RequestDemoPage request;

    @BeforeMethod
    public void before() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        home = new HomePage(driver);
        request = new RequestDemoPage(driver);
        home.clickOnContactUs();
    }

    @Test
    public void applyForContact()  {

        Assert.assertEquals(driver.getCurrentUrl(),"https://www.tesvan.com/#contact_us");

    }

    @Test
    public void allrequired()  {

        request.fillNameField("");
        request.fillEmailField("");
        request.fillPhoneField("");
        request.fillmessageField("");
        request.clickSubmitBtn();

        List<String> fildName = Arrays.asList("Name", "Email", "Phone", "Message");
        System.out.println(fildName);

        for (int i = 0; i < request.getErrMessageLen(); i++) {

            String actualValMessage = request.getErrorMes(i);
            String expectedValMessage = fildName.get(i) + " field is required";
            Assert.assertEquals(actualValMessage, expectedValMessage);

        }

    }

    @Test
    public void EmailValidation() {

        request.fillEmailField("examplegmail.com");
        request.clickSubmitBtn();
        String actualValMessage = request.getErrorMes(1);
        String expectedValMessage = "Incorrect email adress";
        Assert.assertEquals(actualValMessage, expectedValMessage);
        request.fillEmailField("example@gmailcom");
        request.clickSubmitBtn();
        Assert.assertEquals(actualValMessage, expectedValMessage);

    }

    @Test
    public void PhoneValidation()  {

        request.fillPhoneField("ex123+");
        request.clickSubmitBtn();
        String actualValMessage = request.getErrorMes(2);
        String expectedValMessage = "Incorrect phone number";
        Assert.assertEquals(actualValMessage, expectedValMessage);
        request.fillPhoneField("00--00-00-00");
        request.clickSubmitBtn();
        Assert.assertEquals(actualValMessage, expectedValMessage);
    }

    @Test
    public void ValidSubmit() {

        request.fillNameField("NameExample");
        request.fillEmailField("mailExample@gmail.com");
        request.fillPhoneField("1234-561");
        request.fillmessageField("ExampleMessage");
        request.clickSubmitBtn();
        Assert.assertTrue(request.issuccessPopUpVisible());
        request.clickOkBtn();
        Assert.assertTrue(request.isNameFieldVisible());
    }



    @AfterMethod
    public void close() {

        driver.close();
    }
}


