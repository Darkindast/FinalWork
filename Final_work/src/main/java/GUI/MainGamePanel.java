/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author Andrey
 */
import Region_Logic.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.tree.TreeModel;


public class MainGamePanel extends JPanel {

    private BufferedImage image;
    private JButton map = new JButton("Карта мира");
    private JButton inventory = new JButton("Инвентарь");
    List<String> namesOI;
    CommandManager manager;
    RegionManager regionManager;
    Player player;
    JList<String> list = new JList();
    JTextArea area = new JTextArea();

    JLabel label;
    JButton chooseAction = new JButton("Выберите действие");
    JScrollPane scrollPane = new JScrollPane();
    boolean showChoiceButton = false;
    JButton showTree = new JButton("Показать дерево ОИ");
    JButton showGraph;
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
                System.out.println(list.getSelectedIndex());
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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        label = new JLabel("Ваш регион: " + player.getCurrentRegion().getUniqueName());
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground(Color.white);
        add(label);
        JLabel headerLabel = new JLabel("Список доступных ОИ: ");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(Color.white);

        gbc.gridx = 0;
        gbc.gridy = 3;

        map.setBackground(Color.WHITE);
        map.setPreferredSize(new Dimension(100, 50));
        map.addActionListener(new mapListener());
        add(map, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inventory.setBackground(Color.WHITE);
        inventory.setPreferredSize(new Dimension(100, 50));
        inventory.addActionListener(new inventoryListener());
        add(inventory, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        showTree.setPreferredSize(new Dimension(150, 50));
        add(showTree);

        gbc.gridx = 1;
        gbc.gridy = 2;
        scrollPane.setPreferredSize(new Dimension(200, 300));
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setColumnHeaderView(headerLabel);
        add(scrollPane);
        gbc.gridx++;
        add(area);
        gbc.gridx++;
        chooseAction.setBackground(Color.WHITE);
        chooseAction.setPreferredSize(new Dimension(150, 50));
        add(chooseAction);
        chooseAction.setVisible(showChoiceButton);
        chooseAction.addActionListener(new chooseActionListener());

    }

    public class chooseActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String actionName = "";
            ArrayList<JRadioButton> buttons = new ArrayList<>();
            JRadioButton houseB = new JRadioButton("Построить дом");
            JRadioButton treeB = new JRadioButton("Срубить дерево");
            JRadioButton fireB = new JRadioButton("Развести костер");
            buttons.add(houseB);
            buttons.add(treeB);
            buttons.add(fireB);
            JPanel panel = new JPanel();
            panel.add(houseB);
            panel.add(treeB);
            panel.add(fireB);
            panel.setBackground(Color.ORANGE);
            JTextArea area = new JTextArea();
            panel.add(new JTextArea());
            JOptionPane.showMessageDialog(null, panel, null, JOptionPane.PLAIN_MESSAGE);
            for (JRadioButton button : buttons) {
                if (button.isSelected()) {
                    actionName = button.getText();
                }
            }
            try {
                ResultPanel panelResult = new ResultPanel(player, actionName, list.getSelectedIndex(), manager);
                JOptionPane.showMessageDialog(null, panelResult, null, JOptionPane.PLAIN_MESSAGE);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "Действие не выбрано!", null, JOptionPane.PLAIN_MESSAGE);
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

    public class inventoryListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            InventoryPanel panelInventory = new InventoryPanel(player);
            JOptionPane.showMessageDialog(null, panelInventory, null, JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
