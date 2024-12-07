package game;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel {
    private final Game game;

    // Конструктор класса Display, принимает экземпляр игры Game
    public Display(Game game) {
        super();
        this.game = game;
        game.add(this); // Добавляем этот компонент на игровое окно
    }

    // Переопределенный метод paint, вызывается при необходимости перерисовки компонента
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Устанавливаем параметры рендеринга для улучшения качества изображения
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        game.render(g2); // Рендерим игровое состояние
    }
}