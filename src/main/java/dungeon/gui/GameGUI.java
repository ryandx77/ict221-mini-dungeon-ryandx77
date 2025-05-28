package dungeon.gui;

import dungeon.engine.GameEngine;
import dungeon.engine.Player;
import dungeon.engine.Position;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * GUI for the Maze Runner Game.
 *
 * NOTE: Do NOT run this class directly in IntelliJ - run 'RunGame' instead.
 */
public class GameGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the engine
        GameEngine engine = new GameEngine(10);

        // Create and set the player
        Player player = new Player(new Position(0, 0));
        engine.setPlayer(player);

        // Load FXML and get controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game_gui.fxml"));
        BorderPane root = loader.load();
        Controller controller = loader.getController();
        controller.setEngine(engine);

        // Create scene and hook up key handler
        Scene scene = new Scene(root, 800, 800);
        scene.setOnKeyPressed(controller::handleKeyPressed);

        // Show stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("MiniDungeon Game");
        primaryStage.show();
    }

    /** In IntelliJ, do NOT run this method.  Run 'RunGame.main()' instead. */
    public static void main(String[] args) {
        launch(args);
    }
}
