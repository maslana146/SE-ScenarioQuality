package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.model.Scenario;
import pl.put.poznan.sqc.model.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class KeywordsCounter implements ScenarioVisitor<Integer> {

    private String keyword = new String("System"); // just for testing, i gust from desription it should be a list of Strings
    private Integer count;
    private ArrayList<Step> stepList = new ArrayList<Step>();

    @Override
    public Integer visitScenario(Scenario scenario) {
        count = 0;
        if (scenario.getSteps().size() > 0) {
//            updateStepList(scenario.getSteps());
//            calculateStepsWithKeyword(scenario);
        }
        return count;
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
