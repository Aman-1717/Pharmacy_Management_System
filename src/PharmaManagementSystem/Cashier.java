package PharmaManagementSystem;

public class Cashier extends Employee {

	public Cashier() {
		super();
		this.options=new Option[] {
				new ReadAllOrders(),
				new ReadOrder(),
				new ReadOrderItems(),
				new PayOrder(),
				new ViewCashierOrders(),
				new ChangePassword()
		};
	}

	@Override
	String getJob() {
		// TODO Auto-generated method stub
		return "Cashier";
	}
}
