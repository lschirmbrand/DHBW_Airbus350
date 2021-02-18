package event.rudder;

public class RudderLeft {
    private int degree;

    public RudderLeft(int degree) {
        this.degree = degree;
    }

    public String toString() {
        return "Event: Rubber - Left";
    }

    public int getValue() {
        return degree;
    }
}
