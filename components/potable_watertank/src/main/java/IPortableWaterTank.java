public interface IPortableWaterTank {
    public String version();
    public boolean lock();
    public boolean unlock();
    public int takeOut(int amount);
    public int refill();
    public int refill(int amount);
}
