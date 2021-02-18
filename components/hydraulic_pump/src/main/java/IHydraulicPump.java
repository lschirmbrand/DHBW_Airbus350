public interface IHydraulicPump {

    public String version();
    public int compress();
    public int compress(int amount);
    public int decompress();
    public int refillOil();
    public int refillOil(int amount);
}
