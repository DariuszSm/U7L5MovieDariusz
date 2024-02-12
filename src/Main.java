import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void writeToActorCache(ArrayList<Person> actors, File actorCache){
        try {
            FileWriter actorWriter = new FileWriter(actorCache);
            for (Person actor : actors) {
                String actorString = "";
                actorString += actor.getName() + "!^!";
                actorString += actor.getBaconNumber() + "!^!";
                actorString += actor.getBaconPerson().getName() + "!^!";
                actorString += actor.getBaconMovieLink().getTitle() + "\n";
                actorWriter.write(actorString);
            }
            System.out.println("Successfully written!");
            actorWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred with writing to the cached actors file.");
        }
    }


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

        try {
            File actorCache = new File("actorCache.txt");

            if (actorCache.createNewFile()) {
                writeToActorCache(masterActorsList, actorCache);
            } else {
                System.out.println("Cache already exists!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred with reading the cached actors file.");
        }

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