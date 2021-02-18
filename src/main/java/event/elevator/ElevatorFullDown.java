package event.elevator;

public class ElevatorFullDown {

    private int angle;

    public ElevatorFullDown(){this.angle = 0;}

    public int getValue(){return this.angle;}

    public String toString() {
        return "Event: ElevatorAngle - Full down";
    }
}
