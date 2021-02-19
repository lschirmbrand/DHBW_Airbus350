package event.elevator;

public class ElevatorUp {

    private int angle;

    public ElevatorUp(int angle) {
        this.angle += angle;
    }

    public int getValue() {
        return this.angle;
    }

    public String toString() {
        return "Event: ElevatorAngle - Up at " + this.angle;
    }
}
