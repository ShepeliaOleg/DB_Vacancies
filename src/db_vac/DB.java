package db_vac;

import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Created by New on 24.04.14.
 */
public class DB {

    public void recordInTheTable(String linke, String title) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:derby:E://ProjectJava//DBVacancies//db1");
        PreparedStatement statement = connection.prepareStatement("insert into table1 (title, linke) values (?,?)");
        statement.setString(1, title);
        statement.setString(2, linke);
        statement.execute();
        statement.close();
    }

    public void writeDB (String link, String title) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:derby:E://ProjectJava//DBVacancies//DB");
        PreparedStatement statement = connection.prepareStatement("insert into newvacancy (title, link) values (?,?)");
        statement.setString(1, title);
        statement.setString(2, link);
        statement.execute();
        statement.close();
    }

    public void diff () throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:derby:E://ProjectJava//DBVacancies//DB");
        PreparedStatement statement = connection.prepareStatement("select title, link from newvacancy except select title, link from vacancy");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            String title = resultSet.getString("title");
            String link = resultSet.getString("link");
            boolean status = true;
            SimpleDateFormat createTime = new SimpleDateFormat("yyyy-MM-dd");

            statement = connection.prepareStatement("insert into vacancy (title, link, status, createTime) values (?, ?, ?, ?)");
            statement.setString(1, title);
            statement.setString(2, link);
            statement.setBoolean(3, status);
            statement.setString(4, createTime.format(new Date(System.currentTimeMillis())).toString());
            statement.execute();
            System.out.println(title + "  -  " + link);
        }
        statement.close();

    }

}
