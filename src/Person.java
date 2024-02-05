import java.util.ArrayList;

public class Person {
    ArrayList<Movie> starringMovies;
    String role;
    String name;

    public Person(String name, String role) {
        starringMovies = new ArrayList<Movie>();
        this.role = role;
        this.name = name;
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

    public ArrayList<Movie> moviesStarred() {
        return starringMovies;
    }

    public void castInMovie(Movie movie) {
        starringMovies.add(movie);
    }

    public void uncastInMovie(Movie movie) {
        for (int i = 0; i < starringMovies.size(); i++) {
            if (starringMovies.get(i) == movie) {
                starringMovies.remove(i);
                i--;
            }
        }
    }

}
