package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.model.Scenario;
import pl.put.poznan.sqc.model.Step;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;


public class KeywordsCounter implements ScenarioVisitor<Integer> {

    private String keyword = new String("System"); // just for testing, i gust from desription it should be a list of Strings
    private Integer count;


    @Override
    public Integer visitScenario(Scenario scenario) {
        count = 0;
        if(scenario.getSteps().size() > 0) {
            updateStepList(scenario.getSteps());
            calculateStepsWithKeyword(scenario);
        }
        return count;
    }

    public Integer getCount() { return count; }

    private void calculateStepsWithKeyword(Scenario scenario){
        goThroughSteps();
    }

    private void goThroughSteps() {
        for(Step currentStep: stepList){
            if (checkStep(currentStep)) {
                count += 1;
            }
        }
    }

    private Boolean checkStep(Step step){
        String firstWord = step.getAction().split(" ", 2)[0];
        return firstWord.equals(keyword);
    }

    private void updateStepList(ArrayList<Step> steps){
        for (Step step: steps){
            stepList.add(step);
            if(step.getSteps()!=null){
                updateStepList(step.getSteps());
            }
        }

    }
}
