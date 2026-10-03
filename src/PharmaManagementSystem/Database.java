package PharmaManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private String user ="root";
	private String pass ="";
	private String url="jdbc:mysql://localhost:3307/pharmacy";
	private Statement statement;
	
	public Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, pass);
			statement = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			System.out.println("Database connected successfully");
		}
		catch (ClassNotFoundException e) {
			System.out.println("MYSQL Driver not found");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Database connection failed");
			e.printStackTrace();
		}
	}
	public Statement getStatement() {
		return statement;
	}

}
