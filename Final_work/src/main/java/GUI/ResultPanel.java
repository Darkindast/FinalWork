
package GUI;

import Command_Classes.ActionResult;
import Region_Logic.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * Панель отображения результата выполнения команды.
 * 
 * В верхней части отображает уменьшенное изображение, связанное с выполненной командой,
 * в нижней — текст с результатом действия.
 * Текст форматируется с переносом строк и кастомным шрифтом (если доступен Minecraft.ttf).
 * 
 * Используется {@link ActionResult} для получения текста результата,
 * и {@link CommandManager} для получения изображения по имени команды.
 */
public class ResultPanel extends JPanel {
    /** Изображение, связанное с командой */
    private BufferedImage image;
    
    /** Текстовое поле для отображения результата */
    private JTextArea area = new JTextArea();
    
    /** Панель прокрутки для текстового поля */
    private JScrollPane scrollPane;

    /**
     * Конструктор, создающий панель с результатом.
     * 
     * @param result объект результата действия {@link ActionResult}
     * @param actionName имя команды, результат которой отображается
     * @param manager менеджер команд для получения изображения
     */
    public ResultPanel(ActionResult result, String actionName, CommandManager manager) {
        setLayout(new BorderLayout()); 
        
        try {
            this.image = manager.getCommandList().get(actionName).getImage();
      
            int imgWidth = image.getWidth() / 2;
            int imgHeight = image.getHeight() / 2;
            
         
            area.setText(result.getCompleteResult());
            area.setEditable(false);
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            
            
            try {
                Font minecraftFont = Font.createFont(Font.TRUETYPE_FONT, 
                    getClass().getResourceAsStream("/Minecraft.ttf")).deriveFont(14f);
                area.setFont(minecraftFont);
            } catch (Exception e) {
                area.setFont(new Font("Monospaced", Font.PLAIN, 14));
            }
            
            area.setForeground(new Color(0xE0E0E0)); 
            area.setBackground(new Color(0x1A1A1A)); 
            area.setCaretColor(Color.WHITE);
            
            
            JPanel imagePanel = new JPanel() {
                /**
                 * Переопределённый метод рисования панели.
                 * Отрисовывает фоновое изображение.
                 * 
                 * @param g графический контекст для рисования
                 */
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (image != null) {
                        Graphics2D g2d = (Graphics2D) g;
                        g2d.setRenderingHint(
                            RenderingHints.KEY_INTERPOLATION, 
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                        g2d.drawImage(image, 0, 0, imgWidth, imgHeight, null);
                    }
                }
            };
            imagePanel.setPreferredSize(new Dimension(imgWidth, imgHeight));
            
            
            scrollPane = new JScrollPane(area);
            scrollPane.setPreferredSize(new Dimension(imgWidth, 50)); 

            add(imagePanel, BorderLayout.NORTH);
            add(scrollPane, BorderLayout.CENTER);

            setPreferredSize(new Dimension(imgWidth, imgHeight + 50));
            
        } catch (IOException ex) {
            ex.printStackTrace();
         
            add(new JScrollPane(area), BorderLayout.CENTER);
        }
    }
}