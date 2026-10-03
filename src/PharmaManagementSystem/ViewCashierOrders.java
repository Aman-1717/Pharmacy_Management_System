package PharmaManagementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ViewCashierOrders implements Option{

	@Override
	public String getOption() {
		// TODO Auto-generated method stub
		return "View Cashier Orders";
	}

	@Override
	public void oper(Database database, Scanner s, Employee e) {
		System.out.println("Enter Cashier ID (int): (-1 to show all employees)");
		int ID=s.nextInt();
		while(ID<1) {
			new ReadAllEmployees().oper(database, s, e);
			System.out.println("Enter Casjier ID (int): (-1 to show all employees)");
			ID=s.nextInt();
		}
		String select ="SELECT * FROM `orders` WHERE `Cashier`= "+ID+";";
		try {
			ResultSet rs=database.getStatement().executeQuery(select);
			if(rs.next()) {
				Order o =new Order();
				o.setID(rs.getInt("ID"));
				int pharmacistID=rs.getInt("Pharmacist");
				int cashierID=rs.getInt("Cashier");
				o.setDateTime(rs.getString("DateTime"));
				o.setTotal(rs.getDouble("Total"));
				o.setPaid(rs.getDouble("Paid"));
				o.setChange(rs.getDouble("Change_"));
				o.setPayDateTime(rs.getString("PayDateTime"));
				
				String select1 ="SELECT `FirstName`, `LastName`, `Email`, `PhoneNumber`, "
						+ "`Password`, `Salary`, `DateofBirth`, `WorkHours`, `StartTime`, `EndTime`,"
						+ " `JOB` FROM `employees` WHERE `ID` = "+pharmacistID+";";
				
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
					emp.setID(pharmacistID);
					emp.setFirstName(rs1.getString("FirstName"));
					emp.setLastName(rs1.getString("LastNAME"));
					emp.setEmail(rs1.getString("Email"));
					emp.setPhoneNumber(rs1.getString("PhoneNumber"));
					emp.setDateofBirth(rs1.getString("DateofBirth"));
					emp.setSalary(rs1.getDouble("Salary"));
					emp.setWorkHours(rs1.getInt("WorkHours"));
					emp.setStartTime(rs1.getString("StartTime"));
					emp.setEndTime(rs1.getString("EndTime"));
					o.setPharmacist(emp);
				}
				else {
					System.out.println("Employee doesn't exist");
					
				}
				
				if(cashierID>0) {
					String select2 ="SELECT `FirstName`, `LastName`, `Email`, `PhoneNumber`, "
							+ "`Password`, `Salary`, `DateofBirth`, `WorkHours`, `StartTime`, `EndTime`,"
							+ " `JOB` FROM `employees` WHERE `ID` = "+cashierID+";";
					
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
						emp.setID(cashierID);
						emp.setFirstName(rs2.getString("FirstName"));
						emp.setLastName(rs2.getString("LastNAME"));
						emp.setEmail(rs2.getString("Email"));
						emp.setPhoneNumber(rs2.getString("PhoneNumber"));
						emp.setDateofBirth(rs2.getString("DateofBirth"));
						emp.setSalary(rs2.getDouble("Salary"));
						emp.setWorkHours(rs2.getInt("WorkHours"));
						emp.setStartTime(rs2.getString("StartTime"));
						emp.setEndTime(rs2.getString("EndTime"));
						o.setCashier(emp);
					}
				}
					
					String select3="SELECT * FROM `ordersitems` WHERE `Order` = "+
							o.getID()+";";
							ResultSet rs3=database.getStatement().executeQuery(select3);	
							int items = 0;
							while(rs3.next()) {
								items++;
							}
							System.out.println("ID:\t\t"+o.getID());
							System.out.println("Pharmacist:\t"+o.getPharmacist().getFirstName()+" "+
									o.getPharmacist().getLastName());
							System.out.println("Date Time:\t"+o.getDateTime());
							System.out.println("Total:\t\t"+o.getTotal());
							System.out.println("Num of Medicines:"+items);
							if(o.getPaid()>0) {
								System.out.println("Cashier:\t"+o.getCashier().getFirstName()+" "+
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
