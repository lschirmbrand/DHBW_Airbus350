package event.elevator;

public class ElevatorNeutral {

    private final int angle;

    public ElevatorNeutral() {
        this.angle = 90;
    }

    public int getValue() {
        return this.angle;
    }

    public String toString() {
        return "Event: ElevatorAngle - Neutral";
    }
}
