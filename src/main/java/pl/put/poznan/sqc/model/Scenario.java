package pl.put.poznan.sqc.model;

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
}
