package db_vac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by New on 24.04.14.
 */
public class Download {

    public String  download () throws Exception {

        URL url = new URL("http://jobs.dou.ua/vacancies/feeds/?search=Java");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));
        String xmlString
                = null;
        StringBuilder builder = new StringBuilder();
        try {
            while (true){
                xmlString = reader.readLine();
                if (xmlString == null){
                    break;
                }
                builder.append(xmlString);
            }} finally {
            reader.close();
        }
        return builder.toString();
    }

}
