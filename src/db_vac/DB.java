package db_vac;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by New on 24.04.14.
 */
public class DB {

    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    public void recordInTheTable(String linke, String title) throws SQLException {

        connection = DriverManager.getConnection("jdbc:derby:E://ProjectJava//DB_Vacancies//DB");
        PreparedStatement statement = connection.prepareStatement("insert into vacancy (title, linke) values (?,?)");
        statement.setString(1, title);
        statement.setString(2, linke);
        statement.execute();
        statement.close();
    }

    public void createTable (String tableName) throws Exception {
        connection = DriverManager.getConnection("jdbc:derby:E://ProjectJava//DB_Vacancies//DB");
        preparedStatement = connection.prepareStatement("" +
                "create table ? " +
                "(id int not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1))," +
                "title varchar (250)," +
                "link varchar (250)," +
                "closed BOOLEAN," +
                "creationTime DATE," +
                "closingTime DATE ");
        preparedStatement.setString(1, tableName);
        preparedStatement.execute();
        preparedStatement.close();
    }
}
