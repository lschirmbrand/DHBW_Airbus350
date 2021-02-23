package base;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class PrimaryFlightDisplayGUI extends Application {
    private TableView tableView;
    private ArrayList<PrimaryFlightDisplayEntry> dataList;
    private ObservableList data;

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
    
    //droop_nose
    private Button droopNoseNeutralButton;
    private Button droopNoseFullDownButton;
    private TextField droopNoseUpField;
    private TextField droopNoseDownField;

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


    // Elevator
    private PrimaryFlightDisplayEntry degreeElevatorEntry;
    private Label degreeElevator;

    //OxygenBottle
    private Button oxygenBottleReffillButton;
    private Button oxygenBottleTakeOutButton;
    private PrimaryFlightDisplayEntry amountOxygenBottleEntry;
    private Label amountOxygenBottle;

    //NitrogenBottle
    private Button nitrogenBottleReffillButton;
    private Button nitrogenBottleTakeOutButton;
    private PrimaryFlightDisplayEntry amountNitrogenBottleEntry;
    private Label amountNitrogenBottle;

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

        startupButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.startup();
                update();
            }
        });

        Button taxiButton = new Button("Taxi");
        taxiButton.setPrefSize(75, 20);

        taxiButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.taxi();
                update();
            }
        });

        Button takeoffButton = new Button("TakeOff");
        takeoffButton.setPrefSize(75, 20);

        takeoffButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.takeoff();
                update();
            }
        });

        Button climbingButton = new Button("Climbing");
        climbingButton.setPrefSize(75, 20);

        climbingButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.climbing();
                update();
            }
        });

        Button rightTurnButton = new Button("R-Turn");
        rightTurnButton.setPrefSize(75, 20);

        rightTurnButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.rightTurn();
                update();
            }
        });

        Button leftTurnButton = new Button("L-Turn");
        leftTurnButton.setPrefSize(75, 20);

        leftTurnButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.leftTurn();
                update();
            }
        });

        Button descentButton = new Button("Descent");
        descentButton.setPrefSize(75, 20);

        descentButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.descent();
                update();
            }
        });

        Button landingButton = new Button("Landing");
        landingButton.setPrefSize(75, 20);

        landingButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.landing();
                update();
            }
        });

        Button shutdownButton = new Button("Shutdown");
        shutdownButton.setPrefSize(75, 20);

        shutdownButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.shutdown();
                update();
            }
        });

        hBox.getChildren().addAll(startupButton, taxiButton, takeoffButton, climbingButton, rightTurnButton,
                leftTurnButton, descentButton, landingButton, shutdownButton);

        TabPane tabPane = new TabPane();

        Tab visualTab = new Tab();
        visualTab.setText("Visual");
        visualTab.setContent(buildVisualView());
        tabPane.getTabs().add(visualTab);

        Tab tableTab = new Tab();
        tableTab.setText("Table");
        buildTableView();
        tableTab.setContent(tableView);
        tabPane.getTabs().add(tableTab);

        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.getChildren().addAll(hBox, tabPane);

        Scene scene = new Scene(vbox, 850, 500);
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

//        Label flapLabel = new Label("Flap : ");
//        gridPane.add(flapLabel, 4, 0);

