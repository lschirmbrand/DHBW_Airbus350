package event.potable_watertank;

public class PortableWaterTankRefill {
    int amount;

    public PortableWaterTankRefill(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PortableWaterTankRefill{" +
                "amount=" + amount +
                '}';
    }
}
