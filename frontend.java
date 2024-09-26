package runner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SubscriptionPackageTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void validateSubscriptionPackages() {
        // Navigate to the URL
        driver.get("https://subscribe.stctv.com/sa-en");

        // Locate the subscription packages (modify selector as necessary)
        List<WebElement> packages = driver.findElements(By.cssSelector(".package")); // Adjust the selector

        for (WebElement pkg : packages) {
            String type = pkg.findElement(By.cssSelector(".type")).getText(); // Adjust the selector
            String price = pkg.findElement(By.cssSelector(".price")).getText(); // Adjust the selector
            String currency = pkg.findElement(By.cssSelector(".currency")).getText(); // Adjust the selector
            
            // Add your assertions for type, price, and currency as needed
            Assert.assertNotNull(type, "Package type should not be null");
            Assert.assertNotNull(price, "Package price should not be null");
            Assert.assertNotNull(currency, "Package currency should not be null");
            
            // Log the package details for debugging
            System.out.println("Package Type: " + type + ", Price: " + price + ", Currency: " + currency);
        }
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
