public class VHF implements IVHF{

    private String manufacturer;
    private String type;
    private String id;
    private boolean isOn;
    private String[] channelList;
    private String selectedChannel;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public String[] getChannelList() {
        return channelList;
    }

    public void setChannelList(String[] channelList) {
        this.channelList = channelList;
    }

    public String getSelectedChannel() {
        return selectedChannel;
    }

    public void setSelectedChannel(String selectedChannel) {
        this.selectedChannel = selectedChannel;
    }

    @Override
    public String version() {
        return null;
    }

    @Override
    public boolean on() {
        return false;
    }

    @Override
    public String[] serach() {
        return new String[0];
    }

    @Override
    public String forwardChannel() {
        return null;
    }

    @Override
    public String backwardChannel() {
        return null;
    }

    @Override
    public String selectChannel(String channel) {
        return null;
    }

    @Override
    public boolean off() {
        return false;
    }
}
