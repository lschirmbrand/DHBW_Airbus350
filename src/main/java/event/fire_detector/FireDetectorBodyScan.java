package event.fire_detector;

public class FireDetectorBodyScan {
    private String air;

    public FireDetectorBodyScan(String air) {
        this.air = air;
    }

    public String toString() {
        return "Event: FireDetectorBody - Scan";
    }
}
