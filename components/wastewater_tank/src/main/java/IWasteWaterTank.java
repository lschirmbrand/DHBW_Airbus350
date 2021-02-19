public interface IWasteWaterTank {
    public String version();

    public boolean lock();

    public boolean unlock();

    public int add(int amount);

    public int pumpOut();
}
