package event.battery;

public class BatteryCharge {
	private final int stateOfCharge = 80;

	public int getValue() { return stateOfCharge; }

	@Override
	public String toString() {
		return "BatteryCharge{}";
	}
}
