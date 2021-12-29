package steps;

import automation.contract.Dashboard;
import automation.contract.Login;
import com.google.inject.Inject;
import com.typesafe.config.Config;
import io.cucumber.java8.En;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;


public class LoginSteps implements En {

    @Inject
    public LoginSteps(Login login, Dashboard dashboard, WebDriver driver, Config config) {

        Given("^the application is opened in browser$", () -> {
            driver.get(config.getString("url"));
        });

        When("^the user enters valid login credentials$", () -> login.loginApp());

        Then("^the user should redirect to dashboard$", () -> {
            Assert.assertEquals("Error in Login Functionality", true, dashboard.isDashboardDisplayed());
        });


    }
}
