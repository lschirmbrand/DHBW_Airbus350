package event.engine;

public class EngineIncreaseRPM {

    int value;

    public EngineIncreaseRPM(int value){this.value -= value;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return "Event: EngineRPM - Increased";
    }
}
