package event.elevator;

public class ElevatorDown {

    private int angle;

    public ElevatorDown(int angle){this.angle -= angle;}

    public int getValue(){return this.angle;}

    public String toString() {
        return "Event: ElevatorAngle - Down at " + this.angle;
    }
}
