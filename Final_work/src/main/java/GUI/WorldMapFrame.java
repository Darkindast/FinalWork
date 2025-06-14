
package GUI;

import Region_Logic.*;
import javax.swing.JFrame;

/**
 * JFrame для отображения карты мира.
 * Создает окно с панелью карты, связанной с менеджером регионов и игроком.
 */
public class WorldMapFrame extends JFrame {
    
    /**
     * Конструктор создает окно с картой мира.
     * 
     * @param title Заголовок окна
     * @param regionManager Менеджер регионов для отображения на карте
     * @param player Игрок, информация о котором может отображаться на карте
     */
    public WorldMapFrame(String title, RegionManager regionManager, Player player) {
        super(title);
        add(new WorldMapPanel(regionManager, player, this));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}