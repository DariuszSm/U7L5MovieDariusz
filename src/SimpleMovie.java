import java.util.ArrayList;
import java.util.HashMap;

public class SimpleMovie {
    private String title;
    private String actorsData;
    private String[] actorArr;
    private ArrayList<Person> actors;

    public SimpleMovie(String t, String a, HashMap<String,Person> allActors) {
        title = t;
        actorsData = a;
        actors = new ArrayList<Person>();
        String[] tempActors = actorsData.split(":");
        actorArr = tempActors;

        // assign all actor names to keys on hashmap
        for (String tempActor : tempActors) {
            Person newActor;
            if (!allActors.containsKey(tempActor)) {
                newActor = new Person(tempActor, "Actor");
                allActors.put(tempActor, newActor);
            }
        }

        for (String actor : actorArr) {
            actors.add(allActors.get(actor));
        }
    }

    public ArrayList<Person> getActors() {
        return actors;
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        return "Title: " + title + "\n" + "Actors: " + actors + "\n";
    }
}