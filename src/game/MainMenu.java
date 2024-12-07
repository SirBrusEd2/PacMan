package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private JButton startGameButton;
    private JButton exitButton;
    private JButton rulesButton;

    public MainMenu() {
        setTitle("Pac Man - Main Menu");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        startGameButton = new JButton("Начать игру");
        exitButton = new JButton("Закрыть игру");
        rulesButton = new JButton("Правила игры");

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRules();
            }
        });

        add(startGameButton);
        add(rulesButton);
        add(exitButton);

        setVisible(true);
    }

    private void startGame() {
        new Game();
        setVisible(false);
    }

    private void showRules() {
        String rules = "Правила игры:\n" +
                "1. Цель игры: Соберите все точки на игровом поле, избегая столкновения с врагами.\n" +
                "2. Управление: Используйте клавиши со WSAD для управления (вверх, вниз, влево, вправо).\n" +
                "3. Жизни: У игрока есть 1 жизнь. Если игрок сталкивается с врагом, то игра оканчивается\n" +
                "4. Победа: Игра заканчивается победой, если игрок собирает все точки на игровом поле.";
        JOptionPane.showMessageDialog(this, rules, "Правила игры", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}