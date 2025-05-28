package dungeon.engine;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerContent implements CellContent {
    private final Circle representation;

    public PlayerContent() {
        // A simple red circle to represent the player
        this.representation = new Circle(12, Color.RED);
    }

    @Override
    public void onEnter(Player player, GameEngine engine) {
        // Typically nothing happens when a player enters their own cell
    }

    @Override
    public char getSymbol() {
        return 'P'; // You can use any symbol you'd like
    }

    @Override
    public Node getNode() {
        return representation;
    }
}

