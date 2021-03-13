public interface IBusinessClassSeat {
    String version();
    int assign(Passenger passenger);
    boolean nonSmokingSignOn();
    boolean nonSmokingSignOff();
    boolean seatBeltSignOn();
    boolean seatBeltSignOff();
    boolean readingLightOn();
    boolean readingLightOff();
    void upRight();
    int level1();
    int level2();
}
