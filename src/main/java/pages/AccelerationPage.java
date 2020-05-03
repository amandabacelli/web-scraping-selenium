package pages;

import config.ConfigApplication;
import converts.ExcelConverts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import static config.ConfigApplication.elementExists;
import static config.ConfigApplication.waitElement;


public class AccelerationPage {

    public AccelerationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@role='combobox']//i")
    private WebElement comboBox;

    @FindBy(xpath = "(//div[@class='dashboard__view__challenge__name'])[1]")
    private WebElement textLesson;

    HashMap<String, String> allLinksMap = new HashMap<String, String>();

    ResourceBundle bundle = ResourceBundle.getBundle("project");

    public void clickAcceleration() throws InterruptedException {
        waitElement(10, textLesson);
        ConfigApplication.getDriver().findElement(By.xpath("//div[contains(text(), '"+bundle.getString("env.acceleration")+"')]")).click();
        Thread.sleep(2000);
    }

    public void getTextLinksModule() throws InterruptedException {

        String[] module = {"Introdução", "Primeiro", "Segundo", "Terceiro", "Quarto", "Quinto", "Sexto", "Sétimo", "Oitavo", "Nono", "Décimo"};
        List<WebElement> linksModules;
        String linkText,linkHref, linkHrefFinal, linkTextFinal = null;

        for (String s : module) {
            comboBox.click();
            new WebDriverWait(ConfigApplication.getDriver(), 20)
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("((//div[@role='list'])[1]//div[@role='listitem'])//div[contains(text(), '" + s + "')]"))).click();
            Thread.sleep(2000);

            if (elementExists(textLesson, 5)) {
                linksModules = ConfigApplication.getDriver()
                        .findElements(By.xpath("//div[@class='flex shrink']/a/ancestor::div[@class='layout align-center justify-end row fill-height']//div[@class='dashboard__view__challenge__name']|//div[@class='flex shrink']/a"));
                for (WebElement text : linksModules) {
                    linkText = text.getText();
                    linkHref = text.getAttribute("href");
                    if (!linkText.equals("ACESSAR")) {
                        linkTextFinal = linkText;
                    } else if (linkHref != null) {
                        linkHrefFinal = linkHref;
                        allLinksMap.put(linkTextFinal, linkHrefFinal);
                    }
                }
            } else {
                break;
            }
        }
        Thread.sleep(2000);
    }

    public void saveToExcel() throws Exception {
        ExcelConverts excelConverts = new ExcelConverts();
        excelConverts.saveDataToExcel(allLinksMap);
    }


}

