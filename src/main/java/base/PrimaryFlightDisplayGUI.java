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
    // slat
    private PrimaryFlightDisplayEntry slatDegreeEntry;
    private Spinner<Integer> slatDegreeSpinner;
    // left_aileron
    private PrimaryFlightDisplayEntry leftAileronDegreeEntry;
    private Spinner<Integer> leftAileronDegreeSpinner;
    // right_aileron
    private PrimaryFlightDisplayEntry rightAileronDegreeEntry;
    private Spinner<Integer> rightAileronDegreeSpinner;
    // rudder
    private PrimaryFlightDisplayEntry rudderDegreeEntry;
    private Spinner<Integer> rudderDegreeSpinner;
    // spoiler
    private PrimaryFlightDisplayEntry spoilerDegreeEntry;
    private Spinner<Integer> spoilerDegreeSpinner;
    // anti_collision_light
    private PrimaryFlightDisplayEntry antiCollisionLightIsOnEntry;
    private RadioButton antiCollisionLightOffButton;
    private RadioButton antiCollisionLightOnButton;

    // cargo_compartment_light
    private PrimaryFlightDisplayEntry isCargoCompartmentLightOn;
    private RadioButton cargoCompartmentLightOffButton;
    private RadioButton cargoCompartmentLightOnButton;

    //cost_optimizer
    private PrimaryFlightDisplayEntry isCostOptimizerOn;
    private RadioButton costOptimizerOnButton;
    private RadioButton costOptimizerOffButton;
    private PrimaryFlightDisplayEntry indexCostOptimizer;
    private Label indexCostOptimizerLabel;
    private PrimaryFlightDisplayEntry numberOfCheckPointsCostOptimizer;
    private Label numberOfCheckPointsCostOptimizerLabel;

    //landing_light
    private PrimaryFlightDisplayEntry isLandingLightBodyOn;
    private RadioButton landingLightBodyOnButton;
    private RadioButton landingLightBodyOffButton;
    private PrimaryFlightDisplayEntry isLandingLightWingOn;
    private RadioButton landingLightWingOnButton;
    private RadioButton landingLightWingOffButton;

    //left_navigation_light
    private PrimaryFlightDisplayEntry isLeftNavigationLightOn;
    private RadioButton leftNavigationLightOnButton;
    private RadioButton leftNavigationLightOffButton;

    //logo_light
    private PrimaryFlightDisplayEntry isLogoLightOn;
    private RadioButton logoLightOnButton;
    private RadioButton logoLightOffButton;

    //route_management
    private PrimaryFlightDisplayEntry isRouteManagementOn;
    private RadioButton routeManagementOnButton;
    private RadioButton routeManagementOffButton;
    private PrimaryFlightDisplayEntry indexRouteManagement;
    private Label indexRouteManagementLabel;
    private PrimaryFlightDisplayEntry numberOfCheckPointsRouteManagement;
    private Label numberOfCheckPointsRouteManagementLabel;

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

        // slat
        Label slatDegreeLabel = new Label("Slat : ");
        gridPane.add(slatDegreeLabel, 0, 14);

        slatDegreeSpinner = new Spinner<>();
        slatDegreeSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> slatDegreeSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-20, 0, 0);
        slatDegreeSpinner.setValueFactory(slatDegreeSpinnerValueFactory);
        gridPane.add(slatDegreeSpinner, 1, 14);

        // left_aileron
        Label left_aileronDegreeLabel = new Label("Left_aileron : ");
        gridPane.add(left_aileronDegreeLabel, 0, 15);

        leftAileronDegreeSpinner = new Spinner<>();
        leftAileronDegreeSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> left_aileronDegreeSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-30, 30, 0);
        leftAileronDegreeSpinner.setValueFactory(left_aileronDegreeSpinnerValueFactory);
        gridPane.add(leftAileronDegreeSpinner, 1, 15);

        // right_aileron
        Label right_aileronDegreeLabel = new Label("Right_aileron : ");
        gridPane.add(right_aileronDegreeLabel, 0, 16);

        rightAileronDegreeSpinner = new Spinner<>();
        rightAileronDegreeSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> right_aileronDegreeSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-30, 30, 0);
        rightAileronDegreeSpinner.setValueFactory(right_aileronDegreeSpinnerValueFactory);
        gridPane.add(rightAileronDegreeSpinner, 1, 16);

        // rudder
        Label rudderDegreeLabel = new Label("Rudder : ");
        gridPane.add(rudderDegreeLabel, 0, 17);

        rudderDegreeSpinner = new Spinner<>();
        rudderDegreeSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> rudderDegreeSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(-30, 30, 0);
        rudderDegreeSpinner.setValueFactory(rudderDegreeSpinnerValueFactory);
        gridPane.add(rudderDegreeSpinner, 1, 17);

        // spoiler
        Label spoilerDegreeLabel = new Label("Spoiler : ");
        gridPane.add(spoilerDegreeLabel, 0, 18);

        spoilerDegreeSpinner = new Spinner<>();
        spoilerDegreeSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> spoilerDegreeSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);
        spoilerDegreeSpinner.setValueFactory(spoilerDegreeSpinnerValueFactory);
        gridPane.add(spoilerDegreeSpinner, 1, 18);

        // anti_collision_light
        Label antiCollisionLightLabel = new Label("AntiCollisionLight : ");
        gridPane.add(antiCollisionLightLabel, 0, 19);

        ToggleGroup antiCollisionLightToggleGroup = new ToggleGroup();

        antiCollisionLightOffButton = new RadioButton("Off");
        antiCollisionLightOffButton.setToggleGroup(antiCollisionLightToggleGroup);
        antiCollisionLightOffButton.setSelected(true);
        gridPane.add(antiCollisionLightOffButton, 1, 19);

        antiCollisionLightOnButton = new RadioButton("On");
        antiCollisionLightOnButton.setToggleGroup(antiCollisionLightToggleGroup);
        antiCollisionLightOnButton.setSelected(false);
        gridPane.add(antiCollisionLightOnButton, 2, 19);

        // cargo_compartment_light
        Label cargoCompartentLightLabel = new Label("CargoCompartmentLight : ");
        gridPane.add(cargoCompartentLightLabel, 6, 1);

        ToggleGroup cargoCompartentLightToggleGroup = new ToggleGroup();

        cargoCompartmentLightOffButton = new RadioButton("Off");
        cargoCompartmentLightOffButton.setToggleGroup(cargoCompartentLightToggleGroup);
        cargoCompartmentLightOffButton.setSelected(true);
        gridPane.add(cargoCompartmentLightOffButton, 7, 1);

        cargoCompartmentLightOnButton = new RadioButton("On");
        cargoCompartmentLightOnButton.setToggleGroup(cargoCompartentLightToggleGroup);
        cargoCompartmentLightOnButton.setSelected(false);
        gridPane.add(cargoCompartmentLightOnButton, 8, 1);

        // cost_optimizer
        Label costOptimizerLabel = new Label("CostOptimizer : ");
        gridPane.add(costOptimizerLabel, 6, 2);

        ToggleGroup costOptimizerToggleGroup = new ToggleGroup();

        costOptimizerOffButton = new RadioButton("Off");
        costOptimizerOffButton.setToggleGroup(costOptimizerToggleGroup);
        costOptimizerOffButton.setSelected(true);
        gridPane.add(costOptimizerOffButton, 7, 2);

        costOptimizerOnButton = new RadioButton("On");
        costOptimizerOnButton.setToggleGroup(costOptimizerToggleGroup);
        costOptimizerOnButton.setSelected(false);
        gridPane.add(costOptimizerOnButton, 8, 2);

        indexCostOptimizerLabel = new Label("IndexCostOptimizer: " + (PrimaryFlightDisplay.instance.indexCostOptimizer));
        gridPane.add(indexCostOptimizerLabel, 9, 2);

        numberOfCheckPointsCostOptimizerLabel = new Label("NumberOfCheckPoints: " + (PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer));
        gridPane.add(numberOfCheckPointsCostOptimizerLabel, 10, 2);

        // landing_light_body
        Label landingLightBodyLabel = new Label("LandingLightBody : ");
        gridPane.add(landingLightBodyLabel, 6, 3);

        ToggleGroup landingLightBodyToggleGroup = new ToggleGroup();

        landingLightBodyOffButton = new RadioButton("Off");
        landingLightBodyOffButton.setToggleGroup(landingLightBodyToggleGroup);
        landingLightBodyOffButton.setSelected(true);
        gridPane.add(landingLightBodyOffButton, 7, 3);

        landingLightBodyOnButton = new RadioButton("On");
        landingLightBodyOnButton.setToggleGroup(landingLightBodyToggleGroup);
        landingLightBodyOnButton.setSelected(false);
        gridPane.add(landingLightBodyOnButton, 8, 3);

        // landing_light_wing
        Label landingLightWingLabel = new Label("LandingLightWing : ");
        gridPane.add(landingLightWingLabel, 6, 4);

        ToggleGroup landingLightWingToggleGroup = new ToggleGroup();

        landingLightWingOffButton = new RadioButton("Off");
        landingLightWingOffButton.setToggleGroup(landingLightWingToggleGroup);
        landingLightWingOffButton.setSelected(true);
        gridPane.add(landingLightWingOffButton, 7, 4);

        landingLightWingOnButton = new RadioButton("On");
        landingLightWingOnButton.setToggleGroup(landingLightWingToggleGroup);
        landingLightWingOnButton.setSelected(false);
        gridPane.add(landingLightWingOnButton, 8, 4);


        // left_navigation_light
        Label leftNavigationLightLabel = new Label("LeftNavigationLight : ");
        gridPane.add(leftNavigationLightLabel, 6, 5);

        ToggleGroup leftNavigationLightToggleGroupe = new ToggleGroup();

        leftNavigationLightOffButton= new RadioButton("Off");
        leftNavigationLightOffButton.setToggleGroup(leftNavigationLightToggleGroupe);
        leftNavigationLightOffButton.setSelected(true);
        gridPane.add(leftNavigationLightOffButton, 7, 5);

        leftNavigationLightOnButton = new RadioButton("On");
        leftNavigationLightOnButton.setToggleGroup(leftNavigationLightToggleGroupe);
        leftNavigationLightOnButton.setSelected(false);
        gridPane.add(leftNavigationLightOnButton, 8, 5);

        // logo_light
        Label logoLightLabel = new Label("logoLight : ");
        gridPane.add(logoLightLabel, 6, 6);

        ToggleGroup logoLightToggleGoup = new ToggleGroup();

        logoLightOffButton = new RadioButton("Off");
        logoLightOffButton.setToggleGroup(logoLightToggleGoup);
        logoLightOffButton.setSelected(true);
        gridPane.add(logoLightOffButton, 7, 6);

        logoLightOnButton = new RadioButton("On");
        logoLightOnButton.setToggleGroup(logoLightToggleGoup);
        logoLightOnButton.setSelected(false);
        gridPane.add(logoLightOnButton, 8, 6);

        // route_management
        Label routeManageLabel = new Label("routeManage : ");
        gridPane.add(routeManageLabel, 6, 7);

        ToggleGroup routeManageToggleGroup = new ToggleGroup();

        routeManagementOffButton = new RadioButton("Off");
        routeManagementOffButton.setToggleGroup(routeManageToggleGroup);
        routeManagementOffButton.setSelected(true);
        gridPane.add(routeManagementOffButton, 7, 7);

        routeManagementOnButton = new RadioButton("On");
        routeManagementOnButton.setToggleGroup(routeManageToggleGroup);
        routeManagementOnButton.setSelected(false);
        gridPane.add(routeManagementOnButton, 8, 7);

        indexRouteManagementLabel = new Label("IndexRouteManager: "+ (PrimaryFlightDisplay.instance.indexRouteManagement));
        gridPane.add(indexRouteManagementLabel, 9,7);

        numberOfCheckPointsRouteManagementLabel = new Label("NumberOfCheckPoints: " + (PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement));
        gridPane.add(numberOfCheckPointsRouteManagementLabel, 10,7);


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

    // slat
    public void setSlatDegreeValue(int slatDegreeValue) {
        slatDegreeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-20, 0, slatDegreeValue));
    }

    // left_aileron
    public void setLeftAileronDegreeValue(int leftAileronDegreeValue) {
        leftAileronDegreeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-30, 30, leftAileronDegreeValue));
    }

    // right_aileron
    public void setRightAileronDegreeValue(int rightAileronDegreeValue) {
        rightAileronDegreeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-30, 30, rightAileronDegreeValue));
    }

    // rudder
    public void setRudderDegreeValue(int rudderDegreeValue) {
        rudderDegreeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-30, 30, rudderDegreeValue));
    }

    // spoiler
    public void setSpoilerDegreeValue(int spoilerDegreeValue) {
        spoilerDegreeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, spoilerDegreeValue));
    }

    // anti_collision_light
    public void setAntiCollisionLightToggleGroup(boolean isAntiCollisionLightOn) {
        if (isAntiCollisionLightOn) {
            antiCollisionLightOffButton.setSelected(false);
            antiCollisionLightOnButton.setSelected(true);
        } else {
            antiCollisionLightOffButton.setSelected(true);
            antiCollisionLightOnButton.setSelected(false);
        }
    }

    // cargo_compartment_light
    public void setCargoCompartmentLightToggleGroup(boolean isCargoCompartmentLightOn){
        cargoCompartmentLightOffButton.setSelected(!isCargoCompartmentLightOn);
        cargoCompartmentLightOnButton.setSelected(isCargoCompartmentLightOn);
    }
    // cost_optimizer
    public void setCostOptimizerToggleGroup(boolean isCostOptimizerOn){
        costOptimizerOffButton.setSelected(!isCostOptimizerOn);
        costOptimizerOnButton.setSelected(isCostOptimizerOn);
    }
    public void setCheckPointsAndIndexCostOptimizer(int cp, int i){
         i = 0;
         cp = 0;
        numberOfCheckPointsCostOptimizerLabel.setText("Number of CheckPoints: " + Integer.toString(cp));
        indexCostOptimizerLabel.setText("Cost Index: " + Integer.toString(i));
    }

    // landing_light
    public void setLandingLightToggleGroup(boolean isLandingLightBodyOn, boolean isLandingLightWingOn){
        landingLightBodyOffButton.setSelected(!isLandingLightBodyOn);
        landingLightBodyOnButton.setSelected(isLandingLightBodyOn);
        landingLightWingOffButton.setSelected(!isLandingLightWingOn);
        landingLightWingOnButton.setSelected(isLandingLightWingOn);
    }
    // left_navigation_light
    public void setLeftNavigationLightToggleGroup(boolean isLeftNavigationLightOn){
        leftNavigationLightOffButton.setSelected(!isLeftNavigationLightOn);
        leftNavigationLightOnButton.setSelected(isLeftNavigationLightOn);
    }

    // logo_light
    public void setLogoLightToggleGroup(boolean isLogoLightOn){
        logoLightOffButton.setSelected(!isLogoLightOn);
        logoLightOnButton.setSelected(isLogoLightOn);
    }

    // route_management
    public void setRouteManagementToggleGroup(boolean isRouteManagementOn){
        routeManagementOffButton.setSelected(!isRouteManagementOn);
        routeManagementOnButton.setSelected(isRouteManagementOn);
    }
    public void setCheckPointsAndIndexRouteManagement(int cp, int i){
        numberOfCheckPointsRouteManagementLabel.setText("Number of CheckPoints" + Integer.toString(cp));
        indexRouteManagementLabel.setText("Cost Index: " + Integer.toString(i));
    }



    private void initData() {
        dataList = new ArrayList<>();

        // weather_radar
        weatherRadarIsOnEntry = new PrimaryFlightDisplayEntry("WeatherRadar (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        dataList.add(weatherRadarIsOnEntry);
        // slat
        slatDegreeEntry = new PrimaryFlightDisplayEntry("Slat (degree)", Integer.toString(PrimaryFlightDisplay.instance.degreeSlat));
        dataList.add(slatDegreeEntry);
        // left_aileron
        leftAileronDegreeEntry = new PrimaryFlightDisplayEntry("LeftAileron (degree)", Integer.toString(PrimaryFlightDisplay.instance.degreeLeftAileron));
        dataList.add(leftAileronDegreeEntry);
        // right_aileron
        rightAileronDegreeEntry = new PrimaryFlightDisplayEntry("RightAileron (degree)", Integer.toString(PrimaryFlightDisplay.instance.degreeRightAileron));
        dataList.add(rightAileronDegreeEntry);
        // rudder
        rudderDegreeEntry = new PrimaryFlightDisplayEntry("Rudder (degree)", Integer.toString(PrimaryFlightDisplay.instance.degreeRudder));
        dataList.add(rudderDegreeEntry);
        // spoiler
        spoilerDegreeEntry = new PrimaryFlightDisplayEntry("Spoiler (degree)", Integer.toString(PrimaryFlightDisplay.instance.degreeSpoiler));
        dataList.add(spoilerDegreeEntry);
        // anti_collision_light
        antiCollisionLightIsOnEntry = new PrimaryFlightDisplayEntry("AntiCollisionLight (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isAntiCollisionLightOn));
        dataList.add(antiCollisionLightIsOnEntry);

        // cargo_compartment_light
        isCargoCompartmentLightOn = new PrimaryFlightDisplayEntry("CargoCompartmentLight (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isCargoCompartmentLightOn));
        dataList.add(isCargoCompartmentLightOn);

        // cost_optimizer
        isCostOptimizerOn = new PrimaryFlightDisplayEntry("CostOptimizer (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isCostOptimizerOn));
        dataList.add(isCostOptimizerOn);
        numberOfCheckPointsCostOptimizer = new PrimaryFlightDisplayEntry("CostOptimizer (numberOfCheckPoints)", Integer.toString(PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer));
        dataList.add(numberOfCheckPointsCostOptimizer);
        indexCostOptimizer = new PrimaryFlightDisplayEntry("CostOptimizer (Index)", Integer.toString(PrimaryFlightDisplay.instance.indexCostOptimizer));
        dataList.add(indexCostOptimizer);

        // landing_light
        isLandingLightBodyOn = new PrimaryFlightDisplayEntry("LandingLightBody (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isLandingLightBodyOn));
        dataList.add(isLandingLightBodyOn);
        isLandingLightWingOn = new PrimaryFlightDisplayEntry("LandingLightWing (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isLandingLightWingOn));
        dataList.add(isLandingLightWingOn);

        // left_navigation_light
        isLeftNavigationLightOn = new PrimaryFlightDisplayEntry("LeftNavigationLight (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isLeftNavigationLightOn));
        dataList.add(isLeftNavigationLightOn);

        // logo_light
        isLogoLightOn = new PrimaryFlightDisplayEntry("LogoLight (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isLogoLightOn));
        dataList.add(isLogoLightOn);

        // route_management
        isRouteManagementOn = new PrimaryFlightDisplayEntry("RouteManagement (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isRouteManagementOn));
        dataList.add(isRouteManagementOn);
        numberOfCheckPointsRouteManagement = new PrimaryFlightDisplayEntry("RouteManagement (numberOfCheckPoints)", Integer.toString(PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement));
        dataList.add(numberOfCheckPointsRouteManagement);
        indexRouteManagement = new PrimaryFlightDisplayEntry("RouteManagement (Index)", Integer.toString(PrimaryFlightDisplay.instance.indexRouteManagement));
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
        // slat
        slatDegreeEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.degreeSlat));
        setSlatDegreeValue(PrimaryFlightDisplay.instance.degreeSlat);
        // left_aileron
        leftAileronDegreeEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.degreeLeftAileron));
        setLeftAileronDegreeValue(PrimaryFlightDisplay.instance.degreeLeftAileron);
        // right_aileron
        rightAileronDegreeEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.degreeRightAileron));
        setRightAileronDegreeValue(PrimaryFlightDisplay.instance.degreeRightAileron);
        // rudder
        rudderDegreeEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.degreeRudder));
        setRudderDegreeValue(PrimaryFlightDisplay.instance.degreeRudder);
        // spoiler
        spoilerDegreeEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.degreeSpoiler));
        setSpoilerDegreeValue(PrimaryFlightDisplay.instance.degreeSpoiler);
        // anti_collision_light
        antiCollisionLightIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        setAntiCollisionLightToggleGroup(PrimaryFlightDisplay.instance.isAntiCollisionLightOn);

        // cargoCompartmentLight
        isCargoCompartmentLightOn.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isCargoCompartmentLightOn));
        setCargoCompartmentLightToggleGroup(PrimaryFlightDisplay.instance.isCargoCompartmentLightOn);

        //costOptimizer
        isCostOptimizerOn.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isCostOptimizerOn));
        setCostOptimizerToggleGroup(PrimaryFlightDisplay.instance.isCostOptimizerOn);
        numberOfCheckPointsCostOptimizer.setValue(Integer.toString(PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer));
        indexCostOptimizer.setValue(Integer.toString(PrimaryFlightDisplay.instance.indexCostOptimizer));
        setCheckPointsAndIndexCostOptimizer(PrimaryFlightDisplay.instance.numberOfCheckPointsCostOptimizer, PrimaryFlightDisplay.instance.indexCostOptimizer);

        //landingLight
        isLandingLightBodyOn.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isLandingLightBodyOn));
        isLandingLightWingOn.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isLandingLightWingOn));
        setLandingLightToggleGroup(PrimaryFlightDisplay.instance.isLandingLightBodyOn, PrimaryFlightDisplay.instance.isLandingLightWingOn);

        //leftNavigationLight
        isLeftNavigationLightOn.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isLeftNavigationLightOn));
        setLeftNavigationLightToggleGroup(PrimaryFlightDisplay.instance.isLeftNavigationLightOn);

        //LogoLight
        isLogoLightOn.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isLogoLightOn));
        setLogoLightToggleGroup(PrimaryFlightDisplay.instance.isLogoLightOn);

        //RouteManagement
        isRouteManagementOn.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isRouteManagementOn));
        setRouteManagementToggleGroup(PrimaryFlightDisplay.instance.isRouteManagementOn);
        numberOfCheckPointsRouteManagement.setValue(Integer.toString(PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement));
        indexRouteManagement.setValue(Integer.toString(PrimaryFlightDisplay.instance.indexRouteManagement));
        setCheckPointsAndIndexRouteManagement(PrimaryFlightDisplay.instance.numberOfCheckPointsRouteManagement, PrimaryFlightDisplay.instance.indexRouteManagement);

        tableView.refresh();
    }
}