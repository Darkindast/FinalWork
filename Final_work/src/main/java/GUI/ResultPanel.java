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
//public class ResultPanel extends JPanel {
//
//    BufferedImage image;
//    JTextArea area = new JTextArea();
//
//    public ResultPanel(Player player, String actionName, int index, CommandManager manager) {
//        try {
//            this.image = manager.getCommandList().get(actionName).getImage();
//            setPreferredSize(new Dimension(image.getWidth() / 2, image.getHeight() / 2));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        String result = player.makeAction(player.getCurrentRegion().getObjectsInterestList().get(index), manager.getCommandList().get(actionName)).getCompleteResult();
//        
//        area.setText(result);
//        add(area);
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
//    }
//
//}

public class ResultPanel extends JPanel {
    BufferedImage image;
    JTextArea area = new JTextArea();

    public ResultPanel(ActionResult result, String actionName, CommandManager manager) {
        try {
            this.image = manager.getCommandList().get(actionName).getImage();
            setPreferredSize(new Dimension(image.getWidth() / 2, image.getHeight() / 2));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        area.setText(result.getCompleteResult());
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