/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author Andrey
 */
import Command_Classes.ActionResult;
import Command_Classes.Command;
import Region_Logic.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class MainGamePanel extends JPanel {

    private BufferedImage image;
    private JButton map;
    CommandManager manager;
    RegionManager regionManager;
    Player player;
    JList<String> list = new JList();
    JTextArea area = new JTextArea();
    JLabel label;
    JButton chooseAction;
    JScrollPane scrollPane = new JScrollPane();
    boolean showChoiceButton = false;
    JButton showTree;
    JFrame currentFrame;

    public MainGamePanel(CommandManager manager, Player player, RegionManager regionManager, JFrame frame) throws IOException {
        this.player = player;
        this.manager = manager;
        this.regionManager = regionManager;
        this.currentFrame = frame;
        area.setEditable(false);
        makeList();
       
        try {
            image = player.getCurrentRegion().getImage();
            setPreferredSize(new Dimension(image.getWidth() / 2, image.getHeight() / 2));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        addButtons();

        showTree.addActionListener(new showTreeListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    protected void makeList() {
        DefaultListModel<String> dlm = new DefaultListModel<>();
        for (ObjectInterest obj : player.getCurrentRegion().getObjectsInterestList()) {
            dlm.addElement(obj.getObjectType());
        }
        list.setModel(dlm);
        list.setSelectionBackground(Color.getHSBColor(63, 224, 208));
        scrollPane.setViewportView(list);
        scrollPane.createVerticalScrollBar();
        scrollPane.setSize(200, 200);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        area.setPreferredSize(new Dimension(100, 50));

        list.addListSelectionListener(new listSelectionListener());
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                area.setText(list.getSelectedValue());
//                System.out.println(list.getSelectedIndex());
            }
        });
    }

    class listSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            chooseAction.setVisible(true);
        }

    }

private void addButtons() {
    
    removeAll();
    revalidate();
    repaint();
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    Font minecraftFont = new Font("Minecraft", Font.PLAIN, 12); 
    Color buttonColor = new Color(135, 109, 80); 
    Color textColor = Color.WHITE;
    Color borderColor = new Color(58, 47, 34);
    

    gbc.insets = new Insets(4, 4, 4, 4);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.NONE;

 
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 3;
    label = new JLabel(player.getCurrentRegion().getUniqueName());
    label.setFont(minecraftFont.deriveFont(Font.BOLD, 18f));
    label.setForeground(new Color(255, 150, 0)); 
    add(label, gbc);


    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    
    JLabel listLabel = new JLabel("Объекты:");
    listLabel.setFont(minecraftFont);
    listLabel.setFont(minecraftFont.deriveFont(Font.BOLD, 15f));
    listLabel.setForeground(textColor);
    add(listLabel, gbc);
    
    gbc.gridy++;
    list.setVisibleRowCount(5);
    list.setFixedCellWidth(120);
    list.setFixedCellHeight(20);
    list.setFont(minecraftFont);
    list.setBackground(new Color(70, 70, 70)); 
    list.setForeground(textColor);
    list.setSelectionBackground(new Color(114, 114, 114));
    
    JScrollPane listScroll = new JScrollPane(list,
    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
);
    list.setVisibleRowCount(5);
    listScroll.setBorder(BorderFactory.createLineBorder(borderColor, 2));
    listScroll.getViewport().setBackground(new Color(70, 70, 70));
    add(listScroll, gbc);

  

    Dimension buttonSize = new Dimension(140, 30); 

    JPanel rightButtonsPanel = new JPanel();
    rightButtonsPanel.setLayout(new GridLayout(3, 1, 0, 10)); 
    rightButtonsPanel.setOpaque(false); 

    map = createMinecraftButton("Карта мира", buttonSize, minecraftFont);
    map.addActionListener(new mapListener());
    rightButtonsPanel.add(map);

    chooseAction = createMinecraftButton("Выбрать действие", buttonSize, minecraftFont);
    chooseAction.setVisible(showChoiceButton);
    chooseAction.addActionListener(new chooseActionListener());
    rightButtonsPanel.add(chooseAction);

    showTree = createMinecraftButton("Дерево ОИ", buttonSize, minecraftFont);
    showTree.addActionListener(new showTreeListener());
    rightButtonsPanel.add(showTree);
    gbc.gridx = 2;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.NONE;
    add(rightButtonsPanel, gbc);
  
    setBackground(new Color(32, 32, 32)); 
}

