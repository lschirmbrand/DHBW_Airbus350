public class PortableWaterTank implements IPortableWaterTank {

    private String manufacturer;
    private String type;
    private String id;
    private int amount = 1000;
    private boolean isLocked;

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
    public int takeOut(int amount) {
        return 0;
    }

    @Override
    public int refill() {
        return 0;
    }

    @Override
    public int refill(int amount) {
        return 0;
    }
}
