package event.battery;

public class BatteryDischarge {
	private final int stateOfCharge = 80;
	//TODO richtige Werte
	public int getValue() { return stateOfCharge; }

	@Override
	public String toString() {
		return "BatteryDischarge{}";
	}
}
