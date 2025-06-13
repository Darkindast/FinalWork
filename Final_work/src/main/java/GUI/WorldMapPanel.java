/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import Region_Logic.*;
import Regions.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrey
 */
public class WorldMapPanel extends JPanel {

    private final int RADIUS = 30;
    private RegionManager regionManager;
    private CommandManager commandManager = new CommandManager();
    private Player player;
    private Map<BaseRegion, Ellipse2D> regionCircles;
    private JFrame currentFrame;

    public WorldMapPanel(RegionManager regionManager, Player player, JFrame frame) {
        this.regionManager = regionManager;
        this.player = player;
        this.regionCircles = new HashMap<>();
        this.currentFrame = frame;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    handleMouseClick(e);
                } catch (IOException ex) {
                    Logger.getLogger(WorldMapPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        setPreferredSize(new Dimension(800, 800));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int bigRadius = 300;

        ArrayList<BaseRegion> availableRegions = regionManager.checkAvailableRegions(player.getCurrentRegion());
        ArrayList<BaseRegion> regions = regionManager.getRegions();

        int numRegions = regions.size();
        double angleStep = 2 * Math.PI / numRegions;

        regionCircles.clear();

        for (int i = 0; i < numRegions; i++) {
            BaseRegion region = regions.get(i);

            double angle = i * angleStep;
            int x = centerX + (int) (bigRadius * Math.cos(angle)) - RADIUS;
            int y = centerY + (int) (bigRadius * Math.sin(angle)) - RADIUS;

            Ellipse2D circle = new Ellipse2D.Double(x, y, RADIUS * 2, RADIUS * 2);

            if (availableRegions.contains(region)) {
                g2d.setColor(Color.GREEN);
            } else {
                g2d.setColor(Color.LIGHT_GRAY);
            }

            g2d.fill(circle);
            g2d.setColor(Color.BLACK);
            g2d.draw(circle);

            FontMetrics fm = g2d.getFontMetrics();
            String regionName = region.getUniqueName();
            int textWidth = fm.stringWidth(regionName);
            int textHeight = fm.getHeight();
            g2d.drawString(regionName, x + RADIUS - textWidth / 2, y + RADIUS + textHeight / 4);

            regionCircles.put(region, circle);
        }
    }

    private void handleMouseClick(MouseEvent e) throws IOException {
        ArrayList<BaseRegion> availableRegions = regionManager.checkAvailableRegions(player.getCurrentRegion());

        for (Map.Entry<BaseRegion, Ellipse2D> entry : regionCircles.entrySet()) {
            if (entry.getValue().contains(e.getPoint())) {
                BaseRegion selectedRegion = entry.getKey();
                if (availableRegions.contains(selectedRegion)) {
                    player.setCurrentRegion(selectedRegion);
                    repaint();
                    MainGameFrame frame = new MainGameFrame("Главное меню", commandManager, player, regionManager);
                    currentFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Вы можете перемещаться только в соседние регионы.", "Невозможно переместиться", JOptionPane.WARNING_MESSAGE);
                }
                break;
            }
        }
    }

}