package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Andrey
 */
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


public class InventoryPanel extends JPanel {

    BufferedImage image;
    JTextArea area = new JTextArea();

    public InventoryPanel(Player player) {
        try {
            this.image = player.getInventory().getImage();
            setPreferredSize(new Dimension(image.getWidth() / 2, image.getHeight() / 2));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        area.setText("У вас осталось бревен: " + player.getInventory().getNumLogs());
        area.setFont(new Font("Arial", Font.BOLD, 30));
        add(area);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}
