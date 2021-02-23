@SuppressWarnings("unused")
public interface ITurbulentAirFlowSensor {
    String version();

    boolean alarm();

    int measure(String airFlow);
}
