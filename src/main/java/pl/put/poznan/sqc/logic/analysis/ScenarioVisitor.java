package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.model.Scenario;

public interface ScenarioVisitor {
    public void visitScenario(Scenario scenario);
}
