package com.tesvan.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {

    @FindBy(css = "li#menu-item-8>a:nth-of-type(1)")
    private WebElement requestDemo;

    @FindBy(css="li#menu-item-2>a:nth-of-type(1)")
    private WebElement jobBtn;

    public HomePage(WebDriver driver){

        String PAGE_URL = "https://www.tesvan.com/";
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);

    }


    public void clickOnContactUs(){

        requestDemo.click();
    }


    public void clickOnJobBtn()  {
        jobBtn.click();

    }

}