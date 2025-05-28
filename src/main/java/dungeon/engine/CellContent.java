package dungeon.engine;

import javafx.scene.Node;

public interface CellContent {
    /**
     * Triggered when a player steps on the cell containing this content.
     */
    void onEnter(Player player, GameEngine engine);

    /**
     * Returns a symbol used for debugging or map display.
     */
    char getSymbol();

    /**
     * Returns a JavaFX visual representation of this content.
     */
    Node getNode();  //
}


