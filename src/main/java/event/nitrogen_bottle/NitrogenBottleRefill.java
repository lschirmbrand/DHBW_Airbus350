package event.nitrogen_bottle;

public class NitrogenBottleRefill {
    int amount;

    public NitrogenBottleRefill(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "NitrogenBottleRefill{" +
                "amount=" + amount +
                '}';
    }
}
