package pl.put.poznan.sqc.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sqc.logic.analysis.*;
import pl.put.poznan.sqc.model.Scenario;

import java.util.ArrayList;


@RestController
@RequestMapping("/api")
public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    @PostMapping(path="steps")
    public String stepsCounterController(@RequestBody Scenario scenario) {
        StepsCounter stepsCounter = new StepsCounter();
        logger.info("Count steps request message");
        return scenario.accept(stepsCounter);
    }

    @PostMapping(path="keywords")
    public String keywordsCounterController(@RequestParam(name = "keywords") ArrayList<String> keywords,@RequestBody Scenario scenario) {
        KeywordsCounter keywordsCounter = new KeywordsCounter(keywords);
        logger.info("Steps starting with keyword request message");
        return scenario.accept(keywordsCounter);
    }
    @PostMapping(path="download")
    public String textWithStepNumbersDownloaderController(@RequestBody Scenario scenario) {
        TextWithStepNumbersDownloader textWithStepNumbersDownloader = new TextWithStepNumbersDownloader();
        logger.info("Download scenario request message");
        return scenario.accept(textWithStepNumbersDownloader);
    }

    @PostMapping(path="simplified")
    public String simplifiedRequirementsObtainerController(@RequestParam(name = "level") Integer level,@RequestBody Scenario scenario) {
        SimplifiedRequirementsObtainer simplifiedRequirementsObtainer = new SimplifiedRequirementsObtainer(level);
        logger.info("Level of steps request message");
        return scenario.accept(simplifiedRequirementsObtainer);
    }

    @PostMapping(path="no-actor")
    public String stepsWithoutActorFinderController(@RequestBody Scenario scenario) {
        StepsWithoutActorFinder stepsWithoutActorFinder = new StepsWithoutActorFinder();
        logger.info("Check steps without actors request message");
        return scenario.accept(stepsWithoutActorFinder);
    }


    /////////////////////////Testing purposes


    public String stepsCounterController(@RequestBody Scenario scenario, @RequestParam StepsCounter stepsCounter) {

        logger.info("Count steps request message");
        return scenario.accept(stepsCounter);
    }

    public String keywordsCounterController(@RequestBody Scenario scenario, @RequestParam KeywordsCounter keywordsCounter) {

        logger.info("Steps starting with keyword request message");
        return scenario.accept(keywordsCounter);
    }

    public String textWithStepNumbersDownloaderController(@RequestBody Scenario scenario, @RequestParam TextWithStepNumbersDownloader textWithStepNumbersDownloader) {

        logger.info("Download scenario request message");
        return scenario.accept(textWithStepNumbersDownloader);
    }

    public String simplifiedRequirementsObtainerController(@RequestBody Scenario scenario, @RequestParam SimplifiedRequirementsObtainer simplifiedRequirementsObtainer) {

        logger.info("Level of steps request message");
        return scenario.accept(simplifiedRequirementsObtainer);
    }

    public String stepsWithoutActorFinderController(@RequestBody Scenario scenario, @RequestParam StepsWithoutActorFinder stepsWithoutActorFinder) {
        logger.info("Check steps without actors request message");
        return scenario.accept(stepsWithoutActorFinder);
    }



}
