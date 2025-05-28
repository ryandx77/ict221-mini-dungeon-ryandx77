package dungeon.engine;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;


public class Heart implements CellContent {

    @Override
    public void onEnter(Player player, GameEngine engine) {
        player.heal(3); // Heal up to +3 HP
        engine.clearContentAt(player.getPosition()); // Remove heart
        engine.log("You picked up a heart! Gained 3 HP.");
    }

    @Override
    public char getSymbol() {
        return 'H';
    }

    @Override
    public Node getNode() {
        Label label = new Label("♥");
        label.setStyle("-fx-font-size: 20px; -fx-text-fill: crimson; -fx-font-weight: bold;");
        return label;
    }
}

