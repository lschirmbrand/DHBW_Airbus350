package event.elevator;

public class ElevatorFullUp {

    private int angle;

    public ElevatorFullUp(){this.angle = 180;}

    public int getValue(){return this.angle;}

    public String toString() {
        return "Event: ElevatorAngle - Full up";
    }
}
