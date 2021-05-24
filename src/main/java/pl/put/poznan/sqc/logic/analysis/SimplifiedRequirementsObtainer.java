package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.model.Scenario;
import pl.put.poznan.sqc.model.Step;
import java.util.ArrayList;

/**
 * Class responsible for creating new scenario which is simplified version of other scenario. Simplifying relies on
 * throwing away all steps/sub-steps above some level.
 * @author      Hubert Radom
 * @version     1.0
 * @since       v1.0-alpha
 */
public class SimplifiedRequirementsObtainer implements ScenarioVisitor {
    /**
     * Keeping track of steps which are below some 'level'
     */
    private ArrayList<Step> steps = new ArrayList<Step>();
    /**
     * Keeping track of max depth of steps for which we care to add to new, simplified, version of scenario.
     */
    private int maxLevel;

    /**
     * Simple constructor.
     * @param maxLevel      max depth of steps in new, simplified, scenario
     */
    public SimplifiedRequirementsObtainer(int maxLevel){
        this.maxLevel = maxLevel;
    }
    /**
     * Main function which create simplified scenario from original scenario, by cutting all sub-steps above some level
     * @param scenario      scenario which will be simplified
     * @return              simplified scenario
     */
    @Override
    public Scenario visitScenario(Scenario scenario) {
        this.getStepsLevel(scenario);

        Scenario scenario1 = new Scenario();

        scenario1.setHeader(scenario.getHeader());
        scenario1.setSteps(this.steps);

        return scenario1;

    }
    /**
     * Function, which return steps up to some level which is in {@link #maxLevel}
     * If maxLevel is equal 0, then no scenario will be returned.
     * @param scenario      scenario which will be simplified
     * @return              list of steps, which will be in simplified version of scenario
     */
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

    /**
     * Recursive function, which save currently step to {@link #steps}, and if it is possible, repeat this process for all sub-scenario
     * @param step              current step, which is added to new, simplified scenario
     * @param currentLevel      placeholder, which care current 'level' of step
     * @param maxLevel       useless
     */
    public void diveIntoStep(Step step, int currentLevel, int maxLevel) {
        steps.add(step);
        if (step.getSteps() != null && currentLevel < maxLevel) {
            for (Step newStep : step.getSteps()) {
                diveIntoStep(newStep, currentLevel + 1, maxLevel);
            }
        }
    }
}
