package se.iths.gruppfem.app.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.gruppfem.app.model.DramaMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DramaMovieStorage implements MovieStorage<DramaMovie> {
    //ObjectMapper är de som omvandlar Java till Json.
    private final ObjectMapper mapper = new ObjectMapper();

    //Sökvägen till Json filen.
    private final Path file;

    //Tar emot sökvägen.
    public DramaMovieStorage(Path file) {
        this.file = file;
    }

    //findAll metod
    @Override
    public List<DramaMovie> findAll() throws IOException {

        if (!Files.exists(file)) {
            return new ArrayList<>();
        }

        return mapper.readValue(
                file.toFile(),
                new TypeReference<List<DramaMovie>>() {
                }
        );
    }

    //findById metod
    @Override
    public DramaMovie findById(int id) throws IOException {
        return findAll().stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElse(null);
    }

    //save metod
    @Override
    public void save(DramaMovie movie) throws IOException {

        if (findById(movie.getId()) != null) {
            return;
        }

        List<DramaMovie> movies = findAll();
        movies.add(movie);

        mapper.writeValue(file.toFile(), movies);
    }
}