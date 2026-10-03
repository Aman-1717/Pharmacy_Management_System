package PharmaManagementSystem;

public class Manager extends Employee{

	public Manager() {
		super();
		this.options=new Option[] {
				new CreateEmployee(),
				new ReadEmployee(),
				new ReadAllEmployees(),
				new UpdateEmployee(),
				new DeleteEmployee(),
				new CreateMedicine(),
				new ReadAllMedicines(),
				new ReadMedicineByID(),
				new ReadMedicineByName(),
				new ReadMedicineByType(),
				new ReadMedicineByCompany(),
				new UpdateMedicine(),
				new DeleteMedicine(),
				new CreateOrder(),
				new ReadAllOrders(),
				new ReadOrder(),
				new ReadOrderItems(),
				new PayOrder(),
				new DeleteOrder(),
				new ViewPharmacistOrders(),
				new ViewCashierOrders(),
				new ChangePassword()
		};
	}

	@Override
	String getJob() {
		// TODO Auto-generated method stub
		return "Manager";
	}
}
