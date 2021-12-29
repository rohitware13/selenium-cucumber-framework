package automation.config;

import automation.contract.Dashboard;
import automation.contract.Login;

import automation.pages.web.DashboardScreen;
import automation.pages.web.LoginScreen;
import com.google.inject.AbstractModule;


public class WebModule extends AbstractModule {

    @Override
    protected void configure() {
      bind(Login.class).to(LoginScreen.class);
      bind(Dashboard.class).to(DashboardScreen.class);
    }
}
