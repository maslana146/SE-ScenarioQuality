package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.logic.ScenarioObjectBuilder;
import pl.put.poznan.sqc.model.Scenario;
import pl.put.poznan.sqc.model.Step;
import java.util.ArrayList;

public class SimplifiedRequirementsObtainer implements ScenarioVisitor {

    private ArrayList<Step> steps = new ArrayList<Step>();
    private int maxLevel;

    public SimplifiedRequirementsObtainer(int maxLevel){
        this.maxLevel = maxLevel;
    }

    @Override
    public String visitScenario(Scenario scenario) {
        this.getStepsLevel(scenario);

        Scenario scenario1 = new Scenario();

        scenario1.setHeader(scenario.getHeader());
        scenario1.setSteps(this.steps);

        return ScenarioObjectBuilder.scenarioToJson(scenario1);


    }

    public ArrayList<Step> getStepsLevel(Scenario scenario) {
        if (maxLevel < 1 && scenario.getSteps() == null) {
            return null;
        }

        if (maxLevel > 0)
        for (Step step: scenario.getSteps()) {
            diveIntoStep(step, 1, maxLevel);
        }

        return steps;
    }

    public void diveIntoStep(Step step, int currentLevel, int maxLevel) {
        steps.add(step);
        if (step.getSteps() != null && currentLevel < maxLevel) {
            for (Step newStep : step.getSteps()) {
                diveIntoStep(newStep, currentLevel + 1, maxLevel);
            }
        }
    }

}
