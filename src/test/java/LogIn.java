import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class LogIn {
    private WebDriver driver;
    LogIn(WebDriver driver) {
        this.driver = driver;
    }

    public void execute() {
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        driver.findElement(By.linkText("Test Portal")).click();
        driver.findElement(By.linkText("Account Creator")).click();
        driver.findElement(By.
                        cssSelector("input[value = 'Create new user account'][type = 'submit']"))
                .click();
        driver.findElement(By.
                        cssSelector("input[value = 'Auto Login'][type = 'submit']"))
                .click();
    }
}