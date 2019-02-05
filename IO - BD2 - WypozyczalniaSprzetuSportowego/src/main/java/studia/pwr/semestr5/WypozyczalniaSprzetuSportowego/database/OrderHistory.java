package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database;

import java.util.Date;
import java.util.List;

public class OrderHistory {
	private int orderNumber;
	private int clientID;
	private int workerID;
	private int cost;
	private Date orderDate;
	private List<Integer> listEquipmentID;
	private List<Integer> listEquipmentLoanLength;

	public OrderHistory() {
		super();
	}

	public OrderHistory(int orderNumber, int clientID, Date orderDate, int cost, List<Integer> listEquipmentID,
			List<Integer> listEquipmentLoanLength) {
		super();
		this.orderNumber = orderNumber;
		this.clientID = clientID;
		this.workerID = -1;
		this.orderDate = orderDate;
		this.cost = cost;
		this.listEquipmentID = listEquipmentID;
		this.listEquipmentLoanLength = listEquipmentLoanLength;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public int getWorkerID() {
		return workerID;
	}

	public void setWorkerID(int workerID) {
		this.workerID = workerID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public List<Integer> getListEquipmentID() {
		return listEquipmentID;
	}

	public void setListEquipmentID(List<Integer> listEquipmentID) {
		this.listEquipmentID = listEquipmentID;
	}

	public List<Integer> getListEquipmentLoanLength() {
		return listEquipmentLoanLength;
	}

	public void setListEquipmentLoanLength(List<Integer> listEquipmentLoanLength) {
		this.listEquipmentLoanLength = listEquipmentLoanLength;
	}

	@Override
	public String toString() {
		return "OrderHistory [orderNumber=" + orderNumber + ", clientID=" + clientID + ", workerID=" + workerID
				+ ", orderDate=" + orderDate + ", listEquipmentID=" + listEquipmentID + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
