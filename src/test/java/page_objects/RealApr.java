package page_objects;

import command_providers.ActOn;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RealApr extends NavigationBar {
    public final By CalculatorTab = By.xpath("//label[contains(text(),'Calculator')]");
    public final By HomePriceInputField = By.name("HomeValue");
    public final By DownPaymentInDoller = By.name("DownPaymentSel");
    public final By DownPaymentInputField = By.name("DownPayment");
    public final By InterestRateInputField = By.name("Interest");
    public final By CalculaterRateButton = By.name("calculate");
    public final By ActualAprRate = By.xpath("//tbody/tr[6]/td[2]/strong[1]");

    public RealApr(WebDriver driver) {
        super(driver);
    }
    // wait for the element to be visible
    public RealApr waitForPageToLoad() {
        ActOn.wait(driver, CalculatorTab).waitForElementToBeVisible();
        return this;
    }
    // enter home value
    public RealApr enterHomeValue(String value) {
        ActOn.element(driver, HomePriceInputField).setValue(value);
        return this;
    }
    // enter DownPayment
    public RealApr typeDownPayment(String value) {
        ActOn.element(driver, DownPaymentInputField).setValue(value);
        return this;
    }
    // enter DownPayment
    public RealApr clickDownPaymentInDollar() {
        ActOn.element(driver, DownPaymentInDoller).click();
        return this;
    }
    // enter Interest  rate
    public RealApr typeInterestRate(String value) {
        ActOn.element(driver, InterestRateInputField).setValue(value);
        return this;
    }
    // eClick on interest button
    public RealApr clickOnCalculateButton() {
        ActOn.element(driver, CalculaterRateButton).click();
        return this;
    }
    //Validate the APR
    public RealApr validateAprRate(String expectedValue){
        String aprRate = ActOn.element(driver,ActualAprRate).getText();
        Assert.assertEquals(aprRate,expectedValue);
        return this;
    }

}