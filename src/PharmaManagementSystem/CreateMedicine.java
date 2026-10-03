package PharmaManagementSystem;

import java.sql.SQLException;
import java.util.Scanner;

public class CreateMedicine implements Option{


	@Override
	public void oper(Database database, Scanner s, Employee e) {
		
		System.out.println("Enter Name:");
		String name=s.next();
		System.out.println("Enter Type:");
		String type=s.next();
		System.out.println("Enter Qty:");
		int qty=s.nextInt();
		System.out.println("Enter Manufacture Date (yyyy-dd-MM):");
		String manufactureDate=s.next();
		System.out.println("Enter Expiry Date (yyyy-dd-MM):");
		String expiryDate=s.next();
		System.out.println("Enter Company:");
		String company=s.next();
		System.out.println("Enter Cost(double):");
		double cost=s.nextDouble();
		String insert="INSERT INTO `medicines`(`Name`, `Type`, `Qty`, `ManufactureDate`,"
			+ " `ExpiryDate`, `Company`, `Cost`) VALUES ('"+name+"','"+type+"',"
			+ "'"+qty+"','"+manufactureDate+"','"+expiryDate+"','"+company+"','"+cost+"');";
	
		try {
			database.getStatement().execute(insert);
			System.out.println("Employee added successfully");
		} catch(SQLException e1) {
			e1.printStackTrace();
		}
}
	@Override
	public String getOption() {
		return "Add new Medicine";
	}

}
