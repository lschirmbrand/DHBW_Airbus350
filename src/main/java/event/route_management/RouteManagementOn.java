package event.route_management;

import base.PrimaryFlightDisplay;

public class RouteManagementOn {
    public RouteManagementOn(){
        PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement = 0;
        PrimaryFlightDisplay.instance.indexRouteManagement = 0;
    }

    public String toString() {
        return "Event: RouteManagement - On";
    }
}
