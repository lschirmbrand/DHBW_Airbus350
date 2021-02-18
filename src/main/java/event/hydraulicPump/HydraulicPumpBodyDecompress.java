package event.hydraulicPump;

public class HydraulicPumpBodyDecompress {

    private int compress_amount = 1000;
    private final int decompressed = 0;

    public HydraulicPumpBodyDecompress(){
        this.compress_amount = decompressed;
    }

    public HydraulicPumpBodyDecompress(int amount){
        this.compress_amount = amount;
    }

    public String toString(){
        return "Event: HydraulicPump Wing - Decompressed";
    }

    public int getValue(){
        return this.compress_amount;
    }
}
