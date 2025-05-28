package dungeon.engine;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;

public class Wall implements CellContent {

    @Override
    public void onEnter(Player player, GameEngine engine) {
        engine.log("You walked into a wall. You can't move there.");
    }

    @Override
    public Node getNode() {
        Rectangle wall = new Rectangle(40, 40);
        wall.setFill(Color.DARKGRAY);
        wall.setStroke(Color.BLACK);
        return wall;
    }

    @Override
    public char getSymbol() {
        return '#';
    }
}



