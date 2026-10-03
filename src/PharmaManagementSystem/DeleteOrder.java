package PharmaManagementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteOrder implements Option{

	@Override
	public String getOption() {
		// TODO Auto-generated method stub
		return "Delete Order";
	}

	@Override
	public void oper(Database database, Scanner s, Employee e) {
		System.out.println("Enter Order ID (int): (-1 to show all orders)");
		int ID=s.nextInt();
		while(ID<1) {
			new ReadAllEmployees().oper(database, s, e);
			System.out.println("Enter Order ID (int): (-1 to show all orders)");
			ID=s.nextInt();
		}
		String select = "SELECT `Total`, `Paid` FROM `orders` WHERE `ID` = "+ID+" ;";
        try {
			ResultSet rs= database.getStatement().executeQuery(select);
			if(rs.next()) {
				if(rs.getDouble("Paid")!=0) {
					System.out.println("Refund: "+rs.getDouble("Total"));
				}
				String delete1 = "DELETE FROM `ordersitems` WHERE `Order`= "+ID+" ;";
				String delete2 = "DELETE FROM `orders` WHERE `ID` = "+ID+" ;";
				database.getStatement().execute(delete1);
				database.getStatement().execute(delete2);
				System.out.println("Order deleted successfully");
			}else {
				System.out.println("Invalid Order ID:"+ID);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
