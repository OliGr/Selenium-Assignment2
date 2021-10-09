package Steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class PurchaseSweater {

    WebDriver driver;

    @Given("user is logged in")
    public void openPrestaShop(){
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://mystore-testlab.coderslab.pl/");

        WebElement signInButton = driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a"));
        signInButton.click();
        WebElement fieldEmail = driver.findElement(By.name("email"));
        fieldEmail.sendKeys("kolok@wp.pl");
        WebElement fieldPassword = driver.findElement(By.name("password"));
        fieldPassword.sendKeys("12345");
        WebElement loginSignIn = driver.findElement(By.id("submit-login"));
        loginSignIn.click();

    }

    @When("user navigates to clothes page")
    public void openClothesPage(){

        WebElement clothesPage = driver.findElement(By.id("category-3"));
        clothesPage.click();
    }

    @And("user selects product")
    public void selectSweater(){

        WebElement selectSweater = driver.findElement(By.xpath("//*[@id=\"js-product-list\"]/div[1]/article[1]/div/a"));
        selectSweater.click();
    }

    @And("selects the right size")
    public void selectSweaterSize(){

        Select selectSweaterSize = new Select(driver.findElement(By.id("group_1")));
        selectSweaterSize.selectByVisibleText("M");
    }

    @And("selects the right quantity")
    public void selectSweaterQuantity() throws InterruptedException {

        WebElement selectSweaterQuantity = driver.findElement(By.id("quantity_wanted"));
        selectSweaterQuantity.clear();
        selectSweaterQuantity.sendKeys("5");
        Thread.sleep(1000);
    }

    @And("adds the product to cart")
    public void addProductToCart(){

        WebElement addProductToCart = driver.findElement(By.cssSelector("#add-to-cart-or-refresh > div.product-add-to-cart > div > div.add > button"));
        addProductToCart.click();
    }

    @And("proceeds to checkout")
    public void proceedToCheckout(){

        WebElement proceedToCheckout = driver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a"));
        proceedToCheckout.click();
        WebElement proceedToCheckoutAgain = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a"));
        proceedToCheckoutAgain.click();
    }

    @And("confirms address")
    public void confirmAddress(){

        WebElement confirmAddress = driver.findElement(By.name("confirm-addresses"));
        confirmAddress.click();
    }

    @And("selects delivery method")
    public void selectDelivery(){

        WebElement deliveryMethod = driver.findElement(By.name("confirmDeliveryOption"));
        deliveryMethod.click();
    }

    @And("selects payment method and orders with an obligation to pay")
    public void selectPayment(){

        WebElement paymentMethod = driver.findElement(By.id("payment-option-1"));
        paymentMethod.click();
        WebElement termsAndConditions = driver.findElement(By.name("conditions_to_approve[terms-and-conditions]"));
        termsAndConditions.click();
        WebElement orderWithObligationToPay = driver.findElement(By.xpath("//*[@id=\"payment-confirmation\"]/div[1]/button"));
        orderWithObligationToPay.click();

    }

    @Then("user takes a screenshot to confirm the purchase")
    public void takeScreenshot(){
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

    }

}
