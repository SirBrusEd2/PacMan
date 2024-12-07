package game.objects.creatures.enemy;

import game.Game;
import game.objects.creatures.Player;

import java.awt.*;

public class ChasingEnemy extends Enemy {
    // Конструктор для инициализации игрока как преследуемого
    public ChasingEnemy(Game game, Player player, double centerX, double centerY, double radius, double speed, Color color) {
        super(game, player, centerX, centerY, radius, speed, color);
    }

    // Цель, чтобы преследовать игрока
    @Override
    protected void tickTarget() {
        targetX = (int) player.getCenterX();
        targetY = (int) player.getCenterY();
    }
}
