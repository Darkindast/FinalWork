
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

/**
 * Панель для отображения карты мира в виде круговой диаграммы регионов.
 * Каждый регион отображается как интерактивный прямоугольник с возможностью выбора.
 * Обрабатывает клики для перехода игрока между регионами.
 */
public class WorldMapPanel extends JPanel {
    /**
     * Менеджер регионов, содержащий все регионы мира.
     */
    private RegionManager regionManager;
    /**
     * Игрок, для которого отображается текущий регион и доступные для перехода регионы.
     */
    private Player player;
    
    /**
     * Фоновое изображение карты.
     */
    private BufferedImage image;
    
    /**
     * Карта соответствия регионам и их фигур (Shape) на панели для определения кликов.
     */
    private Map<BaseRegion, Shape> regionShapes;
       /**
     * Ссылка на текущее окно (JFrame), которое может быть закрыто при переходе.
     */
    private JFrame currentFrame;

    /**
     * Менеджер команд для главного меню игры.
     */
    private CommandManager commandManager = new CommandManager();

    /**
     * Размер квадратной области, в которой рисуется регион.
     */
    private final int REGION_SIZE = 50;

    /**
     * Радиус круга, по которому размещаются регионы.
     */
    private final int CIRCLE_RADIUS = 250;
    
    /**
     * Конструктор панели карты мира.
     *
     * @param regionManager менеджер регионов, содержащий все регионы
     * @param player текущий игрок, для которого отображается карта
     * @param frame ссылка на текущее окно (для возможности закрыть его при переходе)
     */
    public WorldMapPanel(RegionManager regionManager, Player player, JFrame frame) {
        this.regionManager = regionManager;
        this.player = player;
        this.regionShapes = new HashMap<>();
        this.currentFrame = frame;
        

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
    /**
     * Метод отрисовки компонента — рисует карту, регионы и соединительные линии.
     *
     * @param g графический контекст для рисования
     */
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
            
            
            if (region.equals(player.getCurrentRegion())) {
                g2d.setColor(new Color(255, 70, 70));
            } else if (availableRegions.contains(region)) {
                g2d.setColor(new Color(100, 255, 100)); 
            } else {
                g2d.setColor(new Color(100, 100, 100)); 
            }

            g2d.fill(regionRect);
            
            // Рамка региона
            g2d.setColor(new Color(20, 20, 20));
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(regionRect);
            
            // Название региона
            g2d.setColor(Color.WHITE);
            drawCenteredString(g2d, region.getUniqueName(), x + REGION_SIZE/2, y + REGION_SIZE/2);
            
            regionShapes.put(region, regionRect);
            currentAngle += angleStep;
        }
        
        // Рисуем линии соединений между доступными регионами
        drawConnections(g2d, centerX, centerY, availableRegions);
    }

    /**
     * Рисует круговой контур (компас) вокруг регионов.
     *
     * @param g2d графический контекст для рисования
     * @param centerX координата X центра круга
     * @param centerY координата Y центра круга
     */
    private void drawCompass(Graphics2D g2d, int centerX, int centerY) {
        g2d.setColor(new Color(80, 80, 80));
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(centerX - CIRCLE_RADIUS, centerY - CIRCLE_RADIUS, 
                     CIRCLE_RADIUS*2, CIRCLE_RADIUS*2);
    }
    /**
     * Рисует пунктирные линии между текущим регионом игрока и регионами, в которые можно перейти.
     *
     * @param g2d графический контекст для рисования
     * @param centerX координата X центра панели
     * @param centerY координата Y центра панели
     * @param availableRegions список регионов, доступных для перехода
     */
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
                
                // Рисуем пунктирную линию
                Stroke dashed = new BasicStroke(1.5f, BasicStroke.CAP_BUTT, 
                    BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);
                g2d.setStroke(dashed);
                g2d.drawLine((int)currentCenter.getX(), (int)currentCenter.getY(),
                             (int)targetCenter.getX(), (int)targetCenter.getY());
            }
        }
    }

    /**
     * Возвращает координаты центра заданной фигуры.
     *
     * @param shape фигура, центр которой вычисляется
     * @return точка центра фигуры
     */
    private Point2D getCenter(Shape shape) {
        Rectangle2D bounds = shape.getBounds2D();
        return new Point2D.Double(bounds.getCenterX(), bounds.getCenterY());
    }
    /**
     * Рисует строку, выровненную по центру в указанных координатах.
     *
     * @param g2d графический контекст для рисования текста
     * @param text строка для отображения
     * @param x координата X центра текста
     * @param y координата Y центра текста
     */
    private void drawCenteredString(Graphics2D g2d, String text, int x, int y) {
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        g2d.drawString(text, x - textWidth/2, y + textHeight/4);
    }
    
    /**
     * Обрабатывает клик мыши на панели карты.
     * Если кликнутый регион доступен для перехода, обновляет текущий регион игрока и открывает главное меню.
     * В противном случае выводит предупреждение.
     *
     * @param e событие клика мыши
     * @throws IOException при ошибках при создании главного игрового окна
     */
    private void handleMouseClick(MouseEvent e) throws IOException {
        ArrayList<BaseRegion> availableRegions = regionManager.checkAvailableRegions(player.getCurrentRegion());

        for (Map.Entry<BaseRegion, Shape> entry : regionShapes.entrySet()) {
            if (entry.getValue().contains(e.getPoint())) {
                BaseRegion selectedRegion = entry.getKey();
                if (availableRegions.contains(selectedRegion)) {
                    player.setCurrentRegion(selectedRegion);
                    repaint();
                    MainGameFrame frame = new MainGameFrame("Главное меню", commandManager, player, regionManager);
                    currentFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Вы можете перемещаться только в соседние регионы.", 
                        "Невозможно переместиться", 
                        JOptionPane.WARNING_MESSAGE);
                }
                break;
            }
        }
    }
}