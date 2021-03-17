package event.oxygen_bottle;

public class OxygenBottleTakeOut {
    public final int amount;

    public OxygenBottleTakeOut(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OxygenBottleTakeOut{" +
                "amount=" + amount +
                '}';
    }
}
