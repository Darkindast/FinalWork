/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Command_Classes.ActionResult;
import Region_Logic.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 *
 * @author Andrey
 */
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResultPanel extends JPanel {
    private BufferedImage image;
    private JTextArea area = new JTextArea();
    private JScrollPane scrollPane;

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