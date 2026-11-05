package se.iths.gruppfem.app.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.gruppfem.app.model.HorrorMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class HorrorMovieStorage implements MovieStorage<HorrorMovie> {
    private final Path path;
    private final ObjectMapper mapper = new ObjectMapper();

    public HorrorMovieStorage(Path path) {
        this.path = path;
    }


    @Override
    public void save(HorrorMovie movie) throws IOException {
        if (findById(movie.getId()) != null) {
            return;
        }
        List<HorrorMovie> horrorMovies = new ArrayList<>(findAll());
        horrorMovies.add(movie);
        mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), horrorMovies);

    }

    @Override
    public HorrorMovie findById(int id) throws IOException {
        List<HorrorMovie> horrorMovies = findAll();
        for (HorrorMovie movie : horrorMovies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public List<HorrorMovie> findAll() throws IOException {
        if (Files.notExists(path)) {
            return List.of();
        }
        return mapper.readValue(path.toFile(), new TypeReference<List<HorrorMovie>>() {
        });
    }
}
