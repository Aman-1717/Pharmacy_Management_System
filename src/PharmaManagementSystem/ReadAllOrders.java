package PharmaManagementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadAllOrders implements Option{

	@Override
	public String getOption() {
		return "View All Orders";
	}

	@Override
	public void oper(Database database, Scanner s, Employee e) {
		String select ="SELECT * FROM `orders`;";
		try {
			ResultSet rs=database.getStatement().executeQuery(select);
			ArrayList<Integer> pharmacistsIDs =new ArrayList<>();
			ArrayList<Integer> cashierIDs =new ArrayList<>();
			ArrayList<Order> orders=new ArrayList<>();
			while(rs.next()) {
				Order o =new Order();
				o.setID(rs.getInt("ID"));
				pharmacistsIDs.add(rs.getInt("Pharmacist"));
				cashierIDs.add(rs.getInt("Cashier"));
				o.setDateTime(rs.getString("DateTime"));
				o.setTotal(rs.getDouble("Total"));
				o.setPaid(rs.getDouble("Paid"));
				o.setChange(rs.getDouble("Change_"));
				o.setPayDateTime(rs.getString("PayDateTime"));
				orders.add(o);
			}
			for(int i=0;i<orders.size();i++) {
				String select1 ="SELECT `FirstName`, `LastName`, `Email`, `PhoneNumber`, "
						+ "`Password`, `Salary`, `DateofBirth`, `WorkHours`, `StartTime`, `EndTime`,"
						+ " `JOB` FROM `employees` WHERE `ID` = "+pharmacistsIDs.get(i)+";";
				
				try {
					ResultSet rs1=database.getStatement().executeQuery(select1);
					if(rs1.next()) {
						Employee emp;
						int job = rs1.getInt("Job");
						switch (job) {
						case 1: {
							emp= new Manager();
							break;
						}
						case 2: {
							emp= new Pharmacist();
							break;
						}
						case 3: {
							emp= new Cashier();
							break;
						}
						default:
							emp=new Employee() {
								@Override
								String getJob() {
									return null;
								}
							};
							break;
						}
						emp.setID(pharmacistsIDs.get(i));
						emp.setFirstName(rs1.getString("FirstName"));
						emp.setLastName(rs1.getString("LastNAME"));
						emp.setEmail(rs1.getString("Email"));
						emp.setPhoneNumber(rs1.getString("PhoneNumber"));
						emp.setDateofBirth(rs1.getString("DateofBirth"));
						emp.setSalary(rs1.getDouble("Salary"));
						emp.setWorkHours(rs1.getInt("WorkHours"));
						emp.setStartTime(rs1.getString("StartTime"));
						emp.setEndTime(rs1.getString("EndTime"));
						orders.get(i).setPharmacist(emp);
					}
					else {
						System.out.println("Employee doesn't exist");
					}
				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}
				if(cashierIDs.get(i)>0) {
					String select2 ="SELECT `FirstName`, `LastName`, `Email`, `PhoneNumber`, "
							+ "`Password`, `Salary`, `DateofBirth`, `WorkHours`, `StartTime`, `EndTime`,"
							+ " `JOB` FROM `employees` WHERE `ID` = "+cashierIDs.get(i)+";";
					
					try {
						ResultSet rs2=database.getStatement().executeQuery(select2);
						if(rs2.next()) {
							Employee emp;
							int job = rs2.getInt("Job");
							switch (job) {
							case 1: {
								emp= new Manager();
								break;
							}
							case 2: {
								emp= new Pharmacist();
								break;
							}
							case 3: {
								emp= new Cashier();
								break;
							}
							default:
								emp=new Employee() {
									@Override
									String getJob() {
										return null;
									}
								};
								break;
							}
							emp.setID(cashierIDs.get(i));
							emp.setFirstName(rs2.getString("FirstName"));
							emp.setLastName(rs2.getString("LastNAME"));
							emp.setEmail(rs2.getString("Email"));
							emp.setPhoneNumber(rs2.getString("PhoneNumber"));
							emp.setDateofBirth(rs2.getString("DateofBirth"));
							emp.setSalary(rs2.getDouble("Salary"));
							emp.setWorkHours(rs2.getInt("WorkHours"));
							emp.setStartTime(rs2.getString("StartTime"));
							emp.setEndTime(rs2.getString("EndTime"));
							orders.get(i).setCashier(emp);
						}
						else {
							System.out.println("Employee doesn't exist");
						}
					}
					catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
				String select3="SELECT * FROM `ordersitems` WHERE `Order` = "+
				orders.get(i).getID()+";";
				ResultSet rs3=database.getStatement().executeQuery(select3);	
				int items = 0;
				while(rs3.next()) {
					items++;
				}
				Order o =orders.get(i);
				System.out.println("ID:\t\t"+orders.get(i).getID());
				System.out.println("Pharmacist:\t"+o.getPharmacist().getFirstName()+" "+
						o.getPharmacist().getLastName());
				System.out.println("Date Time:\t"+o.getDateTime());
				System.out.println("Total:\t\t"+o.getTotal()+"$");
				System.out.println("Num of Medicines:"+items);
				if(o.getPaid()>0) {
					System.out.println("Cashier:\t\t"+o.getCashier().getFirstName()+" "+
							o.getCashier().getLastName());
					System.out.println("Paid:\t\t"+o.getPaid()+"$");
					System.out.println("Change:\t\t"+o.getChange()+"$");
					System.out.println("Paid on:\t\t"+o.getPayDateTime());
				}
				System.out.println("----------------------------------------------");
				
			}
		}
	catch(SQLException e1){
		e1.printStackTrace();
	}
		
	}

}
