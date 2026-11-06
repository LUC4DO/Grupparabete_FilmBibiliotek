package se.iths.gruppfem.app.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.gruppfem.app.model.DramaMovie;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class DramaMovieStorageTest {

    @TempDir
    Path tempDir;

    @Test
    void saveAndReadMovie() throws IOException {
        Path file = tempDir.resolve("movie.json");
        DramaMovieStorage storage = new DramaMovieStorage(file);

        DramaMovie movie = new DramaMovie(1, "The Godfather");

        storage.save(movie);

        DramaMovie savedMovie = storage.findById(1);

        assertNotNull(savedMovie);
        assertEquals("The Godfather", savedMovie.getTitle());
    }

    @Test
    void saveMovieWithDuplicateId() throws IOException {
        Path file = tempDir.resolve("movie.json");
        DramaMovieStorage storage = new DramaMovieStorage(file);

        DramaMovie firstMovie = new DramaMovie(1, "The Godfather");
        DramaMovie secondMovie = new DramaMovie(1, "The Shawshank Redemption");

        storage.save(firstMovie);
        storage.save(secondMovie);

        assertEquals(1, storage.findAll().size());
        assertEquals("The Godfather", storage.findById(1).getTitle());
    }

    @Test
    void findAllReturnsEmptyListWhenFileDoesNotExist() throws IOException {
        Path file = tempDir.resolve("movie.json");
        DramaMovieStorage storage = new DramaMovieStorage(file);

        assertTrue(storage.findAll().isEmpty());
    }

    @Test
    void findByIdReturnsNullWhenMovieDoesNotExist() throws IOException {
        Path file = tempDir.resolve("movie.json");
        DramaMovieStorage storage = new DramaMovieStorage(file);

        DramaMovie movie = storage.findById(99);

        assertNull(movie);
    }
}
