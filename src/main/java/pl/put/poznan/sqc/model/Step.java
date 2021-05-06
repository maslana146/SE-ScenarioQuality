package pl.put.poznan.sqc.model;

import java.util.ArrayList;

public class Step {
    private String action;
    private ArrayList<Step> steps;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }
}
