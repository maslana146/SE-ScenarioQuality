package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.model.Scenario;
/**
 * Interface responsible for visiting the scenario.
 * @author      Kajetan Kubik
 * @version     1.1
 * @since       v1.0-alpha
 */

public interface ScenarioVisitor {
    /**
     * All class's build on this interface, use this function
     * @param scenario scenario as a input
     */
    public String visitScenario(Scenario scenario);
}
