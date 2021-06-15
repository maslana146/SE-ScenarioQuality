package pl.put.poznan.sqc.logic.analysis;

import pl.put.poznan.sqc.logic.ScenarioObjectBuilder;
import pl.put.poznan.sqc.model.Scenario;
import pl.put.poznan.sqc.model.Step;
import pl.put.poznan.sqc.model.Header;

import java.util.ArrayList;
/**
 * Class responsible for finding steps without actor.
 * @author      Bartosz Maślanka
 * @version     1.0
 * @since       v1.1-alpha
 */

public class StepsWithoutActorFinder implements ScenarioVisitor {

    /**
     * Keeps track of steps in scenario
     */
    private ArrayList<Step> steps = new ArrayList<Step>();

    /**
     * Main function, which steps do not have scenario
     * @param scenario scenario as a input
     * @return json of scenario steps, without actor
     */
    @Override
    public String visitScenario(Scenario scenario) {
        this.stepsWithoutActor(scenario, scenario.getHeader());

        ArrayList<String>actions = new ArrayList<String>();
        for(Step step: this.steps) {
            actions.add(step.getAction());
        }
        return ScenarioObjectBuilder.answerToJson("steps",actions.toString());
    }

    /**
     *
     * @param scenario scenario, which will be under inspection
     * @param header    header, from which actors will be taken
     * @return list of steps, without actors
     */
    public ArrayList<Step> stepsWithoutActor(Scenario scenario, Header header){
        ArrayList<String> actors = header.getActors();
        actors.add(header.getSystemActor());

        if (scenario.getSteps() != null)
        for (Step step: scenario.getSteps()){
            diveIntoStep(step, actors);
        }
        return steps;
    }

    /**
     * Recursion function which check for steps, if they have actors, and saves it otherwise
     * @param step  which step is under inspection
     * @param actors list of actors in given scenario
     */
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
