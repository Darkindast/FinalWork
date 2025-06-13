/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.*;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Andrey
 */
public class Frame extends JFrame {

    public Frame(String title) throws IOException {
        super(title);
        setSize(700, 400);
        Panel panel = new Panel(this);
        Container con = this.getContentPane();
        con.setLayout(new BorderLayout());
        con.add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

}
