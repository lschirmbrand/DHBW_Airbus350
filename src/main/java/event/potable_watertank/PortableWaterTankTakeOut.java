package event.potable_watertank;

public class PortableWaterTankTakeOut {
    int amount;

    public PortableWaterTankTakeOut(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PortableWaterTankTakeOut{" +
                "amount=" + amount +
                '}';
    }
}

