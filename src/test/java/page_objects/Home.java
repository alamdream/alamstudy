package page_objects;

import command_providers.ActOn;
import command_providers.AssertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home extends NavigationBar{
    private final By HomeValue = By.id("homeval");
    public final By DownPayment = By.id("downpayment");
    public final By DownPaymentInDollar = By.name("param[downpayment_type]");
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
//    public final By TotalMonthlyPayment = By.xpath("//h3[contains(text(),'$1,611.85')]");

    public Home(WebDriver driver) {
        super(driver);
    }

    public Home typeHomePrice(String value){
        ActOn.element(driver , HomeValue).setValue(value);
        return this;
    }
    public Home downPayment(String value){
        ActOn.element(driver , DownPayment).setValue(value);
        return this;
    }
    public Home downPaymentinDollar (String value){
        ActOn.element(driver , DownPaymentInDollar).setValue(value);
        return this;
    }
    public Home loanAmount(String value){
        ActOn.element(driver , LoanAmount).setValue(value);
        return this;
    }
    public Home interestRate(String value){
        ActOn.element(driver , InterestRate).setValue(value);
        return this;
    }
    public Home loanTerm(String value){
        ActOn.element(driver , LoanTerm).setValue(value);
        return this;
    }
    public Home selectMonth(String value){
        ActOn.element(driver , StartMonth).selectValue(value);
        return this;
    }
    public Home selectYear(String value){
        ActOn.element(driver , StartYear).selectValue(value);
        return this;
    }
    public Home propertyTax(String value){
        ActOn.element(driver , PropertyTax).setValue(value);
        return this;
    }
    public Home PMI(String value){
        ActOn.element(driver , PMI).setValue(value);
        return this;
    }
    public Home typeInsurance(String value){
        ActOn.element(driver , Ins).setValue(value);
        return this;
    }
    public Home typeHOA(String value){
        ActOn.element(driver , HOA).setValue(value);
        return this;
    }
    public Home selectLoanType(String value){
        ActOn.element(driver , LoneType).selectValue(value);
        return this;
    }
    public Home selectBuyOption(String value){
        ActOn.element(driver , BuyOption).selectValue(value);
        return this;
    }
    public Home clickCalculate(){
        ActOn.element(driver , Calculate).click();
        return this;
    }
    public Home validateMonthlyPayment(String totalMonthlyPayment){
        By monthlyPaymentLocator = By.xpath("//h3[contains(text(),'$" + totalMonthlyPayment+ "')]");
        AssertThat.elementAssertions(driver,monthlyPaymentLocator).elementIsDisplayed();
        return this;
    }

}
