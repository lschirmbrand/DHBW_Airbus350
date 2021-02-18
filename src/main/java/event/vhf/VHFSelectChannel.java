package event.vhf;

public class VHFSelectChannel {
    String channel;

    public VHFSelectChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "VHFSelectChannel{" +
                "channel='" + channel + '\'' +
                '}';
    }
}
