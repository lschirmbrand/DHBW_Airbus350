package event.apu;

public class APUIncreaseRPM {
    private final int value;

    public APUIncreaseRPM(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return "Event: APU - IncreaseRPM(" + value + ")";
    }
}
