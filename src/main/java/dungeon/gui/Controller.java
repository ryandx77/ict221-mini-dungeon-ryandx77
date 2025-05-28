package dungeon.gui;

import dungeon.engine.*;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    private GridPane gridPane;

    @FXML
    private Label healthLabel;

    @FXML
    private Label scoreLabel;

    @FXML
    private TextArea statusTextArea;

    private GameEngine engine;

    public void logMessage(String message) {
        // Append new message with new line
        statusTextArea.appendText(message + "\n");
    }

    public void setEngine(GameEngine engine) {
        this.engine = engine;

        // Pass Controller reference to GameEngine for logging callback
        this.engine.setController(this);

        System.out.println("Engine set. Player starts at: " + engine.getPlayer().getPosition());

        updateGui();  // Update the GUI when engine is set
    }

    private void updateGui() {
        gridPane.getChildren().clear();

        for (int i = 0; i < engine.getSize(); i++) {
            for (int j = 0; j < engine.getSize(); j++) {
                Cell cell = engine.getMap()[i][j];
                Position pos = new Position(j, i);
                CellContent content = engine.getContentAt(pos);

                // Set cell content, but only if it's NOT the player position
                if (!pos.equals(engine.getPlayer().getPosition())) {
                    cell.setContent(content);
                } else {
                    // Clear content because player will be drawn separately
                    cell.setContent(null);
                }

                gridPane.add(cell, j, i);
            }
        }

        // Now add the player on top of their cell
        Position playerPos = engine.getPlayer().getPosition();
        Cell playerCell = engine.getMap()[playerPos.getY()][playerPos.getX()];
        playerCell.setContent(new PlayerContent());

        healthLabel.setText("Health: " + engine.getPlayer().getHealth());
        scoreLabel.setText("Score: " + engine.getPlayer().getScore());

        gridPane.setGridLinesVisible(true);
    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void handleKeyPressed(KeyEvent event) {
        if (engine == null) return;

        Player player = engine.getPlayer();
        Direction dir = null;

        if (event.getCode() == KeyCode.UP) {
            dir = Direction.UP;
        } else if (event.getCode() == KeyCode.DOWN) {
            dir = Direction.DOWN;
        } else if (event.getCode() == KeyCode.LEFT) {
            dir = Direction.LEFT;
        } else if (event.getCode() == KeyCode.RIGHT) {
            dir = Direction.RIGHT;
        }

        if (dir != null) {
            player.move(dir, engine);
            System.out.println("Player moved to: " + player.getPosition());
            engine.processRangedAttacks();
            updateGui(); // Refresh the grid after movement
        }
    }
}
