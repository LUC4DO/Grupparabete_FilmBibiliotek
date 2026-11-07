package se.iths.gruppfem.app.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.gruppfem.app.model.HorrorMovie;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HorrorMovieStorageTest {
    HorrorMovieStorage storage;
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        Path file = tempDir.resolve("horrormovies-test");
        storage = new HorrorMovieStorage(file);

    }

    @Test
    @DisplayName("A saved movie can be found by its id")
    void savedMovieCanBeFoundById() throws IOException {
        HorrorMovie movie = new HorrorMovie(1, "test");
        storage.save(movie);

        HorrorMovie result = storage.findById(movie.getId());

        assertEquals(1, result.getId());
        assertEquals("test", result.getTitle());
    }

    @Test
    @DisplayName("Saving a movie with an already existing id is ignored")
    void saveIgnoresMovieWithDuplicateId() throws IOException {
        HorrorMovie movie1 = new HorrorMovie(1, "test");
        HorrorMovie movie2 = new HorrorMovie(1, "test2");

        storage.save(movie1);
        storage.save(movie2);

        List<HorrorMovie> result = storage.findAll();

        assertEquals(1, result.size());
        assertEquals("test", result.getFirst().getTitle());
    }

    @Test
    @DisplayName("findAll returns an empty list when the file does not exist")
    void findAllReturnsEmptyListWhenFileDoesNotExist() throws IOException {
        List<HorrorMovie> movies = storage.findAll();

        assertTrue(movies.isEmpty());
    }

    @Test
    @DisplayName("findById returns null when no movie has the given id")
    void findByIdReturnsNullWhenMovieDoesNotExist() throws IOException {
        HorrorMovie movie1 = new HorrorMovie(1, "test");
        storage.save(movie1);
        HorrorMovie result = storage.findById(2);
        assertNull(result);
    }
}
