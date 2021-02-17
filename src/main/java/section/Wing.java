package section;

import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.Subscriber;
import event.left_aileron.*;
import event.right_aileron.*;
import event.slat.SlatDown;
import event.slat.SlatFullDown;
import event.slat.SlatNeutral;
import event.slat.SlatUp;
import event.spoiler.SpoilerDown;
import event.spoiler.SpoilerFullUp;
import event.spoiler.SpoilerNeutral;
import event.spoiler.SpoilerUp;
import factory.LeftAileronFactory;
import factory.RightAileronFactory;
import factory.SlatFactory;
import factory.SpoilerFactory;
import recorder.FlightRecorder;

import java.util.ArrayList;

public class Wing extends Subscriber {
    private ArrayList<Object> SlatPortList;
    private ArrayList<Object> LeftAileronPortList;
    private ArrayList<Object> RightAileronPortList;
    private ArrayList<Object> SpoilerPortList;

    public Wing() {
        SlatPortList = new ArrayList<>();
        LeftAileronPortList = new ArrayList<>();
        RightAileronPortList = new ArrayList<>();
        SpoilerPortList = new ArrayList<>();
        build();
    }

    public void build() {
        for (int i = 0; i < Configuration.instance.numberOfSlat; i++) {
            SlatPortList.add(SlatFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfLeftAileron; i++) {
            LeftAileronPortList.add(LeftAileronFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfRightAileron; i++) {
            RightAileronPortList.add(RightAileronFactory.build());
        }
        for (int i = 0; i < Configuration.instance.numberOfSpoiler; i++) {
            SpoilerPortList.add(SpoilerFactory.build());
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

    // --- RightAileron -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(RightAileronNeutral rightAileronNeutral) { //TODO
        FlightRecorder.instance.insert("Wing", "receive(" + rightAileronNeutral.toString() + ")");
    }

    @Subscribe
    public void receive(RightAileronFullUp rightAileronFullUp) {//TODO
        FlightRecorder.instance.insert("Wing", "receive(" + rightAileronFullUp.toString() + ")");
    }

    @Subscribe
    public void receive(RightAileronFullDown rightAileronFullDown) {//TODO
        FlightRecorder.instance.insert("Wing", "receive(" + rightAileronFullDown.toString() + ")");
    }

    @Subscribe
    public void receive(RightAileronDown rightAileronDown) {//TODO
        FlightRecorder.instance.insert("Wing", "receive(" + rightAileronDown.toString() + ")");
    }

    @Subscribe
    public void receive(RightAileronUp rightAileronUp) {//TODO
        FlightRecorder.instance.insert("Wing", "receive(" + rightAileronUp.toString() + ")");
    }
    
    // --- Spoiler -----------------------------------------------------------------------------------------------

    @Subscribe
    public void receive(SpoilerNeutral spoilerNeutral) { //TODO
        FlightRecorder.instance.insert("Wing", "receive(" + spoilerNeutral.toString() + ")");
    }

    @Subscribe
    public void receive(SpoilerFullUp spoilerFullUp) {//TODO
        FlightRecorder.instance.insert("Wing", "receive(" + spoilerFullUp.toString() + ")");
    }

    @Subscribe
    public void receive(SpoilerDown spoilerDown) {//TODO
        FlightRecorder.instance.insert("Wing", "receive(" + spoilerDown.toString() + ")");
    }

    @Subscribe
    public void receive(SpoilerUp spoilerUp) {//TODO
        FlightRecorder.instance.insert("Wing", "receive(" + spoilerUp.toString() + ")");
    }
}