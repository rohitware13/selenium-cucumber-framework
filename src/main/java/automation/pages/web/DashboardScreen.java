package automation.pages.web;

import automation.config.Base;
import automation.contract.Dashboard;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardScreen extends Base implements Dashboard {


    @FindBy(xpath = "//*[@id=\"\"]/div/form/div[3]/h3")
    public WebElement dashboardElement;

    @Inject
    public DashboardScreen(WebDriver driver) {
        super(driver);
    }


    @Override
    public boolean isDashboardDisplayed() {
        return dashboardElement.isDisplayed();
    }
}
