package dungeon.engine;

public class Player {
    private int hp;
    private int score;
    private int stepsLeft;
    private Position position;

    public Player(Position startPos) {
        this.hp = 10;
        this.score = 0;
        this.stepsLeft = 100;
        this.position = startPos;
    }

    public int getHealth() {
        return hp;
    }

    public int getScore() {
        return score;
    }

    public int getStepsLeft() {
        return stepsLeft;
    }

    public Position getPosition() {
        return position;
    }

    public void applyDamage(int amount) {
        hp -= amount;
        if (hp < 0) hp = 0;
    }

    public void heal(int amount) {
        hp += amount;
        if (hp > 10) hp = 10;
    }

    public void increaseScore(int amount) {
        score += amount;
    }

    public void decreaseSteps() {
        stepsLeft--;
    }

    public void move(Direction dir, GameEngine engine) {
        int newX = position.getX();
        int newY = position.getY();

        switch (dir) {
            case UP:    newY--; break;
            case DOWN:  newY++; break;
            case LEFT:  newX--; break;
            case RIGHT: newX++; break;
        }

        Position newPos = new Position(newX, newY);

        // Check bounds
        if (newX < 0 || newX >= engine.getSize() || newY < 0 || newY >= engine.getSize()) {
            engine.log("You can't walk outside the map!");
            return;
        }

        // Trigger content at new position
        CellContent content = engine.getContentAt(newPos);

        // Prevent movement if it's a wall
        if (content instanceof Wall) {
            content.onEnter(this, engine);
            return;
        }

        position = newPos;
        decreaseSteps();

        // Ladder logic
        if (content instanceof Ladder) {
            content.onEnter(this, engine);
            if (engine.getCurrentLevel() == 1) {
                engine.log("You reached the ladder and are advancing to Level 2!");
                engine.advanceToLevel(2);
            } else {
                engine.log("You reached the ladder and escaped the dungeon. Victory!");
                javafx.application.Platform.exit();
            }
            return;
        }

        // All other content
        if (content != null) {
            content.onEnter(this, engine);
        }
    }
}

