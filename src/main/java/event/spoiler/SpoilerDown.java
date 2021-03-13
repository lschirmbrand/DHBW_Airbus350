package event.spoiler;

public class SpoilerDown {
    private int degree;

    public SpoilerDown(int degree) {
        this.degree = degree;
    }

    public String toString() {
        return "Event: Spoiler - Down";
    }

    public int getValue() {
        return degree;
    }
}
