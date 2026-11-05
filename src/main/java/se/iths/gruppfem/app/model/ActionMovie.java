package se.iths.gruppfem.app.model;

public class ActionMovie extends Movie {

    @Override
    public String toString() {
        return "ActionMovie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

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
