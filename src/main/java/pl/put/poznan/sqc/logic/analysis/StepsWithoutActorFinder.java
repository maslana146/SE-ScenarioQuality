package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.model.Scenario;

public class StepsWithoutActorFinder implements ScenarioVisitor<Integer> {
    @Override
    public Integer visitScenario(Scenario scenario) {
        return 5;
    }
}
