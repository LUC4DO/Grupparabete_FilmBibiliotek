package se.iths.gruppfem.app.storage;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.gruppfem.app.model.ComedyMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ComedyMovieStorage implements MovieStorage<ComedyMovie> {

    ObjectMapper mapper = new ObjectMapper();
    Path path = Path.of("data", "ComedyMovies.json");


    public ComedyMovieStorage(Path path) {
        this.path = path;
    }


    @Override
    public void save(ComedyMovie movie) throws IOException {
        if (findById(movie.getId()) != null) {
            return;
        }

        List<ComedyMovie> ComedyMovie = new ArrayList<>(findAll());
        ComedyMovie.add(movie);
        mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), ComedyMovie);

    }

    @Override
    public ComedyMovie findById(int id) throws IOException {
        List<ComedyMovie> comedyMovies = findAll();
        for (ComedyMovie Movie : comedyMovies) {
            if (Movie.getId() == id) {
            }
        }
        return null;


    }

    @Override
    public List<ComedyMovie> findAll() throws IOException {
        if (Files.notExists(path)) {
            return List.of();
        }
        return mapper.readValue(path.toFile(), new TypeReference<List<ComedyMovie>>() {
        });
    }
}