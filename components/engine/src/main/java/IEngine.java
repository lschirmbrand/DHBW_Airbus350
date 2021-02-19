public interface IEngine {

    public String version();

    public boolean start();

    public int increaseRPM(int value);

    public int decreaseRPM(int value);

    public boolean shutdown();

    public void extinguishFire();
}