//        ComboBox<String> flapComboBox = new ComboBox<>();
//        flapComboBox.getItems().addAll("neutral", "one", "two", "three");
//        flapComboBox.setValue("neutral");
//        flapComboBox.setEditable(false);
//        gridPane.add(flapComboBox, 5, 0);

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

        Button droopNoseDown = new Button("Down");
        Button droopNoseFullDown = new Button("FullDown");
        Button droopNoseUp = new Button("Up");
        Button droopNoseNeutral = new Button("Neutral");
        gridPane.add(droopNoseDown, 1, 4);
        gridPane.add(droopNoseFullDown, 2, 4);
        gridPane.add(droopNoseUp, 3, 4);
        gridPane.add(droopNoseNeutral, 4, 4);

        //Camera
        Label cameraLabel = new Label("Camera: ");
        cameraLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(cameraLabel, 0, 5);

        ToggleGroup cameraBodyToggleGroup = new ToggleGroup();

        gridPane.add(new Label("Body: "), 1, 5);
        RadioButton cameraOn = new RadioButton("On");
        cameraOn.setToggleGroup(cameraBodyToggleGroup);
        gridPane.add(cameraOn, 2, 5);

        RadioButton cameraOff = new RadioButton("Off");
        cameraOff.setToggleGroup(cameraBodyToggleGroup);
        cameraOff.setSelected(true);
        gridPane.add(cameraOff, 3, 5);

        ToggleGroup cameraWingToggleGroup = new ToggleGroup();

        gridPane.add(new Label("Wing: "), 4, 5);
        RadioButton cameraWingOn = new RadioButton("On");
        cameraWingOn.setToggleGroup(cameraWingToggleGroup);
        gridPane.add(cameraWingOn, 5, 5);

        RadioButton cameraWingOff = new RadioButton("Off");
        cameraWingOff.setToggleGroup(cameraWingToggleGroup);
        cameraWingOff.setSelected(true);
        gridPane.add(cameraWingOff, 6, 5);

        //GPS
        Label gpsLabel = new Label("GPS: ");
        gpsLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(gpsLabel, 0, 6);

        ToggleGroup gpsToggleGroup = new ToggleGroup();

        RadioButton gpsOn = new RadioButton("On");
        gpsOn.setToggleGroup(gpsToggleGroup);
        gridPane.add(gpsOn, 1, 6);

        RadioButton gpsOff = new RadioButton("Off");
        gpsOff.setToggleGroup(gpsToggleGroup);
        gpsOff.setSelected(true);
        gridPane.add(gpsOff, 2, 6);

        gridPane.add(new Button("Send"), 3, 6);
        gridPane.add(new Button("Receive"), 4, 6);
        gridPane.add(new Button("Connect"), 5, 6);

        //Radar
        Label radarLabel = new Label("Radar: ");
        radarLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(radarLabel, 0, 7);

        ToggleGroup radarToggleGroup = new ToggleGroup();

        RadioButton radarOn = new RadioButton("On");
        radarOn.setToggleGroup(radarToggleGroup);
        gridPane.add(radarOn, 1, 7);

        RadioButton radarOff = new RadioButton("Off");
        radarOff.setToggleGroup(radarToggleGroup);
        radarOff.setSelected(true);
        gridPane.add(radarOff, 2, 7);

        gridPane.add(new Button("Scan"), 3, 7);

        //TCAS
        Label tcasLabel = new Label("TCAS");
        tcasLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(tcasLabel, 0, 8);

        ToggleGroup tcasToggleGroup = new ToggleGroup();

        RadioButton tcasOn = new RadioButton("On");
        tcasOn.setToggleGroup(tcasToggleGroup);
        gridPane.add(tcasOn, 1, 8);

        RadioButton tcasOff = new RadioButton("Off");
        tcasOff.setToggleGroup(tcasToggleGroup);
        tcasOff.setSelected(true);
        gridPane.add(tcasOff, 2, 8);

        gridPane.add(new Button("Connect"), 3, 8);
        gridPane.add(new Button("Determine Altitude"), 4, 8);
        gridPane.add(new Button("Scan"), 5, 8);

        //Turbulent Airflow Sensor
        Label turbulentLabel = new Label("Turbulent Airflow Sensor: ");
        turbulentLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(turbulentLabel, 0, 9);
        gridPane.add(new Button("Measure Body"), 1, 9);
        gridPane.add(new Button("Measure Wing"), 2, 9);

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
        gridPane.add(engineLabel, 0,10);

        ToggleGroup engineToggleGroup = new ToggleGroup();

        engineOffButton = new RadioButton("Off");
        engineOffButton.setToggleGroup(engineToggleGroup);
        engineOffButton.setSelected(true);
        gridPane.add(engineOffButton, 2, 10);

        engineOnButton = new RadioButton("On");
        engineOnButton.setToggleGroup(engineToggleGroup);
        engineOnButton.setSelected(false);
        gridPane.add(engineOnButton, 1, 10);

        engineRPMLabel = new Label("RPM's: 0");
        gridPane.add(engineRPMLabel, 3,10);

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
        gridPane.add(hydraulicPumpBodyOilAmountLabel, 1,12);
        hydraulicPumpWingOilAmountLabel = new Label("5000 PSI at Wing");
        gridPane.add(hydraulicPumpWingOilAmountLabel, 2,12);

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
        gridPane.add(elevatorLabel,0,14);
        degreeElevator = new Label("90 \u00B0");
        gridPane.add(degreeElevator, 1,14);

        //OxygenBottle
        Label oxygenBottleLabel = new Label("OxygenBottle");
        oxygenBottleLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(oxygenBottleLabel,0,15);
        amountOxygenBottle = new Label("100");
        gridPane.add(amountOxygenBottle, 1,15);
        oxygenBottleReffillButton = new Button("Refill");
        gridPane.add(oxygenBottleReffillButton, 2, 15);
        oxygenBottleTakeOutButton = new Button("TakeOut");
        gridPane.add(oxygenBottleTakeOutButton, 3, 15);

        //NitrogenBottle
        Label nitrogenBottleLabel = new Label("NitrogenBottle");
        nitrogenBottleLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(nitrogenBottleLabel,0,16);
        amountNitrogenBottle = new Label("250");
        gridPane.add(amountNitrogenBottle, 1,16);
        nitrogenBottleReffillButton = new Button("Refill");
        gridPane.add(nitrogenBottleReffillButton, 2, 16);
        nitrogenBottleTakeOutButton = new Button("TakeOut");
        gridPane.add(nitrogenBottleTakeOutButton, 3, 16);

        // --- insert section: end

