package event.nitrogen_bottle;

public class NitrogenBottleRefill {
    int amount = 250;

    public NitrogenBottleRefill(int amount) {
        this.amount = amount;
    }

    public NitrogenBottleRefill() {
    }

    @Override
    public String toString() {
        return "NitrogenBottleRefill{" +
                "amount=" + amount +
                '}';
    }
}
