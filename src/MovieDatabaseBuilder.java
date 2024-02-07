import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

public class MovieDatabaseBuilder {

    public static ArrayList<SimpleMovie> getMovieDB(String fileName) {
        ArrayList<SimpleMovie> movies = new ArrayList<SimpleMovie>();
        try {
            File movieData = new File(fileName);
            Scanner reader = new Scanner(movieData);
            Scanner sc = new Scanner(System.in);
            HashMap<String, Person> actors = new HashMap<String, Person>();
            int moviesParsed = 0;
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split("---");
                if (data.length > 1) {
                    SimpleMovie s = new SimpleMovie(data[0], data[1], actors);
                    s.getActorsFromMap(actors);
                    ArrayList<Person> movieActors = s.getActors();
                    for (int i = 0; i < movieActors.size(); i++) {
                        movieActors.get(i).castInSimpleMovie(s);
                    }
                    movies.add(s);
                }
                moviesParsed++;
                System.out.println((double)moviesParsed/135828);
            }
            System.out.println(actors.size());
            sc.nextLine();
        }
        catch (FileNotFoundException noFile) {
            System.out.println("File not found!");
            return null;
        }

        return movies;
    }

}