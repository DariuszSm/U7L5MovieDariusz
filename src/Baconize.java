import java.util.ArrayList;
import java.util.Scanner;

public class Baconize {

    private static Person getBacon(ArrayList<SimpleMovie> movies) {
        for (int i = 0; i < movies.size(); i++) {
            ArrayList<Person> movieActors = movies.get(i).getActors();
            for (int f = 0; f < movieActors.size(); f++) {
                Person actor = movieActors.get(f);
                if (actor.getName().equals("Kevin Bacon")) {
                    System.out.println(actor);
                    return actor;
                }
            }
        }
        System.out.println("blehhhh");
        return null;
    }

    private static boolean isAllBacon(ArrayList<SimpleMovie> movies) {
        for (SimpleMovie movie : movies) {
            for (Person actor : movie.getActors()) {
                if (actor.getBaconNumber() == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void baconize(ArrayList<SimpleMovie> movies) {
        Scanner s = new Scanner(System.in);
        Person theOneAndOnly = getBacon(movies);
        theOneAndOnly.setBacon(0);
        ArrayList<Person> actors = new ArrayList<Person>();
        actors.add(theOneAndOnly);
        ArrayList<Person> mutualActors = new ArrayList<Person>();
        int baconNum = 1;
        do {
            // search all direct actors
            for (Person actor : actors) {
                // search all movies the actors are starred in
                for (SimpleMovie movie : actor.simpleMoviesStarred()) {
                    ArrayList<Person> movieActors = movie.getActors();
                    // search all actors from that movie
                    for (Person mutualPerson : movieActors) {
                        // search through all of the actors from starting loop again
                        for (int i = 0; i < actors.size(); i++) {
                            if (mutualPerson.getBaconNumber() != null || mutualPerson.getName().equals(actors.get(i).getName())) {
                                // skip the mutual actor
                                break;
                            }
                            if (i == actors.size()-1) {
                                mutualActors.add(mutualPerson);
                            }
                        }
                    }
                }
            }

            for (Person mutActor : mutualActors) {
                mutActor.setBacon(baconNum);
                System.out.println(mutActor);
            }
            baconNum++;
            System.out.println(baconNum);
            s.nextLine();
            actors = mutualActors;
            mutualActors = new ArrayList<Person>();
        } while (!isAllBacon(movies));
        System.out.println("done");
    }
}
