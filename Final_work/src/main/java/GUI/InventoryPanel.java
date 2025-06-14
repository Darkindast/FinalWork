
package GUI;


import Region_Logic.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Панель, отображающая инвентарь игрока, включая изображение и количество оставшихся ресурсов (брёвен).
 */
public class InventoryPanel extends JPanel {
    /** Изображение инвентаря. */
    BufferedImage image;
    
    /** Текстовая область для отображения количества ресурсов. */
    JTextArea count_res_area = new JTextArea();
    
    /**
     * Создаёт панель инвентаря для указанного игрока.
     * Загружает изображение инвентаря и отображает текст с количеством брёвен.
     *
     * @param player игрок, чей инвентарь отображается
     */
    public InventoryPanel(Player player) {
        try {
            this.image = player.getInventory().getImage();
            setPreferredSize(new Dimension(image.getWidth() / 2, image.getHeight() / 2));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        count_res_area.setText("У вас осталось бревен: " + player.getInventory().getNumLogs());
        count_res_area.setFont(new Font("Arial", Font.BOLD, 30));
        add(count_res_area);
    }
     /**
     * Отрисовывает изображение инвентаря на панели, с использованием сглаживания.
     *
     * @param g графический контекст
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}
