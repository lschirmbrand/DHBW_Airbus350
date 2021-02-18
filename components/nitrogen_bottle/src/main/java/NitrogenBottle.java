public class NitrogenBottle implements INitrogenBottle{

    private String manufacturer;
    private String type;
    private String id;
    private int amount = 250;


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
