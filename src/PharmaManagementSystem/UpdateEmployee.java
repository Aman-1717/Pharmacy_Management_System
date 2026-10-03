package PharmaManagementSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateEmployee implements Option{

	@Override
	public String getOption() {
		return "Edit Employee";
	}

	@Override
	public void oper(Database database, Scanner s, Employee e) {
		System.out.println("Enter Employee ID (int): (-1 to show all Emplyees)");
		int ID=s.nextInt();
		while(ID<0) {
			new ReadAllEmployees().oper(database, s, e);
			System.out.println("Enter Employee ID (int): (-1 to show all Emplyees)");
			ID=s.nextInt();
		}
		String select ="SELECT `FirstName`, `LastName`, `Email`, `PhoneNumber`, "
				+ "`Password`, `Salary`, `DateofBirth`, `WorkHours`, `StartTime`, `EndTime`,"
				+ " `JOB` FROM `employees` WHERE `ID` = "+ID+";";
		try {
			ResultSet rs=database.getStatement().executeQuery(select);
			if(rs.next()) {
				Employee emp;
				int job = rs.getInt("Job");
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
				emp.setID(ID);
				emp.setFirstName(rs.getString("FirstName"));
				emp.setLastName(rs.getString("LastNAME"));
				emp.setEmail(rs.getString("Email"));
				emp.setPhoneNumber(rs.getString("PhoneNumber"));
				emp.setDateofBirth(rs.getString("DateofBirth"));
				emp.setSalary(rs.getDouble("Salary"));
				emp.setWorkHours(rs.getInt("WorkHours"));
				emp.setStartTime(rs.getString("StartTime"));
				emp.setEndTime(rs.getString("EndTime"));
				emp.print();
				System.out.println("Enter First Name: (-1 to keep "+emp.getFirstName()+"):");
				String firstName=s.next();
				if(!firstName.equals("-1"))emp.setFirstName(firstName);
				System.out.println("Enter Last Name:(-1 to keep "+emp.getLastName()+"):");
				String lastName=s.next();
				if(!lastName.equals("-1"))emp.setLastName(lastName);
				System.out.println("Enter Email:(-1 to keep "+emp.getEmail()+"):");
				String email=s.next();
				if(!email.equals("-1"))emp.setEmail(email);
				System.out.println("Enter PhoneNumber:(-1 to keep "+emp.getPhoneNumber()+"):");
				String phonenumber=s.next();
				if(!phonenumber.equals("-1"))emp.setPhoneNumber(phonenumber);
				System.out.println("Enter Salary:(-1 to keep "+emp.getSalary()+"):");
				double salary=s.nextDouble();
				if(salary!=-1)emp.setSalary(salary);
				System.out.println("Enter Date of Birth:("+emp.getDateFormat()+"):(-1 to keep "+emp.getDateofBirth()+"):");
				String dateOfBirth=s.next();
				if(!dateOfBirth.equals("-1"))emp.setDateofBirth(dateOfBirth);
				
				System.out.println("Enter Work Hours: (-1 to keep "+emp.getWorkHours()+"):");
				int workHours=s.nextInt();
				if(workHours!=-1)emp.setWorkHours(workHours);
				
				System.out.println("Enter Start Time:("+emp.getTimeFormat()+"):(-1 to keep "+emp.getStartTime()+"):");
				String startTime=s.next();
				if(!startTime.equals("-1"))emp.setStartTime(startTime);
				
				System.out.println("Enter End Time:("+emp.getTimeFormat()+"):(-1 to keep "+emp.getEndTime()+"):");
				String endTime=s.next();
				if(!endTime.equals("-1"))emp.setEndTime(endTime);
				
				System.out.println("Enter Job:\n1. Manager\n2. Pharmacist\n3. Cashier");
				System.out.println("(-1 to keep "+emp.getJob()+")");
				int jobN=s.nextInt();
				if(jobN==1)jobN=job;
				
				String update="UPDATE `employees` SET `FirstName`='"+
				emp.getFirstName()+"',`LastName`='"+emp.getLastName()+"',`Email`='"+emp.getEmail()+"',"
						+ "`PhoneNumber`='"+emp.getPhoneNumber()+"',`Salary`='"+emp.getSalary()+"',"
						+ "`DateofBirth`='"+emp.getDateofBirth()+"',`WorkHours`='"+emp.getWorkHours()+"',`StartTime`='"+emp.getStartTime()+"',"
						+ "`EndTime`='"+emp.getEndTime()+"',`JOB`='"+jobN+"' WHERE `ID` = '"+ID+"';";
				
				database.getStatement().execute(update);
				System.out.println("Employee updated successfully");
				

			}
			else {
				System.out.println("Employee doesn't exist");
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		
	}

}
