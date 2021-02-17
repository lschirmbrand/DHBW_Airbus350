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
    // weather_radar
    private PrimaryFlightDisplayEntry weatherRadarIsOnEntry;
    private RadioButton weatherRadarOffButton;
    private RadioButton weatherRadarOnButton;
    //droop_nose
    private Button droopNoseNeutralButton;
    private Button droopNoseFullDownButton;
    private TextField droopNoseUpField;
    private TextField droopNoseDownField;

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

        Label frontGearLabel = new Label("Front Gear : ");
        gridPane.add(frontGearLabel, 0, 0);

        ComboBox<String> frontGearComboBox = new ComboBox<>();
        frontGearComboBox.getItems().addAll("down", "up");
        frontGearComboBox.setValue("down");
        frontGearComboBox.setEditable(false);
        gridPane.add(frontGearComboBox, 1, 0);

        Label rearGearLabel = new Label("Rear Gear : ");
        gridPane.add(rearGearLabel, 2, 0);

        ComboBox<String> rearGearComboBox = new ComboBox<>();
        rearGearComboBox.getItems().addAll("down", "up");
        rearGearComboBox.setValue("down");
        rearGearComboBox.setEditable(false);
        gridPane.add(rearGearComboBox, 3, 0);

        Label flapLabel = new Label("Flap : ");
        gridPane.add(flapLabel, 4, 0);

        ComboBox<String> flapComboBox = new ComboBox<>();
        flapComboBox.getItems().addAll("neutral", "one", "two", "three");
        flapComboBox.setValue("neutral");
        flapComboBox.setEditable(false);
        gridPane.add(flapComboBox, 5, 0);

        // --- insert section: begin

        // weather_radar
        Label weatherRadarLabel = new Label("WeatherRadar : ");
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
        gridPane.add(new Label("Camera: "), 0, 5);

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
        gridPane.add(new Label("GPS: "), 0, 6);

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
        gridPane.add(new Label("Radar: "), 0, 7);

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
        gridPane.add(new Label("TCAS: "), 0, 8);

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
        gridPane.add(new Label("Turbulent Airflow Sensor: "), 0, 9);
        gridPane.add(new Button("Measure Body"), 1, 9);
        gridPane.add(new Button("Measure Wing"), 2, 9);

        // --- insert section: end

        Label frequencyLabel = new Label("Frequency : ");
        gridPane.add(frequencyLabel, 0, 2);

        Spinner<Integer> vcfSpinner = new Spinner<>();
        vcfSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> vcfSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(200, 300, 250);
        vcfSpinner.setValueFactory(vcfSpinnerValueFactory);
        gridPane.add(vcfSpinner, 1, 2);

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

        tableView.refresh();
    }
}