//        Label frequencyLabel = new Label("Frequency : ");
//        gridPane.add(frequencyLabel, 0, 5);
//
//        Spinner<Integer> vcfSpinner = new Spinner<>();
//        vcfSpinner.setMaxWidth(60);
//        SpinnerValueFactory<Integer> vcfSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(200, 300, 250);
//        vcfSpinner.setValueFactory(vcfSpinnerValueFactory);
//        gridPane.add(vcfSpinner, 1, 5);

        return gridPane;
    }

    public void buildTableView() {
        tableView = new TableView();
        data = getInitialTableData();
        tableView.setItems(data);

        TableColumn attributeColumn = new TableColumn("attribute");
        attributeColumn.setCellValueFactory(new PropertyValueFactory("attribute"));

        TableColumn valueColumn = new TableColumn("value");
        valueColumn.setCellValueFactory(new PropertyValueFactory("value"));

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
        engineRPMLabel.setText("RPM's: "+rpm);
    }

    public void setOilAmount(int amountB, int amountW){
        hydraulicPumpBodyOilAmountLabel.setText(amountB + " psi at Body");
        hydraulicPumpWingOilAmountLabel.setText(amountW + " psi at Wing");
    }

    public void setDegreeElevator(int degree){
        degreeElevator.setText(degree+" \u00B0");
    }

    //nitrogenBottle
    public void setNitrogenBottleAmount(int amount){amountNitrogenBottle.setText(String.valueOf(amount));}
    //OxygenBottle
    public void setOxygenBottleAmount(int amount){amountOxygenBottle.setText(String.valueOf(amount));}

    private void initData() {
        dataList = new ArrayList<>();

        weatherRadarIsOnEntry = new PrimaryFlightDisplayEntry("WeatherRadar (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        dataList.add(weatherRadarIsOnEntry);
        dataList.add(new PrimaryFlightDisplayEntry("DroopNose (degree)", Integer.toString(PrimaryFlightDisplay.instance.degreeDroopNose)));
        dataList.add(new PrimaryFlightDisplayEntry("TCAS (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isTCASOn)));
        dataList.add(new PrimaryFlightDisplayEntry("TCAS (isConnected)", Boolean.toString(PrimaryFlightDisplay.instance.isTCASConnected)));
        dataList.add(new PrimaryFlightDisplayEntry("TCAS (isAlarm)", Boolean.toString(PrimaryFlightDisplay.instance.isTCASAlarm)));
        dataList.add(new PrimaryFlightDisplayEntry("TCAS (Altitude)", Integer.toString(PrimaryFlightDisplay.instance.altitudeTCAS)));
        dataList.add(new PrimaryFlightDisplayEntry("TurbulentAirFlowSensor (isAlarm)", Boolean.toString(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm)));
        dataList.add(new PrimaryFlightDisplayEntry("Camera (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isCameraOn)));
        dataList.add(new PrimaryFlightDisplayEntry("GPS (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isGPSOn)));
        dataList.add(new PrimaryFlightDisplayEntry("GPS (isConnected)", Boolean.toString(PrimaryFlightDisplay.instance.isGPSConnected)));
        dataList.add(new PrimaryFlightDisplayEntry("Radar (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isRadarOn)));
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
        amountOxygenBottleEntry = new PrimaryFlightDisplayEntry("OxygenBottle (OxygenBottle amount)",Integer.toString(PrimaryFlightDisplay.instance.amountOxygenBottle));
        dataList.add(amountOxygenBottleEntry);
        //NitrogenBottle
        amountNitrogenBottleEntry = new PrimaryFlightDisplayEntry("NitrogenBottle (NitrogenBottle amount)",Integer.toString(PrimaryFlightDisplay.instance.amountNitrogenBottle));
        dataList.add(amountNitrogenBottleEntry);

    }

    private ObservableList getInitialTableData() {
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

        //engine
        engineIsStartedEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isEngineStarted));
        setEngineToggleGroup(PrimaryFlightDisplay.instance.isEngineStarted);

        engineRPMEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.rpmEngine));
        setEngineRPMLabel(PrimaryFlightDisplay.instance.rpmEngine);

        // hydraulic pump
        hydraulicPumpBodyOilAmountEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount));
        hydraulicPumpWingOilAmountEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount));
        setOilAmount(PrimaryFlightDisplay.instance.hydraulicPumpBodyOilAmount, PrimaryFlightDisplay.instance.hydraulicPumpWingOilAmount);

        // Elevator
        degreeElevatorEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.degreeElevator));
        setDegreeElevator(PrimaryFlightDisplay.instance.degreeElevator);

        //OxygenBottle
        amountOxygenBottleEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.amountOxygenBottle));
        setOxygenBottleAmount(PrimaryFlightDisplay.instance.amountOxygenBottle);

        //NitrogeBottle
        amountNitrogenBottleEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.amountNitrogenBottle));
        setNitrogenBottleAmount(PrimaryFlightDisplay.instance.amountNitrogenBottle);

        tableView.refresh();
    }
}