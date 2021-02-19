public interface IAirConditioning {
    boolean on();

    String clean(String airFlow);

    int heat(String airFlow, int temperature);

    int cool(String airFlow, int temperature);

    boolean off();
}
