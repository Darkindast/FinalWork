package GUI;

import Region_Logic.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import javax.swing.JFrame;

/**
 * Главное окно игры, содержащее основную игровую панель.
 * Наследует {@link JFrame} и настраивает расположение компонентов.
 */
public class MainGameFrame extends JFrame {
    /**
     * Создаёт главное окно игры с указанным заголовком и добавляет в него {@link MainGamePanel}.
     *
     * @param title          заголовок окна
     * @param commandManager менеджер команд, используемый для обработки действий игрока
     * @param player         объект игрока
     * @param regionManager  менеджер регионов, отвечающий за логику игры на карте
     * @throws IOException если произошла ошибка при загрузке ресурсов в {@link MainGamePanel}
     */
    public MainGameFrame(String title, CommandManager commandManager, Player player, RegionManager regionManager) throws IOException {
        super(title);
        setSize(800, 400);
        Container con = this.getContentPane();
        con.setLayout(new BorderLayout());
        con.add(new MainGamePanel(commandManager, player, regionManager, this), BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
