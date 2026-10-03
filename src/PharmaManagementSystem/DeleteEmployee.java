package PharmaManagementSystem;

import java.sql.SQLException;
import java.util.Scanner;

public class DeleteEmployee implements Option{

	@Override
	public String getOption() {
		// TODO Auto-generated method stub
		return "Delete Employee";
	}

	@Override
	public void oper(Database database, Scanner s, Employee e) {
		System.out.println("Enter Employee ID (int): (-1 to show all Employees)");
		int ID=s.nextInt();
		while(ID<0) {
			new ReadAllEmployees().oper(database, s, e);
			System.out.println("Enter Employee ID (int): (-1 to show all Employees)");
			ID=s.nextInt();
		}
		String delete ="DELETE FROM `employees` WHERE `ID` = "+ID+";";
        try {
			database.getStatement().execute(delete);
			System.out.println("Employee deleted successfully");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

}
