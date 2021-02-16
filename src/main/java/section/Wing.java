package section;

import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.left_aileron.*;
import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
import event.weather_radar.WeatherRadarScan;
import factory.LeftAileronFactory;
import factory.SlatFactory;
import recorder.FlightRecorder;

import java.util.ArrayList;

public class Wing extends Subscriber {
    private ArrayList<Object> SlatPortList;
    private ArrayList<Object> LeftAileronPortList;

    public Wing() {
        SlatPortList = new ArrayList<>();
        LeftAileronPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
            SlatPortList.add(SlatFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
            LeftAileronPortList.add(LeftAileronFactory.build());
        }
    }

    // --- Slat -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(SlatNeutral slatNeutral) { //TODO
        FlightRecorder.instance.insert("Wing", "receive(" + slatNeutral.toString() + ")");
    }

    @Subscribe
    public void receive(SlatFullDown slatFullDown) {//TODO
        FlightRecorder.instance.insert("Wing", "receive(" + slatFullDown.toString() + ")");
    }

    @Subscribe
    public void receive(SlatDown slatDown) {//TODO
        FlightRecorder.instance.insert("Wing", "receive(" + slatDown.toString() + ")");
    }

    @Subscribe
    public void receive(SlatUp slatUp) {//TODO
        FlightRecorder.instance.insert("Wing", "receive(" + slatUp.toString() + ")");
    }

    // --- LeftAileron -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(LeftAileronNeutral leftAileronNeutral) { //TODO
        FlightRecorder.instance.insert("Wing", "receive(" + leftAileronNeutral.toString() + ")");
    }

    @Subscribe
    public void receive(LeftAileronFullUp leftAileronFullUp) {//TODO
        FlightRecorder.instance.insert("Wing", "receive(" + leftAileronFullUp.toString() + ")");
    }

    @Subscribe
    public void receive(LeftAileronFullDown leftAileronFullDown) {//TODO
        FlightRecorder.instance.insert("Wing", "receive(" + leftAileronFullDown.toString() + ")");
    }

    @Subscribe
    public void receive(LeftAileronDown leftAileronDown) {//TODO
        FlightRecorder.instance.insert("Wing", "receive(" + leftAileronDown.toString() + ")");
    }

    @Subscribe
    public void receive(LeftAileronUp leftAileronUp) {//TODO
        FlightRecorder.instance.insert("Wing", "receive(" + leftAileronUp.toString() + ")");
    }
}