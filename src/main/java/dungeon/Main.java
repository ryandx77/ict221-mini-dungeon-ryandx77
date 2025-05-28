package dungeon;

import dungeon.engine.GameEngine;
import dungeon.gui.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dungeon/gui/game_gui.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        GameEngine engine = new GameEngine(10);
        controller.setEngine(engine);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(controller::handleKeyPressed);  // Key input

        primaryStage.setScene(scene);
        primaryStage.setTitle("MiniDungeon");
        primaryStage.show();

        root.requestFocus(); // So keyboard input works immediately
    }

    public static void main(String[] args) {
        launch(args);
    }
}


