package base;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.util.ArrayList;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class PrimaryFlightDisplayGUI extends Application {
    private TableView<PrimaryFlightDisplayEntry> tableView;
    private ArrayList<PrimaryFlightDisplayEntry> dataList;
    private ObservableList<PrimaryFlightDisplayEntry> data;

    // air_conditioning
    private PrimaryFlightDisplayEntry airConditioningOnEntry;
    private RadioButton airConditioningOffButton;
    private RadioButton airConditioningOnButton;
    private PrimaryFlightDisplayEntry temperatureAirConditioningEntry;
    private Label temperatureAirConditioningLabel;

    // apu
    private PrimaryFlightDisplayEntry apuIsStartedEntry;
    private RadioButton apuStartedButton;
    private RadioButton apuShutdownButton;
    private PrimaryFlightDisplayEntry apuRPMEntry;
    private Label apuRPMLabel;

    // gear
    private PrimaryFlightDisplayEntry gearIsDownEntry;
    private ComboBox<String> gearComboBox;
    private PrimaryFlightDisplayEntry gearBrakePercentageEntry;
    private Label gearBrakePercentageLabel;

    // weather_radar
    private PrimaryFlightDisplayEntry weatherRadarIsOnEntry;
    private RadioButton weatherRadarOffButton;
    private RadioButton weatherRadarOnButton;

    // engine
    private PrimaryFlightDisplayEntry engineIsStartedEntry;
    private RadioButton engineOffButton;
    private RadioButton engineOnButton;
    private PrimaryFlightDisplayEntry engineRPMEntry;
    private Label engineRPMLabel;


    // HydraulicPump
    private PrimaryFlightDisplayEntry hydraulicPumpBodyOilAmountEntry;
    private PrimaryFlightDisplayEntry hydraulicPumpWingOilAmountEntry;
    private Label hydraulicPumpBodyOilAmountLabel;
    private Label hydraulicPumpWingOilAmountLabel;

    //DroopNose
    private PrimaryFlightDisplayEntry degreeDroopNoseEntry;
    private Button droopNoseNeutralButton;
    private Button droopNoseFullDownButton;
    private Button droopNoseUpButton;
    private Button droopNoseDownButton;
    private Label droopNoseDegreeLabel;

    //TCAS
    private PrimaryFlightDisplayEntry tcasIsOnEntry;
    private PrimaryFlightDisplayEntry tcasIsAlarmEntry;
    private PrimaryFlightDisplayEntry tcasIsConnectedEntry;
    private PrimaryFlightDisplayEntry tcasAltitudeEntry;
    private ToggleGroup tcasToggleGroup;
    private RadioButton tcasOnRadio;
    private RadioButton tcasOffRadio;
    private Button tcasConnectButton;
    private Button tcasDetermineAltitudeButton;
    private Button tcasScanButton;
    private Label tcasIsAlarmLabel;
    private Label tcasIsConnectedLabel;
    private Label tcasAltitudeLabel;

    //Turbulent AirFlow Sensor
    private PrimaryFlightDisplayEntry turbulentAirFlowSensorIsAlarmEntry;
    private Button turbulentAirFlowSensorMeasureBodyButton;
    private Button turbulentAirFlowSensorMeasureWingButton;
    private Label turbulentAirFlowSensorIsAlarmLabel;

    //Camera
    private PrimaryFlightDisplayEntry cameraIsOnEntry;
    private ToggleGroup cameraBodyToggleGroup;
    private ToggleGroup cameraWingToggleGroup;
    private RadioButton cameraBodyOnRadio;
    private RadioButton cameraBodyOffRadio;
    private RadioButton cameraWingOnRadio;
    private RadioButton cameraWingOffRadio;

    //GPS
    private PrimaryFlightDisplayEntry gpsIsOnEntry;
    private PrimaryFlightDisplayEntry gpsIsConnectedEntry;
    private ToggleGroup gpsToggleGroup;
    private RadioButton gpsOnRadio;
    private RadioButton gpsOffRadio;
    private Button gpsSendButton;
    private Button gpsReceiveButton;
    private Button gpsConnectButton;
    private Label gpsConnectedLabel;

    //Radar
    private PrimaryFlightDisplayEntry radarIsOnEntry;
    private ToggleGroup radarToggleGroup;
    private RadioButton radarOnRadio;
    private RadioButton radarOffRadio;
    private Button radarScanButton;

    // Elevator
    private PrimaryFlightDisplayEntry degreeElevatorEntry;
    private Label degreeElevator;

    //OxygenBottle
    private Button oxygenBottleRefillButton;
    private Button oxygenBottleTakeOutButton;
    private PrimaryFlightDisplayEntry amountOxygenBottleEntry;
    private Label amountOxygenBottle;

    //NitrogenBottle
    private Button nitrogenBottleRefillButton;
    private Button nitrogenBottleTakeOutButton;
    private PrimaryFlightDisplayEntry amountNitrogenBottleEntry;
    private Label amountNitrogenBottle;

    // crew_seat
    private PrimaryFlightDisplayEntry crewSeatNonSmokingSignEntry;
    private RadioButton crewSeatNonSmokingSignOn;
    private RadioButton crewSeatNonSmokingSignOff;
    private PrimaryFlightDisplayEntry crewSeatSeatBeltSignEntry;
    private RadioButton crewSeatBeltSignOn;
    private RadioButton crewSeatBeltSignOff;

    // economy_class_seat
    private PrimaryFlightDisplayEntry economyClassSeatNonSmokingSignEntry;
    private RadioButton economyClassSeatNonSmokingSignOn;
    private RadioButton economyClassSeatNonSmokingSignOff;
    private PrimaryFlightDisplayEntry economyClassSeatSeatBeltSignEntry;
    private RadioButton economyClassSeatBeltSignOn;
    private RadioButton economyClassSeatBeltSignOff;
    private PrimaryFlightDisplayEntry economyClassSeatLevelOneSeatEntry;
    private Label economyClassSeatLevelOneSeat;

    // fire_detector
    private PrimaryFlightDisplayEntry fireDetectorBodyEntry;
    private Label fireDetectorBodyAlarm;
    private PrimaryFlightDisplayEntry fireDetectorWingEntry;
    private Label fireDetectorWingAlarm;

    //oxygen_sensor
    private PrimaryFlightDisplayEntry oxygenSensorEntry;
    private Label oxygenSensorAlarm;

    // air_flow_sensor
    private PrimaryFlightDisplayEntry airFlowSensorIsBodyAlarmEntry;
    private PrimaryFlightDisplayEntry airFlowSensorIsWingAlarmEntry;
    private PrimaryFlightDisplayEntry airFlowSensorAirPressureEntry;
    private Button airFlowSensorBodyMeasureButton;
    private Button airFlowSensorWingMeasureButton;
    private Label airPressureLabel;
    private Label isAirFlowSensorBodyAlarmLabel;
    private Label isAirFlowSensorWingAlarmLabel;

    //battery
    private PrimaryFlightDisplayEntry batteryPercentageBatteryEntry;
    private Button batteryChargeButton;
    private Button batteryDischargeButton;
    private Label batteryPercentageLabel;

    //deicing_system
    private PrimaryFlightDisplayEntry deicingSystemOnEntry;
    private PrimaryFlightDisplayEntry deicingSystemAmountDeIcingSystemEntry;
    private RadioButton deIcingSystemOnButton;
    private RadioButton deIcingSystemOffButton;
    private Button deIcingSystemDeIceButton;
    private Button deIcingSystemRefillButton;
    private Label amountDeIcingSystemLabel;
    private Label isDeIcingSystemActivatedLabel;

    // temperature_sensor
    private PrimaryFlightDisplayEntry temperatureSensorTemperatureBodyEntry;
    private PrimaryFlightDisplayEntry temperatureSensorBodyAlarmEntry;
    private PrimaryFlightDisplayEntry temperatureSensorTemperatureWingEntry;
    private PrimaryFlightDisplayEntry temperatureSensorWingAlarmEntry;
    private Button temperatureSensorBodyMeasureButton;
    private Button temperatureSensorWingMeasureButton;
    private Label temperatureBodyLabel;
    private Label isTemperatureSensorBodyAlarmLabel;
    private Label temperatureWingLabel;
    private Label isTemperatureSensorWingAlarmLabel;

    public static void main(String... args) {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();

        Application.launch(args);

        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("A350 - Primary Flight Display");

        Airplane airplane = new Airplane();
        airplane.build();

        Cockpit cockpit = new Cockpit(airplane);

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #336699;");

        Button startupButton = new Button("Startup");
        startupButton.setPrefSize(75, 20);

        startupButton.setOnAction(event -> {
            cockpit.startup();
            update();
        });

        Button taxiButton = new Button("Taxi");
        taxiButton.setPrefSize(75, 20);

        taxiButton.setOnAction(event -> {
            cockpit.taxi();
            update();
        });

        Button takeoffButton = new Button("TakeOff");
        takeoffButton.setPrefSize(75, 20);

        takeoffButton.setOnAction(event -> {
            cockpit.takeoff();
            update();
        });

        Button climbingButton = new Button("Climbing");
        climbingButton.setPrefSize(75, 20);

        climbingButton.setOnAction(event -> {
            cockpit.climbing();
            update();
        });

        Button rightTurnButton = new Button("R-Turn");
        rightTurnButton.setPrefSize(75, 20);

        rightTurnButton.setOnAction(event -> {
            cockpit.rightTurn();
            update();
        });

        Button leftTurnButton = new Button("L-Turn");
        leftTurnButton.setPrefSize(75, 20);

        leftTurnButton.setOnAction(event -> {
            cockpit.leftTurn();
            update();
        });

        Button descentButton = new Button("Descent");
        descentButton.setPrefSize(75, 20);

        descentButton.setOnAction(event -> {
            cockpit.descent();
            update();
        });

        Button landingButton = new Button("Landing");
        landingButton.setPrefSize(75, 20);

        landingButton.setOnAction(event -> {
            cockpit.landing();
            update();
        });

        Button shutdownButton = new Button("Shutdown");
        shutdownButton.setPrefSize(75, 20);

        shutdownButton.setOnAction(event -> {
            cockpit.shutdown();
            update();
        });

        hBox.getChildren().addAll(startupButton, taxiButton, takeoffButton, climbingButton, rightTurnButton,
                leftTurnButton, descentButton, landingButton, shutdownButton);

        TabPane tabPane = new TabPane();

        Tab visualTab = new Tab();
        visualTab.setText("Visual");
        ScrollPane sp = new ScrollPane(buildVisualView());
        sp.setFitToHeight(true);
        sp.setFitToWidth(true);
        visualTab.setContent(sp);
        tabPane.getTabs().add(visualTab);

        Tab tableTab = new Tab();
        tableTab.setText("Table");
        buildTableView();
        tableTab.setContent(tableView);
        tabPane.getTabs().add(tableTab);

        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        sp = new ScrollPane(tabPane);
        sp.setFitToWidth(true);
        vbox.getChildren().addAll(hBox, sp);

        Scene scene = new Scene(vbox, 1300, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public GridPane buildVisualView() {
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.BASELINE_LEFT);

        // --- insert section: begin

        // weather_radar
        Label weatherRadarLabel = new Label("WeatherRadar : ");
        weatherRadarLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(weatherRadarLabel, 0, 3);

        ToggleGroup weatherRadarToggleGroup = new ToggleGroup();

        weatherRadarOffButton = new RadioButton("Off");
        weatherRadarOffButton.setToggleGroup(weatherRadarToggleGroup);
        weatherRadarOffButton.setSelected(true);
        gridPane.add(weatherRadarOffButton, 2, 3);

        weatherRadarOnButton = new RadioButton("On");
        weatherRadarOnButton.setToggleGroup(weatherRadarToggleGroup);
        weatherRadarOnButton.setSelected(false);

        gridPane.add(weatherRadarOnButton, 1, 3);

        // droop_nose
        Label droopNoseLabel = new Label("DroopNose : ");
        droopNoseLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(droopNoseLabel, 0, 4);

        droopNoseDownButton = new Button("Down");
        droopNoseFullDownButton = new Button("FullDown");
        droopNoseUpButton = new Button("Up");
        droopNoseNeutralButton = new Button("Neutral");
        droopNoseDegreeLabel = new Label("0\u00B0");
        gridPane.add(droopNoseDownButton, 1, 4);
        gridPane.add(droopNoseFullDownButton, 2, 4);
        gridPane.add(droopNoseUpButton, 3, 4);
        gridPane.add(droopNoseNeutralButton, 4, 4);
        gridPane.add(droopNoseDegreeLabel, 5, 4);

        //Camera
        Label cameraLabel = new Label("Camera: ");
        cameraLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(cameraLabel, 0, 5);

        cameraBodyToggleGroup = new ToggleGroup();

        gridPane.add(new Label("Body: "), 1, 5);
        cameraBodyOnRadio = new RadioButton("On");
        cameraBodyOnRadio.setToggleGroup(cameraBodyToggleGroup);
        gridPane.add(cameraBodyOnRadio, 2, 5);

        cameraBodyOffRadio = new RadioButton("Off");
        cameraBodyOffRadio.setToggleGroup(cameraBodyToggleGroup);
        cameraBodyOffRadio.setSelected(true);
        gridPane.add(cameraBodyOffRadio, 3, 5);

        cameraWingToggleGroup = new ToggleGroup();

        gridPane.add(new Label("Wing: "), 4, 5);
        cameraWingOnRadio = new RadioButton("On");
        cameraWingOnRadio.setToggleGroup(cameraWingToggleGroup);
        gridPane.add(cameraWingOnRadio, 5, 5);

        cameraWingOffRadio = new RadioButton("Off");
        cameraWingOffRadio.setToggleGroup(cameraWingToggleGroup);
        cameraWingOffRadio.setSelected(true);
        gridPane.add(cameraWingOffRadio, 6, 5);

        //GPS
        Label gpsLabel = new Label("GPS: ");
        gpsLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(gpsLabel, 0, 6);

        gpsToggleGroup = new ToggleGroup();

        gpsOnRadio = new RadioButton("On");
        gpsOnRadio.setToggleGroup(gpsToggleGroup);
        gridPane.add(gpsOnRadio, 1, 6);

        gpsOffRadio = new RadioButton("Off");
        gpsOffRadio.setToggleGroup(gpsToggleGroup);
        gpsOffRadio.setSelected(true);
        gridPane.add(gpsOffRadio, 2, 6);

        gridPane.add((gpsSendButton = new Button("Send")), 3, 6);
        gridPane.add((gpsReceiveButton = new Button("Receive")), 4, 6);
        gridPane.add((gpsConnectButton = new Button("Connect")), 5, 6);
        gridPane.add((gpsConnectedLabel = new Label("Not Connected")), 6, 6);

        //Radar
        Label radarLabel = new Label("Radar: ");
        radarLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(radarLabel, 0, 7);

        radarToggleGroup = new ToggleGroup();

        radarOnRadio = new RadioButton("On");
        radarOnRadio.setToggleGroup(radarToggleGroup);
        gridPane.add(radarOnRadio, 1, 7);

        radarOffRadio = new RadioButton("Off");
        radarOffRadio.setToggleGroup(radarToggleGroup);
        radarOffRadio.setSelected(true);
        gridPane.add(radarOffRadio, 2, 7);

        gridPane.add((radarScanButton = new Button("Scan")), 3, 7);

        //TCAS
        Label tcasLabel = new Label("TCAS");
        tcasLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(tcasLabel, 0, 8);

        tcasToggleGroup = new ToggleGroup();

        tcasOnRadio = new RadioButton("On");
        tcasOnRadio.setToggleGroup(tcasToggleGroup);
        gridPane.add(tcasOnRadio, 1, 8);

        tcasOffRadio = new RadioButton("Off");
        tcasOffRadio.setToggleGroup(tcasToggleGroup);
        tcasOffRadio.setSelected(true);
        gridPane.add(tcasOffRadio, 2, 8);

        gridPane.add((tcasConnectButton = new Button("Connect")), 3, 8);
        gridPane.add((tcasIsConnectedLabel = new Label("Not Connected")), 4, 8);
        gridPane.add((tcasDetermineAltitudeButton = new Button("Determine Altitude")), 5, 8);
        gridPane.add((tcasAltitudeLabel = new Label("0\u00B0")), 6, 8);
        gridPane.add((tcasScanButton = new Button("Scan")), 7, 8);
        gridPane.add((tcasIsAlarmLabel = new Label("No Alarm")), 8, 8);

        //Turbulent Airflow Sensor
        Label turbulentLabel = new Label("Turbulent Airflow Sensor: ");
        turbulentLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(turbulentLabel, 0, 9);
        gridPane.add((turbulentAirFlowSensorMeasureBodyButton = new Button("Measure Body")), 1, 9);
        gridPane.add((turbulentAirFlowSensorMeasureWingButton = new Button("Measure Wing")), 2, 9);
        gridPane.add((turbulentAirFlowSensorIsAlarmLabel = new Label("No Alarm")), 3, 9);

        // apu
        Label apuLabel = new Label("APU : ");
        apuLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(apuLabel, 0, 1);

        ToggleGroup apuToggleGroup = new ToggleGroup();
        apuShutdownButton = new RadioButton("Off");
        apuShutdownButton.setToggleGroup(apuToggleGroup);
        apuShutdownButton.setSelected(true);
        gridPane.add(apuShutdownButton, 2, 1);

        apuStartedButton = new RadioButton("On");
        apuStartedButton.setToggleGroup(apuToggleGroup);
        gridPane.add(apuStartedButton, 1, 1);

        apuRPMLabel = new Label("0 rpm");
        gridPane.add(apuRPMLabel, 3, 1);

        // engine
        Label engineLabel = new Label("Engine : ");
        engineLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(engineLabel, 0, 10);

        ToggleGroup engineToggleGroup = new ToggleGroup();

        engineOffButton = new RadioButton("Off");
        engineOffButton.setToggleGroup(engineToggleGroup);
        engineOffButton.setSelected(true);
        gridPane.add(engineOffButton, 2, 10);

        engineOnButton = new RadioButton("On");
        engineOnButton.setToggleGroup(engineToggleGroup);
        engineOnButton.setSelected(false);
        gridPane.add(engineOnButton, 1, 10);

        //noinspection SpellCheckingInspection
        engineRPMLabel = new Label("RPM's: 0");
        gridPane.add(engineRPMLabel, 3, 10);

        // gear
        Label gearLabel = new Label("Gear : ");
        gearLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(gearLabel, 0, 11);

        gearComboBox = new ComboBox<>();
        gearComboBox.getItems().addAll("down", "up");
        gearComboBox.setValue("down");
        gearComboBox.setEditable(false);
        gridPane.add(gearComboBox, 1, 11);

        Label gearBrakeLabel = new Label("Brake: ");
        gridPane.add(gearBrakeLabel, 2, 11);

        gearBrakePercentageLabel = new Label(0 + "%");
        gridPane.add(gearBrakePercentageLabel, 3, 11);

        // Hydraulic Pump
        Label hydraulicPumpLabel = new Label("Hydraulic Pump:");
        hydraulicPumpLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(hydraulicPumpLabel, 0, 12);
        hydraulicPumpBodyOilAmountLabel = new Label("5000 PSI at Body");
        gridPane.add(hydraulicPumpBodyOilAmountLabel, 1, 12);
        hydraulicPumpWingOilAmountLabel = new Label("5000 PSI at Wing");
        gridPane.add(hydraulicPumpWingOilAmountLabel, 2, 12);

        // air_conditioning
        Label airConditioningLabel = new Label("AirConditioning : ");
        airConditioningLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(airConditioningLabel, 0, 13);

        ToggleGroup ACToggleGroup = new ToggleGroup();

        airConditioningOffButton = new RadioButton("Off");
        airConditioningOffButton.setToggleGroup(ACToggleGroup);
        airConditioningOffButton.setSelected(true);
        gridPane.add(airConditioningOffButton, 2, 13);

        airConditioningOnButton = new RadioButton("On");
        airConditioningOnButton.setToggleGroup(ACToggleGroup);
        gridPane.add(airConditioningOnButton, 1, 13);

        temperatureAirConditioningLabel = new Label("0 \u2103");
        gridPane.add(temperatureAirConditioningLabel, 3, 13);

        // Elevator
        Label elevatorLabel = new Label("Elevators");
        elevatorLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(elevatorLabel, 0, 14);
        degreeElevator = new Label("90 \u00B0");
        gridPane.add(degreeElevator, 1, 14);

        //OxygenBottle
        Label oxygenBottleLabel = new Label("OxygenBottle");
        oxygenBottleLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(oxygenBottleLabel, 0, 15);
        amountOxygenBottle = new Label("100");
        gridPane.add(amountOxygenBottle, 1, 15);
        oxygenBottleRefillButton = new Button("Refill");
        gridPane.add(oxygenBottleRefillButton, 2, 15);
        oxygenBottleTakeOutButton = new Button("TakeOut");
        gridPane.add(oxygenBottleTakeOutButton, 3, 15);

        //NitrogenBottle
        Label nitrogenBottleLabel = new Label("NitrogenBottle");
        nitrogenBottleLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(nitrogenBottleLabel, 0, 16);
        amountNitrogenBottle = new Label("250");
        gridPane.add(amountNitrogenBottle, 1, 16);
        nitrogenBottleRefillButton = new Button("Refill");
        gridPane.add(nitrogenBottleRefillButton, 2, 16);
        nitrogenBottleTakeOutButton = new Button("TakeOut");
        gridPane.add(nitrogenBottleTakeOutButton, 3, 16);

// crew_seat
        Label crewSeatNonSmokingSignLabel = new Label("CrewSeatNonSmokingSign : ");
        gridPane.add(crewSeatNonSmokingSignLabel, 0, 3);

        ToggleGroup crewSeatNonSmokingSignToggleGroup = new ToggleGroup();

        //crew_seat_smoking_sign_On
        crewSeatNonSmokingSignOn = new RadioButton("On");
        crewSeatNonSmokingSignOn.setToggleGroup(crewSeatNonSmokingSignToggleGroup);
        crewSeatNonSmokingSignOn.setSelected(false);
        gridPane.add(crewSeatNonSmokingSignOn, 1, 3);

        //crew_seat_smoking_sign_Off
        crewSeatNonSmokingSignOff = new RadioButton("Off");
        crewSeatNonSmokingSignOff.setToggleGroup(crewSeatNonSmokingSignToggleGroup);
        crewSeatNonSmokingSignOff.setSelected(true);
        gridPane.add(crewSeatNonSmokingSignOff, 2, 3);

        Label crewSeatSeatBeltSignLabel = new Label("CrewSeatBeltSign : ");
        gridPane.add(crewSeatSeatBeltSignLabel, 0, 4);

        ToggleGroup crewSeatBeltSignToggleGroup = new ToggleGroup();

        //crew_seat_belt_sign_On
        crewSeatBeltSignOn = new RadioButton("On");
        crewSeatBeltSignOn.setToggleGroup(crewSeatBeltSignToggleGroup);
        crewSeatBeltSignOn.setSelected(false);
        gridPane.add(crewSeatBeltSignOn, 1, 4);

        //crew_seat_belt_sign_Off
        crewSeatBeltSignOff = new RadioButton("Off");
        crewSeatBeltSignOff.setToggleGroup(crewSeatBeltSignToggleGroup);
        crewSeatBeltSignOff.setSelected(true);
        gridPane.add(crewSeatBeltSignOff, 2,4);

        // economy_class_seat
        Label economyClassSeatNonSmokingSignLabel = new Label("EconomyClassSeatNonSmokingSign : ");
        gridPane.add(economyClassSeatNonSmokingSignLabel, 0, 5);

        ToggleGroup economyClassSeatNonSmokingSignToggleGroup = new ToggleGroup();

        // economy_class_seat_smoking_sign_On
        economyClassSeatNonSmokingSignOn = new RadioButton("On");
        economyClassSeatNonSmokingSignOn.setToggleGroup(economyClassSeatNonSmokingSignToggleGroup);
        economyClassSeatNonSmokingSignOn.setSelected(false);
        gridPane.add(economyClassSeatNonSmokingSignOn, 1, 5);

        //economy_class_seat_smoking_sign_Off
        economyClassSeatNonSmokingSignOff = new RadioButton("Off");
        economyClassSeatNonSmokingSignOff.setToggleGroup(economyClassSeatNonSmokingSignToggleGroup);
        economyClassSeatNonSmokingSignOff.setSelected(true);
        gridPane.add(economyClassSeatNonSmokingSignOff, 2, 5);

        Label economyClassSeatSeatBeltSignLabel = new Label("EconomyClassSeatBeltSign : ");
        gridPane.add(economyClassSeatSeatBeltSignLabel, 0, 6);

        ToggleGroup economyClassSeatBeltSignToggleGroup = new ToggleGroup();

        // economy_class_seat_belt_sign_On
        economyClassSeatBeltSignOn = new RadioButton("On");
        economyClassSeatBeltSignOn.setToggleGroup(economyClassSeatBeltSignToggleGroup);
        economyClassSeatBeltSignOn.setSelected(false);
        gridPane.add(economyClassSeatBeltSignOn, 1, 6);

        // economy_class_seat_belt_sign_Off
        economyClassSeatBeltSignOff = new RadioButton("Off");
        economyClassSeatBeltSignOff.setToggleGroup(economyClassSeatBeltSignToggleGroup);
        economyClassSeatBeltSignOff.setSelected(true);
        gridPane.add(economyClassSeatBeltSignOff, 2, 6);

        economyClassSeatLevelOneSeat = new Label("EconomyClassSeatLevelOne :");
        gridPane.add(economyClassSeatLevelOneSeat, 0, 7);

        // fire_detector
        fireDetectorBodyAlarm = new Label("FireDetector Body : ");
        gridPane.add(fireDetectorBodyAlarm, 0, 8);

        fireDetectorWingAlarm = new Label("FireDetector Wing : ");
        gridPane.add(fireDetectorWingAlarm, 0, 9);

        // oxygen_sensor
        oxygenSensorAlarm = new Label("OxygenSensor Alarm : ");
        gridPane.add(oxygenSensorAlarm, 0, 10);
        // air_flow_sensor
        Label airFlowSensorLabel = new Label("AirFlowSensor");
        airFlowSensorLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(airFlowSensorLabel, 0, 17);

        isAirFlowSensorBodyAlarmLabel = new Label("AirPressure: 0");
        gridPane.add(isAirFlowSensorBodyAlarmLabel, 1, 17);

        airFlowSensorBodyMeasureButton = new Button("MeasureBody");
        gridPane.add(airFlowSensorBodyMeasureButton, 2, 17);
        isAirFlowSensorWingAlarmLabel = new Label("No body alarm");
        gridPane.add(isAirFlowSensorWingAlarmLabel, 3, 17);

        airFlowSensorWingMeasureButton = new Button("MeasureWing");
        gridPane.add(airFlowSensorWingMeasureButton, 4, 17);
        airPressureLabel = new Label("No wing alarm");
        gridPane.add(airPressureLabel, 5, 17);

        // battery
        Label batteryLabel = new Label("Battery");
        batteryLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(batteryLabel, 0, 18);

        batteryPercentageLabel = new Label("BatteryPercentage: 100%");
        gridPane.add(batteryPercentageLabel, 1, 18);

        batteryChargeButton = new Button("ChargeBattery");
        gridPane.add(batteryChargeButton, 2, 18);

        batteryDischargeButton = new Button("DischargeBattery");
        gridPane.add(batteryDischargeButton, 3, 18);

        // de_icing_system
        Label deIcingSystemLabel = new Label("DeIcingSystem");
        deIcingSystemLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(deIcingSystemLabel, 0, 19);

        ToggleGroup deIcingSystemToggleGroup = new ToggleGroup();

        deIcingSystemOffButton = new RadioButton("Off");
        deIcingSystemOffButton.setToggleGroup(deIcingSystemToggleGroup);
        deIcingSystemOffButton.setSelected(true);
        gridPane.add(deIcingSystemOffButton, 2, 19);

        deIcingSystemOnButton = new RadioButton("On");
        deIcingSystemOnButton.setToggleGroup(deIcingSystemToggleGroup);
        deIcingSystemOnButton.setSelected(false);
        gridPane.add(deIcingSystemOnButton, 1, 19);

        amountDeIcingSystemLabel = new Label("Amount: 50");
        gridPane.add(amountDeIcingSystemLabel, 3, 19);

        deIcingSystemDeIceButton = new Button("DeIce");
        gridPane.add(deIcingSystemDeIceButton, 4, 19);

        deIcingSystemRefillButton = new Button("Refill");
        gridPane.add(deIcingSystemRefillButton, 5, 19);

        // temperature_sensor
        Label temperatureSensorLabel = new Label("TemperatureSensor");
        temperatureSensorLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(temperatureSensorLabel, 0,20);

        temperatureBodyLabel = new Label("TemperatureBody: 0\u2103");
        gridPane.add(temperatureBodyLabel, 1, 20);

        temperatureSensorBodyMeasureButton = new Button("MeasureBody");
        gridPane.add(temperatureSensorBodyMeasureButton, 3, 20);

        isTemperatureSensorBodyAlarmLabel = new Label("No body alarm");
        gridPane.add(isTemperatureSensorBodyAlarmLabel, 4, 20);

        temperatureWingLabel = new Label("TemperatureWing: 0\u2103");
        gridPane.add(temperatureWingLabel, 5, 20);

        temperatureSensorWingMeasureButton = new Button("MeasureWing");
        gridPane.add(temperatureSensorWingMeasureButton, 6, 20);

        isTemperatureSensorWingAlarmLabel = new Label("No wing alarm");
        gridPane.add(isTemperatureSensorWingAlarmLabel, 7, 20);


        // --- insert section: end

        return gridPane;
    }

    public void buildTableView() {
        tableView = new TableView<>();
        data = getInitialTableData();
        tableView.setItems(data);

        TableColumn<PrimaryFlightDisplayEntry, String> attributeColumn = new TableColumn<>("attribute");
        attributeColumn.setCellValueFactory(new PropertyValueFactory<>("attribute"));

        TableColumn<PrimaryFlightDisplayEntry, String> valueColumn = new TableColumn<>("value");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        //noinspection unchecked
        tableView.getColumns().setAll(attributeColumn, valueColumn);
        tableView.setPrefWidth(450);
        tableView.setPrefHeight(450);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }


    // weather_radar
    public void setWeatherRadarToggleGroup(boolean isWeatherRadarOn) {
        if (isWeatherRadarOn) {
            weatherRadarOffButton.setSelected(false);
            weatherRadarOnButton.setSelected(true);
        } else {
            weatherRadarOffButton.setSelected(true);
            weatherRadarOnButton.setSelected(false);
        }
    }

    // apu
    public void setApuToggleGroup(boolean isAPUStarted) {
        apuStartedButton.setSelected(isAPUStarted);
        apuShutdownButton.setSelected(!isAPUStarted);
    }

    public void setAPURPMLabel(int rpm) {
        apuRPMLabel.setText(rpm + " rpm");
    }

    // gear
    public void setGearComboBox(boolean isGearDown) {
        gearComboBox.setValue(isGearDown ? "down" : "up");
    }

    public void setGearBrakePercentageLabel(int percentage) {
        gearBrakePercentageLabel.setText(percentage + "%");
    }

    // air_conditioning
    public void setAirConditioningToggleGroup(boolean isAirConditioningOn) {
        airConditioningOnButton.setSelected(isAirConditioningOn);
        airConditioningOffButton.setSelected(!isAirConditioningOn);
    }

    public void setTemperatureAirConditioningLabel(int temperature) {
        temperatureAirConditioningLabel.setText(temperature + " \u2103");
    }

    // air_flow_sensor
    public void setAirFlowSensorAirPressureLabel(int airPressure){
        airPressureLabel.setText("AirPressure:" + airPressure);
    }
    public void setIsAirFlowSensorBodyAlarmLabel(boolean bodyAlarm){
        isAirFlowSensorBodyAlarmLabel.setText((bodyAlarm ? "" : "No ") + "body alarm");
    }
    public void setIsAirFlowSensorWingAlarmLabel(boolean wingAlarm){
        isAirFlowSensorBodyAlarmLabel.setText((wingAlarm ? "" : "No ") + "wing alarm");
    }

    //battery
    public void setBatteryPercentageLabel(int batteryPercentage){
        batteryPercentageLabel.setText(batteryPercentage + " %");
    }
    // de_icing_system
    public void setDeIcingSystemToggleGroup(boolean isDeIcingSystemOn){
        deIcingSystemOnButton.setSelected(isDeIcingSystemOn);
        deIcingSystemOffButton.setSelected(!isDeIcingSystemOn);
    }
    public void setAmountDeIcingSystemLabel(int amountDeIcingSystem){
        amountDeIcingSystemLabel.setText(Integer.toString(amountDeIcingSystem));
    }
    //temperature_sensor
    public void setTemperatureBodyLabel(int temperatureBody){
        temperatureBodyLabel.setText("TemperatureBody: " + temperatureBody + "\u2103");
    }
    public void setTemperatureWingLabel(int temperatureWing){
        temperatureWingLabel.setText("TemperatureBody: " + temperatureWing + "\u2103");
    }
    public void setIsTemperatureSensorBodyAlarmLabel(boolean isTemperatureSensorBodyAlarm){
        isTemperatureSensorBodyAlarmLabel.setText((isTemperatureSensorBodyAlarm ? "" : "No ") + "body alarm");
    }
    public void setIsTemperatureSensorWingAlarmLabel(boolean isTemperatureSensorWingAlarm){
        isTemperatureSensorWingAlarmLabel.setText((isTemperatureSensorWingAlarm ? "" : "No ") + "wing alarm");
    }

    public void setEngineToggleGroup(boolean isEngineStarted) {
        if (isEngineStarted) {
            engineOffButton.setSelected(false);
            engineOnButton.setSelected(true);
        } else {
            engineOffButton.setSelected(true);
            engineOnButton.setSelected(false);
        }
    }

    public void setEngineRPMLabel(int rpm) {
        //noinspection SpellCheckingInspection
        engineRPMLabel.setText("RPM's: " + rpm);
    }

    public void setOilAmount(int amountB, int amountW) {
        hydraulicPumpBodyOilAmountLabel.setText(amountB + " psi at Body");
        hydraulicPumpWingOilAmountLabel.setText(amountW + " psi at Wing");
    }

    public void setDroopNoseDegreeLabel(int degree) {
        degreeDroopNoseEntry.setValue(Integer.toString(degree));
        droopNoseDegreeLabel.setText(degree + "\u00B0");
    }

    public void setTCASStartedToggleGroup(boolean isTCASStarted) {
        tcasIsOnEntry.setValue(Boolean.toString(isTCASStarted));
        tcasOffRadio.setSelected(!isTCASStarted);
        tcasOnRadio.setSelected(isTCASStarted);
    }

    public void setTCASIsConnected(boolean isTCASConnected) {
        tcasIsConnectedEntry.setValue(Boolean.toString(isTCASConnected));
        tcasIsConnectedLabel.setText((isTCASConnected ? "" : "Not ") + "Connected");
    }

    public void setTCASIsAlarm(boolean isTCASAlarm) {
        tcasIsAlarmEntry.setValue(Boolean.toString(isTCASAlarm));
        tcasIsAlarmLabel.setText((isTCASAlarm ? "" : "No ") + "Alarm");
    }

    public void setTCASAltitude(int altitude) {
        tcasAltitudeEntry.setValue(Integer.toString(altitude));
        tcasAltitudeLabel.setText(altitude + "\u00B0");
    }

    public void setGPSStartedToggleGroup(boolean isGPSStarted) {
        gpsIsOnEntry.setValue(Boolean.toString(isGPSStarted));
        gpsOffRadio.setSelected(!isGPSStarted);
        gpsOnRadio.setSelected(isGPSStarted);
    }

    public void setGPSConnectedLabel(boolean isGPSConnected) {
        gpsIsConnectedEntry.setValue(Boolean.toString(isGPSConnected));
        gpsConnectedLabel.setText((isGPSConnected ? "" : "Not ") + "Connected");
    }

    public void setTurbulentAirFlowSensorAlarmLabel(boolean isAlarm) {
        turbulentAirFlowSensorIsAlarmEntry.setValue(Boolean.toString(isAlarm));
        turbulentAirFlowSensorIsAlarmLabel.setText((isAlarm ? "" : "No ") + "Alarm");
    }

    public void setRadarStartedToggleGroup(boolean isTCASStarted) {
        radarIsOnEntry.setValue(Boolean.toString(isTCASStarted));
        radarOffRadio.setSelected(!isTCASStarted);
        radarOnRadio.setSelected(isTCASStarted);
    }

    public void setCameraBodyToggleGroup(boolean isCameraOnBodyStarted) {
        cameraBodyOffRadio.setSelected(!isCameraOnBodyStarted);
        cameraBodyOnRadio.setSelected(isCameraOnBodyStarted);
    }

    public void setCameraToggleGroups(boolean isCameraStarted) {
        cameraIsOnEntry.setValue(Boolean.toString(isCameraStarted));
        setCameraWingToggleGroup(isCameraStarted);
        setCameraBodyToggleGroup(isCameraStarted);
    }

    public void setCameraWingToggleGroup(boolean isCameraOnWingStarted) {
        cameraWingOffRadio.setSelected(!isCameraOnWingStarted);
        cameraWingOnRadio.setSelected(isCameraOnWingStarted);
    }

    public void setDegreeElevator(int degree) {
        degreeElevator.setText(degree + " \u00B0");
    }

    //nitrogenBottle
    public void setNitrogenBottleAmount(int amount) {
        amountNitrogenBottle.setText(String.valueOf(amount));
    }

    //OxygenBottle
    public void setOxygenBottleAmount(int amount) {
        amountOxygenBottle.setText(String.valueOf(amount));
    }

 // crew_seat
    public void setCrewSeatNonSmokingSignToggleGroup(boolean isNonSmokingSignOn) {
        if(isNonSmokingSignOn) {
            crewSeatNonSmokingSignOn.setSelected(true);
            crewSeatNonSmokingSignOff.setSelected(false);
        } else {
            crewSeatNonSmokingSignOff.setSelected(true);
            crewSeatNonSmokingSignOn.setSelected(false);
        }
    }

    public void setCrewSeatBeltSignToggleGroup(boolean isSeatBeltSignOn) {
        if(isSeatBeltSignOn) {
            crewSeatBeltSignOn.setSelected(true);
            crewSeatBeltSignOff.setSelected(false);
        } else {
            crewSeatBeltSignOff.setSelected(true);
            crewSeatBeltSignOn.setSelected(false);
        }
    }

    // economy_class_seat
    public void setEconomyClassSeatNonSmokingSignToggleGroup(boolean isNonSmokingSignOn) {
        if(isNonSmokingSignOn) {
            economyClassSeatNonSmokingSignOn.setSelected(true);
            economyClassSeatNonSmokingSignOff.setSelected(false);
        } else {
            economyClassSeatNonSmokingSignOff.setSelected(true);
            economyClassSeatNonSmokingSignOn.setSelected(false);
        }
    }

    public void setEconomyClassSeatBeltSignToggleGroup(boolean isSeatBeltSignOn) {
        if(isSeatBeltSignOn) {
            economyClassSeatBeltSignOn.setSelected(true);
            economyClassSeatBeltSignOff.setSelected(false);
        } else {
            economyClassSeatBeltSignOn.setSelected(false);
            economyClassSeatBeltSignOff.setSelected(true);
        }
    }

    public void setEconomyClassSeatLevelOneSeatLabel(int levelOneSeat) {
        economyClassSeatLevelOneSeat.setText("EconomyClassSeatLevelOne : " + levelOneSeat);
    }

    // fire_detector
    public void setFireDetectorBodyAlarmLabel(boolean isFireDetectedBody) {
        fireDetectorBodyAlarm.setText("FireDetector Body : " + isFireDetectedBody);
    }

    public void setFireDetectorWingAlarmLabel(boolean isFireDetectedWing) {
        fireDetectorWingAlarm.setText("FireDetector Wing : " + isFireDetectedWing);
    }

    // oxygen_sensor
    public void setOxygenSensorAlarmLabel(boolean isOxygenSensorAlarm) {
        oxygenSensorAlarm.setText("OxygenSensor Alarm : " + isOxygenSensorAlarm);
    }

    private void initData() {
        dataList = new ArrayList<>();

        weatherRadarIsOnEntry = new PrimaryFlightDisplayEntry("WeatherRadar (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        dataList.add(weatherRadarIsOnEntry);

        //Droop Nose
        dataList.add((degreeDroopNoseEntry = new PrimaryFlightDisplayEntry("DroopNose (degree)", Integer.toString(PrimaryFlightDisplay.instance.degreeDroopNose))));

        //TCAS
        dataList.add((tcasIsOnEntry = new PrimaryFlightDisplayEntry("TCAS (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isTCASOn))));
        dataList.add((tcasIsConnectedEntry = new PrimaryFlightDisplayEntry("TCAS (isConnected)", Boolean.toString(PrimaryFlightDisplay.instance.isTCASConnected))));
        dataList.add((tcasIsAlarmEntry = new PrimaryFlightDisplayEntry("TCAS (isAlarm)", Boolean.toString(PrimaryFlightDisplay.instance.isTCASAlarm))));
        dataList.add((tcasAltitudeEntry = new PrimaryFlightDisplayEntry("TCAS (Altitude)", Integer.toString(PrimaryFlightDisplay.instance.altitudeTCAS))));

        //Turbulent AirFlow Sensor
        dataList.add((turbulentAirFlowSensorIsAlarmEntry = new PrimaryFlightDisplayEntry("TurbulentAirFlowSensor (isAlarm)", Boolean.toString(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm))));

        //Camera
        dataList.add((cameraIsOnEntry = new PrimaryFlightDisplayEntry("Camera (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isCameraOn))));

        //GPS
        dataList.add((gpsIsOnEntry = new PrimaryFlightDisplayEntry("GPS (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isGPSOn))));
        dataList.add((gpsIsConnectedEntry = new PrimaryFlightDisplayEntry("GPS (isConnected)", Boolean.toString(PrimaryFlightDisplay.instance.isGPSConnected))));

        //Radar
        dataList.add((radarIsOnEntry = new PrimaryFlightDisplayEntry("Radar (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isRadarOn))));

        // apu
        apuIsStartedEntry = new PrimaryFlightDisplayEntry("APU (isStarted)", Boolean.toString(PrimaryFlightDisplay.instance.isAPUStarted));
        dataList.add(apuIsStartedEntry);
        apuRPMEntry = new PrimaryFlightDisplayEntry("APU (rpm)", Integer.toString(PrimaryFlightDisplay.instance.rpmAPU));
        dataList.add(apuRPMEntry);

        // gear
        gearIsDownEntry = new PrimaryFlightDisplayEntry("Gear (isDown)", Boolean.toString(PrimaryFlightDisplay.instance.isGearDown));
        dataList.add(gearIsDownEntry);
        gearBrakePercentageEntry = new PrimaryFlightDisplayEntry("Gear (brakePercentage)", Integer.toString(PrimaryFlightDisplay.instance.gearBrakePercentage));
        dataList.add(gearBrakePercentageEntry);

        // air_conditioning
        airConditioningOnEntry = new PrimaryFlightDisplayEntry("AirConditioning (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isGearDown));
        dataList.add(airConditioningOnEntry);
        temperatureAirConditioningEntry = new PrimaryFlightDisplayEntry("AirConditioning (temperature)", Integer.toString(PrimaryFlightDisplay.instance.temperatureAirConditioning));
        dataList.add(temperatureAirConditioningEntry);

        // air_flow_sensor
        airFlowSensorIsBodyAlarmEntry = new PrimaryFlightDisplayEntry("AirFlowSensor (isBodyAlarm)", Boolean.toString(PrimaryFlightDisplay.instance.isAirFlowSensorBodyAlarm));
        dataList.add(airFlowSensorIsBodyAlarmEntry);
        airFlowSensorIsWingAlarmEntry = new PrimaryFlightDisplayEntry("AirFlowSensor (isWingAlarm)", Boolean.toString(PrimaryFlightDisplay.instance.isAirFlowSensorWingAlarm));
        dataList.add(airFlowSensorIsWingAlarmEntry);
        airFlowSensorAirPressureEntry = new PrimaryFlightDisplayEntry("AirFlowSensor (airPressure)", Integer.toString(PrimaryFlightDisplay.instance.airPressure));
        dataList.add(airFlowSensorAirPressureEntry);

        // battery
        batteryPercentageBatteryEntry = new PrimaryFlightDisplayEntry("Battery (percentage)", Integer.toString(PrimaryFlightDisplay.instance.percentageBattery));
        dataList.add(batteryPercentageBatteryEntry);

        // de_icing_sensor
        deicingSystemOnEntry = new PrimaryFlightDisplayEntry("DeIcingSystem (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isDeIcingSystemActivated));
        dataList.add(deicingSystemOnEntry);
        deicingSystemAmountDeIcingSystemEntry = new PrimaryFlightDisplayEntry("DeIcingSystem (amount)", Integer.toString(PrimaryFlightDisplay.instance.amountDeIcingSystem));
        dataList.add(deicingSystemAmountDeIcingSystemEntry);

        // temperature_sensor
        temperatureSensorTemperatureBodyEntry = new PrimaryFlightDisplayEntry("TemperatureSensor (temperatureBody)", Integer.toString(PrimaryFlightDisplay.instance.temperatureBody));
        temperatureSensorTemperatureWingEntry = new PrimaryFlightDisplayEntry("TemperatureSensor (temperatureWing)", Integer.toString(PrimaryFlightDisplay.instance.temperatureWing));
        temperatureSensorBodyAlarmEntry = new PrimaryFlightDisplayEntry("TemperatureSensor (bodyAlarm)", Boolean.toString(PrimaryFlightDisplay.instance.isTemperatureSensorBodyAlarm));
        temperatureSensorWingAlarmEntry = new PrimaryFlightDisplayEntry("TemperatureSensor (wingAlarm)", Boolean.toString(PrimaryFlightDisplay.instance.isTemperatureSensorWingAlarm));
        dataList.add(temperatureSensorTemperatureBodyEntry);
        dataList.add(temperatureSensorTemperatureWingEntry);
        dataList.add(temperatureSensorBodyAlarmEntry);
        dataList.add(temperatureSensorWingAlarmEntry);

        // engine
        engineIsStartedEntry = new PrimaryFlightDisplayEntry("Engine (isStarted)", Boolean.toString(PrimaryFlightDisplay.instance.isEngineStarted));
        dataList.add(engineIsStartedEntry);

        engineRPMEntry = new PrimaryFlightDisplayEntry("Engine (RPM)", Integer.toString(PrimaryFlightDisplay.instance.rpmEngine));
        dataList.add(engineRPMEntry);

        // Hydraulic Pump
        hydraulicPumpBodyOilAmountEntry = new PrimaryFlightDisplayEntry("HydraulicPump  (Hydraulic Pump Pressure Body)", Integer.toString(PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount));
        hydraulicPumpWingOilAmountEntry = new PrimaryFlightDisplayEntry("HydraulicPump (Hydraulic Pump Pressure Wing)", Integer.toString(PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount));
        dataList.add(hydraulicPumpBodyOilAmountEntry);
        dataList.add(hydraulicPumpWingOilAmountEntry);

        // Elevator
        degreeElevatorEntry = new PrimaryFlightDisplayEntry("Elevator (Elevator degree)", Integer.toString(PrimaryFlightDisplay.instance.degreeElevator));
        dataList.add(degreeElevatorEntry);

        //OxygenBottle
        amountOxygenBottleEntry = new PrimaryFlightDisplayEntry("OxygenBottle (OxygenBottle amount)", Integer.toString(PrimaryFlightDisplay.instance.amountOxygenBottle));
        dataList.add(amountOxygenBottleEntry);
        //NitrogenBottle
        amountNitrogenBottleEntry = new PrimaryFlightDisplayEntry("NitrogenBottle (NitrogenBottle amount)", Integer.toString(PrimaryFlightDisplay.instance.amountNitrogenBottle));
        dataList.add(amountNitrogenBottleEntry);

// crew_seat
        crewSeatNonSmokingSignEntry = new PrimaryFlightDisplayEntry("CrewSeat (NonSmokingSign)", Boolean.toString(PrimaryFlightDisplay.instance.isNonSmokingSignOn));
        dataList.add(crewSeatNonSmokingSignEntry);
        crewSeatSeatBeltSignEntry = new PrimaryFlightDisplayEntry("CrewSeat (BeltSign)", Boolean.toString(PrimaryFlightDisplay.instance.isSeatBeltSignOn));
        dataList.add(crewSeatSeatBeltSignEntry);

        // economy_class_seat
        economyClassSeatNonSmokingSignEntry = new PrimaryFlightDisplayEntry("EconomyClassSeat (NonSmokingSign)", Boolean.toString(PrimaryFlightDisplay.instance.isNonSmokingSignOn));
        dataList.add(economyClassSeatNonSmokingSignEntry);
        economyClassSeatSeatBeltSignEntry = new PrimaryFlightDisplayEntry("EconomyClassSeat (BeltSign)", Boolean.toString(PrimaryFlightDisplay.instance.isSeatBeltSignOn));
        dataList.add(economyClassSeatSeatBeltSignEntry);
        economyClassSeatLevelOneSeatEntry = new PrimaryFlightDisplayEntry("EconomyClassSeat (LevelSeat)", Integer.toString(PrimaryFlightDisplay.instance.levelSeat));
        dataList.add(economyClassSeatLevelOneSeatEntry);

        // fire_detector
        fireDetectorBodyEntry = new PrimaryFlightDisplayEntry("FireDetector (FireDetectedBody)", Boolean.toString(PrimaryFlightDisplay.instance.isFireDetectedBody));
        dataList.add(fireDetectorBodyEntry);
        fireDetectorWingEntry = new PrimaryFlightDisplayEntry("FireDetector (FireDetectedWing)", Boolean.toString(PrimaryFlightDisplay.instance.isFireDetectedWing));
        dataList.add(fireDetectorWingEntry);

        // oxygen_sensor
        oxygenSensorEntry = new PrimaryFlightDisplayEntry("OxygenSensor (isOxygenSensorAlarm)", Boolean.toString(PrimaryFlightDisplay.instance.isOxygenSensorAlarm));
        dataList.add(oxygenSensorEntry);
    }

    private ObservableList<PrimaryFlightDisplayEntry> getInitialTableData() {
        initData();
        data = FXCollections.observableList(dataList);
        return data;
    }

    public void update() {
        // weather_radar
        weatherRadarIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        setWeatherRadarToggleGroup(PrimaryFlightDisplay.instance.isWeatherRadarOn);

        // apu
        apuIsStartedEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isAPUStarted));
        setApuToggleGroup(PrimaryFlightDisplay.instance.isAPUStarted);
        apuRPMEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.rpmAPU));
        setAPURPMLabel(PrimaryFlightDisplay.instance.rpmAPU);

        // gear
        gearIsDownEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isGearDown));
        setGearComboBox(PrimaryFlightDisplay.instance.isGearDown);
        gearBrakePercentageEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.gearBrakePercentage));
        setGearBrakePercentageLabel(PrimaryFlightDisplay.instance.gearBrakePercentage);

        // air_conditioning

        airConditioningOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isAirConditioningOn));
        setAirConditioningToggleGroup(PrimaryFlightDisplay.instance.isAirConditioningOn);
        temperatureAirConditioningEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.temperatureAirConditioning));
        setTemperatureAirConditioningLabel(PrimaryFlightDisplay.instance.temperatureAirConditioning);

        // air_flow_sensor
        airFlowSensorIsBodyAlarmEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isAirFlowSensorBodyAlarm));
        airFlowSensorIsWingAlarmEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isAirFlowSensorWingAlarm));
        airFlowSensorAirPressureEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.airPressure));
        setAirFlowSensorAirPressureLabel(PrimaryFlightDisplay.instance.airPressure);
        setIsAirFlowSensorBodyAlarmLabel(PrimaryFlightDisplay.instance.isAirFlowSensorBodyAlarm);
        setIsAirFlowSensorWingAlarmLabel(PrimaryFlightDisplay.instance.isAirFlowSensorWingAlarm);

        // battery
        batteryPercentageBatteryEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.percentageBattery));
        setBatteryPercentageLabel(PrimaryFlightDisplay.instance.percentageBattery);

        // de_icing_system
        deicingSystemOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isDeIcingSystemActivated));
        deicingSystemAmountDeIcingSystemEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.amountDeIcingSystem));
        setDeIcingSystemToggleGroup(PrimaryFlightDisplay.instance.isDeIcingSystemActivated);
        setAmountDeIcingSystemLabel(PrimaryFlightDisplay.instance.amountDeIcingSystem);

        // temperature_sensor
        temperatureSensorBodyAlarmEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isTemperatureSensorBodyAlarm));
        temperatureSensorTemperatureWingEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isTemperatureSensorWingAlarm));
        setTemperatureBodyLabel(PrimaryFlightDisplay.instance.temperatureBody);
        setTemperatureWingLabel(PrimaryFlightDisplay.instance.temperatureWing);

        //engine
        engineIsStartedEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isEngineStarted));
        setEngineToggleGroup(PrimaryFlightDisplay.instance.isEngineStarted);

        engineRPMEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.rpmEngine));
        setEngineRPMLabel(PrimaryFlightDisplay.instance.rpmEngine);

        // hydraulic pump
        hydraulicPumpBodyOilAmountEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount));
        hydraulicPumpWingOilAmountEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount));
        setOilAmount(PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount, PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount);

        //DroopNose
        setDroopNoseDegreeLabel(PrimaryFlightDisplay.instance.degreeDroopNose);

        //TCAS
        setTCASStartedToggleGroup(PrimaryFlightDisplay.instance.isTCASOn);
        setTCASIsAlarm(PrimaryFlightDisplay.instance.isTCASAlarm);
        setTCASIsConnected(PrimaryFlightDisplay.instance.isTCASConnected);
        setTCASAltitude(PrimaryFlightDisplay.instance.altitudeTCAS);

        //Turbulent AirFLow Sensor
        setTurbulentAirFlowSensorAlarmLabel(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm);

        //Camera
        setCameraToggleGroups(PrimaryFlightDisplay.instance.isCameraOn);

        //GPS
        setGPSConnectedLabel(PrimaryFlightDisplay.instance.isGPSConnected);
        setGPSStartedToggleGroup(PrimaryFlightDisplay.instance.isGPSOn);

        //Radar
        setRadarStartedToggleGroup(PrimaryFlightDisplay.instance.isRadarOn);

        // Elevator
        degreeElevatorEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.degreeElevator));
        setDegreeElevator(PrimaryFlightDisplay.instance.degreeElevator);

        //OxygenBottle
        amountOxygenBottleEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.amountOxygenBottle));
        setOxygenBottleAmount(PrimaryFlightDisplay.instance.amountOxygenBottle);

        //NitrogenBottle
        amountNitrogenBottleEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.amountNitrogenBottle));
        setNitrogenBottleAmount(PrimaryFlightDisplay.instance.amountNitrogenBottle);

