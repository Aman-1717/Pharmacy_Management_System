package PharmaManagementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
 
	
    public static void main(String[] args){
    	Scanner s =new Scanner(System.in);
    	Database database = new Database();
        System.out.println("Welcome to Pharmacy Management System");
        System.out.println("Enter your email:");
        String email=s.next();
        System.out.println("Enter your password:");
        String password=s.next();
        String select = "SELECT * FROM `employees` WHERE `Email` = '"+email+
        		"' AND `Password` = '"+password+"';";
        try {
        	if(database.getStatement()== null) {
        		System.out.println("Cannot execute query");
        		return ;
        	}
			ResultSet rs=database.getStatement().executeQuery(select);
			if(rs.next()) {
				Employee e;
				int job = rs.getInt("Job");
				switch (job) {
				case 1: {
					e= new Manager();
					break;
				}
				case 2: {
					e= new Pharmacist();
					break;
				}
				case 3: {
					e= new Cashier();
					break;
				}
				default:
					e=new Employee() {
						String getJob() {
							return null;
						}
					};
					break;
				}
				e.setID(rs.getInt("ID"));
				e.setFirstName(rs.getString("FirstName"));
				e.setLastName(rs.getString("LastNAME"));
				e.setEmail(rs.getString("Email"));
				e.setPhoneNumber(rs.getString("PhoneNumber"));
				e.setPassword(rs.getString("Password"));
				e.setDateofBirth(rs.getString("DateofBirth"));
				e.setSalary(rs.getDouble("Salary"));
				e.setWorkHours(rs.getInt("WorkHours"));
				e.setStartTime(rs.getString("StartTime"));
				e.setEndTime(rs.getString("EndTime"));
				e.showOptions(database, s);
			}
			else {
				System.out.println("Incorrect email or password");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
        
    }
}
