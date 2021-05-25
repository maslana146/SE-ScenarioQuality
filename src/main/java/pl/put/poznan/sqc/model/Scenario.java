package pl.put.poznan.sqc.model;

import pl.put.poznan.sqc.logic.analysis.ScenarioVisitor;

import java.util.ArrayList;

public class Scenario {

    private Header header;
    private ArrayList<Step> steps;
    
    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public String accept(ScenarioVisitor v) {
        return v.visitScenario(this);
    }

    @Override
    public String toString() {
        return "Scenario{" +
                "header=" + header +
                ", steps=" + steps +
                '}';
    }
}
