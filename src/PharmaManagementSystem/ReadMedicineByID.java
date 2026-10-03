package PharmaManagementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ReadMedicineByID implements Option{

	@Override
	public String getOption() {
		
		return "Search Medicine By ID";
	}

	@Override
	public void oper(Database database, Scanner s, Employee e) {
		System.out.println("Enter Medicine ID (int): (-1 to show all medicines)");
		int ID=s.nextInt();
		while(ID<0) {
			new ReadAllMedicines().oper(database, s, e);
			System.out.println("Enter Medicine ID (int): (-1 to show all medicines)");
			ID=s.nextInt();
		}
		String select =" SELECT * FROM `medicines` WHERE `ID` = '"+ID+"';";
		
		try {
			ResultSet rs=database.getStatement().executeQuery(select);
			if(rs.next()) {
				Medicine med =new Medicine();
				med.setID(rs.getInt("ID"));
				med.setName(rs.getString("Name"));
				med.setType(rs.getString("Type"));
				med.setQty(rs.getInt("Qty"));
				med.setManufactureDate(rs.getString("ManufactureDate"));
				med.setExpiryDate(rs.getString("ExpiryDate"));
				med.setCompany(rs.getString("Company"));
				med.setCost(rs.getDouble("Cost"));
				med.print();
			}
			else {
				System.out.println("Invalid ID");
			}
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
	}

}
