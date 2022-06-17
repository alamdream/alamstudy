package automation_test.mortgage_calculator;

import command_providers.ActOn;
import command_providers.AssertThat;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.DateUtils;

import java.util.concurrent.TimeUnit;

public class CalculateMonthlyPayment {
    WebDriver driver;
    private final By HomeValue = By.id("homeval");
    public final By DownPayment = By.id("downpayment");
    public final By DownPaymentInDoller = By.name("param[downpayment_type]");
    public final By LoanAmount = By.id("loanamt");
    public final By InterestRate = By.id("intrstsrate");
    public final By LoanTerm = By.id("loanterm");
    public final By StartMonth = By.name("param[start_month]");
    public final By StartYear = By.id("start_year");
    public final By PropertyTax = By.id("pptytax");
    public final By PMI = By.id("pmi");
    public final By Ins = By.id("hoi");
    public final By HOA = By.id("hoa");
    public final By LoneType = By.name("param[milserve]");
    public final By BuyOption = By.name("param[refiorbuy]");
    public final By CreditRatingDropDown = By.name("param[credit_rating]");
    public final By Calculate = By.name("cal");
    public final By TotalMonthlyPayment = By.xpath("//h3[contains(text(),'$1,611.85')]");

    @BeforeTest
    public void openBrowser(){
        String url = "https://www.mortgagecalculator.org/";
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(url);
    }
    // Enter data from mortgage calculations
    public void enterData(){
        // Enter home value "300000"
        ActOn.element(driver,HomeValue).setValue("300000");

        // Enter downpayment "60000"
        ActOn.element(driver, DownPayment).setValue("60000");

        // Clicking on the radio button $
        ActOn.element(driver, DownPaymentInDoller).click();

        // Enter the loan amount "240000"
        ActOn.element(driver, LoanAmount).setValue("240000");

        // Enter the Interest rate "3.0%"
        ActOn.element(driver,InterestRate).setValue("3");

        // Enter the loan Term 30 Year
        ActOn.element(driver,LoanTerm).setValue("30");

        // Enter Start date will be always next month
        String date = DateUtils.returnNextMonth();
        String[] dates = date.split("-");
        String month = dates[0];
        String year = dates[1];

        // Selecting value form the dropdown
        ActOn.element(driver, StartMonth).selectValue(month);

        // Enter year
        ActOn.element(driver,StartYear).setValue(year);

        //Enter Property Tax "5000"
        ActOn.element(driver, PropertyTax).setValue("5000");

        // Enter PMI "0.5"
        ActOn.element(driver,PMI).setValue("0.5");

        // Enter Home Insurance "1000"
        ActOn.element(driver,Ins).setValue("1000");

        // Enter monthly HOA "100"
        ActOn.element(driver, HOA).setValue("100");;

        // Select Loan Type "FHA"
        ActOn.element(driver,LoneType).selectValue("FHA");

        // Selecet Buy option
        ActOn.element(driver, BuyOption).selectValue("Buy");

        // Select Credit Rating "Excellent"
//        ActOn.element(driver,CreditRatingDropDown).selectValue("Excellent (720+)");

    }

    @Test
    public void validateTitle(){
        String actualTitle = ActOn.browser(driver).captureTitle();
        String expectedTitle = "Mortgage Calculator";
        Assert.assertEquals(actualTitle,expectedTitle);
    }

    @Test
    public void calculateRate(){
//        //Implicit wait
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//
//        // Entering data into the desired field
//        enterData();
//
//        //Click on alculate button
//        ActOn.element(driver, Calculate).click();
//
//        // Validating the total monthly payment is generated as per the calculater
//        AssertThat.elementAssertions(driver, TotalMonthlyPayment).elementIsDisplayed();

    }

    @AfterTest
    public void closeBrowser(){
        ActOn.browser(driver).closeBrowser();
    }
}
