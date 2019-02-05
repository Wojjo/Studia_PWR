package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database;

import java.util.Date;
import java.util.List;

public class MaintenanceHistory {
	private int maintenanceNumber;
	private Date maintenanceDate;
	private List<Integer> listEquipmentID;
	private int workerID;
	private int price;
	
	public MaintenanceHistory() {
		super();
	}

	public MaintenanceHistory(int maintenanceNumber, Date maintenanceDate, List<Integer> listEquipmentID, int workerID,
			int price) {
		super();
		this.maintenanceNumber = maintenanceNumber;
		this.maintenanceDate = maintenanceDate;
		this.listEquipmentID = listEquipmentID;
		this.workerID = workerID;
		this.price = price;
	}

	public int getMaintenanceNumber() {
		return maintenanceNumber;
	}

	public void setMaintenanceNumber(int maintenanceNumber) {
		this.maintenanceNumber = maintenanceNumber;
	}

	public Date getMaintenanceDate() {
		return maintenanceDate;
	}

	public void setMaintenanceDate(Date maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}

	public List<Integer> getListEquipmentID() {
		return listEquipmentID;
	}

	public void setListEquipmentID(List<Integer> listEquipmentID) {
		this.listEquipmentID = listEquipmentID;
	}

	public int getWorkerID() {
		return workerID;
	}

	public void setWorkerID(int workerID) {
		this.workerID = workerID;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "MaintenanceHistory [maintenanceNumber=" + maintenanceNumber + ", maintenanceDate=" + maintenanceDate
				+ ", listEquipmentID=" + listEquipmentID + ", workerID=" + workerID + ", price=" + price
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
