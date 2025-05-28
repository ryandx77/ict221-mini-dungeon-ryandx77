package dungeon.engine;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.Node;

public class Ladder implements CellContent {
    @Override
    public void onEnter(Player player, GameEngine engine) {
        // Ladder logic handled in Player.move()
    }

    @Override
    public Node getNode() {
        Label label = new Label("L");
        label.setTextFill(Color.GREEN);
        label.setFont(new Font("Monospaced", 20));
        return label;
    }

    @Override
    public char getSymbol() {
        return 'L';
    }
}


