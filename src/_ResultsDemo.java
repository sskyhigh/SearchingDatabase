import java.sql.*;

public class _ResultsDemo {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String holder = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Magnum12!");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select id, last_name, first_name, salary from employees");

            ResultSetMetaData setMetaData = resultSet.getMetaData();

            int columnCount = setMetaData.getColumnCount();
            System.out.println("Columns: " + columnCount + "\n");

            for (int col = 1; col <= columnCount; ++col) {
                System.out.println("Column Name: " + setMetaData.getColumnName(col));
                System.out.println("Column Type name: " + setMetaData.getColumnTypeName(col));
                // This replaces the 0's and 1's
                holder = ((setMetaData.isNullable(col) == 0) ? "No" : "Yes");
                System.out.println("Is nullable: " + holder);
                System.out.println("Is auto increment: " + setMetaData.isAutoIncrement(col));
                System.out.println();
            }
        } catch (Exception exception) {exception.printStackTrace();
        } finally {close(connection, statement, resultSet);}
    }

    // as the name stated:　closes the connection. 
    private static void close(Connection connection,
                              Statement statement, ResultSet set) throws SQLException {
        try {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
            if (set != null) set.close();
        } catch (Exception exception) {exception.printStackTrace();}
    }
}
