package PharmaManagementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadOrderItems implements Option{

	@Override
	public String getOption() {
		// TODO Auto-generated method stub
		return "View Order Items";
	}

	@Override
	public void oper(Database database, Scanner s, Employee e) {
		System.out.println("Enter Order ID(int): (-1 to show all orders)");
		int ID=s.nextInt();
		while(ID<1) {
			new ReadAllOrders().oper(database, s, e);
			System.out.println("Enter Order ID(int): (-1 to show all orders)");
			ID=s.nextInt();
		}
		String select="SELECT * FROM `ordersitems` WHERE `Order` = "+ID+";";
		ArrayList<Integer> medicinesIDs=new ArrayList<>();
		ArrayList<Integer> qtys=new ArrayList<>();
		try{
		ResultSet rs=database.getStatement().executeQuery(select);
		while(rs.next()) {
			medicinesIDs.add(rs.getInt("Medicine"));
			qtys.add(rs.getInt("Qty"));
		
		}
		ArrayList<Medicine> medicines=new ArrayList<>();
		for(int i=0;i<medicinesIDs.size();i++) {
			String select1 =" SELECT * FROM `medicines` WHERE `ID` = '"+medicinesIDs.get(i)+"';";
			
				ResultSet rs1=database.getStatement().executeQuery(select1);
				if(rs1.next()) {
					Medicine med =new Medicine();
					med.setID(rs1.getInt("ID"));
					med.setName(rs1.getString("Name"));
					med.setType(rs1.getString("Type"));
					med.setQty(qtys.get(i));
					med.setManufactureDate(rs1.getString("ManufactureDate"));
					med.setExpiryDate(rs1.getString("ExpiryDate"));
					med.setCompany(rs1.getString("Company"));
					med.setCost(rs1.getDouble("Cost"));
					medicines.add(med);
					med.print();
				}
				else {
					System.out.println("Invalid-Medicine ID"+medicinesIDs.get(i));
				}
			}
		
	}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}

}
