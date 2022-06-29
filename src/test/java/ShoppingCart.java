import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ShoppingCart {
    private WebDriver driver;
    ShoppingCart(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForBook(String bookTitle) {
        WebElement searchTextBox = new WebDriverWait(driver, 5)
                .until(driver -> driver.findElement(By.name("keyword")));
        searchTextBox.sendKeys(bookTitle);
        WebElement searchButton = driver.findElement(By.cssSelector("input[type = 'submit']"
                + "[value = 'Search']"));
        searchButton.click();
    }

    //It won't work if Search returned more than 1 book, but currently it is not possible.
    public void addSelectedBook() {
        WebElement addToCartButton = driver
                .findElement(By.cssSelector("img[src *= 'add_to_cart.gif']"));
        addToCartButton.click();
    }

    public void searchForBookAndAddIt(String bookTitle) {
        searchForBook(bookTitle);
        addSelectedBook();
    }

    public void checkIsBookAddedSuccessfully() {
        String actualErrorMessage = driver.findElement(By.className("confirmation_message"))
                .getText();
        Assert.assertEquals(actualErrorMessage, "Book was added to the Shopping Cart",
                "Book was not added to the shopping cart");
    }
}
