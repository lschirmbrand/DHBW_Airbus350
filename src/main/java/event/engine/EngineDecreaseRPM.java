package event.engine;

public class EngineDecreaseRPM {

    int value;

    public EngineDecreaseRPM(int value){
        this.value += value;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return "Event: EngineRPM - Decreased";
    }
}

