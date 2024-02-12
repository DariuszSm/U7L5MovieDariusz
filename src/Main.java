import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        ArrayList<Person> masterActorsList = new ArrayList<Person>();
        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data", masterActorsList);
        // for (SimpleMovie movie : movies) {
        //     System.out.println(movie);
        // }
        System.out.println("Number of movies: " + movies.size());

        // baconizes all actors (assigns bacon numbers and associated movies and actors)
        Baconize.baconize(movies);

        System.out.println("Which actor do you want to find connections to Kevin Bacon with?: ");
        String wantedActorInput = s.nextLine();

        Person wantedPerson = Baconize.getActor(movies, wantedActorInput);

        if (wantedPerson == null) {
            System.out.println("Actor not found in the database!");
        } else {
            System.out.println(wantedPerson.getName() + "'s bacon number: " + wantedPerson.getBaconNumber());
            System.out.print(wantedPerson.getName() + " -> ");
            SimpleMovie focusedMovie = wantedPerson.getBaconMovieLink();
            Person linkPerson = wantedPerson.getBaconPerson();
            System.out.print(focusedMovie.getTitle() + " - > ");
            while (!linkPerson.getName().equals("Kevin Bacon")) {
                System.out.print(linkPerson.getName() + " - > ");
                focusedMovie = linkPerson.getBaconMovieLink();
                linkPerson = linkPerson.getBaconPerson();
                System.out.print(focusedMovie.getTitle() + " -> ");
            }
            System.out.print(linkPerson.getName());
        }

    }
}