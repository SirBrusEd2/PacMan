package game.objects.tiles;

import java.awt.*;
import java.awt.geom.Ellipse2D;

// Поиты, которые собирает игрок, получая очки

public class Dot extends Tile {
    protected final double radius;

    // Конструктор класса Dot, инициализирует клетку точки
    protected Dot(int x, int y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    // Конструктор класса Dot, инициализирует клетку точки с радиусом по умолчанию
    public Dot(int x, int y) {
        this(x, y, 0.125);
    }

    // Переопределенный метод render, отрисовывает точку
    @Override
    public void render(Graphics2D g, int tileSize) {
        double centerXOnScreen = getCenterX() * tileSize;
        double centerYOnScreen = getCenterY() * tileSize;
        double radiusOnScreen = radius * tileSize;
        double diameterOnScreen = radiusOnScreen * 2.0;

        g.setColor(Color.WHITE);
        g.fill(new Ellipse2D.Double(centerXOnScreen - radiusOnScreen, centerYOnScreen - radiusOnScreen, diameterOnScreen, diameterOnScreen));
    }

    // Возвращает центральную координату X точки
    public double getCenterX() {
        return x + 0.5;
    }

    // Возвращает центральную координату Y точки
    public double getCenterY() {
        return y + 0.5;
    }

    // Возвращает радиус точки
    public double getRadius() {
        return radius;
    }
}