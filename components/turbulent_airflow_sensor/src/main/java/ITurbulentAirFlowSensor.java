public interface ITurbulentAirFlowSensor {
    public String version();
    public boolean alarm();
    public int measure(String airFlow);
}
