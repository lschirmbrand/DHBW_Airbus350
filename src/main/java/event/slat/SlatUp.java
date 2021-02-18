package event.slat;

public class SlatUp {
    private int degree;

    public SlatUp(int degree) {
        this.degree = degree;
    }

    public String toString() {
        return "Event: Slat - Up";
    }

    public int getValue() {
        return degree;
    }
}
