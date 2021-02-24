package event.oxygen_bottle;

public class OxygenBottleRefill {
    int amount = 100;

    public OxygenBottleRefill(int amount) {
        this.amount = amount;
    }

    public OxygenBottleRefill() {
    }

    @Override
    public String toString() {
        return "OxygenBottleRefill{" +
                "amount=" + amount +
                '}';
    }
}
