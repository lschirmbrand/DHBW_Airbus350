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
    private TextField temperatureAirConditioningField;

    // apu
    private PrimaryFlightDisplayEntry apuIsStartedEntry;
    private RadioButton apuStartedButton;
    private RadioButton apuShutdownButton;
    private PrimaryFlightDisplayEntry apuRPMEntry;
    private TextField apuRPMField;

    // gear
    private PrimaryFlightDisplayEntry gearIsDownEntry;
    private ComboBox<String> gearComboBox;
    private PrimaryFlightDisplayEntry gearBrakePercentageEntry;
    private TextField gearBrakeField;

    // weather_radar
    private PrimaryFlightDisplayEntry weatherRadarIsOnEntry;
    private RadioButton weatherRadarOffButton;
    private RadioButton weatherRadarOnButton;

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
        gridPane.add(weatherRadarLabel, 0, 0);

        ToggleGroup weatherRadarToggleGroup = new ToggleGroup();

        weatherRadarOffButton = new RadioButton("Off");
        weatherRadarOffButton.setToggleGroup(weatherRadarToggleGroup);
        weatherRadarOffButton.setSelected(true);
        gridPane.add(weatherRadarOffButton, 1, 0);

        weatherRadarOnButton = new RadioButton("On");
        weatherRadarOnButton.setToggleGroup(weatherRadarToggleGroup);
        weatherRadarOnButton.setSelected(false);
        gridPane.add(weatherRadarOnButton, 2, 0);

        // apu
        Label apuLabel = new Label("APU : ");
        apuLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(apuLabel, 0, 1);

        ToggleGroup apuToggleGroup = new ToggleGroup();

        apuShutdownButton = new RadioButton("Off");
        apuShutdownButton.setToggleGroup(apuToggleGroup);
        apuShutdownButton.setSelected(true);
        gridPane.add(apuShutdownButton, 1, 1);

        apuStartedButton = new RadioButton("On");
        apuStartedButton.setToggleGroup(apuToggleGroup);
        gridPane.add(apuStartedButton, 2, 1);

        apuRPMField = new TextField("0 rpm");
        gridPane.add(apuRPMField, 3, 1);

        // gear

        Label gearLabel = new Label("Gear : ");
        gearLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(gearLabel, 7, 1);

        gearComboBox = new ComboBox<>();
        gearComboBox.getItems().addAll("down", "up");
        gearComboBox.setValue("down");
        gearComboBox.setEditable(false);
        gridPane.add(gearComboBox, 8, 1);

        Label gearBrakeLabel = new Label("Brake: ");
        gridPane.add(gearBrakeLabel, 9, 1);

        gearBrakeField = new TextField(0 + "%");
        gridPane.add(gearBrakeField, 10, 1);

        // air_conditioning
        Label airConditioningLabel = new Label("AirConditioning : ");
        airConditioningLabel.setStyle("-fx-font-weight: bold");
        gridPane.add(airConditioningLabel, 0, 2);

        ToggleGroup ACToggleGroup = new ToggleGroup();

        airConditioningOffButton = new RadioButton("Off");
        airConditioningOffButton.setToggleGroup(ACToggleGroup);
        airConditioningOffButton.setSelected(true);
        gridPane.add(airConditioningOffButton, 1, 2);

        airConditioningOnButton = new RadioButton("On");
        airConditioningOnButton.setToggleGroup(ACToggleGroup);
        gridPane.add(airConditioningOnButton, 2, 2);

        temperatureAirConditioningField = new TextField("0 \u2103");
        gridPane.add(temperatureAirConditioningField, 3, 2);

        // --- insert section: end

//        Label frequencyLabel = new Label("Frequency : ");
//        gridPane.add(frequencyLabel, 0, 15);
//
//        Spinner<Integer> vcfSpinner = new Spinner<>();
//        vcfSpinner.setMaxWidth(60);
//        SpinnerValueFactory<Integer> vcfSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(200, 300, 250);
//        vcfSpinner.setValueFactory(vcfSpinnerValueFactory);
//        gridPane.add(vcfSpinner, 1, 15);

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

    public void setAPURPMField(int rpm) {
        apuRPMField.setText(rpm + " rpm");
    }

    // gear
    public void setGearComboBox(boolean isGearDown) {
        gearComboBox.setValue(isGearDown ? "down" : "up");
    }

    public void setGearBrakeField(int percentage) {
        gearBrakeField.setText("Brake: " + percentage + "%");
    }

    // air_conditioning
    public void setAirConditioningToggleGroup(boolean isAirConditioningOn) {
        airConditioningOnButton.setSelected(isAirConditioningOn);
        airConditioningOffButton.setSelected(!isAirConditioningOn);
    }

    public void setTemperatureAirConditioningField(int temperature) {
        temperatureAirConditioningField.setText(temperature + " \u2103");
    }

    private void initData() {
        dataList = new ArrayList<>();

        // weather_radar
        weatherRadarIsOnEntry = new PrimaryFlightDisplayEntry("WeatherRadar (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        dataList.add(weatherRadarIsOnEntry);

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
        setAPURPMField(PrimaryFlightDisplay.instance.rpmAPU);

        // gear
        gearIsDownEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isGearDown));
        setGearComboBox(PrimaryFlightDisplay.instance.isGearDown);
        gearBrakePercentageEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.gearBrakePercentage));
        setGearBrakeField(PrimaryFlightDisplay.instance.gearBrakePercentage);

        // air_conditioning

        airConditioningOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isAirConditioningOn));
        setAirConditioningToggleGroup(PrimaryFlightDisplay.instance.isAirConditioningOn);
        temperatureAirConditioningEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.temperatureAirConditioning));
        setTemperatureAirConditioningField(PrimaryFlightDisplay.instance.temperatureAirConditioning);

        tableView.refresh();
    }
}