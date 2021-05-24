package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.logic.ScenarioObjectBuilder;
import pl.put.poznan.sqc.model.Scenario;
import pl.put.poznan.sqc.model.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class KeywordsCounter implements ScenarioVisitor {

    private ArrayList<String> keywords;
    private Integer count;

    @Override
    public String visitScenario(Scenario scenario) {
        if (scenario.getSteps().size() > 0) {
            calculateStepsWithKeyword(scenario, this.keywords);
        }
        return ScenarioObjectBuilder.answerToJson("keywords", count);
    }

    public KeywordsCounter(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public Integer calculateStepsWithKeyword(Scenario scenario, ArrayList<String> keywords) {
        count = 0;
        List<String> keywordsLowercase = keywords.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        goThroughSteps(scenario.getSteps(), keywordsLowercase);
        System.out.println(count);
        return count;
    }

    private void goThroughSteps(ArrayList<Step> steps, List<String> keywords) {
        for (Step step : steps) {
            String firstWord = step.getAction().split(" ")[0];

            if (keywords.contains(firstWord.toLowerCase())) {
                count++;
            }
            if (step.getSteps() != null) {
                goThroughSteps(step.getSteps(), keywords);
            }
        }
        return;

    }
}
