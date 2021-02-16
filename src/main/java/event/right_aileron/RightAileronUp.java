package event.right_aileron;

public class RightAileronUp {
    int degree;

    public RightAileronUp(int degree) {
        this.degree = degree;
    }

    public String toString() {
        return "Event: RightAileron - Up";
    }
}
