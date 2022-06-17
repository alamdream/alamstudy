package mortgage_calculator;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;

public class CalculateRealApr {
    public final By RatesLink = By.linkText("Rates");
    public final By RealAprLink = By.linkText("Real APR");
    public final By CalculatorTab = By.xpath("//label[contains(text(),'Calculator')]");
    public final By HomePriceInputField = By.name("HomeValue");
    public final By DownPaymentInDoller = By.name("DownPaymentSel");
    public final By DownPaymentInputField =  By.name("DownPayment");
    public final By InterestRateInputField = By.name("Interest");
    public final By CalculaterRateButton = By.name("calculate");
    public final By ActualAprRate = By.xpath("//tbody/tr[6]/td[2]/strong[1]");

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

    public void navigateToRealAprPage(){
        //Mouse hover to Rates link
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(RatesLink)).perform();

        // Click on Real APR link
        driver.findElement(RealAprLink).click();

        // Wait for the Page to load
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(CalculatorTab));

    }

    public void enterData(){
        // Enter Home Price "200000"
        driver.findElement(HomePriceInputField).clear();
        driver.findElement(HomePriceInputField).sendKeys("200000");

        //Click on the radio button of downpayment in Dollar
        driver.findElement(DownPaymentInDoller).click();

        // Enter Down Payment "15000"
        driver.findElement(DownPaymentInputField).clear();
        driver.findElement(DownPaymentInputField).sendKeys("15000");

        // Enter interest rate "3"
        driver.findElement(InterestRateInputField).clear();
        driver.findElement(InterestRateInputField).sendKeys("3");

    }

    @Test
    public void calculateRealApr(){
        navigateToRealAprPage();
        enterData();

        // Clik on Calculate Button
        driver.findElement(CalculaterRateButton).click();

        // Vlidate the real Apr  rate is "3.130"
        String actualRealAprRate = driver.findElement(ActualAprRate).getText();
        Assert.assertEquals(actualRealAprRate,"3.130%");
    }


    @AfterMethod
    public void closeBrowser(){

        driver.quit();
    }
}
