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

    private static double howManyNoBacon(ArrayList<SimpleMovie> movies) {
        int noBacon = 0;
        int allCast = 0;
        for (SimpleMovie movie : movies) {
            for (Person actor : movie.getActors()) {
                if (actor.getBaconNumber() == null) {
                    noBacon++;
                }
                allCast++;
            }
        }
        return (double)noBacon/allCast;
    }

    public static void baconize(ArrayList<SimpleMovie> movies, int actorsAmnt) {
        Scanner s = new Scanner(System.in);
        Person theOneAndOnly = getBacon(movies);
        theOneAndOnly.setBacon(0);
        ArrayList<Person> actors = new ArrayList<Person>();
        actors.add(theOneAndOnly);
        ArrayList<Person> mutualActors = new ArrayList<Person>();
        int baconNum = 1;
        double percentNoBaconCast;
        int actorsNummd = 0;
        do {
            // search all direct actors
            for (Person actor : actors) {
                // search all movies the actors are starred in
                for (SimpleMovie movie : actor.simpleMoviesStarred()) {
                    ArrayList<Person> movieActors = movie.getActors();
                    // search all actors from that movie
                    for (Person mutualPerson : movieActors) {
                        if (mutualPerson.getBaconNumber() != null) {continue;}
                        System.out.println("Movies starred by " + mutualPerson + ": " + mutualPerson.simpleMoviesStarred().size());
                        mutualActors.add(mutualPerson);
                        mutualPerson.setBaconPerson(actor);
                        mutualPerson.setBaconMovieLink(movie);
                        actorsNummd++;
                    }
                }
            }
            actors = new ArrayList<Person>();

            for (Person mutActor : mutualActors) {
                mutActor.setBacon(baconNum);
                actors.add(mutActor);
            }
            baconNum++;
            percentNoBaconCast = howManyNoBacon(movies);
            System.out.println("That was bacon " + (baconNum-2) + "!\nNo bacon: " + (int)(percentNoBaconCast * 10000) + "%");
            System.out.println((double)actorsNummd/actorsAmnt);
            s.nextLine();

            mutualActors = new ArrayList<Person>();
        } while (percentNoBaconCast > 0);
        System.out.println("done");
    }
}
