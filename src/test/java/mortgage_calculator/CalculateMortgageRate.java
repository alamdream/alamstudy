package mortgage_calculator;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CalculateMortgageRate {
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
    public final By Calculate = By.name("cal");
    public final By TotalMonthlyPayment = By.xpath("//h3[contains(text(),'$1,611.85')]");

    WebDriver driver;
    Select select;


    @BeforeMethod
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.mortgagecalculator.org/");
        driver.manage().window().maximize();


    }

    private void enterData(){
        // Enter Home value "300000"
        driver.findElement(HomeValue).clear();
        driver.findElement(HomeValue).sendKeys("300000");

        // Enter Down payment "60000"
        driver.findElement(DownPayment).clear();
        driver.findElement(DownPayment).sendKeys("60000");

        // click on the radio button $
        driver.findElement(DownPaymentInDoller).click();

        // Enter Loan Amount "240000"
        driver.findElement(LoanAmount).clear();
        driver.findElement(LoanAmount).sendKeys("240000");

        // Enter interest Rate 3%
        driver.findElement(InterestRate).clear();
        driver.findElement(InterestRate).sendKeys("3");

        // Enter Loan Term 30 Year
        driver.findElement(LoanTerm).clear();
        driver.findElement(LoanTerm).sendKeys("30");

        // Select the start date month "Dec"
        select = new Select(driver.findElement(StartMonth));
        select.selectByVisibleText("Dec");

        // Enter the year "2021"
        driver.findElement(StartYear).clear();
        driver.findElement(StartYear).sendKeys("2021");

        // Enter Property Tax "5000"
        driver.findElement(PropertyTax).clear();
        driver.findElement(PropertyTax).sendKeys("5000");

        // Enter PMI "0.5"
        driver.findElement(PMI).clear();
        driver.findElement(PMI).sendKeys("0.5");

        // Enter Home ins "1000"
        driver.findElement(Ins).clear();
        driver.findElement(Ins).sendKeys("1000");

        // Enter Monthly HOA
        driver.findElement(HOA).clear();
        driver.findElement(HOA).sendKeys("100");

        // Select Loan Type FHA
        select = new Select(driver.findElement(LoneType));
        select.selectByVisibleText("FHA");

        // Select Buy option
        select = new Select(driver.findElement(BuyOption));
        select.selectByVisibleText("Buy");


    }

    @Test
    public void calculateMonthlyPayment(){
        enterData();

        // Click on the Calculate Button
        driver.findElement(Calculate).click();

        //String expected = "1,611.85";
        //String formattedPath = "//h3[contains(text(),'$" + expected + "')]";
        // String path = String.format("//h3[contains(text(),'$")

        boolean totalMonthlyPaymentExist = driver.findElement(TotalMonthlyPayment).isDisplayed();


        // Validate that the total monthly payment is $1,611.85
        Assert.assertTrue(totalMonthlyPaymentExist);

    }

    @AfterMethod
    public void closeBrowser(){

        driver.quit();
    }


}
