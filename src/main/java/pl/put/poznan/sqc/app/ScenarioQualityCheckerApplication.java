package pl.put.poznan.sqc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.sqc.rest"})
public class ScenarioQualityCheckerApplication {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(ScenarioQualityCheckerApplication.class, args);

    }
}
