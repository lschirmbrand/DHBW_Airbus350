package event.oxygen_bottle;

public class OxygenBottleRefill {
    int amount;

    public OxygenBottleRefill(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OxygenBottleRefill{" +
                "amount=" + amount +
                '}';
    }
}
