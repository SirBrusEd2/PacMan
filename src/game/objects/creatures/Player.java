package game.objects.creatures;

import game.Game;
import game.objects.creatures.enemy.Enemy;
import game.objects.tiles.Air;
import game.objects.tiles.Dot;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

public class Player extends Creature implements KeyListener {
    // Конструктор класса Player, инициализирует игрока
    public Player(Game game, double centerX, double centerY, double radius, double speed) {
        super(game, centerX, centerY, radius, speed, Color.YELLOW);
    }

    // Проверяет столкновение с точкой
    private void tickDotCollision() {
        int x = (int) centerX;
        int y = (int) centerY;

        if (game.getMap().getTile(x, y) instanceof Dot dot) {
            double dx = dot.getCenterX() - centerX;
            double dy = dot.getCenterY() - centerY;
            double r = dot.getRadius() + radius;

            if (dx * dx + dy * dy < r * r) {
                game.getMap().setTile(x, y, new Air(x, y));
                if (game.getMap().dotCount() == 0) {
                    game.win();
                }
            }
        }
    }

    // Переопределенный метод tickPreferredDirection, обновляет предпочтительное направление движения
    @Override
    protected void tickPreferredDirection() {
        for (Enemy enemy : game.getEnemies()) {
            enemy.tickPreferredDirection();
        }
    }

    // Переопределенный метод tick, обновляет состояние игрока
    @Override
    public void tick() {
        super.tick();
        tickDotCollision();
    }

    // Переопределенный метод render, отрисовывает игрока
    @Override
    public void render(Graphics2D g, int tileSize) {
        double centerXOnScreen = centerX * tileSize;
        double centerYOnScreen = centerY * tileSize;
        double radiusOnScreen = radius * tileSize;
        double diameterOnScreen = radiusOnScreen * 2.0;

        g.setColor(color);
        g.fill(new Ellipse2D.Double(centerXOnScreen - radiusOnScreen, centerYOnScreen - radiusOnScreen, diameterOnScreen, diameterOnScreen));

        // Eyes
        Enemy closestEnemy = null;
        double closestSqDistance = Double.MAX_VALUE;
        for (Enemy enemy : game.getEnemies()) {
            double difX = enemy.centerX - centerX;
            double difY = enemy.centerY - centerY;
            double sqDistance = difX * difX + difY * difY;
            if (sqDistance < closestSqDistance) {
                closestEnemy = enemy;
                closestSqDistance = sqDistance;
            }
        }
        renderEyes(g, centerXOnScreen, centerYOnScreen, radiusOnScreen, closestEnemy.centerX, closestEnemy.centerY);
    }

    // Переопределенный метод keyTyped, игнорирует события клавиатуры
    @Override
    public void keyTyped(KeyEvent e) {
        // ignore
    }

    // Переопределенный метод keyPressed, обрабатывает нажатия клавиш для управления игроком
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                preferredDirectionX = 0;
                preferredDirectionY = -1;
            }
            case KeyEvent.VK_A -> {
                preferredDirectionX = -1;
                preferredDirectionY = 0;
            }
            case KeyEvent.VK_S -> {
                preferredDirectionX = 0;
                preferredDirectionY = 1;
            }
            case KeyEvent.VK_D -> {
                preferredDirectionX = 1;
                preferredDirectionY = 0;
            }
        }
    }

    // Переопределенный метод keyReleased, игнорирует события клавиатуры
    @Override
    public void keyReleased(KeyEvent e) {
        // ignore
    }
}