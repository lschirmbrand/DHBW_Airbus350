package event.air_conditioning;

public class AirConditioningCool {
    private String airFlow;
    private int temperature;

    public AirConditioningCool(String airFlow, int temperature) {
        this.airFlow = airFlow;
        this.temperature = temperature;
    }

    public String getAirFlow() {
        return airFlow;
    }

    public int getTemperature() {
        return temperature;
    }

    public String toString() {
        return "Event: AirConditioning - Cool(" + airFlow + ", " + temperature + ")";
    }
}
