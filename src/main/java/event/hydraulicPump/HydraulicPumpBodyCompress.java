package event.hydraulicPump;

public class HydraulicPumpBodyCompress {

    private final int compressed = 1000;
    private int compress_amount = 1000;

    public HydraulicPumpBodyCompress() {
        this.compress_amount = compressed;
    }

    public HydraulicPumpBodyCompress(int amount) {
        this.compress_amount = amount;
    }

    public String toString() {
        return "Event: HydraulicPump Body - Compressed";
    }

    public int getValue() {
        return this.compress_amount;
    }
}
