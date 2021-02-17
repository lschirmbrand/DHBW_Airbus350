package section;

import base.PrimaryFlightDisplay;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.cargo_compartment_light.CargoCompartmentLightDim;
import event.cargo_compartment_light.CargoCompartmentLightOff;
import event.cargo_compartment_light.CargoCompartmentLightOn;
import event.cost_optimizer.*;
import event.landing_light.LandingLightBodyOff;
import event.landing_light.LandingLightBodyOn;
import event.left_navigation_light.LeftNavigationLightOn;
import event.logo_light.LogoLightOff;
import event.logo_light.LogoLightOn;
import event.route_management.*;
import event.weather_radar.WeatherRadarOff;
import event.weather_radar.WeatherRadarOn;
import event.weather_radar.WeatherRadarScan;
import factory.*;
import logging.LogEngine;
import org.checkerframework.checker.units.qual.A;
import recorder.FlightRecorder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

public class Body extends Subscriber {
    private ArrayList<Object> weatherRadarPortList;
    private ArrayList<Object> cargoCompartmentLightPortList;
    private ArrayList<Object> costOptimizerPortList;
    private ArrayList<Object> landingLightBodyPortList;
    private ArrayList<Object> logoLightPortList;
    private ArrayList<Object> routeManagementPortList;

