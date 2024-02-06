import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private ArrayList<Person> people;
    private String genres;
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        people = new ArrayList<Person>();
        genres = "";
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a tital search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void sortPeople(ArrayList<Person> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Person temp = listToSort.get(j);
            String tempTitle = temp.getName();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getName()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void sortGenres(ArrayList<String> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            String temp = listToSort.get(j);
            String tempTitle = String.valueOf(temp);

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1)) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void searchCast()
    {
        System.out.print("Enter a cast member to search: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Person> results = new ArrayList<Person>();

        // search through ALL movies in collection
        for (Person person : people)
        {
            if (!person.role.equals("Actor")) {
                continue;
            }
            String name = person.getName().toLowerCase();

            if (name.indexOf(searchTerm) != -1)
            {
                //add the Movie objects to the results list
                results.add(person);
            }
        }

        // sort the results by title
        sortPeople(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String name = results.get(i).getName();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + name);
        }

        System.out.println("Which Actor would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Person selectedPerson = results.get(choice - 1);

        ArrayList<Movie> actorMovies = selectedPerson.moviesStarred();

        // now, display them all to the user
        for (int i = 0; i < actorMovies.size(); i++)
        {
            String title = actorMovies.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = actorMovies.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void searchKeywords()
    {
        System.out.print("Enter a keyword search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieKeys = movies.get(i).getKeywords();
            movieKeys = movieKeys.toLowerCase();

            if (movieKeys.indexOf(searchTerm) != -1)
            {
                //add the Movie objects to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listGenres()
    {
        // arraylist to hold search results
        ArrayList<String> results = new ArrayList<String>();

        String[] genreList = genres.split("\\|");

        // search through ALL movies in collection
        for (int i = 0; i < genreList.length; i++)
        {
            results.add(genreList[i]);
        }

        // sort the results by title
        sortGenres(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String genre = results.get(i);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + genre.substring(0, genre.length()-1));
        }

        System.out.println("Which genre would you like to see?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String selectedGenre = results.get(choice - 1);
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        int choiceNum = 1;
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
//            System.out.println(selectedGenre);
//            System.out.println(movie.getGenres());
            if (movie.getGenres().indexOf(selectedGenre.substring(0, selectedGenre.length()-1)) != -1) {
                System.out.println("" + choiceNum + ". " + movie.getTitle());
                movieList.add(movie);
                choiceNum++;
            }
        }

        System.out.println("Which movie would you like to see?");
        System.out.print("Enter number: ");

        choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = movieList.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRated()
    {

    }

    private void listHighestRevenue()
    {

    }

    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];

                String director = movieFromCSV[2];

                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String movieGenres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, movieGenres, userRating, year, revenue);
                movies.add(nextMovie);

                for (String actor : cast.split("\\|")) {
                    boolean dupe = false;
                    for (Person person : people) {
                        if (person.getName().equals(actor) && person.getRole().equals("Actor")) {
                            dupe = true;
                            break;
                        }
                    }
                    if (!dupe) {
                        people.add(new Person(actor, "Actor"));
                        people.get(people.size() - 1).castInMovie(nextMovie);
                    }
                }

                for (String direct : director.split("\\|")) {
                    boolean dupe = false;
                    for (Person person : people) {
                        if (person.getName().equals(direct) && person.getRole().equals("Director")) {
                            dupe = true;
                            break;
                        }
                    }
                    if (!dupe) {
                        people.add(new Person(direct, "Director"));
                        people.get(people.size() - 1).castInMovie(nextMovie);
                    }
                }

                String[] genresArr = movieGenres.split("\\|");
                for (String genre : genresArr) {
                    if (genres.contains(genre)) {
                        continue;
                    } else {
                        genres += genre + "\\|";
                    }
                }

            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}