package event.rudder;

public class RudderRight {
    private int degree;

    public RudderRight(int degree) {
        this.degree = degree;
    }

    public String toString() {
        return "Event: Rubber - Right";
    }

    public int getValue() {
        return degree;
    }
}
