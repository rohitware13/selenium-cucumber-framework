package automation.config;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.typesafe.config.Config;

import org.openqa.selenium.WebDriver;


public class WebDriverModule implements Provider<WebDriver> {

    private final String browser = System.getProperty("browser");
    private static  WebDriver driver;
    private  final Config config;

    @Inject
    public WebDriverModule(Config config){
        this.config = config;
    }

    @Override
    @Provides
    @Singleton
    public WebDriver get(){
        driver = new WebUIDriver(config).getWebDriverForBrowser(browser);
        return driver;
    }
}
