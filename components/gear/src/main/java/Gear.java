import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gear {
    // static instance
    private static final Gear instance = new Gear();
    // port
    public Port port;
    private final String manufacturer = "8864957";

    private GearType type;
    private final ArrayList<Wheel> wheels = new ArrayList<>();
    private boolean isDown = true;

    // private constructor
    private Gear() {
        port = new Port();

        wheels.addAll(List.of(
                new Wheel("1", new Date().getTime(), new Brake("1", new Date().getTime())),
                new Wheel("2", new Date().getTime(), new Brake("2", new Date().getTime()))
        ));
    }

    // static method getInstance
    public static Gear getInstance() {
        return instance;
    }

    // inner methods
    public String innerVersion() {
        return "Gear // " + manufacturer + " - " + type;
    }

    public GearType innerSetGearType(String type) {
        this.type = switch (type) {
            case "front" -> GearType.front;
            case "rear" -> GearType.rear;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        return this.type;
    }

    public boolean innerDown() {
        isDown = true;
        return true;
    }

    public boolean innerUp() {
        isDown = false;
        return false;
    }

    public int innerSetBrake() {
        return innerSetBrake(100);
    }

    public int innerSetBrake(int percentage) {
        if (percentage > 100) {
            percentage = 100;
        }
        if (percentage < 0) {
            percentage = 0;
        }
        for (Wheel wheel : wheels) {
            wheel.getBrake().setPercentage(percentage);
        }
        return percentage;
    }

    public int innerReleaseBrake() {
        return innerSetBrake(0);
    }


    // inner class port
    public class Port implements IGear {
        public String version() {
            return innerVersion();
        }

        public GearType setGearType(String type) {
            return innerSetGearType(type);
        }

        public boolean down() {
            return innerDown();
        }

        public boolean up() {
            return innerUp();
        }

        public int setBrake() {
            return innerSetBrake();
        }

        public int setBrake(int percentage) {
            return innerSetBrake(percentage);
        }

        public int releaseBrake() {
            return innerReleaseBrake();
        }


    }
}
