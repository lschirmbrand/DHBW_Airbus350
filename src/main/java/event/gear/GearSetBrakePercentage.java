package event.gear;

public class GearSetBrakePercentage {
    private final int value;

    public GearSetBrakePercentage(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return "Event: Gear - setBrakePercentage";
    }
}
