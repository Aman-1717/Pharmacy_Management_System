package PharmaManagementSystem;

import java.sql.SQLException;
import java.util.Scanner;

public class ChangePassword implements Option{

	@Override
	public String getOption() {
		// TODO Auto-generated method stub
		return "Change Password";
	}

	@Override
	public void oper(Database database, Scanner s, Employee e) {
		System.out.println("Enter your old password");
		String oldpassword=s.next();
		if(!e.getPassword().equals(oldpassword)) {
			System.out.println("Incorrect password!");
			return;
		}
		else {
			String password,confirmPassword;
			do {
				System.out.println("Enter new Password");
				password=s.next();
				System.out.println("Confirm Password!");
				confirmPassword=s.next();
			}while(!password.equals(confirmPassword));
			String update = "UPDATE `employees` SET `Password`='"+password+"' WHERE `ID` = "+e.getID()+";";
		try {
			database.getStatement().execute(update);
			System.out.println("Password changed successfully");
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		}
	}

}
