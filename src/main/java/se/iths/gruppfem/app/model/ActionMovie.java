package se.iths.gruppfem.app.model;

public class ActionMovie extends Movie {

    @Override
    public String category() {
        return "Action";
    }

    public ActionMovie() {
    }

    public ActionMovie(int id, String title) {
        super(id, title);
    }
}
