package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ResourceBundle;

import static config.ConfigApplication.waitElement;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@type='submit']/div")
    private WebElement buttonEnter;

    @FindBy(xpath = "//nav//a[@href='/dashboard']")
    private WebElement buttonMyAccelerations;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;


    public void login() {
        ResourceBundle bundle = ResourceBundle.getBundle("project");
        emailField.sendKeys(bundle.getString("env.email"));
        passwordField.sendKeys(bundle.getString("env.password"));
        buttonEnter.click();
        waitElement(10, buttonMyAccelerations);
        buttonMyAccelerations.click();
    }
}
