package kindof.gokzachievementsweb.dao;

import java.sql.*;

import static kindof.gokzachievementsweb.Globals.*;

public class DAO {
    protected static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected static void execute(String query) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static ResultSet executeQuery(String query) {
        try {
            return connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static void add(String table, String... values) {
        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            sb.append("'").append(value).append("', ");
        }
        sb.delete(sb.length() - 2, sb.length());
        execute("INSERT INTO " + table + " VALUES(" + sb + ")");
    }

    protected static ResultSet selectBy(String table, String column, String value) {
        return executeQuery("SELECT * FROM " + table + " WHERE " + column + " = '" + value + "'");
    }

    protected static void updateBy(String table, String column, String value, String... updateValues) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < updateValues.length; i += 2) {
            sb.append(updateValues[i - 1]).append(" = '").append(updateValues[i]).append("', ");
        }
        sb.delete(sb.length() - 2, sb.length());
        execute("UPDATE " + table + " SET " + sb + " WHERE " + column + " = '" + value + "'");
    }
}
