import java.util.ArrayList;

public class SimpleMovie {
    private String title;
    private String actorsData;
    private ArrayList<Person> actors;

    public SimpleMovie(String t, String a) {
        title = t;
        actorsData = a;
        actors = new ArrayList<Person>();
        String[] tempActors = actorsData.split(":");
        for (int i = 0; i < tempActors.length; i++) {
            actors.add(new Person(tempActors[i], "Actor"));
        }

    }

    public ArrayList<Person> getActors() {
        return actors;
    }

    public String toString() {
        return "Title: " + title + "\n" + "Actors: " + actors + "\n";
    }
}