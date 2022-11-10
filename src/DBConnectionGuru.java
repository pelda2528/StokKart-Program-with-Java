import java.sql.*;
import java.sql.DriverManager;


public class DBConnectionGuru {

	public static void main(String[] args) {
		try{
		//	@SuppressWarnings("unused")
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/gurustokkartdb","root","99911152pO*");
			System.out.println("Database'e basariyla baglanilmistir.");
			
		}catch(Exception e) {
			System.out.println("Database'e baglanilamamistir.");
		}

	}

	public static Connection dbConnector() {
		
		return null;
	}

} 






























/**
public class DBConnection{
    
    private Connection connection;

    public DBConnection(String host, int puerto, String database, String usuario, String password) {
        try {
            if (connection != null && !connection.isClosed()) {
                
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gurustokkartdb","root","99911152pO*");
            System.out.println("Database'e basariyla baglanilmistir.");
            
        } catch(SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Database'e baglanilamamistir.", e);   
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
public static Connection dbConnector() {
		
		return null;
	}
} */