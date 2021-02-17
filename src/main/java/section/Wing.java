package section;

import com.google.common.eventbus.Subscribe;
import event.Subscriber;
import event.camera.CameraBodyOff;
import event.camera.CameraBodyOn;
import event.camera.CameraWingOff;
import event.camera.CameraWingOn;
import event.droop_nose.DroopNoseDown;
import event.droop_nose.DroopNoseFullDown;
import event.droop_nose.DroopNoseNeutral;
import event.droop_nose.DroopNoseUp;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorBodyMeasure;
import event.turbulent_air_flow_sensor.TurbulentAirFlowSensorWingMeasure;

public class Wing extends Subscriber {

    //DroopNose---------------------------------
    @Subscribe
    public void receive(DroopNoseDown droopNoseDown) {
        System.out.println(droopNoseDown);
    }
    @Subscribe
    public void receive(DroopNoseFullDown droopNoseFullDown) {
        System.out.println(droopNoseFullDown);
    }
    @Subscribe
    public void receive(DroopNoseNeutral droopNoseNeutral) {
        System.out.println(droopNoseNeutral);
    }
    @Subscribe
    public void receive(DroopNoseUp droopNoseUp) {
        System.out.println(droopNoseUp);
    }
    //------------------------------------

    //TurbulentAirFlowSensor-------------------------
    @Subscribe
    public void receive(TurbulentAirFlowSensorWingMeasure turbulentAirFlowSensorWingMeasure) {
        System.out.println(turbulentAirFlowSensorWingMeasure);
    }
    //------------------------------------------

    //Camera-------------------------------------------
    @Subscribe
    public void receive(CameraWingOff cameraWingOff) {
        System.out.println(cameraWingOff);
    }

    @Subscribe
    public void receive(CameraWingOn cameraWingOn) {
        System.out.println(cameraWingOn);
    }
    //--------------------------------------------------------
}