package event.hydraulicPump;

public class HydraulicPumpWingCompress {

    private final int compressed = 1000;
    private int compress_amount = 1000;

    public HydraulicPumpWingCompress() {
        this.compress_amount = compressed;
    }

    public HydraulicPumpWingCompress(int amount) {
        this.compress_amount = amount;
    }

    public String toString() {
        return "Event: HydraulicPump Body - Compressed";
    }

    public int getValue() {
        return this.compress_amount;
    }
}
