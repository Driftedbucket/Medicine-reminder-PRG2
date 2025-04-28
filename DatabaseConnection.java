import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/medicinereminder"; // Change to your DB name
    private static final String USER = "root"; // Change to your MySQL username
    private static final String PASSWORD = "muddysituation" ; // Change to your MySQL password

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!"); // Debugging
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
