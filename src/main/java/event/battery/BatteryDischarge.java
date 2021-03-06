package event.battery;

public class BatteryDischarge {
	private final int stateOfCharge = 80;

	public int getValue() { return stateOfCharge; }

	@Override
	public String toString() {
		return "BatteryDischarge{}";
	}
}
