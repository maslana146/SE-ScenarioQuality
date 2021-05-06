package pl.put.poznan.sqc.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.sqc.model.Scenario;
import pl.put.poznan.sqc.model.Step;

import java.io.File;
import java.io.IOException;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.sqc.rest"})
public class ScenarioQualityCheckerApplication {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Scenario scenario = mapper.readValue(new File("scenario_example.json"),Scenario.class);

//Test czy wszystko działa i działa ZAAAJEBISCIE!
//        for (Step step:scenario.getSteps()){
//            System.out.println(step);
//            if (step.getSteps() != null){
//                for (Step step1: step.getSteps()){
//                    System.out.println(step1);
//                }
//            }
//        }

//        SpringApplication.run(ScenarioQualityCheckerApplication.class, args);
    }
}
