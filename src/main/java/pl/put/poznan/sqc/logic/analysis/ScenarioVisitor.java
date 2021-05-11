package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.model.Scenario;

public interface ScenarioVisitor<T> {
    public T visitScenario(Scenario scenario);
}
