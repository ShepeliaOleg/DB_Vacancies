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

    public void difference() throws SQLException {
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

    public void closedVacancy() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:derby:E://ProjectJava//DBVacancies//DB");
        PreparedStatement statement = connection.prepareStatement("select title, link from vacancy except select title, link from newvacancy");
        ResultSet resultSet = statement.executeQuery();

        boolean status = false;
        SimpleDateFormat closingTime = new SimpleDateFormat("yyyy-MM-dd");

       while (resultSet.next()){
           String link = resultSet.getString("link");
           String title = resultSet.getString("title");
           System.out.println(link + " - " + title);
           PreparedStatement statement1 = connection.prepareStatement("update vacancy set status = ?, closingTime = ? where title = ?");
           statement1.setBoolean(1, status);
           statement1.setString(2, closingTime.format(new Date(System.currentTimeMillis())).toString());
           statement1.setString(3, title);
           statement1.execute();

        }
        statement.close();
    }

    public void cleanNewvacancy () throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:derby:E://ProjectJava//DBVacancies//DB");
        Statement statement = connection.createStatement();
        statement.execute("delete from newvacancy");
        statement.close();
    }
}
