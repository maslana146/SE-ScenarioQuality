package pl.put.poznan.sqc.model;

import java.util.ArrayList;

/**
 * Class which create header object, which is at the top of scenario
 */
public class Header {
    /**
     * title of header
     */
    private String title;
    /**
     * list of actors in given scenario
     */
    private ArrayList<String> actors;
    /**
     * name of the main actor
     */
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

    /**
     * overwriting of toString method
     * @return string in form "Header{title="sometitle", actors="[actor1, actor2, ...]", systemActor="systemActor"}
     */
    @Override
    public String toString() {
        return "Header{" +
                "title='" + title + '\'' +
                ", actors=" + actors +
                ", systemActor='" + systemActor + '\'' +
                '}';
    }
}
