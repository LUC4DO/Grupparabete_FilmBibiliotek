package se.iths.gruppfem.app;

import se.iths.gruppfem.app.model.HorrorMovie;
import se.iths.gruppfem.app.storage.HorrorMovieStorage;
import se.iths.gruppfem.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;

import se.iths.gruppfem.app.model.DramaMovie;
import se.iths.gruppfem.app.storage.DramaMovieStorage;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    static void main() {
        MovieStorage<HorrorMovie> horrorStorage = new HorrorMovieStorage(Path.of("data","horrormovies.json"));
        HorrorMovie movie1 = new HorrorMovie(1,"scarymovie");
        HorrorMovie movie2 = new HorrorMovie(2,"scarymovie2");
        HorrorMovie movie3 = new HorrorMovie(3,"scarymovie3");
        HorrorMovie movie4 = new HorrorMovie(4,"scarymovie4");
        HorrorMovie movie5 = new HorrorMovie(5,"scarymovie5");

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
    }
}
