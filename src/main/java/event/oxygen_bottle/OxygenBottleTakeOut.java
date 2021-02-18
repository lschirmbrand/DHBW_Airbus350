package event.oxygen_bottle;

public class OxygenBottleTakeOut {
    int amount;

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
