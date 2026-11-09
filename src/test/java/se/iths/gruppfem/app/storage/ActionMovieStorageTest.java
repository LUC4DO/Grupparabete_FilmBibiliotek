package se.iths.gruppfem.app.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.gruppfem.app.model.ActionMovie;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ActionMovieStorageTest {
    ActionMovieStorage storage;

    @TempDir
    Path tempdir;

    @BeforeEach
    public void setUp() {
        Path path = tempdir.resolve("action-movies.json");
        storage = new ActionMovieStorage(path);
    }

    @Test
    @DisplayName("A saved movie can be found by its id")
    void savedMovieFoundById() throws IOException {
        ActionMovie movie = new ActionMovie(1, "test");
        storage.save(movie);

        ActionMovie result = storage.findById(movie.getId());

        assertEquals(1, result.getId());
        assertEquals("test", result.getTitle());
    }

    @Test
    @DisplayName("Saving a movie with an already existing id is ignored")
    void saveIgnoresMovieDuplicateId() throws IOException {
        ActionMovie movie1 = new ActionMovie(1, "test");
        ActionMovie movie2 = new ActionMovie(1, "test2");

        storage.save(movie1);
        storage.save(movie2);

        List<ActionMovie> result = storage.findAll();

        assertEquals(1, result.size());
        assertEquals("test", result.getFirst().getTitle());
    }

    @Test
    @DisplayName("findAll returns an empty list when the file does not exist")
    void findAllEmptyWhenFileNotExist() throws IOException {
        List<ActionMovie> movies = storage.findAll();

        assertTrue(movies.isEmpty());
    }

    @Test
    @DisplayName("findById returns null when no movie has the given id")
    void findByIdNullWhenMovieNotExist() throws IOException {
        ActionMovie movie1 = new ActionMovie(1, "test");
        storage.save(movie1);
        ActionMovie result = storage.findById(2);
        assertNull(result);
    }
}
