package com.tesvan.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class JobPage {

    WebDriver driver;

    @FindBy(css = "input#name")
    private WebElement name_input;

    @FindBy(css = "input#surname")
    private WebElement surname_input;

    @FindBy(css = "input#email")
    private WebElement email_input;

    @FindBy(css = "input#phone")
    private WebElement phone_input;

    @FindBy(css = "input#city")
    private WebElement city_input;

    @FindBy(css = "[data-next-id='form_apply']")
    private WebElement nextBtn;

    @FindBy(css = "input#education")
    private WebElement education_input;

    @FindBy(css = "input#company")
    private WebElement company_input;

    @FindBy(css = "input#course")
    private WebElement course_input;

    @FindBy(css = "input#cv")
    private WebElement cv_input;

    @FindBy(css = ".custom-btn a")
    private WebElement applyBtn;

    @FindBy(css="[class='radio_group d-flex justify-content-between'] label")
    private List<WebElement> RBtns;

    @FindBy(css="[class='radio_group d-flex justify-content-between'] input")
    private List<WebElement> RBtnsInput;

    @FindBy(css = "div.invalid-feedback")
    private List<WebElement> jFieleds;

    @FindBy(css = "[class='form-control is-valid']")
    private List<WebElement> jFieledsContent;

    @FindBy(css = "[type='submit']")
    private WebElement apply_Submit;

    @FindBy(css = "[class='job_btn job_back_btn']")
    private WebElement backBtn;

    @FindBy(css = "div.modal-content")
    private WebElement successPopUp;

    @FindBy(css = "div.success_btn>button:nth-of-type(1)")
    private WebElement okBtn;


    public JobPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public boolean issuccessPopUpVisible() {

        WebElement firstResult = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOf(successPopUp));
            return successPopUp.isDisplayed();


    }

    public boolean isNameFieldVisible() {

        return name_input.isDisplayed();

    }

    public void fillNameField(String name) {

        name_input.clear();
        name_input.sendKeys(name);

    }

    public void fillSurNameField(String surname) {

        surname_input.clear();
        surname_input.sendKeys(surname);

    }

    public void fillEmailField(String email) {

        email_input.clear();
        email_input.sendKeys(email);

    }

    public void fillPhoneField(String phoneNumber) {

        phone_input.clear();
        phone_input.sendKeys(phoneNumber);

    }

    public void fillCityField(String message) {

        city_input.clear();
        city_input.sendKeys(message);

    }

    public void clickNextBtn() {

        nextBtn.click();

    }

    public void fillEducationField(String education) {

        education_input.clear();
        education_input.sendKeys(education);

    }

    public void fillCompanyField(String company) {

        company_input.clear();
        company_input.sendKeys(company);

    }

    public void fillCourseField(String course) {

        course_input.clear();
        course_input.sendKeys(course);

    }

    public void clearAttachCVField() {

        cv_input.clear();

    }

    public void attachCV(String path) {

        cv_input.clear();
        cv_input.sendKeys(path);

    }

    public void clickApplyBtn() {

        applyBtn.click();

    }


    public void isRBtn1Clickable() {

        WebElement firstResult = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(RBtns.get(1)));

    }

    public void clickApply()  {

        apply_Submit.click();

    }

    public void clickBackBtn() {

        backBtn.click();
    }

    public void clickOkBtn() {

        okBtn.click();

    }

    public Integer getErrMessageLen() {

        return jFieleds.size();

    }

    public Integer getRBtnsLen() {

        return RBtns.size();

    }

    public void clickRBtns(Integer ind) {

        RBtns.get(ind).click();

    }

        public WebElement retRBtnsInput(Integer ind) {

        return RBtnsInput.get(ind);

    }


    public Integer getInputFieldsLen() {

        return jFieledsContent.size();

    }

    public String getErrorMes(Integer ind) {

        return jFieleds.get(ind).getText();

    }

    public String getInnerText(Integer ind) {

        return  jFieledsContent.get(ind).getAttribute("value");

        }

    }


