package automation.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.reporter.ExtentAventReporter;
import com.google.inject.Inject;
import com.typesafe.config.Config;

import io.cucumber.core.api.Scenario;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Base64;


public class Hooks {

    private final WebDriver driver;
    private final Config config;
    private final Logger log = LoggerFactory.getLogger(Hooks.class);

    @Inject
    public Hooks(WebDriver driver, Config config){
        this.driver = driver;
        this.config = config;
    }

    @Before
    public void startExecution(){
        log.info("Start of Execution");
    }

    @After
    public void getScreenshot(Scenario scenario) throws IOException {

        if(scenario.isFailed()){
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            try {
                //This takes a screenshot from the driver at save it to the specified location
                File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                //Copy taken screenshot from source location to destination location

                File destinationPath = new File(System.getProperty("user.dir") + "/test-output/screenshots/" + screenshotName + ".png");
                FileUtils.copyFile(sourcePath, destinationPath);

                scenario.embed(FileUtils.readFileToByteArray(destinationPath),"image/png");
                String screenshot_scenario = "data:image/png;base64,"+ Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(destinationPath));

                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(screenshot_scenario);
            } catch (IOException   e) {
            }
        }

        log.info("End of Execution.");
        driver.quit();
    }
}
