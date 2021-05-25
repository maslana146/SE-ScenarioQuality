package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.logic.ScenarioObjectBuilder;
import pl.put.poznan.sqc.model.Scenario;
import pl.put.poznan.sqc.model.Step;

public class StepsCounter implements ScenarioVisitor {

    int numOfSteps = 0;

    public void diveIntoStep(Step step) {
        numOfSteps++;
        if (step.getSteps() != null)
        for (Step newStep: step.getSteps()) {
            diveIntoStep(newStep);
        }
    }
    @Override
    public String visitScenario(Scenario scenario) {
        if (scenario.getSteps() != null)
        for (Step step: scenario.getSteps()) {
            diveIntoStep(step);
        }
        return ScenarioObjectBuilder.answerToJson("steps",numOfSteps);
    }
}
