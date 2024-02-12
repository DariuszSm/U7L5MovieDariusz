import java.util.ArrayList;
import java.util.Scanner;

public class Baconize {

    public static Person getActor(ArrayList<SimpleMovie> movies, String actorName) {
        for (int i = 0; i < movies.size(); i++) {
            ArrayList<Person> movieActors = movies.get(i).getActors();
            for (int f = 0; f < movieActors.size(); f++) {
                Person actor = movieActors.get(f);
                if (actor.getName().equals(actorName)) {
                    // System.out.println(actor);
                    return actor;
                }
            }
        }
        System.out.println("No Bacon!!!");
        return null;
    }

    /**
     * Checks how many casts have a bacon number (not individual actors, rather all casted roles in a movie)
     * @param movies
     * @return
     */
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

    /**
     * Assigns a bacon number to all actors in the movies list
     * @param movies List of movies to baconize all of its actors in
     */
    public static void baconize(ArrayList<SimpleMovie> movies) {
        Scanner s = new Scanner(System.in);
        Person theOneAndOnly = getActor(movies, "Kevin Bacon");
        theOneAndOnly.setBacon(0);
        ArrayList<Person> actors = new ArrayList<Person>();
        actors.add(theOneAndOnly);
        ArrayList<Person> mutualActors = new ArrayList<Person>();
        int baconNum = 1;
        double percentNoBaconCast;
        do {
            // search all direct actors
            for (Person actor : actors) {
                // search all movies the actors are starred in
                for (SimpleMovie movie : actor.simpleMoviesStarred()) {
                    ArrayList<Person> movieActors = movie.getActors();
                    // search all actors from that movie
                    for (Person mutualPerson : movieActors) {
                        if (mutualPerson.getBaconNumber() != null) {continue;}
                        // System.out.println("Movies starred by " + mutualPerson + ": " + mutualPerson.simpleMoviesStarred().size());
                        mutualActors.add(mutualPerson);
                        mutualPerson.setBaconPerson(actor);
                        mutualPerson.setBaconMovieLink(movie);
                    }
                }
            }

            // since all actors in the actors array have been searched, clear the array and fill in all mutual actors
            actors = new ArrayList<Person>();
            for (Person mutActor : mutualActors) {
                mutActor.setBacon(baconNum);
                actors.add(mutActor);
            }
            baconNum++;
            percentNoBaconCast = howManyNoBacon(movies);
            System.out.println("Processed bacon number " + (baconNum-2) + "!\nNo bacon: " + (int)(percentNoBaconCast * 100) + "%");

            mutualActors = new ArrayList<Person>();
        } while (!actors.isEmpty());
        System.out.println("done");
    }
}