// crew_seat
        crewSeatNonSmokingSignEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isNonSmokingSignOn));
        setCrewSeatNonSmokingSignToggleGroup(PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        crewSeatSeatBeltSignEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isSeatBeltSignOn));
        setCrewSeatBeltSignToggleGroup(PrimaryFlightDisplay.instance.isSeatBeltSignOn);

        // economy_class_seat
        economyClassSeatNonSmokingSignEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isNonSmokingSignOn));
        setEconomyClassSeatNonSmokingSignToggleGroup(PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        economyClassSeatSeatBeltSignEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isSeatBeltSignOn));
        setEconomyClassSeatBeltSignToggleGroup(PrimaryFlightDisplay.instance.isSeatBeltSignOn);
        economyClassSeatLevelOneSeatEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.levelSeat));
        setEconomyClassSeatLevelOneSeatLabel(PrimaryFlightDisplay.instance.levelSeat);

        // fire_detector
        fireDetectorBodyEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isFireDetectedBody));
        setFireDetectorBodyAlarmLabel(PrimaryFlightDisplay.instance.isFireDetectedBody);
        fireDetectorWingEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isFireDetectedWing));
        setFireDetectorWingAlarmLabel(PrimaryFlightDisplay.instance.isFireDetectedWing);

        // oxygen_sensor
        oxygenSensorEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isOxygenSensorAlarm));
        setOxygenSensorAlarmLabel(PrimaryFlightDisplay.instance.isOxygenSensorAlarm);

        tableView.refresh();
    }
}