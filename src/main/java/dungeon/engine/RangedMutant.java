package dungeon.engine;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Random;

public class RangedMutant implements CellContent {
    private static final Random rand = new Random();
    private boolean alive = true;

    @Override
    public void onEnter(Player player, GameEngine engine) {
        if (!alive) return;

        // Player attacks and wins immediately when stepping on ranged mutant
        player.increaseScore(2);
        engine.log("You attacked a ranged mutant and won!");
        alive = false;
        engine.clearContentAt(player.getPosition()); // Remove mutant
    }

    @Override
    public char getSymbol() {
        return 'R';
    }

    @Override
    public Node getNode() {
        Label label = new Label("👺"); // or use "🧟"
        label.setStyle("-fx-font-size: 22px;");
        return label;
    }

    /**
     * Called by the engine after player moves, checks if player is in attack range.
     * 50% chance to hit player for 2 damage.
     */
    public void tryRangedAttack(Player player, Position myPos, GameEngine engine) {
        if (!alive) return;

        Position playerPos = player.getPosition();

        int dx = Math.abs(myPos.getX() - playerPos.getX());
        int dy = Math.abs(myPos.getY() - playerPos.getY());

        // Check same row or same column within 2 tiles
        boolean inRange = (dx == 0 && dy <= 2) || (dy == 0 && dx <= 2);
        if (inRange) {
            if (rand.nextBoolean()) { // 50% chance
                player.applyDamage(2);
                engine.log("A ranged mutant attacked you and you lost 2 HP.");
            } else {
                engine.log("A ranged mutant attacked, but missed.");
            }
        }
    }

    public boolean isAlive() {
        return alive;
    }
}

