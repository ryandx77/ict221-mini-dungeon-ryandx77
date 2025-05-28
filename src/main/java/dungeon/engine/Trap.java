package dungeon.engine;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Trap implements CellContent {

    @Override
    public void onEnter(Player player, GameEngine engine) {
        player.applyDamage(2); // Reduce HP
        engine.clearContentAt(player.getPosition()); // Remove trap from map
        engine.log("You stepped on a trap! Lost 2 HP.");
    }

    @Override
    public char getSymbol() {
        return 'T';
    }

    @Override
    public Node getNode() {
        // A red triangle to represent a trap
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                12.0, 0.0,
                24.0, 24.0,
                0.0, 24.0
        );
        triangle.setFill(Color.DARKRED);
        return triangle;
    }
}

