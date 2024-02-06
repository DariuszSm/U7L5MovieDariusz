import java.util.ArrayList;

public class Person {
    ArrayList<Movie> starringMovies;
    ArrayList<SimpleMovie> starringSimpleMovies;
    String role;
    String name;
    Integer baconNumber;

    public Person(String name, String role) {
        starringMovies = new ArrayList<Movie>();
        starringSimpleMovies = new ArrayList<SimpleMovie>();
        this.role = role;
        this.name = name;
        baconNumber = null;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBacon(int num) {
        if (baconNumber == null || num < baconNumber) {
            baconNumber = num;
        }
    }

    public Integer getBaconNumber() {
        return baconNumber;
    }

    public ArrayList<Movie> moviesStarred() {
        return starringMovies;
    }

    public ArrayList<SimpleMovie> simpleMoviesStarred() {
        return starringSimpleMovies;
    }

    public void castInMovie(Movie movie) {
        starringMovies.add(movie);
    }

    public void castInSimpleMovie(SimpleMovie movie) {
        starringSimpleMovies.add(movie);
    }

    public void uncastInMovie(Movie movie) {
        for (int i = 0; i < starringMovies.size(); i++) {
            if (starringMovies.get(i) == movie) {
                starringMovies.remove(i);
                i--;
            }
        }
    }

    public String toString() {
        return role + ": " + name;
    }

}
