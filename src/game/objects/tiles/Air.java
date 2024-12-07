package game.objects.tiles;

import java.awt.*;

// Пути передвижения игрока и врагов

public class Air extends Tile {
    public Air(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics2D g, int tileSize) {
        // Air is invisible
    }
}