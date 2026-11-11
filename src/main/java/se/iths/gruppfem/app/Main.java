package se.iths.gruppfem.app;

import se.iths.gruppfem.app.model.*;
import se.iths.gruppfem.app.storage.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main() {
        MovieStorage<HorrorMovie> horrorStorage = new HorrorMovieStorage(Path.of("data", "horrormovies.json"));
        HorrorMovie movie1 = new HorrorMovie(1, "scarymovie");
        HorrorMovie movie2 = new HorrorMovie(2, "scarymovie2");
        HorrorMovie movie3 = new HorrorMovie(3, "scarymovie3");
        HorrorMovie movie4 = new HorrorMovie(4, "scarymovie4");
        HorrorMovie movie5 = new HorrorMovie(5, "scarymovie5");

        try {
            horrorStorage.save(movie1);
            horrorStorage.save(movie2);
            horrorStorage.save(movie3);
            horrorStorage.save(movie4);
            horrorStorage.save(movie5);
        } catch (IOException e) {
            IO.readln("Filen existerar inte");
        }

        MovieStorage<DramaMovie> dramaStorage = new DramaMovieStorage(Path.of("data", "dramamovies.json"));
        DramaMovie dramamovie1 = new DramaMovie(1, "The Godfather");
        DramaMovie dramamovie2 = new DramaMovie(2, "The Shawshank Redemption");
        DramaMovie dramamovie3 = new DramaMovie(3, "Forest Gump");
        DramaMovie dramamovie4 = new DramaMovie(4, "The Pianist");
        DramaMovie dramamovie5 = new DramaMovie(5, "The Green Mile");

        try {
            dramaStorage.save(dramamovie1);
            dramaStorage.save(dramamovie2);
            dramaStorage.save(dramamovie3);
            dramaStorage.save(dramamovie4);
            dramaStorage.save(dramamovie5);
        } catch (IOException e) {
            IO.readln("Filen existerar inte");
        }

        MovieStorage<ComedyMovie> ComedyMovieStorage = new ComedyMovieStorage(Path.of("data", "ComedyMovie.json"));
        ComedyMovie ComedyMovie1 = new ComedyMovie(1, "Dum och Dummare");
        ComedyMovie Comedymovie2 = new ComedyMovie(2, "Repmånad");
        ComedyMovie ComedyMovie3 = new ComedyMovie(3, "Dumpad");
        ComedyMovie ComedyMovie4 = new ComedyMovie(4, "Nu blåser vi snuten");
        ComedyMovie Comedymovie5 = new ComedyMovie(5, "Sälskapsresan");

        try {

            ComedyMovieStorage.save(ComedyMovie1);
            ComedyMovieStorage.save(Comedymovie2);
            ComedyMovieStorage.save(ComedyMovie3);
            ComedyMovieStorage.save(ComedyMovie4);
            ComedyMovieStorage.save(Comedymovie5);
        } catch (IOException e) {

            IO.println("Filen finns inte");

        }

        MovieStorage<ActionMovie> actionStorage = new ActionMovieStorage(Path.of("data", "action-movies.json"));
        ActionMovie movieOne = new ActionMovie(1, "Iron man");
        ActionMovie movieTwo = new ActionMovie(2, "Wonder Woman");
        ActionMovie movieThree = new ActionMovie(3, "Batman");
        ActionMovie movieFour = new ActionMovie(4, "Captain America");
        ActionMovie movieFive = new ActionMovie(5, "Flash");

        try {
            actionStorage.save(movieOne);
            actionStorage.save(movieTwo);
            actionStorage.save(movieThree);
            actionStorage.save(movieFour);
            actionStorage.save(movieFive);
        } catch (IOException e) {
            IO.println("Filen kan inte hittas");
        }

        boolean exit = false;
        while (!exit) {
            String choice = IO.readln("Val 1: Visa filmer i en viss kategori\nVal 2: Visa alla filmer \nVal 0: Avsluta applikationen\n");
            try {
                switch (choice.trim()) {
                    case "1" -> {
                        String category = IO.readln("Ange kategori (1: Skräckfilm, 2: Dramafilm, 3: Actionfilm, 4: Komedifilm): ");
                        List<? extends Movie> movies = switch (category.toLowerCase()) {
                            case "1", "skräckfilm" -> horrorStorage.findAll();
                            case "2", "dramafilm" -> dramaStorage.findAll();
                            case "3", "actionfilm" -> actionStorage.findAll();
                            case "4", "komedifilm" -> ComedyMovieStorage.findAll();
                            default -> {
                                IO.println("Okänd kategori");
                                yield List.of();
                            }
                        };
                        for (Movie movie : movies) {
                            IO.println(movie.getTitle());
                        }
                    }
                    case "2" -> {
                        List<Movie> allMovies = new ArrayList<>();
                        allMovies.addAll(horrorStorage.findAll());
                        allMovies.addAll(dramaStorage.findAll());
                        allMovies.addAll(actionStorage.findAll());
                        allMovies.addAll(ComedyMovieStorage.findAll());
                        for (Movie movie : allMovies) {
                            IO.println(movie.getTitle() + " (" + movie.category() + ")");
                        }
                    }
                    case "0" -> exit = true;
                    default -> IO.println("Ogiltigt val, försök igen.");
                }
            } catch (IOException e) {
                IO.println("Kunde inte läsa filmerna: " + e.getMessage());
            }
        }

    }


}
