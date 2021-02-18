
import java.util.Arrays;

public class VHF {
    // static instance
    private static VHF instance = new VHF();
    // port
    public Port port;
    private String manufacturer = "Manuel Truckses / Andreas Köhler";
    private String type = "Team 05";
    private String id = "9008480 / 1253402";
    private boolean isOn = true;
    private String[] channelList;
    private String selectedChannel;

    // private constructor
    private VHF() {
        port = new Port();
    }

    public static VHF getInstance() {
        return instance;
    }

    //inner methods
    public String innerVersion() {
        return "VHF // " + manufacturer + " - " + type + " - " + id;
    }

    public boolean innerOn() {
        return isOn = true;
    }

    public String[] innerSerach() {
        return channelList;
    }

    public String innerForwardChannel() {
        return channelList[Arrays.asList(channelList).indexOf(selectedChannel) + 1];
    }

    public String innerBackwardChannel() {
        return channelList[Arrays.asList(channelList).indexOf(selectedChannel) - 1];
    }

    public String innerSelectChannel(String channel) {
        return selectedChannel = channel;
    }

    public boolean InnerOff() {
        return isOn = false;
    }

    //inner class port
    public class Port implements IVHF {


        public String version() {
            return innerVersion();
        }

        public boolean on() {
            return innerOn();
        }

        public String[] serach() {
            return innerSerach();
        }

        public String forwardChannel() {
            return innerVersion();
        }

        public String backwardChannel() {
            return innerBackwardChannel();
        }

        public String selectChannel(String channel) {
            return innerSelectChannel(channel);
        }

        public boolean off() {
            return InnerOff();
        }
    }
}
