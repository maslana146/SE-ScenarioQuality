package pl.put.poznan.sqc.test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.testng.annotations.Test;
import pl.put.poznan.sqc.logic.ScenarioObjectBuilder;
import pl.put.poznan.sqc.logic.analysis.*;
import pl.put.poznan.sqc.model.Scenario;

import javax.swing.plaf.basic.BasicLabelUI;

import static org.junit.Assert.*;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


@SpringBootTest
public class logicAnalysisTest {
    Scenario scenario = getScenario();
    ArrayList keywords = new ArrayList<>(Arrays.asList("Librarian", "System"));

    @Autowired
    StepsCounter stepsCounter = new StepsCounter();
    KeywordsCounter keywordsCounter = new KeywordsCounter(keywords);
    StepsWithoutActorFinder stepsWithoutActorFinder = new StepsWithoutActorFinder();
    TextWithStepNumbersDownloader textWithStepNumbersDownloader = new TextWithStepNumbersDownloader();
    ScenarioObjectBuilder scenarioObjectBuilder = new ScenarioObjectBuilder();


    public logicAnalysisTest() throws IOException {
    }

    private Scenario getScenario() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Scenario scenario = mapper.readValue(new File("scenario_example.json"), Scenario.class);
        return scenario;
    }

    @Test
    public void stepsCounterTest() throws IOException {
        String result = stepsCounter.visitScenario(scenario);
        assertThat("stepsCounterTest - FAILED   Result returns wrong answer.", result, CoreMatchers.containsString("\"steps\" : 13"));
    }

    @Test
    public void stepsWithoutActorTest() {
        String result = stepsWithoutActorFinder.visitScenario(scenario);
        assertThat("stepsWithoutActorTest - FAILED   Result returns wrong answer.", result, CoreMatchers.containsString("\"steps\" : \"[A form is displayed, FOR EACH: instance:]\""));
    }

    @Test
    public void keywordsCounterTest() {
        String result = keywordsCounter.visitScenario(scenario);
        assertThat("keywordsCounterTest - FAILED   Result returns wrong answer.", result, CoreMatchers.containsString("\"keywords\" : 10"));
    }
    @Test
    public void textWithStepNumbersDownloaderTest() {
        String result = textWithStepNumbersDownloader.visitScenario(scenario);
        assertThat("textWithStepNumbersDownloaderTest - FAILED   Result returns wrong answer.", result, CoreMatchers.containsString("200"));
    }
    @Test
    public void calculateStepsWithKeywordTest() {
        Integer result = keywordsCounter.calculateStepsWithKeyword(scenario,keywords);
        assertEquals(10, (int) result);
    }
    @Test
    public void answerToJsonTest() {
        String result = scenarioObjectBuilder.answerToJson("test","test");
        assertThat("answerToJsonTest - FAILED   Result returns wrong answer.", result, CoreMatchers.containsString("\"test\" : \"test\""));
    }
    @Test
    public void answerToJsonIntTest() {
        String result = scenarioObjectBuilder.answerToJson("test",100);
        assertThat("answerToJsonIntTest - FAILED   Result returns wrong answer.", result, CoreMatchers.containsString("\"test\" : 100"));
    }
}
