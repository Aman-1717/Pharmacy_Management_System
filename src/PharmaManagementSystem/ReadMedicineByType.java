package PharmaManagementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadMedicineByType implements Option {

	@Override
	public String getOption() {
		return "Search Medicine By Type";
	}

	@Override
	public void oper(Database database, Scanner s, Employee e) {
		// TODO Auto-generated method stub
		System.out.println("Enter Medicine Type");
		String type=s.next();
		String select ="SELECT * FROM `medicines` WHERE `Type` LIKE '"+type+"';";
		ArrayList<Medicine> medicines =new ArrayList<>();
		try {
		ResultSet rs=database.getStatement().executeQuery(select);
		while(rs.next()) {
			Medicine med =new Medicine();
			med.setID(rs.getInt("ID"));
			med.setName(rs.getString("Name"));
			med.setType(rs.getString("Type"));
			med.setQty(rs.getInt("Qty"));
			med.setManufactureDate(rs.getString("ManufactureDate"));
			med.setExpiryDate(rs.getString("ExpiryDate"));
			med.setCompany(rs.getString("Company"));
			med.setCost(rs.getDouble("Cost"));
			medicines.add(med);
		}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		for(Medicine med : medicines) {
			med.print();
		}
	}

}
