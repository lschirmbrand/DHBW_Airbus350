package event.hydraulicPump;

public class HydraulicPumpWingRefillOil {

    private final int oil_filled = 1000;
    private int oil_amount = 1000;

    public HydraulicPumpWingRefillOil() {
        this.oil_amount = 1000;
    }

    public HydraulicPumpWingRefillOil(int amount) {
        this.oil_amount += amount;
    }

    public int getValue() {
        return oil_amount;
    }

    public String toString() {
        return "Event: HydraulicPump Body Oil - Increased";
    }
}
