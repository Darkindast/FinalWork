package GUI;

import Region_Logic.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import javax.swing.JFrame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Andrey
 */
public class MainGameFrame extends JFrame {

    public MainGameFrame(String title, CommandManager commandManager, Player player, RegionManager regionManager) throws IOException {
        super(title);
        setSize(800, 400);
        Container con = this.getContentPane();
        con.setLayout(new BorderLayout());
        con.add(new MainGamePanel(commandManager, player, regionManager, this), BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
