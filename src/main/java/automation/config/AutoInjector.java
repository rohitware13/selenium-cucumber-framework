package automation.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AutoInjector implements InjectorSource {

    private final Logger log = LoggerFactory.getLogger(AutoInjector.class);
    @Override
    public Injector getInjector() {
        String providedPlatform = GetEnvironment.systemProperty("platform");
        log.info(String.format("Running test on '%s' platform.",providedPlatform));
        return Guice.createInjector(Stage.PRODUCTION,
                new FrameworkModule(),
                CucumberModules.createScenarioModule(),
                PlatformModule.valueOf(providedPlatform).getModule());
    }
}
