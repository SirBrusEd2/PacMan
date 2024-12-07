package game;

import game.objects.creatures.Player;
import game.objects.creatures.enemy.ChasingEnemy;
import game.objects.creatures.enemy.CuttingEnemy;
import game.objects.creatures.enemy.Enemy;
import game.objects.creatures.enemy.RandomEnemy;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game extends JFrame {
    private final Display display; // Компонент для отображения игрового состояния
    private final GameMap map; // Карта игры
    private final Player player; // Игрок

    private boolean won; // Флаг, указывающий на победу
    private boolean isRunning; // Флаг, указывающий, запущена ли игра

    private final Enemy[] enemies; // Массив врагов
    private final ScheduledExecutorService executorService; // Сервис для управления игровым циклом

    // Конструктор класса Game, инициализирует игровое окно и все его компоненты
    public Game() {
        super("Game");

        display = new Display(this);
        map = new GameMap(40);
        player = new Player(this, 13.5, 10.5, 0.375, 0.07);
        addKeyListener(player); // Добавляем слушатель клавиатуры для управления игроком

        enemies = new Enemy[]{
                new ChasingEnemy(this, player, 12.5, 8.5, 0.375, 0.06, Color.RED),
                new CuttingEnemy(this, player, 13.5, 8.5, 0.375, 0.065, Color.GREEN),
                new RandomEnemy(this, player, 14.5, 8.5, 0.375, 0.07, Color.MAGENTA)
        };

        setSize(1096, 759);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        isRunning = true; // Устанавливаем флаг запуска игры
        executorService = Executors.newSingleThreadScheduledExecutor(); // Создаем сервис для игрового цикла
        startGameLoop(); // Запускаем игровой цикл
    }

    // Запускает игровой цикл, который обновляет состояние игры и перерисовывает экран с заданной частотой (60 FPS)
    private void startGameLoop() {
        executorService.scheduleAtFixedRate(() -> {
            if (isRunning) { // Проверяем, запущена ли игра
                tick(); // Обновляем состояние игры
                display.repaint(); // Перерисовываем экран
            }
        }, 0L, 1000L / 60L, TimeUnit.MILLISECONDS);
    }

    // Сбрасывает игру в начальное состояние
    private void reset() {
        won = false;
        map.reset();
        player.reset();
        for (Enemy enemy : enemies) {
            enemy.reset();
        }
    }

    // Устанавливает флаг победы
    public void win() {
        won = true;
    }

    public void lose() {
        int choice = JOptionPane.showOptionDialog(
                this,
                "Вы проиграли. Хотите сыграть ещё раз?",
                "Поражение",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Да", "Нет"},
                "Да"
        );

        if (choice == JOptionPane.YES_OPTION) {
            reset(); // Перезапускаем игру
        } else {
            isRunning = false; // Останавливаем игровой цикл
            executorService.shutdown(); // Останавливаем сервис игрового цикла
            dispose(); // Закрываем окно игры
            showMainMenu(); // Показываем главное меню
        }
    }

    // Обновляет состояние игры, вызывая методы tick для игрока и врагов
    private void tick() {
        if (won) {
            JOptionPane.showMessageDialog(null, "Вы победили");
            reset(); // Перезапускаем игру после победы
        }
        player.tick();
        for (Enemy enemy : enemies) {
            enemy.tick();
        }
    }

    // Отрисовывает игровое состояние, включая карту, игрока и врагов
    public void render(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        int tileSize = map.getTileSize();

        map.render(g2, tileSize);
        player.render(g2, tileSize);
        for (Enemy enemy : enemies) {
            enemy.render(g2, tileSize);
        }
    }

    // Возвращает карту игры
    public GameMap getMap() {
        return map;
    }

    // Возвращает массив врагов
    public Enemy[] getEnemies() {
        return enemies;
    }

    // Показывает главное меню
    private void showMainMenu() {
        new MainMenu();
    }

}