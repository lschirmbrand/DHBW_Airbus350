package event.crew_seat;

public class SeatAssignCrewMember {
    private CrewMember crewMember;

    public SeatAssignCrewMember(CrewMember crewMember) {
        this.crewMember = crewMember;
    }

    public String toString() {
        return "Event: SeatAssignCrewMember - toCrewMemberSeat";
    }
}
