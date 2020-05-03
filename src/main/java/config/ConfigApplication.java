package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ResourceBundle;

public class ConfigApplication {

    public static WebDriver driver = new ChromeDriver();

    public static void startApplication() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/Caskroom/chromedriver/81.0.4044.69/chromedriver");
        ResourceBundle bundle = ResourceBundle.getBundle("project");
        driver.get(bundle.getString("env.url"));
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void waitElement(int timeout, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static boolean elementExists(WebElement element, int timeout) {
        try {
            waitElement(timeout, element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
