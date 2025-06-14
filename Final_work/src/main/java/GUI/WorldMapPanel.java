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

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class WorldMapPanel extends JPanel {
   
    private RegionManager regionManager;
    private Player player;
    private BufferedImage image;
    private Map<BaseRegion, Shape> regionShapes;
    private JFrame currentFrame;
    private CommandManager commandManager = new CommandManager();
    private final int REGION_SIZE = 50;
    private final int CIRCLE_RADIUS = 250;
    public WorldMapPanel(RegionManager regionManager, Player player, JFrame frame) {
        this.regionManager = regionManager;
        this.player = player;
        this.regionShapes = new HashMap<>();
        this.currentFrame = frame;
        
        // Настройка панели в стиле Minecraft
        image = ResourceLoader.getInstance().getImage("map.jpg");
        setPreferredSize(new Dimension(800, 800));
        
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
    }

    @Override
    protected void paintComponent(Graphics g) {
           super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

       
        if (image != null) {
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        
     
        drawCompass(g2d, centerX, centerY);
        
        ArrayList<BaseRegion> availableRegions = regionManager.checkAvailableRegions(player.getCurrentRegion());
        ArrayList<BaseRegion> allRegions = regionManager.getRegions();
        
        regionShapes.clear();
        
       
        double angleStep = 2 * Math.PI / allRegions.size();
        double currentAngle = -Math.PI / 2; 
        
        for (BaseRegion region : allRegions) {
          
            int x = centerX + (int)(CIRCLE_RADIUS * Math.cos(currentAngle)) - REGION_SIZE/2;
            int y = centerY + (int)(CIRCLE_RADIUS * Math.sin(currentAngle)) - REGION_SIZE/2;
            
          
            RoundRectangle2D regionRect = new RoundRectangle2D.Double(
                x, y, REGION_SIZE, REGION_SIZE, 10, 10);
            
     
//            if (region.equals(player.getCurrentRegion())) {
//                g2d.setColor(new Color(255, 70, 70));
//            } else if (availableRegions.contains(region)) {
//                g2d.setColor(new Color(100, 255, 100));
//            } else {
//                g2d.setColor(new Color(100, 100, 100)); 
//            }
            g2d.setColor(new Color(100, 100, 100));
            g2d.fill(regionRect);
            
            
            g2d.setColor(new Color(20, 20, 20));
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(regionRect);
            
          
            g2d.setColor(Color.WHITE);
            drawCenteredString(g2d, region.getUniqueName(), x + REGION_SIZE/2, y + REGION_SIZE/2);
            
            regionShapes.put(region, regionRect);
            currentAngle += angleStep;
        }
        
        
//        drawConnections(g2d, centerX, centerY, availableRegions);
    }

    private void drawCompass(Graphics2D g2d, int centerX, int centerY) {
        g2d.setColor(new Color(80, 80, 80));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(centerX - CIRCLE_RADIUS, centerY - CIRCLE_RADIUS, 
                     CIRCLE_RADIUS*2, CIRCLE_RADIUS*2);
        
      
    }

    private void drawConnections(Graphics2D g2d, int centerX, int centerY, 
                               ArrayList<BaseRegion> availableRegions) {
        g2d.setColor(new Color(200, 200, 200, 100));
        g2d.setStroke(new BasicStroke(1.5f));
        
        BaseRegion current = player.getCurrentRegion();
        if (current == null) return;
        
        Shape currentShape = regionShapes.get(current);
        Point2D currentCenter = getCenter(currentShape);
        
        for (BaseRegion region : availableRegions) {
            if (!region.equals(current)) {
                Shape targetShape = regionShapes.get(region);
                Point2D targetCenter = getCenter(targetShape);
                

//                Stroke dashed = new BasicStroke(1.5f, BasicStroke.CAP_BUTT, 
//                    BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);
//                g2d.setStroke(dashed);
//                g2d.drawLine((int)currentCenter.getX(), (int)currentCenter.getY(),
//                             (int)targetCenter.getX(), (int)targetCenter.getY());
            }
        }
    }

    private Point2D getCenter(Shape shape) {
        Rectangle2D bounds = shape.getBounds2D();
        return new Point2D.Double(bounds.getCenterX(), bounds.getCenterY());
    }

    private void drawCenteredString(Graphics2D g2d, String text, int x, int y) {
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        g2d.drawString(text, x - textWidth/2, y + textHeight/4);
    }

//    private void handleMouseClick(MouseEvent e) throws IOException {
//        ArrayList<BaseRegion> availableRegions = regionManager.checkAvailableRegions(player.getCurrentRegion());
//
//        for (Map.Entry<BaseRegion, Shape> entry : regionShapes.entrySet()) {
//            if (entry.getValue().contains(e.getPoint())) {
//                BaseRegion selectedRegion = entry.getKey();
//                if (availableRegions.contains(selectedRegion)) {
//                    player.setCurrentRegion(selectedRegion);
//                    repaint();
//                    MainGameFrame frame = new MainGameFrame("Главное меню", commandManager, player, regionManager);
//                    currentFrame.dispose();
//                } else {
//                    JOptionPane.showMessageDialog(this, 
//                        "Вы можете перемещаться только в соседние регионы.", 
//                        "Невозможно переместиться", 
//                        JOptionPane.WARNING_MESSAGE);
//                }
//                break;
//            }
//        }
//    }
    private void handleMouseClick(MouseEvent e) throws IOException {
    for (Map.Entry<BaseRegion, Shape> entry : regionShapes.entrySet()) {
        if (entry.getValue().contains(e.getPoint())) {
            BaseRegion selectedRegion = entry.getKey();
            player.setCurrentRegion(selectedRegion);
            repaint();
            MainGameFrame frame = new MainGameFrame("Главное меню", commandManager, player, regionManager);
            currentFrame.dispose();
            break;
        }
    }
}
}