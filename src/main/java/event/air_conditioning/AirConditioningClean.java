package event.air_conditioning;

public class AirConditioningClean {
    private String airFlow;

    public AirConditioningClean(String airFlow) {
        this.airFlow = airFlow;
    }

    public String getAirFlow() {
        return airFlow;
    }

    public String toString() {
        return "Event: AirConditioning - Clean(" + airFlow + ")";
    }
}
