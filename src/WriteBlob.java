import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WriteBlob {
    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        FileInputStream inputStream = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Magnum12!");
            String addInfo = "update employees set resume=? where email='john.doe@foo.com'";
            preparedStatement = connection.prepareStatement(addInfo);

            File file = new File("sample_resume.pdf");
            inputStream = new FileInputStream(file);
            preparedStatement.setBinaryStream(1, inputStream);

            System.out.println("Reading input file: " + file.getAbsoluteFile());

            // executing update
            System.out.println("\nStoring pdf in database:");
            System.out.println(addInfo);

            preparedStatement.executeUpdate();

            System.out.println("Update done!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, preparedStatement, inputStream);
        }
    }

    private static void close(Connection connection,
                             PreparedStatement statements,
                             FileInputStream stream) throws SQLException, IOException {
        if (connection != null) {
            connection.close();
        }
        if (statements != null) {
            statements.close();
        }
        if (stream != null) {
            stream.close();
        }
    }
}
