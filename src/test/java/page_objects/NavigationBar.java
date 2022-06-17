package page_objects;

import command_providers.ActOn;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationBar {
    public WebDriver driver;
    private final By MortgageCalculatorLogo = By.xpath("//header/div[1]/div[1]/a[1]/img[1]");
    private final By RatesLink = By.linkText("Rates");
    private final By RealAprLink = By.linkText("Real APR");

    public NavigationBar (WebDriver driver){

        this.driver = driver;
    }

    // navigate to the home page
    public Home navigateToHome(){
        ActOn.element(driver, MortgageCalculatorLogo).click();
        return new Home(driver);
    }

    //Mouse hover to the rates link
    public NavigationBar mouseHoverToRates(){
        ActOn.element(driver,RatesLink).mouseHover();
        return this;
    }

    // navigate to the real APR page
    public RealApr navigateToRealApr(){
        ActOn.element(driver,RealAprLink).click();
        return new RealApr(driver);
    }
}
