package studia.pwr.semestr5.WypozyczalniaSprzetuSportowego.database;

public class Model {
	private int modelID;
	private String modelName;
	private String producer;
	private String equipmentType;
	private boolean season;
	private int costPerDay;
	private int damageDeposit;
	
	public Model() {
		super();
	}
	public Model(int modelID, String modelName, String producer, String equipmentType, boolean season, int costPerDay,
			int damageDeposit) {
		super();
		this.modelID = modelID;
		this.modelName = modelName;
		this.producer = producer;
		this.equipmentType = equipmentType;
		this.season = season;
		this.costPerDay = costPerDay;
		this.damageDeposit = damageDeposit;
	}
	public int getModelID() {
		return modelID;
	}
	public void setModelID(int modelID) {
		this.modelID = modelID;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public boolean getSeason() {
		return season;
	}
	public void setSeason(boolean season) {
		this.season = season;
	}
	public int getCostPerDay() {
		return costPerDay;
	}
	public void setCostPerDay(int costPerDay) {
		this.costPerDay = costPerDay;
	}
	public int getDamageDeposit() {
		return damageDeposit;
	}
	public void setDamageDeposit(int damageDeposit) {
		this.damageDeposit = damageDeposit;
	}
	
	@Override
	public String toString() {
		return modelID + " !&#*&% " + modelName + " !&#*&% " + producer + " !&#*&% "
				+ equipmentType + " !&#*&% " + season + " !&#*&% " + costPerDay + " !&#*&% "
				+ damageDeposit;
	}
	
}
