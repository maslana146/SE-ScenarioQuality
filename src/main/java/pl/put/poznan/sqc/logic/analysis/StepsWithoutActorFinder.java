package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.model.Scenario;
import pl.put.poznan.sqc.model.Step;
import pl.put.poznan.sqc.model.Header;

import java.util.ArrayList;

public class StepsWithoutActorFinder implements ScenarioVisitor {

    private ArrayList<Step> steps = new ArrayList<Step>();

    @Override
    public String visitScenario(Scenario scenario) {
        this.stepsWithoutActor(scenario, scenario.getHeader());

        return this.steps.toString();
    }

    public ArrayList<Step> stepsWithoutActor(Scenario scenario, Header header){
        ArrayList<String> actors = header.getActors();

        if (scenario.getSteps() != null)
        for (Step step: scenario.getSteps()){
            diveIntoStep(step, actors);
        }
        return steps;
    }

    public void diveIntoStep(Step step, ArrayList<String> actors){
        int count = 0;
        for(String actor: actors){
            if(step.getAction().contains(actor)) {
                count+=1;
                break;
            }
        }
        if (count == 0){
            steps.add(step);
        }
        if (step.getSteps() != null)
        for (Step newStep: step.getSteps()) {
            diveIntoStep(newStep, actors);
        }
    }
}
