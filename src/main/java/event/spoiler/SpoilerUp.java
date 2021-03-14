package event.spoiler;

public class SpoilerUp {
    private int degree;

    public SpoilerUp(int degree) {
        this.degree = degree;
    }

    public String toString() {
        return "Event: Spoiler - Up";
    }

    public int getValue() {
        return degree;
    }
}
