public interface IDeicingSystem {
    String version();

    boolean activate();

    int deIce(int amount);

    int refill();

    boolean deactivate();
}
