package PharmaManagementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PayOrder implements Option{

	@Override
	public String getOption() {
		// TODO Auto-generated method stub
		return "Pay Order";
	}

	@Override
	public void oper(Database database, Scanner s, Employee e) {
		System.out.println("Enter Order ID (int)L (-1 to show all orders)");
		int ID=s.nextInt();
		while(ID<0) {
			System.out.println("Enter Order ID (int)L (-1 to show all orders)");
			ID=s.nextInt();
		}
		String select ="SELECT * FROM `orders` WHERE `ID` = "+ID+";";
		try {
			ResultSet rs=database.getStatement().executeQuery(select);
			if(rs.next()) {
				double total =rs.getDouble("Total");
				System.out.println("Total: "+total);
				System.out.println("Enter paid (double): ");
				double paid =s.nextDouble();
				if(paid<total) {
					System.out.println("Paid is less than total!");
					return;
				}else {
					double change=paid-total;
					System.out.println("Change: "+change);
					String payDateTime =DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm:ss").format(LocalDateTime.now());
					String update="UPDATE `orders` SET `Cashier`='"+e.getID()+"',`Paid`='"+paid+"',`Change_`='"+change+"',`PayDateTime`='"+payDateTime+"' WHERE `ID` = "+ID+";";
					database.getStatement().execute(update);
					System.out.println("Order paid successfully!");
				}
			}else {
				System.out.println("Order Doesnt't Exist.");
			}
		}
	catch(SQLException e1){
		e1.printStackTrace();
	}
		
	}

}
