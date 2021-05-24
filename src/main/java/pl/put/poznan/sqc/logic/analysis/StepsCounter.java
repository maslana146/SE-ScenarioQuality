package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.model.Scenario;
import pl.put.poznan.sqc.model.Step;

/**
 * Class responsible for counting steps in scenario's.
 * @author      Hubert Radom
 * @version     1.0
 * @since       v1.0-alpha
 */
public class StepsCounter implements ScenarioVisitor<Integer> {

    /**
     * Keeps track of number of steps during visiting this scenario
     */
    int numOfSteps;

    /**
     * Adding step to {@link #numOfSteps} counter, and if step have some sub-steps, visit all this sub-steps by recurrence.
     * @param step      currently visited step
     */
    private void diveIntoStep(Step step) {
        numOfSteps++;
        if (step.getSteps() != null)
        for (Step newStep: step.getSteps()) {
            diveIntoStep(newStep);
        }
    }

    /**
     * Main function of this class. It's count the steps.
     * @param scenario      the scenario which steps will be counted
     * @return              the number of steps in this scenario
     */
    @Override
    public Integer visitScenario(Scenario scenario) {
        numOfSteps = 0;
        if (scenario.getSteps() != null)
        for (Step step: scenario.getSteps()) {
            diveIntoStep(step);
        }
        return numOfSteps;
    }
}
