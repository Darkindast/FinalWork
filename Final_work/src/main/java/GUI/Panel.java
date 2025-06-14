/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Region_Logic.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;




public class Panel extends JPanel {

    private BufferedImage image;
    private Map<String, BufferedImage> loadedImages = new HashMap<>();

    JLabel mainLabelPanel;
    JLabel labelTundra;
    JLabel labelDesert;
    JLabel labelMildClimate;
    TextField countTundra;
    TextField countDesert;
    TextField countMildClimate;
    JButton goButton;
    RegionManager regionManager = new RegionManager();
    CommandManager commandManager = new CommandManager();
    Player player = new Player();
    JFrame currentFrame;

    public Panel(JFrame frame) throws IOException {
        this.currentFrame = frame;
        ResourceLoader.getInstance().loadRequiredResourcesFromFolder();
        image = ResourceLoader.getInstance().getImage("background.png");

        mainLabelPanel = new JLabel("Добавьте регионы в свой мир!", SwingConstants.CENTER);
        labelTundra = new JLabel("Тундра");
        labelDesert = new JLabel("Пустыня");
        labelMildClimate = new JLabel("Смешанный Лес");

        countTundra = new TextField();
        countDesert = new TextField();
        countMildClimate = new TextField();

        goButton = new JButton("Новая игра");
        goButton.addActionListener(new goButtonListener());

       
        setupMinecraftStyles();

        setLayout(new GridBagLayout());
        addComponents();

 
    }
    private Font getMinecraftFont(float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/Minecraft.ttf")).deriveFont(size);
        } catch (Exception e) {
            return new Font("Monospaced", Font.BOLD, (int)size); 
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    private void changeLabels(JLabel label) {
        try {
            Font minecraftFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/Minecraft.ttf")).deriveFont(20f);
            label.setFont(minecraftFont);
        } catch (Exception e) {
            label.setFont(new Font("Monospaced", Font.BOLD, 20)); 
        }
        label.setOpaque(true);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(130, 130, 130)); 
        Border border = BorderFactory.createLineBorder(new Color(143, 119, 72), 3); 
        label.setBorder(border);
    }

 

    private void setupMinecraftStyles() {
        
        setBackground(new Color(32, 32, 32));
        
       
       for (TextField field : new TextField[]{countTundra, countDesert, countMildClimate}) {
            field.setBackground(new Color(64, 64, 64));
            field.setForeground(Color.WHITE);
            field.setFont(new Font("Monospaced", Font.PLAIN, 14));
}
        
   
        goButton.setBackground(new Color(130, 130, 130));
        goButton.setForeground(Color.WHITE);
        goButton.setFont(new Font("Monospaced", Font.BOLD, 20));
        goButton.setBorder(BorderFactory.createRaisedBevelBorder());
        goButton.setFocusPainted(false);

        mainLabelPanel.setFont(new Font("Monospaced", Font.BOLD, 24));
        mainLabelPanel.setForeground(new Color(130, 130, 130)); // Золотой цвет
    }

private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        changeLabels(labelTundra);
        changeLabels(labelDesert);
        changeLabels(labelMildClimate);
        add(labelTundra, gbc);
        gbc.gridy++;
        add(labelDesert, gbc);
        gbc.gridy++;
        add(labelMildClimate, gbc);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainLabelPanel.setFont(new Font("Monospaced", Font.BOLD, 30));
        mainLabelPanel.setForeground(Color.white);
        add(mainLabelPanel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(countTundra, gbc);
        gbc.gridy++;
        add(countDesert, gbc);
        gbc.gridy++;
        add(countMildClimate, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        add(goButton, gbc);

    }

    public class goButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (choiceCheck(countTundra) && choiceCheck(countDesert) && choiceCheck(countMildClimate)) {
                if (checkChoice(Integer.parseInt(countTundra.getText()), Integer.parseInt(countDesert.getText()), Integer.parseInt(countMildClimate.getText()))) {

                    removeAll();
                    revalidate();
                    repaint();
                    try {

                        regionManager.generateRegions(Integer.parseInt(countTundra.getText()), Integer.parseInt(countDesert.getText()), Integer.parseInt(countMildClimate.getText()));
                    } catch (IOException ex) {
                        Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    player.setCurrentRegion(regionManager.getRegion(0));
                      
                        WorldMapFrame frame = new WorldMapFrame("Карта мира", regionManager, player);
                        currentFrame.dispose();

                }
            }
        }

        public boolean checkChoice(int countTundra, int countDesert, int countMildClimate) {
            boolean result = true;
            if (countTundra == 0 && countDesert == 0 && countMildClimate == 0) {
                JOptionPane.showMessageDialog(null, "Ошибка! Введите количество регионов больше нуля хотя бы одного биома", null, JOptionPane.WARNING_MESSAGE);
                result = false;
            }
            return result;
        }

        public boolean choiceCheck(TextField count) {
            int choice = 0;
            boolean result = true;
            try {
                choice = Integer.parseInt(count.getText());
                if (choice < 0) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Введено отрицательное число.", null, JOptionPane.WARNING_MESSAGE);
                    result = false;
                } else if (choice > 7) {
                    JOptionPane.showMessageDialog(null, "В вашем мире может быть максимум 7 регионов для каждого биома!", null, JOptionPane.WARNING_MESSAGE);
                    result = false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ошибка! Введен неверный тип данных", null, JOptionPane.WARNING_MESSAGE);
                result = false;
            }
            return result;
        }

    }

}