package db_vac;

/**
 * Created by New on 24.04.14.
 */
public class ConnectionToDB {

    public static void main (String [] a) throws Exception {
        Parsing parsing = new Parsing();
        parsing.Parsing(new Download().download());
        parsing.print(parsing.vacancies);
    }
}
