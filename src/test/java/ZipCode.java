import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ZipCode {
    private WebDriver driver;

    ZipCode(WebDriver driver)
    {
        this.driver = driver;
    }
    public void enter(String zipCode) {
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys(zipCode);
    }
    public void clickContinue() {
        WebElement continueButton = driver.findElement(By.cssSelector("[value = 'Continue']"));
        continueButton.click();
    }

    public void enterAndContinue(String zipCode) throws InterruptedException {
        enter(zipCode);
        Thread.sleep(5000);
        clickContinue();
    }
    public void checkIsAccepted(String messageIfError) {
        Assert.assertFalse(driver.findElement(By.name("zip_code")).isDisplayed(),
                messageIfError);
        Assert.assertTrue(driver.findElement(By.name("first_name")).isDisplayed(),
                messageIfError);
    }
    public void checkIsNotAccepted(String expectedErrorMessage, String messageIfError) {
        Assert.assertTrue(driver.findElement(By.name("zip_code")).isDisplayed(),
                messageIfError);
        String actualErrorMessage = driver.findElement(By.className("error_message"))
                .getText();
        Assert.assertEquals(actualErrorMessage,
                expectedErrorMessage);
    }
}
