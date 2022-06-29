import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ShoppingCartTests {

    private WebDriver driver;

    @BeforeClass
    private void initDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterClass
    private void quitDriver() {
        driver.quit();
    }

    @Test
    public void addBookToShoppingCart() throws InterruptedException {
        LogIn login = new LogIn(driver);
        login.execute();
        ShoppingCart shoppingCart = new ShoppingCart(driver);
        shoppingCart.searchForBookAndAddIt("Adventures");
        shoppingCart.checkIsBookAddedSuccessfully();
    }
}