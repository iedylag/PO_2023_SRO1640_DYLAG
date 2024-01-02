package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.ConsoleMapDisplay;
import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;


public class SimulationPresenter implements MapChangeListener {

    @FXML
    private Label moveDescription;

    @FXML
    private TextField textField;

    @FXML
    private Button startButton;

    @FXML
    private Label infoLabel;
    private WorldMap worldMap;  //MODEL

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    @FXML
    private void onSimulationStartClicked() {
        List<MoveDirection> directions = OptionsParser.parse((textField.getText()).split("\\s+"));

        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        ConsoleMapDisplay display = new ConsoleMapDisplay();

        List<Simulation> simulations = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            WorldMap grassField = new GrassField(10);
            setWorldMap(grassField);
            grassField.subscribe(display);
            grassField.subscribe(this);
            simulations.add(new Simulation(directions, positions, grassField));
        }

        SimulationEngine engine = new SimulationEngine(simulations);
        engine.runAsyncInThreadPool();
    }

    @FXML
    public void drawMap() {
        infoLabel.setText(worldMap.toString());
    }

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap();
            moveDescription.setText(message);
        });
    }
}
