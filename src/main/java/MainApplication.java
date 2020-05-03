import config.ConfigApplication;
import pages.AccelerationPage;
import pages.LoginPage;

public class MainApplication {

    public static void main(String[] args) throws Exception {

        ConfigApplication.startApplication();

        LoginPage loginPage = new LoginPage(ConfigApplication.getDriver());
        loginPage.login();

        AccelerationPage accelerationPage = new AccelerationPage(ConfigApplication.getDriver());
        accelerationPage.clickAcceleration();
        accelerationPage.getTextLinksModule();
        accelerationPage.saveToExcel();
        ConfigApplication.getDriver().quit();

    }
}
