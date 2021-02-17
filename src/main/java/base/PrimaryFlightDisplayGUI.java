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
    private Label gearBrakeLabel;

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

        Label flapLabel = new Label("Flap : ");
        gridPane.add(flapLabel, 4, 0);

        ComboBox<String> flapComboBox = new ComboBox<>();
        flapComboBox.getItems().addAll("neutral", "one", "two", "three");
        flapComboBox.setValue("neutral");
        flapComboBox.setEditable(false);
        gridPane.add(flapComboBox, 5, 0);

        // --- insert section: begin

        // apu
        Label apuLabel = new Label("APU : ");
        gridPane.add(apuLabel, 0, 1);

        ToggleGroup apuToggleGroup = new ToggleGroup();

        apuShutdownButton = new RadioButton("Off");
        apuShutdownButton.setToggleGroup(apuToggleGroup);
        apuShutdownButton.setSelected(true);
        gridPane.add(apuShutdownButton, 1, 1);

        apuStartedButton = new RadioButton("On");
        apuStartedButton.setToggleGroup(apuToggleGroup);
        gridPane.add(apuStartedButton, 2, 1);

        apuRPMLabel = new Label("0 rpm");
        gridPane.add(apuRPMLabel, 3, 1);

        // gear

        Label gearLabel = new Label("Gear : ");
        gridPane.add(gearLabel, 6, 1);

        gearComboBox = new ComboBox<>();
        gearComboBox.getItems().addAll("down", "up");
        gearComboBox.setValue("down");
        gearComboBox.setEditable(false);
        gridPane.add(gearComboBox, 7, 1);

        gearBrakeLabel = new Label("Brake: " + 0 + "%");
        gridPane.add(gearBrakeLabel, 9, 1);


        // weather_radar
        Label weatherRadarLabel = new Label("WeatherRadar : ");
        gridPane.add(weatherRadarLabel, 6, 0);

        ToggleGroup weatherRadarToggleGroup = new ToggleGroup();

        weatherRadarOffButton = new RadioButton("Off");
        weatherRadarOffButton.setToggleGroup(weatherRadarToggleGroup);
        weatherRadarOffButton.setSelected(true);
        gridPane.add(weatherRadarOffButton, 7, 0);

        weatherRadarOnButton = new RadioButton("On");
        weatherRadarOnButton.setToggleGroup(weatherRadarToggleGroup);
        weatherRadarOnButton.setSelected(false);
        gridPane.add(weatherRadarOnButton, 8, 0);

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

    // apu
    public void setApuToggleGroup(boolean isAPUStarted) {
            apuStartedButton.setSelected(isAPUStarted);
            apuShutdownButton.setSelected(!isAPUStarted);
    }

    // gear
    public void setGearComboBox(boolean isGearDown) {
        gearComboBox.setValue(isGearDown ? "down" : "up");
    }

    public void setGearBrakeLabel(int percentage) {
        gearBrakeLabel.setText("Brake: " + percentage + "%");
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

    public void setAPURPMLabel(int rpm) {
        apuRPMLabel.setText(rpm + " rpm");
    }

    private void initData() {
        dataList = new ArrayList<>();

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

        // weather_radar
        weatherRadarIsOnEntry = new PrimaryFlightDisplayEntry("WeatherRadar (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        dataList.add(weatherRadarIsOnEntry);
    }

    private ObservableList getInitialTableData() {
        initData();
        data = FXCollections.observableList(dataList);
        return data;
    }

    public void update() {
        // apu
        apuIsStartedEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isAPUStarted));
        setApuToggleGroup(PrimaryFlightDisplay.instance.isAPUStarted);
        apuRPMEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.rpmAPU));
        setAPURPMLabel(PrimaryFlightDisplay.instance.rpmAPU);

        // gear
        gearIsDownEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isGearDown));
        setGearComboBox(PrimaryFlightDisplay.instance.isGearDown);
        gearBrakePercentageEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.gearBrakePercentage));
        setGearBrakeLabel(PrimaryFlightDisplay.instance.gearBrakePercentage);

        // weather_radar
        weatherRadarIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        setWeatherRadarToggleGroup(PrimaryFlightDisplay.instance.isWeatherRadarOn);

        tableView.refresh();
    }
}