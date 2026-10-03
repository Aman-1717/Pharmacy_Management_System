package PharmaManagementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateOrder implements Option {

	@Override
	public String getOption() {
		return "Create new Order";
	}

	@Override
	public void oper(Database database, Scanner s, Employee e) {
		int pharmacistID=e.getID();
		
		ArrayList<Integer> medicineIDs=new ArrayList<>();
		ArrayList<Integer> qty=new ArrayList<>();
		System.out.println("Select Medicines: (-1 to checkout)");
		int selected;
		do {
			new ReadAllMedicines().oper(database, s, e);
			selected=s.nextInt();
			if(selected>0) {
				medicineIDs.add(selected);
				System.out.println("Enter Qty:");
				qty.add(s.nextInt());
				}
		}while(selected>0);
		
		ArrayList<Medicine> medicines=new ArrayList<>();
		for(int ID:medicineIDs) {
			String select =" SELECT * FROM `medicines` WHERE `ID` = '"+ID+"';";
			
			try {
				ResultSet rs=database.getStatement().executeQuery(select);
				if(rs.next()) {
					Medicine med =new Medicine();
					med.setID(rs.getInt("ID"));
					med.setName(rs.getString("Name"));
					med.setType(rs.getString("Type"));
					med.setManufactureDate(rs.getString("ManufactureDate"));
					med.setExpiryDate(rs.getString("ExpiryDate"));
					med.setCompany(rs.getString("Company"));
					med.setCost(rs.getDouble("Cost"));
					medicines.add(med);
				}
				else {
					System.out.println("Invalid ID: "+ID);
				}
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		double total=0;
		for(int i=0;i<medicines.size();i++) {
			Medicine med=medicines.get(i);
			med.setQty(qty.get(i));
			System.out.println(med.getName()+"\t"+med.getCost()+"\t"+med.getQty());
			total+=med.getCost()*med.getQty();
		}
		System.out.println("Total: "+total);
		System.out.println("1. Confirm Order");
		if(s.nextInt()==1) {
			String dateTime=DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm:ss")
					.format(LocalDateTime.now());
			String insert1="INSERT INTO `orders`(`Cashier`, `Pharmacist`, `DateTime`, `Total`, `Paid`, `Change_`, `PayDateTime`) "
					+ "VALUES ('0','"+pharmacistID+"','"+dateTime+"','"+total+"','0','0','1000-01-01 01:01:01');";
			try {
				database.getStatement().execute(insert1);
				ResultSet rs =database.getStatement().executeQuery("SELECT LAST_INSERT_ID();");
				rs.next();
				int orderID=rs.getInt("LAST_INSERT_ID()");
				
				
				for(Medicine med:medicines) {
					String insert ="INSERT INTO `ordersitems`(`Order`, `Medicine`, `Qty`) VALUES ('"+orderID+"','"+med.getID()+"','"+med.getQty()+"');";
					database.getStatement().execute(insert);
				}
				System.out.println("Order created successfully");
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			
		}
		
	}

}
