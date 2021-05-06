package pl.put.poznan.sqc.model;

import java.util.ArrayList;

public class Header {
    private String title;
    private ArrayList<String> actors;
    private String systemActor;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public String getSystemActor() {
        return systemActor;
    }

    public void setSystemActor(String systemActor) {
        this.systemActor = systemActor;
    }

    @Override
    public String toString() {
        return "Header{" +
                "title='" + title + '\'' +
                ", actors=" + actors +
                ", systemActor='" + systemActor + '\'' +
                '}';
    }
}
