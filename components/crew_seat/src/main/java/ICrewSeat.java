public interface ICrewSeat {
    String version();
    int assign(CrewMember crewMember);
    boolean nonSmokingSignOn();
    boolean nonSmokingSignOff();
    boolean seatBeltSignOn();
    boolean seatBeltSignOff();
    boolean readingLightOn();
    boolean readingLightOff();
}
