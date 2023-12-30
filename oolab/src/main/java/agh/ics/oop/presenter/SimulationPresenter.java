package agh.ics.oop.presenter;

import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class SimulationPresenter implements MapChangeListener {

    /*@FXML
    private Button stopButton;
    @FXML
    private Button startButton;

    private SimulationEngine engine;
*/
    @FXML
    private Label infoLabel;
    private WorldMap worldMap;  //MODEL

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

 /*   @FXML
    private void onStartClicked() {
        engine.start();
    }

    @FXML
    private void onStopClicked() {
        engine.stop();
    }
*/
    @FXML
    public void drawMap(){
        infoLabel.setText(worldMap.toString());
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            drawMap();
        });
    }
}