private JButton createMinecraftButton(String text, Dimension size, Font font) {
    JButton button = new JButton(text);
    button.setPreferredSize(size);
    button.setFont(font);
    button.setForeground(Color.WHITE);
    button.setBackground(new Color(135, 109, 80));
    button.setBorder(BorderFactory.createCompoundBorder(
     BorderFactory.createLineBorder(new Color(58, 47, 34)), 
     BorderFactory.createEmptyBorder(2, 10, 2, 10)         
 ));
    button.setFocusPainted(false);
    button.setContentAreaFilled(false);
    button.setOpaque(true);
    

    button.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            button.setBackground(new Color(156, 126, 93)); 
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            button.setBackground(new Color(135, 109, 80)); 
        }
    });
    
    return button;
}


public class chooseActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
  
    Color mcDark = new Color(34, 34, 34);
    Color mcBorder = new Color(85, 85, 85);
    Color mcText = Color.WHITE;
    Font mcFont = new Font("Arial", Font.BOLD, 14);

    String actionName = "";
    ArrayList<JRadioButton> buttons = new ArrayList<>();
    
 
    JRadioButton houseB = new JRadioButton("Построить дом");
    JRadioButton treeB = new JRadioButton("Срубить дерево");
    JRadioButton fireB = new JRadioButton("Развести костер");
    
    
    for (JRadioButton button : new JRadioButton[]{houseB, treeB, fireB}) {
        button.setBackground(mcDark);
        button.setForeground(mcText);
        button.setFont(mcFont);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(mcBorder),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        button.setOpaque(true);
        button.setFocusPainted(false);
    }
    
    buttons.add(houseB);
    buttons.add(treeB);
    buttons.add(fireB);
    
 
    JPanel panel = new JPanel();
    panel.setBackground(mcDark);
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    panel.add(houseB);
    panel.add(treeB);
    panel.add(fireB);
    
 
    JOptionPane.showMessageDialog(
        null, 
        panel, 
        "Выберите действие", 
        JOptionPane.PLAIN_MESSAGE,
        null
    );
    
  
    for (JRadioButton button : buttons) {
        if (button.isSelected()) {
            actionName = button.getText();
            break;
        }
    }

    try {
        int selectedIndex = list.getSelectedIndex();
        ObjectInterest selectedObj = player.getCurrentRegion().getObjectsInterestList().get(selectedIndex);
        Command action = manager.getCommandList().get(actionName);
        
        ActionResult result = player.makeAction(selectedObj, action);
        
       if (result.isDeleteObjectFromRegion()) {
    
            DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();

            
            model.remove(selectedIndex);

            
            player.getCurrentRegion().getObjectsInterestList().remove(selectedIndex);

            
            area.setText("");
        }
        
        ResultPanel panelResult = new ResultPanel(result, actionName, manager);
        panelResult.setBackground(mcDark);
        panelResult.setForeground(mcText);
        
        JOptionPane.showMessageDialog(
            null, 
            panelResult, 
            "Результат действия", 
            JOptionPane.PLAIN_MESSAGE,
            null
        );
        
    } catch (NullPointerException ex) {
       
        JOptionPane.showMessageDialog(
            null,
            "Действие не выбрано!",
            "Ошибка",
            JOptionPane.WARNING_MESSAGE
        );
    } catch (IndexOutOfBoundsException ex) {
        JOptionPane.showMessageDialog(
            null,
            "Объект не выбран!",
            "Ошибка",
            JOptionPane.WARNING_MESSAGE
        );
    }
}
}

    public class mapListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            currentFrame.dispose();
            WorldMapFrame frame = new WorldMapFrame("Карта мира", regionManager, player);
        }
    }

    public class showTreeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = new JPanel();
            MyTreeModel model = new MyTreeModel();
            model.addObjectsList(player.getCurrentRegion().getObjectsInterestList());
            JTree tree = new JTree(model);
            tree.setBackground(Color.WHITE);
            panel.add(tree);
            JScrollPane scrollPane = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            JOptionPane.showMessageDialog(null, scrollPane, null, JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
