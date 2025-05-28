package dungeon.engine;

import javafx.scene.text.Text;

public class GameEngine {

    /**
     * An example board to store the current game state.
     *
     * Note: depending on your game, you might want to change this from 'int' to String or something?
     */
    private Cell[][] map;
    private CellContent[][] contents;
    private Player player;
    private int currentLevel = 1;

    // Field for the Controller reference
    private dungeon.gui.Controller controller;

    /**
     * Creates a square game board.
     *
     * @param size the width and height.
     */
    public GameEngine(int size) {
        map = new Cell[size][size];
        contents = new CellContent[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = new Cell();
                Text text = new Text(i + "," + j);
                cell.getChildren().add(text);
                map[i][j] = cell;
            }
        }

        map[0][0].setStyle("-fx-background-color: #7baaa4");
        map[size-1][size-1].setStyle("-fx-background-color: #7baaa4");

        // Place Melee Mutants randomly (3 as per requirement)
        placeContent(new Position(5, 5), new MeleeMutant());
        placeContent(new Position(6, 2), new MeleeMutant());
        placeContent(new Position(3, 7), new MeleeMutant());

        // Place Ranged Mutants randomly (for example 2 of them)
        placeContent(new Position(4, 6), new RangedMutant());
        placeContent(new Position(7, 1), new RangedMutant());

        // Place content on the board
        placeContent(new Position(1, 1), new Gold());
        placeContent(new Position(2, 2), new Trap());
        placeContent(new Position(3, 3), new Heart());

        placeContent(new Position(0, 7), new Wall());
        placeContent(new Position(8, 0), new Wall());
        placeContent(new Position(5, 3), new Wall());

        placeContent(new Position(7, 7), new Ladder());


        // Create and position the player at the starting cell
        player = new Player(new Position(0, map.length - 1));
    }

    // Method to set the Controller
    public void setController(dungeon.gui.Controller controller) {
        this.controller = controller;
    }

    public void clearContentAt(Position pos) {
        contents[pos.getY()][pos.getX()] = null;
    }

    public void log(String message) {
        System.out.println(message);

        if (controller != null) {
            controller.logMessage(message);
        }
    }

    public void placeContent(Position pos, CellContent content) {
        contents[pos.getY()][pos.getX()] = content;
    }

    public CellContent[][] getContents() {
        return contents;
    }

    // Ranged Mutant Attacks:
    public void processRangedAttacks() {
        Position playerPos = player.getPosition();

        for (int y = 0; y < getSize(); y++) {
            for (int x = 0; x < getSize(); x++) {
                CellContent content = getContentAt(new Position(x, y));
                if (content instanceof RangedMutant) {
                    Position mutantPos = new Position(x, y);

                    boolean inRange = (mutantPos.getX() == playerPos.getX() &&
                            Math.abs(mutantPos.getY() - playerPos.getY()) <= 2)
                            || (mutantPos.getY() == playerPos.getY() &&
                            Math.abs(mutantPos.getX() - playerPos.getX()) <= 2);

                    if (inRange) {
                        if (Math.random() < 0.5) {
                            player.applyDamage(2);
                            log("A ranged mutant attacked and you lost 2 HP!");
                        } else {
                            log("A ranged mutant attacked, but missed.");
                        }
                    }
                }
            }
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public CellContent getContentAt(Position pos) {
        return contents[pos.getY()][pos.getX()];
    }

    public void advanceToLevel(int newLevel) {
        this.currentLevel = newLevel;
        log("Level set to " + newLevel);
        // TODO: clear the board, add new content, update difficulty, etc.
    }

    /**
     * The size of the current game.
     *
     * @return this is both the width and the height.
     */
    public int getSize() {
        return map.length;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * The map of the current game.
     *
     * @return the map, which is a 2d array.
     */
    public Cell[][] getMap() {
        return map;
    }

    /**
     * Plays a text-based game
     */
    public static void main(String[] args) {
        GameEngine engine = new GameEngine(10);
        System.out.printf("The size of map is %d * %d\n", engine.getSize(), engine.getSize());
    }
}
