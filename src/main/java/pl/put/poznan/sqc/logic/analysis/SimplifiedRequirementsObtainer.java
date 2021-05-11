package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.model.Scenario;

public class SimplifiedRequirementsObtainer implements ScenarioVisitor {
    @Override
    public Integer visitScenario(Scenario scenario) {
        return 5;
    }
}