    public Body() {
        weatherRadarPortList = new ArrayList<>();
        cargoCompartmentLightPortList = new ArrayList<>();
        costOptimizerPortList = new ArrayList<>();
        landingLightBodyPortList = new ArrayList<>();
        logoLightPortList = new ArrayList<>();
        routeManagementPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
            weatherRadarPortList.add(WeatherRadarFactory.build());
        }
        for(int i = 0; i < Configuration.instance.numberOfCargoCompartmentLight; i++){
            cargoCompartmentLightPortList.add(CargoCompartmentLightFactory.build());
        }
        for(int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++){
            costOptimizerPortList.add(CostOptimizerFactory.build());
        }
        for(int i = 0; i < Configuration.instance.numberOfLandingLightBody; i++){
            landingLightBodyPortList.add(LandingLightFactory.build());
        }
        for(int i = 0; i < Configuration.instance.numberOfLogoLight; i++){
            logoLightPortList.add(LogoLightFactory.build());
        }
        for(int i = 0; i < Configuration.instance.numberOfRouteManagement; i++){
            routeManagementPortList.add(RouteManagementFactory.build());
        }
    }

    // --- WeatherRadar -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(WeatherRadarOn weatherRadarOn) {
        LogEngine.instance.write("+ Body.receive(" + weatherRadarOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
                Method onMethod = weatherRadarPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(weatherRadarPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
                FlightRecorder.instance.insert("Body", "WeatherRadar (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isWeatherRadarOn): " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isWeatherRadarOn: " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Subscribe
    public void receive(WeatherRadarOff weatherRadarOff) {
        LogEngine.instance.write("+ Body.receive(" + weatherRadarOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfWeatherRadar; i++) {
                Method offMethod = weatherRadarPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(weatherRadarPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isWeatherRadarOn = isOn;
                FlightRecorder.instance.insert("Body", "WeatherRadar (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isWeatherRadarOn): " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isWeatherRadarOn: " + PrimaryFlightDisplay.instance.isWeatherRadarOn);
    }

    @Subscribe
    public void receive(WeatherRadarScan weatherRadarScan) {
        FlightRecorder.instance.insert("Body", "receive(" + weatherRadarScan.toString() + ")");
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- CargoCompartmentLight -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(CargoCompartmentLightOn cargoCompartmentLightOn){
        LogEngine.instance.write("+ Body.receive(" + cargoCompartmentLightOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + cargoCompartmentLightOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCargoCompartmentLight; i++) {
                Method onMethod = cargoCompartmentLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(cargoCompartmentLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCargoCompartmentLightOn = isOn;
                FlightRecorder.instance.insert("Body", "CargoCompartmentLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isCargoCompartmentLightOn): " + PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCargoCompartmentLightOn: " + PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);
    }

    @Subscribe
    public void receive(CargoCompartmentLightOff cargoCompartmentLightOff) {
        LogEngine.instance.write("+ Body.receive(" + cargoCompartmentLightOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + cargoCompartmentLightOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCargoCompartmentLight; i++) {
                Method offMethod = cargoCompartmentLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(cargoCompartmentLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCargoCompartmentLightOn = isOn;
                FlightRecorder.instance.insert("Body", "CargoCompartmentLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isCargoCompartmentLightOn): " + PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCargoCompartmentLightOn: " + PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);
    }

    @Subscribe
    public void receive(CargoCompartmentLightDim cargoCompartmentLightDim) {
        FlightRecorder.instance.insert("Body", "receive(" + cargoCompartmentLightDim.toString() + ")");
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- CostOptimizer -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(CostOptimizerOn costOptimizerOn){
        LogEngine.instance.write("+ Body.receive(" + costOptimizerOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method onMethod = costOptimizerPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(costOptimizerPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCostOptimizerOn = isOn;
                FlightRecorder.instance.insert("Body", "CostOptimizerOn (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isCostOptimizerOn): " + PrimaryFlightDisplay.instance.isCostOptimizerOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCostOptimizerOn: " + PrimaryFlightDisplay.instance.isCostOptimizerOn);
    }

    @Subscribe
    public void receive(CostOptimizerOff costOptimizerOff) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++) {
                Method offMethod = costOptimizerPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(costOptimizerPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCostOptimizerOn = isOn;
                FlightRecorder.instance.insert("Body", "CostOptimizerOff (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isCostOptimizerOn): " + PrimaryFlightDisplay.instance.isCostOptimizerOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isCostOptimizerOn: " + PrimaryFlightDisplay.instance.isCostOptimizerOn);
    }

    @Subscribe
    public void receive(CostOptimizerAddCheckPoint costOptimizerAddCheckPoint) {
        LogEngine.instance.write("+ Body.receive(" + costOptimizerAddCheckPoint.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + costOptimizerAddCheckPoint.toString() + ")");

        try{
            for(int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++){
                Method addMethod = costOptimizerPortList.get(i).getClass().getDeclaredMethod("add");//, CheckPoint.class);
                LogEngine.instance.write("addMethod = " + addMethod);

                int numberOfCheckPoints = (int) addMethod.invoke(costOptimizerPortList.get(i));
                LogEngine.instance.write("numberOfCheckPoints = " + numberOfCheckPoints);

                PrimaryFlightDisplay.instance.indexCostOptimizer = numberOfCheckPoints;
                FlightRecorder.instance.insert("Body", "CostOptimizerAddCheckPoint (numberOfCheckPoints): " + numberOfCheckPoints);

                LogEngine.instance.write("+");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (indexCostOptimizer): " + PrimaryFlightDisplay.instance.indexCostOptimizer);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "indexCostOptimizer: " + PrimaryFlightDisplay.instance.indexCostOptimizer);
    }

    @Subscribe
    public void receive(CostOptimizerRemoveCheckPoint costOptimizerRemoveCheckPoint){
        LogEngine.instance.write("+ Body.receive(" + costOptimizerRemoveCheckPoint.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive("+costOptimizerRemoveCheckPoint.toString() + ")");

        try {
            for(int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++)
            {
                Method removeMethod = costOptimizerPortList.get(i).getClass().getDeclaredMethod("remove", Integer.class);
                LogEngine.instance.write("removeMethod = " + removeMethod);

                int numberOfCheckPoints = (int) removeMethod.invoke(costOptimizerPortList.get(i), 1);
                LogEngine.instance.write("numberOfCheckPoints = " + numberOfCheckPoints);

                PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer = numberOfCheckPoints;
                FlightRecorder.instance.insert("Body", "CostOptimizerRemoveCheckPoint (numberOfCheckPoints): " + numberOfCheckPoints);

                LogEngine.instance.write("+");
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsCostOptimizer): " + PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsCostOptimizer: " + PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer);
    }

    @Subscribe
    public void receive(CostOptimizerValidate costOptimizerValidate){
        FlightRecorder.instance.insert("Body", "receive("+costOptimizerValidate.toString() + ")");
    }

    @Subscribe
    public void receive(CostOptimizerOptimize costOptimizerOptimize){
        LogEngine.instance.write("+ Body.receive(" + costOptimizerOptimize.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive("+costOptimizerOptimize.toString() + ")");

        try {
            for(int i = 0; i < Configuration.instance.numberOfCostOptimizer; i++)
            {
                Method optimizeMethod = costOptimizerPortList.get(i).getClass().getDeclaredMethod("optimize");
                LogEngine.instance.write("optimizeMethod" + optimizeMethod);

                int costIndex = (int) optimizeMethod.invoke(costOptimizerPortList.get(i));
                LogEngine.instance.write("costIndex" + costIndex);

                PrimaryFlightDisplay.instance.indexCostOptimizer = costIndex;
                FlightRecorder.instance.insert("Body", "CostOptimizerOptimize (costIndex): " + costIndex);

                LogEngine.instance.write("+");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (indexCostOptimizer): " + PrimaryFlightDisplay.instance.indexCostOptimizer);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "indexCostOptimizer: " + PrimaryFlightDisplay.instance.indexCostOptimizer);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- LandingLight -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(LandingLightBodyOn landingLightBodyOn){
        LogEngine.instance.write("+ Body.receive(" + landingLightBodyOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + landingLightBodyOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLandingLightBody; i++) {
                Method onMethod = landingLightBodyPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(landingLightBodyPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLandingLightBodyOn = isOn;
                FlightRecorder.instance.insert("Body", "LandingLightBody (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLandingLightOn): " + PrimaryFlightDisplay.instance.isLandingLightBodyOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLandingLightOn: " + PrimaryFlightDisplay.instance.isLandingLightBodyOn);
    }

    @Subscribe
    public void receive(LandingLightBodyOff landingLightBodyOff) {
        LogEngine.instance.write("+ Body.receive(" + landingLightBodyOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + landingLightBodyOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLandingLightBody; i++) {
                Method offMethod = landingLightBodyPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(landingLightBodyPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLandingLightBodyOn = isOn;
                FlightRecorder.instance.insert("Body", "LandingLightBody (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLandingLightBodyOn): " + PrimaryFlightDisplay.instance.isLandingLightBodyOn);
        FlightRecorder.instance.insert("isLandingLightBodyOn", "isLandingLightOn: " + PrimaryFlightDisplay.instance.isLandingLightBodyOn);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- LeftNavigationLight -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(LogoLightOn logoLightOn){
        LogEngine.instance.write("+ Body.receive(" + logoLightOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + logoLightOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLogoLight; i++) {
                Method onMethod = logoLightPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(logoLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isLogoLightOn = isOn;
                FlightRecorder.instance.insert("Body", "LogoLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLogoLightOn): " + PrimaryFlightDisplay.instance.isLogoLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLogoLightOn: " + PrimaryFlightDisplay.instance.isLogoLightOn);
    }

    @Subscribe
    public void receive(LogoLightOff logoLightOff) {
        LogEngine.instance.write("+ Body.receive(" + logoLightOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + logoLightOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfLogoLight; i++) {
                Method offMethod = logoLightPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(logoLightPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isCargoCompartmentLightOn = isOn;
                FlightRecorder.instance.insert("Body", "LogoLight (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isLogoLightOn): " + PrimaryFlightDisplay.instance.isLogoLightOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isLogoLightOn: " + PrimaryFlightDisplay.instance.isLogoLightOn);
    }

    // ----------------------------------------------------------------------------------------------------------------

    // --- RouteManagement -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(RouteManagementOn routeManagementOn){
        LogEngine.instance.write("+ Body.receive(" + routeManagementOn.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementOn.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method onMethod = routeManagementPortList.get(i).getClass().getDeclaredMethod("on");
                LogEngine.instance.write("onMethod = " + onMethod);

                boolean isOn = (boolean) onMethod.invoke(routeManagementPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isRouteManagementOn = isOn;
                FlightRecorder.instance.insert("Body", "RouteManagement (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isRouteManagementOn): " + PrimaryFlightDisplay.instance.isRouteManagementOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isRouteManagementOn: " + PrimaryFlightDisplay.instance.isRouteManagementOn);
    }

    @Subscribe
    public void receive(RouteManagementOff routeManagementOff) {
        LogEngine.instance.write("+ Body.receive(" + routeManagementOff.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive(" + routeManagementOff.toString() + ")");

        try {
            for (int i = 0; i < Configuration.instance.numberOfRouteManagement; i++) {
                Method offMethod = routeManagementPortList.get(i).getClass().getDeclaredMethod("off");
                LogEngine.instance.write("offMethod = " + offMethod);

                boolean isOn = (boolean) offMethod.invoke(routeManagementPortList.get(i));
                LogEngine.instance.write("isOn = " + isOn);

                PrimaryFlightDisplay.instance.isRouteManagementOn = isOn;
                FlightRecorder.instance.insert("Body", "RouteManagement (isOn): " + isOn);

                LogEngine.instance.write("+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (isRouteManagementOn): " + PrimaryFlightDisplay.instance.isRouteManagementOn);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "isRouteManagementOn: " + PrimaryFlightDisplay.instance.isRouteManagementOn);
    }

    @Subscribe
    public void receive(RouteManagementAdd routeManagementAdd){
        LogEngine.instance.write("+ Body.receive(" + routeManagementAdd.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive("+routeManagementAdd.toString() + ")");

        try{
            for(int i = 0; i < Configuration.instance.numberOfRouteManagement; i++){
                Method addMethod = routeManagementPortList.get(i).getClass().getDeclaredMethod("add");//, CheckPoint.class);
                LogEngine.instance.write("addMethod = " + addMethod);

                int numberOfCheckPoints = (int) addMethod.invoke(routeManagementPortList.get(i));//, new CheckPoint(PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement, "London", "51° 31′ N", "0° 7′ W"));
                FlightRecorder.instance.insert("Body", "RouteManagement (addMethod): " + addMethod);

                PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement = numberOfCheckPoints;
                FlightRecorder.instance.insert("Body", "RouteManagement (numberOfCheckPoints): " + numberOfCheckPoints);

                LogEngine.instance.write("+");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsRouteManagement): " + PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsRouteManagement: " + PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement);
    }

    @Subscribe
    public void receive(RouteManagementRemove routeManagementRemove){
        LogEngine.instance.write("+ Body.receive(" + routeManagementRemove.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive("+routeManagementRemove.toString() + ")");

        try{
            for(int i = 0; i < Configuration.instance.numberOfRouteManagement; i++){
                Method removeMethod = routeManagementPortList.get(i).getClass().getDeclaredMethod("remove", Integer.class);
                LogEngine.instance.write("removeMethod = " + removeMethod);

                int numberOfCheckPoints = (int) removeMethod.invoke(routeManagementPortList.get(i), PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement / 2);
                FlightRecorder.instance.insert("Body", "RouteManagement (removeMethod): " + removeMethod);

                PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement = numberOfCheckPoints;
                FlightRecorder.instance.insert("Body", "RouteManagement (numberOfCheckPoints): " + numberOfCheckPoints);

                LogEngine.instance.write("+");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (numberOfCheckPointsRouteManagement): " + PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "numberOfCheckPointsRouteManagement: " + PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement);
    }

    @Subscribe
    public void receive(RouteManagementSetCostIndex routeManagementSetCostIndex){
        LogEngine.instance.write("+ Body.receive(" + routeManagementSetCostIndex.toString() + ")");
        FlightRecorder.instance.insert("Body", "receive("+routeManagementSetCostIndex.toString() + ")");

        try {
            for(int i = 0; i < Configuration.instance.numberOfRouteManagement; i++)
            {
                Method setCostIndexMethod = routeManagementPortList.get(i).getClass().getDeclaredMethod("setCostIndex", Integer.class);
                LogEngine.instance.write("setCostIndexMethod" + setCostIndexMethod);

                double costIndex = (double) setCostIndexMethod.invoke(routeManagementPortList.get(i));
                LogEngine.instance.write("costIndex" + costIndex);

                PrimaryFlightDisplay.instance.indexRouteManagement = (int ) costIndex;
                FlightRecorder.instance.insert("Body", "RouteManagerSetCostIndex (costIndex): " + (int) costIndex);

                LogEngine.instance.write("+");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        LogEngine.instance.write("PrimaryFlightDisplay (indexRouteManagement): " + PrimaryFlightDisplay.instance.indexRouteManagement);
        FlightRecorder.instance.insert("PrimaryFlightDisplay", "indexRouteManagement: " + PrimaryFlightDisplay.instance.indexRouteManagement);
    }

    // ----------------------------------------------------------------------------------------------------------------

}