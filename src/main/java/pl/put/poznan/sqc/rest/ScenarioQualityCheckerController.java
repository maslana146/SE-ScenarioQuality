package pl.put.poznan.sqc.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sqc.logic.analysis.KeywordsCounter;
import pl.put.poznan.sqc.logic.analysis.ScenarioVisitor;
import pl.put.poznan.sqc.logic.analysis.StepsCounter;
import pl.put.poznan.sqc.logic.analysis.TextWithStepNumbersDownloader;
import pl.put.poznan.sqc.model.Scenario;
import pl.put.poznan.sqc.model.Step;

import java.util.ArrayList;


@RestController
@RequestMapping("/api")
public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    @PostMapping(path="steps")
    public Integer stepsCounterController(@RequestBody Scenario scenario) {
        StepsCounter stepsCounter = new StepsCounter();
        return stepsCounter.visitScenario(scenario);
    }

    @PostMapping(path="keywords")
    public Integer keywordsCounterController(@RequestBody Scenario scenario, ArrayList<String> keywords) {
        KeywordsCounter keywordsCounter = new KeywordsCounter();
        return keywordsCounter.calculateStepsWithKeyword(scenario,keywords);
    }
    @PostMapping(path="downloadScenario")
    public Integer downloadScenarioController(@RequestBody Scenario scenario) {
        TextWithStepNumbersDownloader TextWithStepNumbersDownloader = new TextWithStepNumbersDownloader();
        return TextWithStepNumbersDownloader.visitScenario(scenario);
    }



//REST controllers example
/*
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public void get(@PathVariable String text,
                              @RequestParam(value="transforms", defaultValue="upper,escape") String[] transforms) {

        // log the parameters
        logger.debug(text);

       // return transformer.transform(text);
    }


    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                      @RequestBody String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        ScenarioQualityChecker transformer = new ScenarioQualityChecker(transforms);
        return transformer.transform(text);
    }
*/
}


