package event.left_aileron;

public class LeftAileronUp {
    int degree;

    public LeftAileronUp(int degree) {
        this.degree = degree;
    }

    public String toString() {
        return "Event: LeftAileron - Up";
    }
}
