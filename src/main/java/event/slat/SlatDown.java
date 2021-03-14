package event.slat;

public class SlatDown {
    private int degree;

    public SlatDown(int degree) {
        this.degree = degree;
    }

    public String toString() {
        return "Event: Slat - Down";
    }

    public int getValue() {
        return degree;
    }
}
