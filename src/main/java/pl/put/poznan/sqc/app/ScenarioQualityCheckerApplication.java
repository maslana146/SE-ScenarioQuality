package pl.put.poznan.sqc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
/**
 * Main class, which run spring application.
 * @author      Huber Radom
 * @version     2.0
 * @since       v1.0-alpha
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.sqc.rest"})
public class ScenarioQualityCheckerApplication {
    /**
     * Main function, which run spring application
     * @param args          some standard argument
     * @throws IOException  standard exception
     */
    public static void main(String[] args) throws IOException {

        SpringApplication.run(ScenarioQualityCheckerApplication.class, args);

    }
}
