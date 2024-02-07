import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Person> masterActorsList = new ArrayList<Person>();
        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data", masterActorsList);
        for (SimpleMovie movie : movies) {
            System.out.println(movie);
        }
        System.out.println("Number of movies: " + movies.size());

        // baconizes all actors (assigns bacon numbers and associated movies and actors)
        Baconize.baconize(movies, 1600000);

    }
}