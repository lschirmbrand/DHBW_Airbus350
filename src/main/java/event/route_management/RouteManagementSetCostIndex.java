package event.route_management;

public class RouteManagementSetCostIndex {
    int value = 0;

    public RouteManagementSetCostIndex(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        return "Event: RouteManagement - SetCostIndex";
    }
}
