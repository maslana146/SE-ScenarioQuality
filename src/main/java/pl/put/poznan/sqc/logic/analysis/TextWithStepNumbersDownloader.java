package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.model.Scenario;
import pl.put.poznan.sqc.model.Step;

import java.util.ArrayList;

public class TextWithStepNumbersDownloader implements ScenarioVisitor {
  
    @Override
    public Integer visitScenario(Scenario scenario) {
        goThroughSteps(scenario.getSteps(), "");
        return 5;
    }

    private void goThroughSteps(ArrayList<Step> steps, String prefix){
        Integer currentIndex = 1;
        for(Step step: steps){
            String line = prefix + currentIndex.toString() + step.getAction();
            writeLineToFile(line);
            if(step.getSteps() != null){
                prefix = prefix + currentIndex.toString() + ".";
                goThroughSteps(step.getSteps(), prefix);
                String nextPrefix = prefix + currentIndex.toString() + ".";
                goThroughSteps(step.getSteps(), nextPrefix);
            }
            currentIndex++;
        }
    }

    private void writeLineToFile(String line){
        System.out.println(line);
    }


}
