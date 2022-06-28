import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class EnterZipCode {
    private WebDriver driver;

    EnterZipCode(WebDriver driver)
    {
        this.driver = driver;
    }
    public void enterZipCode(String zipCode) {
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys(zipCode);
    }
    public void clickContinue() {
        WebElement continueButton = driver.findElement(By.cssSelector("[value = 'Continue']"));
        continueButton.click();
    }

    public void enterZipCodeAndContinue(String zipCode) throws InterruptedException {
        enterZipCode(zipCode);
        Thread.sleep(5000);
        clickContinue();
    }
    public void checkIsSystemAcceptedEnteredZipCode(String messageIfError) {
        Assert.assertFalse(driver.findElement(By.name("zip_code")).isDisplayed(),
                messageIfError);
        Assert.assertTrue(driver.findElement(By.name("first_name")).isDisplayed(),
                messageIfError);
    }
    public void checkIsSystemNotAcceptedEnteredZipCode(String messageIfError) {
        Assert.assertTrue(driver.findElement(By.name("zip_code")).isDisplayed(),
                messageIfError);
        String actualErrorMessage = driver.findElement(By.className("error_message"))
                .getText();
        Assert.assertEquals(actualErrorMessage,
                "Oops, error on page. ZIP code should have 5 digits");
    }
}
