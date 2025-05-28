package dungeon.engine;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

public class MeleeMutant implements CellContent {

    @Override
    public void onEnter(Player player, GameEngine engine) {
        player.applyDamage(2);
        player.increaseScore(2);
        engine.clearContentAt(player.getPosition());
        engine.log("You attacked a melee mutant and won. Lost 2 HP, gained 2 score.");
    }

    @Override
    public char getSymbol() {
        return 'M';
    }

    @Override
    public Node getNode() {
        Label label = new Label("👹");
        label.setStyle("-fx-font-size: 22px;");
        return label;
    }
}

