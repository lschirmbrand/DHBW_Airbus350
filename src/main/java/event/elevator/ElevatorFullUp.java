package event.elevator;

public class ElevatorFullUp {

    private final int angle;

    public ElevatorFullUp() {
        this.angle = 180;
    }

    public int getValue() {
        return this.angle;
    }

    public String toString() {
        return "Event: ElevatorAngle - Full up";
    }
}
