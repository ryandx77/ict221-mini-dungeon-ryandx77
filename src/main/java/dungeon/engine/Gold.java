package dungeon.engine;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;

public class Gold implements CellContent {

    @Override
    public void onEnter(Player player, GameEngine engine) {
        player.increaseScore(2); // Add to score
        engine.clearContentAt(player.getPosition()); // Remove gold from map
        engine.log("You picked up a gold piece!");
    }

    @Override
    public char getSymbol() {
        return 'G';
    }

    @Override
    public Node getNode() {
        Label label = new Label("🪙");
        label.setStyle("-fx-font-size: 20px;");
        return label;
    }
}
