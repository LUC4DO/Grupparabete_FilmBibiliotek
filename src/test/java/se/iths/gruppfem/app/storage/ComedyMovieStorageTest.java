package se.iths.gruppfem.app.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.gruppfem.app.model.ComedyMovie;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ComedyMovieStorageTest {


    @TempDir
    Path tempDir;

    ObjectMapper mapper = new ObjectMapper();


    @Test
    void SavedAndReadMovie() throws IOException {
        Path path = tempDir.resolve("ComedyMovie.json");

        ComedyMovieStorage storage = new ComedyMovieStorage(path);//instans med sökväg
        ComedyMovie movie = new ComedyMovie(1, "Dum och dummare");
        storage.save(movie);
        ComedyMovie result = storage.findById(1);
        assertEquals(1, result.getId());//kontrollerar ID
        assertEquals("Dum och dummare", result.getTitle());// kontrollerar film titel
        // hittas och stämmer


    }


    @Test
    void Duplicates() throws IOException {

        Path path = tempDir.resolve("ComedyMovie.json");
        ComedyMovieStorage storage = new ComedyMovieStorage(path);//instans med sökväg

        ComedyMovie movie = new ComedyMovie(1, "Dum och dummare");
        storage.save(movie);
        ComedyMovie movie1 = new ComedyMovie(1, "Sälllskapsresan");
        storage.save(movie1);


        List<ComedyMovie> movies = storage.findAll();

        assertEquals(1, movies.size());// kontrollerar att listan innehåller exakt 1 film;
        assertEquals("Dum och dummare", movies.get(0).getTitle());

    }

    @Test
    void FindAllReturnEmpty() throws IOException {

        Path path = tempDir.resolve("ComedyMovie.json");

        ComedyMovieStorage storage = new ComedyMovieStorage(path);
        List<ComedyMovie> movies = storage.findAll();
        assertEquals(0, movies.size());


    }

    @Test
    void FindByIDReturnNullWhenNotFound() throws IOException {

        Path path = tempDir.resolve("ComedyMovie.json");
        ComedyMovieStorage storage = new ComedyMovieStorage(path);
        ComedyMovie movie = new ComedyMovie(1, "Dum och Dummare");
        storage.save(movie);
        ComedyMovie result = storage.findById(99);//returnera null
        assertNull(result);
    }

}









