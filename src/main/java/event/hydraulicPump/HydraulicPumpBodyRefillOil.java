package event.hydraulicPump;

public class HydraulicPumpBodyRefillOil {

    private int oil_amount = 1000;
    private final int oil_filled = 1000;

    public HydraulicPumpBodyRefillOil(){
        this.oil_amount = 1000;
    }

    public HydraulicPumpBodyRefillOil(int amount){
        this.oil_amount += amount;
    }

    public int getValue() {
        return oil_amount;
    }

    public String toString() {
        return "Event: HydraulicPump Body Oil - Increased";
    }
}
