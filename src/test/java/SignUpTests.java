import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUpTests {
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

    @BeforeMethod
    private void openRegistrationPage() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.manage().window().maximize();
    }

    @Test
    public void enterValidZipCode() throws InterruptedException {
        ZipCode zipCode = new ZipCode(driver);
        zipCode.enterAndContinue("12345");
        zipCode.checkIsAccepted("Sign up is not successful "
                + "that is incorrect because all valid data entered");
        Thread.sleep(5000);
    }

    @Test
    public void enterEmptyZipCode() throws InterruptedException {
        ZipCode zipCode = new ZipCode(driver);
        zipCode.enterAndContinue("");
        zipCode.checkIsNotAccepted("Oops, error on page. "
                + "ZIP code should have 5 digits","Sign up is successful"
                + "that is incorrect because empty code was entered");
        Thread.sleep(5000);
    }

    @Test
    public void enterMoreThanFiveCharactersZipCode() throws InterruptedException {
        ZipCode zipCode = new ZipCode(driver);
        zipCode.enterAndContinue("123456");
        zipCode.checkIsNotAccepted("Oops, error on page. "
                + "ZIP code should have 5 digits",
                "Sign up is successful "
                + "that is incorrect because more than 5 characters entered");
        Thread.sleep(5000);
    }

    @Test
    public void signUpSuccessfully() throws InterruptedException {
        SignUp signUp = new SignUp(driver);
        signUp.execute("12345", "Alex", "Smith", "12345@t.by",
                "Password1", "Password1");
        signUp.checkIsSuccessful("Sign Up failed. "
                + "It is incorrect because sign up should be successful");
        Thread.sleep(5000);
    }

    @ Test
    public void enterIncorrectConfirmationPassword()
            throws InterruptedException {
        SignUp signUp = new SignUp(driver);
        signUp.execute("12345", "Alex", "Smith", "12345@t.by",
                "Password1", "Password2");
        signUp.checkIsFailed("Sign Up is successful. It is incorrect because " +
                "confirm password is different from the original");
        Thread.sleep(5000);
    }

    @Test
    public void mandatoryDataNotEntered() throws InterruptedException {
        SignUp signUp = new SignUp(driver);
        signUp.execute("12345", "", "", "",
                "", "");
        signUp.checkIsFailed("Sign Up succeeded. It is incorrect " +
                "because no mandatory data provided");
        signUp.checkErrorMessageIsCorrect("Oops, error on page. "
                + "Some of your fields have invalid data or email was previously used");
        Thread.sleep(5000);
    }
}


