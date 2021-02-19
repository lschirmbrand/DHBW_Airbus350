package event.hydraulicPump;

public class HydraulicPumpWingDecompress {

    private final int decompressed = 0;
    private int compress_amount = 1000;

    public HydraulicPumpWingDecompress() {
        this.compress_amount = decompressed;
    }

    public HydraulicPumpWingDecompress(int amount) {
        this.compress_amount = amount;
    }

    public String toString() {
        return "Event: HydraulicPump Wing - Decompressed";
    }

    public int getValue() {
        return this.compress_amount;
    }
}
