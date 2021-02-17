package event.route_management;

public class RouteManagementRemove {
    CheckPoint checkPoint;

    public void setCheckPoint(CheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }

    public CheckPoint getCheckPoint() {
        return checkPoint;
    }

    public RouteManagementRemove(CheckPoint checkPoint)
    {
        this.checkPoint = checkPoint;
    }

    public String toString() {
        return "Event: RouteManagement - Remove";
    }
}
