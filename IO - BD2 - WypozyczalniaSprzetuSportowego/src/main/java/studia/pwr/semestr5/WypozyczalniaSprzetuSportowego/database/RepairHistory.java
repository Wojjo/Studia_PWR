package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database;

import java.util.Date;
import java.util.List;

public class RepairHistory {
	private int repairNumber;
	private Date repairDate;
	private List<Integer> listEqupimentID;
	private int workerID;
	private int price;
	
	public RepairHistory() {
		super();
	}
	
	public RepairHistory(int repairNumber, Date repairDate, List<Integer> listEqupimentID, int workerID, int price) {
		super();
		this.repairNumber = repairNumber;
		this.repairDate = repairDate;
		this.listEqupimentID = listEqupimentID;
		this.workerID = workerID;
		this.price = price;
	}

	public int getRepairNumber() {
		return repairNumber;
	}

	public void setRepairNumber(int repairNumber) {
		this.repairNumber = repairNumber;
	}

	public Date getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(Date reapirDate) {
		this.repairDate = reapirDate;
	}

	public List<Integer> getListEqupimentID() {
		return listEqupimentID;
	}

	public void setListEqupimentID(List<Integer> listEqupimentID) {
		this.listEqupimentID = listEqupimentID;
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
		return "RepairHistory [repairNumber=" + repairNumber + ", reapirDate=" + repairDate + ", listEqupimentID="
				+ listEqupimentID + ", workerID=" + workerID + ", price=" + price + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
