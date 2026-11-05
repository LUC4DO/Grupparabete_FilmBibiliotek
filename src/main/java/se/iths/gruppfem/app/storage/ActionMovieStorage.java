package se.iths.gruppfem.app.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.gruppfem.app.model.ActionMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ActionMovieStorage implements MovieStorage<ActionMovie> {
    Path path = Path.of("data", "action-movies.json");
    ObjectMapper mapper = new ObjectMapper();

    public ActionMovieStorage(Path path) {
        this.path = path;
    }

    @Override
    public void save(ActionMovie movie) throws IOException {
        //Så att man bara kan sätta ett id högre än noll
        if (movie.getId() <= 0) {
            IO.println("The product id has to be positive");
            return;
        }
        // Om findByID() returnerar filmen (ID existerar redan) -> save metoden avbryts
        if (findById(movie.getId()) != null) {
            IO.println("This Id already exist");
            return;
        }
        //Skapar en lista med hjälpa av findAll() metoden, -> lägger till film -> skriver om JSON filen
        List<ActionMovie> actionMovieList = findAll();
        actionMovieList.add(movie);
        mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), actionMovieList);
    }

    @Override
    public ActionMovie findById(int id) throws IOException {
        for (ActionMovie m : findAll()) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    @Override
    public List<ActionMovie> findAll() throws IOException {
        if (Files.notExists(path)) {
            return List.of();
        }
        return mapper.readValue(path.toFile(), new TypeReference<List<ActionMovie>>() {
        });
    }
}
