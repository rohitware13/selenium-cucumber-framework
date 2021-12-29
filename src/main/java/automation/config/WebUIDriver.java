package automation.config;

import com.google.inject.Inject;
import com.typesafe.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.concurrent.TimeUnit;

public class WebUIDriver extends WebModule{

    private static WebDriver driver;
    private final Config config;
    private final Logger log = LoggerFactory.getLogger(WebUIDriver.class);


    @Inject
    public WebUIDriver(Config config) {
        this.config = config;
    }

    public  WebDriver getWebDriverForBrowser(String browser)  {
        switch(browser.toLowerCase()){
            case "chrome":
                log.info("Starting Execution on Chrome Browser");
                System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                log.info("Starting Execution on Firefox Browser");

                driver = new FirefoxDriver();
                break;
            case "headless":
                log.info("Starting Execution on Chrome Browser in headless mode");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                options.addArguments("window-size=1200x600");
                driver = new ChromeDriver(options);
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

}
