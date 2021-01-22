package com.tesvan.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class RequestDemoPage {
    WebDriver driver;

    @FindBy(css = "input#name")
    private WebElement name_input;

    @FindBy(css = "input#email" )
    private WebElement email_input;

    @FindBy(css = "input#phone")
    private WebElement phone_input;

    @FindBy(css = "textarea#message")
    private WebElement message_input;

    @FindBy(css = "button[type=submit]")
    private WebElement submit_btn;

    @FindBy(css = "div.invalid-feedback")
    private List<WebElement> RDfieleds;

    @FindBy(css = "div#emailStatus")
    private  WebElement invalidEmail;

    @FindBy(css = "div#phoneStatus")
    private  WebElement invalidPhone;

    @FindBy(css = "div.modal-content")
    private WebElement successPopUp;

    @FindBy(css = "div.success_btn>button:nth-of-type(1)")
    private WebElement okBtn;


    public RequestDemoPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isNameFieldVisible(){

        return name_input.isDisplayed();
    }

    public boolean issuccessPopUpVisible(){

        WebElement firstResult = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOf(successPopUp));
        return successPopUp.isDisplayed();

    }

    public void fillNameField(String text) {

        name_input.clear();
        name_input.sendKeys(text);

    }

    public void fillEmailField(String email) {

        email_input.clear();
        email_input.sendKeys(email);

    }

    public void fillPhoneField(String phoneNumber) {

        phone_input.clear();
        phone_input.sendKeys(phoneNumber);

    }

    public void fillmessageField(String message) {

        message_input.clear();
        message_input.sendKeys(message);

    }


    public void clickSubmitBtn(){

        submit_btn.click();

    }

    public Integer getErrMessageLen() {

        return RDfieleds.size();

    }

    public String getErrorMes(Integer ind) {

        return RDfieleds.get(ind).getText();

    }

    public void clickOkBtn() {

        okBtn.click();

    }
}



