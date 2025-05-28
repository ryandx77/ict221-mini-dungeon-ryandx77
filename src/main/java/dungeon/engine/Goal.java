package dungeon.engine;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Goal implements CellContent {

    @Override
    public void onEnter(Player player, GameEngine engine) {
        engine.log("You reached the goal! Congratulations!");
        // Optionally end game or set flag
    }

    @Override
    public char getSymbol() {
        return 'X';
    }

    @Override
    public Node getNode() {
        // Visual representation: gold square
        Rectangle rect = new Rectangle(24, 24);
        rect.setFill(Color.GOLD);
        return rect;
    }
}

