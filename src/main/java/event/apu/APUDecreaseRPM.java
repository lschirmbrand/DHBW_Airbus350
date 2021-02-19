package event.apu;

public class APUDecreaseRPM {
    private final int value;

    public APUDecreaseRPM(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return "Event: APU - DecreaseRPM(" + value + ")";
    }
}
