package event.nitrogen_bottle;

public class NitrogenBottleTakeOut {
    public final int amount;

    public NitrogenBottleTakeOut(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "NitrogenBottleTakeOut{" +
                "amount=" + amount +
                '}';
    }
}
