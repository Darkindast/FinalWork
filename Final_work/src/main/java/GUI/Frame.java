
package GUI;

import java.awt.*;
import java.io.IOException;
import javax.swing.JFrame;

/**
 * Класс {@code Frame} представляет основное окно графического интерфейса приложения.
 * Наследует {@link JFrame} и инициализирует главный {@link Panel}.
 */
public class Frame extends JFrame {
    /**
     * Создаёт новое окно с указанным заголовком, задаёт размеры, добавляет панель и делает окно видимым.
     *
     * @param title заголовок окна
     * @throws IOException если возникли ошибки при создании {@link Panel}, например, при загрузке ресурсов
     */
    public Frame(String title) throws IOException {
        super(title);
        setSize(700, 400);
        Panel panel = new Panel(this);
        Container con = this.getContentPane();
        con.setLayout(new BorderLayout());
        con.add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

}
