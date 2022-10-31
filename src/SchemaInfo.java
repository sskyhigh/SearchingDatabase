import javax.xml.transform.Result;
import java.sql.*;

public class SchemaInfo {
    public static void main(String[] args) throws SQLException {
        String catalog = null;
        String schemaPattern = null;
        String tableNamePattern = null;
        String columnNamePattern = null;
        String[] Type = null;

        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Magnum12!");

            // get metabase data
            DatabaseMetaData metaData = connection.getMetaData();

            System.out.println("List of Tables");
            System.out.println("--------------");

            resultSet = metaData.getTables(catalog, schemaPattern, tableNamePattern, Type);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("TABLE_NAME"));
            }

            System.out.println("\n\nList of columns");
            System.out.println("-------------------");
            resultSet = metaData.getColumns(catalog, schemaPattern, "employees", columnNamePattern);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("COLUMN_NAME"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            close(connection, resultSet);
        }
    }

    private static void close(Connection connection, ResultSet set) throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (set != null) {
            set.close();
        }
    }
}
