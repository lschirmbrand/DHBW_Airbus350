package event.deicing_system;

public class DeIcingSystemDeIce {
	private final int amount;

	public DeIcingSystemDeIce(int amount) { this.amount = amount; }

	public int getValue(){ return amount; }

	public String toString() {
		return "DeIcingSystemDeIce{amount= " + amount + "}";
	}
}
