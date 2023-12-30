package agh.ics.oop;

import agh.ics.oop.model.ConsoleMapDisplay;
import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SimulationApp extends Application {  //dziedziczymy po Application

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();            //ładujemy drzewo kontrolek z FXML (WIDOK)
        SimulationPresenter presenter = loader.getController();  //zaciągamy prezentera z fxmla
        configureStage(primaryStage, viewRoot);

        List<String> parameters = getParameters().getRaw();     //pobieramy przekazane argumenty

        List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
        ConsoleMapDisplay display = new ConsoleMapDisplay();

        try {
            List<MoveDirection> directions = OptionsParser.parse(parameters.toArray(new String[0]));    //new String[0] tworzy tablicę stringów o rozmiarze 0
            List<Simulation> simulations = new ArrayList<>();

            for (int i = 0; i < 2; i++) {
                WorldMap grassField = new GrassField(10);
                presenter.setWorldMap(grassField);          //przekazujemy mapę do prezentera
                grassField.subscribe(display);
                grassField.subscribe(presenter);
                simulations.add(new Simulation(directions, positions, grassField));
            }

            SimulationEngine engine = new SimulationEngine(simulations);
            engine.runAsyncInThreadPool();
            engine.awaitSimulationsEnd();
        } catch (IllegalArgumentException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);   //tworzymy scene w oknie
        primaryStage.setTitle("Simulation app");   //konfigurujemy okno
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());

        primaryStage.show();  // wyswietlamy okno
    }

}
