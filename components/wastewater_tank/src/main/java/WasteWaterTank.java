public class WasteWaterTank implements IWasteWaterTank{

    private String manufacturer;
    private String type;
    private String id;
    private int capacity = 1000;
    private boolean isLocked;

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    @Override
    public String version() {
        return null;
    }

    @Override
    public boolean lock() {
        return false;
    }

    @Override
    public boolean unlock() {
        return false;
    }

    @Override
    public int add(int amount) {
        return 0;
    }

    @Override
    public int pumpOut() {
        return 0;
    }
}
