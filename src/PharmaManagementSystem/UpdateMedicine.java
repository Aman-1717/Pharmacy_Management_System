package PharmaManagementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateMedicine implements Option{

	@Override
	public String getOption() {
		return "Update Medicine";
	}

	@Override
	public void oper(Database database, Scanner s, Employee e) {
		
		System.out.println("Enter Medicine ID(int): (-1 to show all medicines)");
		int ID=s.nextInt();
		while(ID<0) {
			new ReadAllMedicines().oper(database, s, e);
			System.out.println("Enter Medicine ID(int): (-1 to show all medicines)");
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
				
				System.out.println("Enter Medicine Name (-1 to keep "+med.getName()+");");
				String name=s.next();
				if(!name.equals("-1"))med.setName(name);
				
				System.out.println("Enter Medicine Type (-1 to keep "+med.getType()+");");
				String type=s.next();
				if(!type.equals("-1"))med.setType(type);
				
				System.out.println("Enter Medicine Qty (-1 to keep "+med.getQty()+");");
				int qty=s.nextInt();
				if(qty!=-1)med.setQty(qty);
				
				System.out.println("Enter Medicine Manufacture Date(-1 to keep "+med.getManufactureDate()+");");
				String manufactureDate=s.next();
				if(!manufactureDate.equals("-1"))med.setManufactureDate(manufactureDate);
				
				System.out.println("Enter Medicine Expiry Date (-1 to keep "+med.getExpiryDate()+");");
				String expiryDate=s.next();
				if(!expiryDate.equals("-1"))med.setExpiryDate(expiryDate);
				
				System.out.println("Enter Medicine Company (-1 to keep "+med.getCompany()+");");
				String company=s.next();
				if(!company.equals("-1"))med.setCompany(company);
				
				System.out.println("Enter Medicine Cost (-1 to keep "+med.getCost()+");");
				double cost=s.nextDouble();
				if(cost!=-1)med.setCost(cost);
				
				String Update="UPDATE `medicines` SET `Name`='"+med.getName()+"',`Type`='"+med.getType()+"',"
						+ "`Qty`='"+med.getQty()+"',`ManufactureDate`='"+med.getManufactureDate()+"',`ExpiryDate`='"+med.getExpiryDate()+"',"
						+ "`Company`='"+med.getCompany()+"',`Cost`='"+med.getCost()+"' WHERE `ID` = "+med.getID()+";";
				
				try {
					database.getStatement().execute(Update);
					System.out.println("Medicine updated");
				}catch(SQLException e1){
					e1.printStackTrace();
				}
				
			}
			else {
				System.out.println("Invalid ID");
			}
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
	}

}
