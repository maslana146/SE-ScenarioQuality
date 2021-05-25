package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.model.Scenario;

public interface ScenarioVisitor {
    public String visitScenario(Scenario scenario);
}
