public class DroopNose implements IDroopNose{

    private String manufacturer;
    private String type;
    private String id;
    private int degree;

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

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    @Override
    public String version() {
        return null;
    }

    @Override
    public int neutral() {
        return 0;
    }

    @Override
    public int fullDown() {
        return 0;
    }

    @Override
    public int down(int degree) {
        return 0;
    }

    @Override
    public int up(int degree) {
        return 0;
    }
}
