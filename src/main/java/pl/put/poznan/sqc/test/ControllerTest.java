package pl.put.poznan.sqc.test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.mockito.InjectMocks;
import org.testng.annotations.Test;
import pl.put.poznan.sqc.logic.analysis.StepsCounter;
import pl.put.poznan.sqc.logic.analysis.StepsWithoutActorFinder;
import pl.put.poznan.sqc.model.Scenario;
import pl.put.poznan.sqc.rest.ScenarioQualityCheckerController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import pl.put.poznan.sqc.logic.analysis.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ControllerTest {


    public ControllerTest() throws IOException {
    }

    private Scenario getScenario() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Scenario scenario = mapper.readValue(new File("scenario_example.json"), Scenario.class);
        return scenario;
    }



    @InjectMocks
    ScenarioQualityCheckerController controller = new ScenarioQualityCheckerController();

    @Test
    public void stepsCounterTest() throws IOException {
        StepsCounter stepsCounter = new StepsCounter();
        Scenario scenario = mock(Scenario.class);

        when(scenario.accept(stepsCounter)).thenReturn(getScenario().accept(stepsCounter));

        String result = controller.stepsCounterController(scenario, stepsCounter);
        assertThat("stepsCounterTest - FAILED   Result returns wrong answer.", result, CoreMatchers.containsString("\"steps\" : 13"));
    }

    @Test
    public void stepsCounterTestType() throws IOException {
        StepsCounter stepsCounter = new StepsCounter();
        Scenario scenario = mock(Scenario.class);

        when(scenario.accept(stepsCounter)).thenReturn(getScenario().accept(stepsCounter));

        String result = controller.stepsCounterController(scenario, stepsCounter);

        String numberOnly = result.replaceAll("[^0-9]", "");
        assertTrue((Object)Integer.parseInt(numberOnly) instanceof Integer);

    }

    @Test
    public void stepsCounterTestExistence() throws IOException {
        StepsCounter stepsCounter = new StepsCounter();
        Scenario scenario = mock(Scenario.class);

        when(scenario.accept(stepsCounter)).thenReturn(getScenario().accept(stepsCounter));

        String result = controller.stepsCounterController(scenario, stepsCounter);

        String numberOnly = result.replaceAll("[^0-9]", "");
        assertTrue(Integer.parseInt(numberOnly) > 0);
    }

    @Test
    public void stepsWithoutActorTest() throws IOException {
        StepsWithoutActorFinder stepsWithoutActorFinder = new StepsWithoutActorFinder();
        Scenario scenario = mock(Scenario.class);

        when(scenario.accept(stepsWithoutActorFinder)).thenReturn(getScenario().accept(stepsWithoutActorFinder));

        String result = controller.stepsWithoutActorFinderController(scenario, stepsWithoutActorFinder);
        assertThat("stepsWithoutActorTest - FAILED   Result returns wrong answer.", result, CoreMatchers.containsString("\"steps\" : \"[A form is displayed, FOR EACH: instance:]\""));
    }


    @Test
    public void keywordsCounterTest() throws IOException {
        KeywordsCounter keywordsCounter = new KeywordsCounter(new ArrayList<>(Arrays.asList("Librarian", "System")));
        Scenario scenario = mock(Scenario.class);

        when(scenario.accept(keywordsCounter)).thenReturn(getScenario().accept(keywordsCounter));

        String result = controller.keywordsCounterController(scenario, keywordsCounter);

        assertThat("keywordsCounterTest - FAILED   Result returns wrong answer.", result, CoreMatchers.containsString("\"keywords\" : 10"));
    }


    @Test
    public void keywordsCounterTestType() throws IOException {
        KeywordsCounter keywordsCounter = new KeywordsCounter(new ArrayList<>(Arrays.asList("Librarian", "System")));
        Scenario scenario = mock(Scenario.class);

        when(scenario.accept(keywordsCounter)).thenReturn(getScenario().accept(keywordsCounter));

        String result = controller.keywordsCounterController(scenario, keywordsCounter);
        String numberOnly = result.replaceAll("[^0-9]", "");
        assertTrue((Object)Integer.parseInt(numberOnly) instanceof Integer);
    }

    @Test
    public void keywordsCounterTestExistence() throws IOException {
        KeywordsCounter keywordsCounter = new KeywordsCounter(new ArrayList<>(Arrays.asList("Librarian", "System")));
        Scenario scenario = mock(Scenario.class);

        when(scenario.accept(keywordsCounter)).thenReturn(getScenario().accept(keywordsCounter));

        String result = controller.keywordsCounterController(scenario, keywordsCounter);

        String numberOnly = result.replaceAll("[^0-9]", "");
        assertTrue(Integer.parseInt(numberOnly) > 0);
    }


    @Test
    public void textWithStepNumbersDownloaderTest() throws IOException {
        TextWithStepNumbersDownloader textWithStepNumbersDownloader = new TextWithStepNumbersDownloader();
        Scenario scenario = mock(Scenario.class);

        when(scenario.accept(textWithStepNumbersDownloader)).thenReturn(getScenario().accept(textWithStepNumbersDownloader));

        String result = controller.textWithStepNumbersDownloaderController(scenario, textWithStepNumbersDownloader);
        assertThat("textWithStepNumbersDownloaderTest - FAILED   Result returns wrong answer.", result, CoreMatchers.containsString("200"));
    }

    @Test
    public void SimplifiedRequirementsObtainerTest() throws IOException{
        SimplifiedRequirementsObtainer simplifiedRequirementsObtainer = new SimplifiedRequirementsObtainer(0);
        Scenario scenario = mock(Scenario.class);

        String str = getScenario().accept(simplifiedRequirementsObtainer);
        when(scenario.accept(simplifiedRequirementsObtainer)).thenReturn(str);

        String result = controller.simplifiedRequirementsObtainerController(scenario, simplifiedRequirementsObtainer);
        assertEquals(result, str);
    }
    
    @Test
    public void SimplifiedRequirementsObtainerTest1() throws IOException{
        SimplifiedRequirementsObtainer simplifiedRequirementsObtainer = new SimplifiedRequirementsObtainer(1);
        Scenario scenario = mock(Scenario.class);

        String str = getScenario().accept(simplifiedRequirementsObtainer);
        when(scenario.accept(simplifiedRequirementsObtainer)).thenReturn(str);

        String result = controller.simplifiedRequirementsObtainerController(scenario, simplifiedRequirementsObtainer);

        ObjectMapper objectMapper = new ObjectMapper();
        Scenario sc = objectMapper.readValue(result, Scenario.class);

        assertTrue(sc instanceof Scenario);

    }

}
