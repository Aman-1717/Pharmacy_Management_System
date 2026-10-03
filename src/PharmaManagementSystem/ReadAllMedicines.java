package PharmaManagementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadAllMedicines implements Option {

	@Override
	public String getOption() {
		return "View All Medicines";
	}

	@Override
	public void oper(Database database, Scanner s, Employee e) {
		String select ="SELECT * FROM `medicines`;";
		
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
