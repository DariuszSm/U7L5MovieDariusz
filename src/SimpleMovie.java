import java.util.ArrayList;
import java.util.HashMap;

public class SimpleMovie {
    private String title;
    private String actorsData;
    private String[] actorArr;
    private ArrayList<Person> actors;

    public SimpleMovie(String t, String a, HashMap<String, Person> allActors) {
        title = t;
        actorsData = a;
        actors = new ArrayList<Person>();
//        for (Person actor : allActors) {
//            if (a.contains(actor.getName())) {
//                actors.add(actor);
//            }
//        }
        String[] tempActors = actorsData.split(":");
        actorArr = tempActors;
        for (String tempActor : tempActors) {
            Person newActor;
            if (!allActors.containsKey(tempActor)) {
                newActor = new Person(tempActor, "Actor");
                allActors.put(tempActor, newActor);
            }
//            for (int actor = 0; actor < actors.size(); actor++) {
//                if (actors.get(actor).getName().equals(tempActor)) {
//                    break;
//                }
//                if (actors.get(actor) == actors.get(actors.size()-1)) {
//                    Person newActor = new Person(tempActor, "Actor");
//                    actors.add(newActor);
//                    allActors.add(newActor);
//                }
//            }
        }
//        for (String tempActor : tempActors) {
//            Person actorToAdd = null;
//            for (Person actor : actors) {
//                if (tempActor.equals(actor.getName())) {
//                    actorToAdd = actor;
//                }
//            }
//            if (actorToAdd == null) {
//                actorToAdd = new Person(tempActor, "Actor");
//            }
//            this.actors.add(actorToAdd);
//            actors.add(actorToAdd);
//        }
    }

    public ArrayList<Person> getActors() {
        return actors;
    }

    public void getActorsFromMap(HashMap<String, Person> actorMap) {
        for (String actor : actorArr) {
            actors.add(actorMap.get(actor));
        }
    }

    public String toString() {
        return "Title: " + title + "\n" + "Actors: " + actors + "\n";
    }
}