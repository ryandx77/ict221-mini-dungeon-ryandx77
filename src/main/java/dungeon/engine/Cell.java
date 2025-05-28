package dungeon.engine;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends StackPane {
    private CellContent content;
    private Rectangle background;

    public Cell() {
        // Create a rectangle to serve as the visible cell background
        background = new Rectangle(60, 60); // size can be adjusted
        background.setFill(Color.LIGHTGRAY); // light gray fill
        background.setStroke(Color.BLACK);   // black borderlines

        // Add background first, so content will be on top
        getChildren().add(background);
    }

    public void setContent(CellContent content) {
        this.content = content;

        // Remove everything except background (keep background intact)
        getChildren().removeIf(node -> node != background);

        if (content != null) {
            Node visual = content.getNode();
            if (visual != null) {
                getChildren().add(visual);
            }
        }
    }

    public CellContent getContent() {
        return content;
    }
}
