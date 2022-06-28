import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SignUp {
    private WebDriver driver;
    SignUp(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String firstName) {
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        WebElement passwordInput = driver.findElement(By.name("password2"));
        passwordInput.sendKeys(password);
    }

    public void clickRegister() {
        WebElement buttonRegistered = driver.findElement(
                By.cssSelector("[value = 'Register']"));
        buttonRegistered.click();
    }

    public void checkIsSuccessful(String messageIfError) {
        Assert.assertTrue(driver.findElement(By.className("confirmation_message")).isDisplayed(),
                messageIfError);
    }

    public void checkIsFailed(String assertMessageIfError) {
        Assert.assertFalse(driver.findElements(By.name("first_name")).isEmpty(),
                assertMessageIfError);
    }

    public void checkErrorMessageIsCorrect(String expectedErrorMessage) {
        String actualErrorMessage = driver.findElement(By.className("error_message"))
                .getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }
    public void execute(String zipCode, String firstName, String lastName,
                        String email, String password, String confirmPassword)
            throws InterruptedException {
        EnterZipCode enterZipCode = new EnterZipCode(driver);
        enterZipCode.enterZipCodeAndContinue(zipCode);
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        clickRegister();
    }
}
