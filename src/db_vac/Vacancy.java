package db_vac;

/**
 * Created by New on 24.04.14.
 */
public class Vacancy {


    private String title;
    private String link;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return link + "  -  "+ title + ";";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
