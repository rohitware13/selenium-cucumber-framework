package automation.config;

import java.util.Optional;

public class GetEnvironment {

    public static String systemProperty(String name){
        return Optional.ofNullable(System.getProperty(name))
                .map(String::toUpperCase)
                .orElseThrow(
                        () -> new RuntimeException("No Property")
                );
    }
}
