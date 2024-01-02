package agh.ics.oop.presenter;

import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class SimulationPresenter implements MapChangeListener {

    @FXML
    private Button stopButton;

    @FXML
    private Button startButton;

    private SimulationEngine engine;

    @FXML
    private Label infoLabel;
    private WorldMap worldMap;  //MODEL

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

   @FXML
    private void initialize() {
        startButton.setOnAction(event -> engine.startSimulation());
       // startButton.disableProperty().bind(stopButton.disableProperty().not());
    }
/*
    @FXML
    private void onStopClicked() {
        engine.stopSimulation();
    }
*/
    public void setEngine(SimulationEngine engine) {
        this.engine = engine;
       //stopButton.disableProperty().bind(engine.stoppedProperty());
    }

    @FXML
    public void drawMap() {
        infoLabel.setText(worldMap.toString());
    }

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(this::drawMap);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
