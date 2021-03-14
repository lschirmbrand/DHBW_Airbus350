package event.economy_class_seat;

public class SeatAssignPassenger {

    private Passenger passenger;

    public SeatAssignPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Passenger getPassenger() {
        return this.passenger;
    }

    public String toString() {
        return "Event: SeatAssignPassenger - Passenger assigned";
    }
}