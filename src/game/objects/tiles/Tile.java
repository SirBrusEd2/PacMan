package game.objects.tiles;

import game.objects.GameObject;

// Общий абстрактный класс для клеток карты Air, Block, Dot

public abstract class Tile extends GameObject {
    protected final int x;
    protected final int y;

    // Координата клетки
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
