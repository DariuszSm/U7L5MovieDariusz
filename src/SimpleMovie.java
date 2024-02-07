import java.util.ArrayList;

public class SimpleMovie {
    private String title;
    private String actorsData;
    private ArrayList<Person> actors;

    public SimpleMovie(String t, String a, ArrayList<Person> allActors) {
        title = t;
        actorsData = a;
        actors = new ArrayList<Person>();
        for (Person actor : allActors) {
            if (a.contains(actor.getName())) {
                actors.add(actor);
            }
        }
        String[] tempActors = actorsData.split(":");
        actors.add(new Person(tempActors[0], "Actor"));
        for (String tempActor : tempActors) {
            for (int actor = 0; actor < actors.size(); actor++) {
                if (actors.get(actor).getName().equals(tempActor)) {
                    break;
                }
                if (actors.get(actor) == actors.get(actors.size()-1)) {
                    Person newActor = new Person(tempActor, "Actor");
                    actors.add(newActor);
                    allActors.add(newActor);
                }
            }
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

    public String toString() {
        return "Title: " + title + "\n" + "Actors: " + actors + "\n";
    }
}