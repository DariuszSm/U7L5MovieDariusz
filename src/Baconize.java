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
                System.out.println("Movies starred by " + actor + ": " + actor.simpleMoviesStarred().size());
                // search all movies the actors are starred in
                for (SimpleMovie movie : actor.simpleMoviesStarred()) {
                    ArrayList<Person> movieActors = movie.getActors();
                    // search all actors from that movie
                    for (Person mutualPerson : movieActors) {
                        if (mutualPerson.getBaconNumber() != null) {continue;}
                        mutualActors.add(mutualPerson);
                    }
                }
            }
            actors = new ArrayList<Person>();

            for (Person mutActor : mutualActors) {
                mutActor.setBacon(baconNum);
                actors.add(mutActor);
            }
            baconNum++;
            s.nextLine();

            mutualActors = new ArrayList<Person>();
        } while (!isAllBacon(movies));
        System.out.println("done");
    }
}
