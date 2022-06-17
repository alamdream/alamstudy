package automation_test.mortgage_calculator;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page_objects.Home;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CalculateApr {
    WebDriver driver;
    public final By RatesLink = By.linkText("Rates");
    public final By RealAprLink = By.linkText("Real APR");
    public final By RealAprHeader = By.xpath("//label[contains(text(),'Calculator')]");
    public final By HomePriceInputField = By.name("HomeValue");
    public final By DownPaymentInDoller = By.name("DownPaymentSel");
    public final By DownPaymentInputField =  By.name("DownPayment");
    public final By InterestRateInputField = By.name("Interest");
    public final By CalculaterRateButton = By.name("calculate");
    public final By ActualAprRate = By.xpath("//tbody/tr[6]/td[2]/strong[1]");

    @BeforeTest
    public void openBrowser(){
        String url = "https://www.mortgagecalculator.org/";
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(url);
    }

    public void navigateToRatesPage(){
        // Mouse Hover to the rates link
        ActOn.element(driver,RatesLink).mouseHover();

        // Click on Real Apr link
        ActOn.element(driver,RealAprLink).click();

    }

    @Test
    public void calculateRealApr(){
//        navigateToRatesPage();
//
//        // Wait until the locator is visible in the page
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(RealAprHeader));
//
//        // Set timeout 15 seconds on every single test steps to be completed.
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//
//        ActOn.element(driver,HomePriceInputField).setValue("200000");
//
//        ActOn.element(driver, DownPaymentInputField).setValue("15000");
//        ActOn.element(driver, DownPaymentInDoller).click();
//
//        ActOn.element(driver,InterestRateInputField).setValue("3");
//        ActOn.element(driver,CalculaterRateButton).click();
//
//        String aprRate = ActOn.element(driver,ActualAprRate).getText();


        // using POM
        new Home(driver)
                .mouseHoverToRates()
                .navigateToRealApr()
                .waitForPageToLoad()
                .enterHomeValue("200000")
                .typeDownPayment("15000")
                .clickDownPaymentInDollar()
                .typeInterestRate("3")
                .clickOnCalculateButton()
                .validateAprRate("3.130%");
    }

    @AfterTest
    public void closeBrowser (){

        ActOn.browser(driver).closeBrowser();
    }

}
