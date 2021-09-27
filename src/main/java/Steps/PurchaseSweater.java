package Steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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
        driver.get("https://prod-kurs.coderslab.pl/index.php");

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
    public void selectSweaterQuantity(){

        WebElement selectSweaterQuantity = driver.findElement(By.id("quantity_wanted"));
        selectSweaterQuantity.clear();
        selectSweaterQuantity.sendKeys("5");
    }

    @And("adds the product to cart")
    public void addProductToCart(){

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement addProductToCart = driver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button"));
        addProductToCart.click();
    }

}
