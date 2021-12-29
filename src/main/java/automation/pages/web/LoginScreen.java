package automation.pages.web;

import automation.config.Base;
import automation.contract.Login;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginScreen extends Base implements Login {

    private final Config config;

    @FindBy(id = "user-name")
    public WebElement userName;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @Inject
    public  LoginScreen(WebDriver driver, Config config){
        super(driver);
        this.config = config;
    }

    @Override
    public void loginApp() {
        enterUsername();
        enterPassword();
        clickOnLogin();
    }

    @Override
    public void enterUsername() {
        userName.sendKeys(config.getString("username"));
    }

    @Override
    public void enterPassword() {
        password.sendKeys(config.getString("password"));
    }

    @Override
    public void clickOnLogin() {
        loginButton.click();
    }
}
