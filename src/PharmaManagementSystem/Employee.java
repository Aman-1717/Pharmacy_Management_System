package PharmaManagementSystem;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public abstract class Employee {
	    private int ID;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String phoneNumber;
	    private double salary;
	    private String password;
	    private LocalDate dateofBirth;
	    private int workHours;
	    private LocalTime startTime;
	    private LocalTime endTime;
	    protected Option[] options;
	    private static final DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    private static final DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("H:mm");

	    public Employee(){}

	    public int getID(){
	        return ID;
	    }
	    public void setID(int ID){
	        this.ID=ID;
	    }

	    public String getFirstName(){
	        return firstName;
	    }
	    public void setFirstName(String firstName){
	        this.firstName=firstName;
	    }

	    public String getLastName(){
	        return lastName;
	    }
	    public void setLastName(String lastName){
	        this.lastName=lastName;
	    }

	    public String getEmail(){return email;}
	    public void setEmail(String email){
	        this.email=email;
	    }

	    public String getPhoneNumber(){
	        return phoneNumber;
	    }
	    public void setPhoneNumber(String phoneNumber){
	        this.phoneNumber=phoneNumber;
	    }

	    public double getSalary(){
	        return salary;
	    }
	    public void setSalary(double salary){
	        this.salary=salary;
	    }

	    public String getPassword(){
	        return password;
	    }
	    public void setPassword(String password){
	        this.password=password;
	    }

	    public String getDateofBirth(){
	        return dateFormatter.format(dateofBirth);
	    }
	    public void setDateofBirth(String dateofBirth){
	        this.dateofBirth=LocalDate.parse(dateofBirth,dateFormatter);
	    }

	    public int getWorkHours(){
	        return workHours;
	    }
	    public void setWorkHours(int workHours){
	        this.workHours=workHours;
	    }

	    public String getStartTime(){
	        return timeFormatter.format(startTime);
	    }
	    public void setStartTime(String startTime){
	    this.startTime=LocalTime.parse(startTime,timeFormatter);
	    }
	    
	    public String getEndTime(){
	        return timeFormatter.format(endTime);
	    }
	    public void setEndTime(String endTime){
	    this.endTime=LocalTime.parse(endTime,timeFormatter);
	    }  

	    public String getDateFormat(){
	        return "yyyy-dd-MM";
	    } 

	    public String getTimeFormat(){
	        return "HH:mm";
	    } 
	    
	    abstract String getJob();
	    public void showOptions(Database database,Scanner s) {
	    	System.out.println("-----------------------------------------------------------");
	    	for(int i=0;i<options.length;i++) {
	    		System.out.println((i+1)+". "+options[i].getOption());
	    	}
	    	System.out.println("-----------------------------------------------------------");
	    	int i=s.nextInt();
	    	options[i-1].oper(database,s,this);
	    	showOptions(database,s);
	    }
	    
	    public void print() {
			System.out.println("ID:\t\t"+getID());
			System.out.println("Name:\t\t"+getFirstName()+" "+getLastName());
			System.out.println("Email:\t\t"+getEmail());
			System.out.println("PhoneNumber:\t"+getPhoneNumber());
			System.out.println("Salary:\t\t"+getSalary()+"$");
			System.out.println("DateofBirth:\t"+getDateofBirth());
			System.out.println("WorkHours:\t"+getWorkHours());
			System.out.println("StartTime:\t"+getStartTime());
			System.out.println("EndTime:\t\t"+getEndTime());
			System.out.println("Job:\t\t"+getJob());
			System.out.println("-----------------------------------------------------------");
	    }

	 ;
}
