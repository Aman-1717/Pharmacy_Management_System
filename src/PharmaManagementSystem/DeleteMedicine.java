package PharmaManagementSystem;

import java.sql.SQLException;
import java.util.Scanner;

public class DeleteMedicine implements Option {

	@Override
	public String getOption() {
		// TODO Auto-generated method stub
		return "Delete Medicine";
	}

	@Override
	public void oper(Database database, Scanner s, Employee e) {
		
		System.out.println("Enter Medicine ID (int): (-1 to show all Medicines)");
		int ID=s.nextInt();
		while(ID<0) {
			new ReadAllMedicines().oper(database, s, e);
			System.out.println("Enter Medicine ID (int): (-1 to show all Medicines)");
			ID=s.nextInt();
		}
		String delete ="DELETE FROM `medicines` WHERE `ID` = "+ID+";";
        try {
			database.getStatement().execute(delete);
			System.out.println("Medicine deleted successfully");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

}
