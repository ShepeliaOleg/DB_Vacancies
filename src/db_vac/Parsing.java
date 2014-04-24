package db_vac;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by New on 24.04.14.
 */
public class Parsing {

    List<Vacancy> vacancies = new ArrayList<Vacancy>();

    public void Parsing(String string) throws SQLException {

        String [] item = string.split("<item>");
        int i = 1;
        for (String line : item){
            String link1 = line.split("link")[1];
            String link2 = link1.split(">")[1];
            String link = link2.split("</")[0];
            String title1 = line.split("title")[1];
            String title2 = title1.split(",")[0];
            String title = title2.split(">")[1];
            Vacancy vacancy = new Vacancy();
            if (link != null){
                vacancy.setLink(link);
            } if (title != null){
                vacancy.setTitle(title);
            }
            vacancies.add(vacancy);
            //db.writeBD(link,title);
        }
    }

    public void print (Collection collection){
        Iterator <Vacancy> iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
