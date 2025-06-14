
package GUI;


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
import javax.swing.ButtonGroup;
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


/**
 * Основная панель игрового интерфейса.
 * Отображает текущий регион, список объектов интереса, а также кнопки для управления игрой.
 * Поддерживает взаимодействия пользователя с объектами и переходы к другим экранам.
 */
public class MainGamePanel extends JPanel {

    /**
     * Фоновое изображение текущего региона.
     */
    private BufferedImage image;

    /**
     * Кнопка для перехода на карту мира.
     */
    private JButton map;

    /**
     * Менеджер команд игры.
     */
    CommandManager manager;

    /**
     * Менеджер регионов мира.
     */
    RegionManager regionManager;

    /**
     * Игрок, для которого отображается интерфейс.
     */
    Player player;

    /**
     * Список объектов интереса текущего региона.
     */
    JList<String> list_region = new JList<>();

    /**
     * Текстовая область для отображения информации об объекте.
     */
    JTextArea info_obj_area = new JTextArea();

    /**
     * Метка с названием текущего региона.
     */
    JLabel name_region;

    /**
     * Кнопка выбора действия с объектом.
     */
    JButton chooseAction;

    /**
     * Кнопка для открытия инвентаря игрока.
     */
    private JButton inventory = new JButton();

    /**
     * Панель прокрутки для списка объектов.
     */
    JScrollPane scrollPane = new JScrollPane();

    /**
     * Флаг видимости кнопки выбора действия.
     */
    boolean showChoiceButton = false;

    /**
     * Кнопка для отображения дерева объектов интереса.
     */
    JButton showTree;

    /**
     * Ссылка на текущее окно (JFrame) для возможности закрытия при переходах.
     */
    JFrame currentFrame;;

    /**
     * Конструктор панели игрового интерфейса.
     *
     * @param manager менеджер команд игры
     * @param player текущий игрок
     * @param regionManager менеджер регионов
     * @param frame текущее окно JFrame
     * @throws IOException если не удалось загрузить изображение региона
     */
    public MainGamePanel(CommandManager manager, Player player, RegionManager regionManager, JFrame frame) throws IOException {
        this.player = player;
        this.manager = manager;
        this.regionManager = regionManager;
        this.currentFrame = frame;
        info_obj_area.setEditable(false);
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

    /**
     * Переопределённый метод отрисовки панели.
     * Рисует фоновое изображение региона с масштабированием.
     *
     * @param g графический контекст для рисования
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * Инициализирует и настраивает список объектов интереса для текущего региона.
     * Добавляет слушатели выбора и клика по элементам списка.
     */
    protected void makeList() {
        DefaultListModel<String> dlm = new DefaultListModel<>();
        for (ObjectInterest obj : player.getCurrentRegion().getObjectsInterestList()) {
            dlm.addElement(obj.getObjectType());
        }
        list_region.setModel(dlm);
        list_region.setSelectionBackground(Color.getHSBColor(63, 224, 208));
        scrollPane.setViewportView(list_region);
        scrollPane.createVerticalScrollBar();
        scrollPane.setSize(200, 200);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        info_obj_area.setPreferredSize(new Dimension(100, 50));

        list_region.addListSelectionListener(new listSelectionListener());
        list_region.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                info_obj_area.setText(list_region.getSelectedValue());
//                System.out.println(list_region.getSelectedIndex());
            }
        });
    }
    
    /**
     * Добавляет и настраивает кнопки и компоненты управления на панели.
     * Используется GridBagLayout для гибкого расположения.
     */
    class listSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            chooseAction.setVisible(true);
        }

    }

    /**
     * Добавляет и настраивает кнопки и компоненты управления на панели.
     * Используется GridBagLayout для гибкого расположения.
     */
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
        name_region = new JLabel(player.getCurrentRegion().getUniqueName());
        name_region.setFont(minecraftFont.deriveFont(Font.BOLD, 18f));
        name_region.setForeground(new Color(255, 150, 0)); 
        add(name_region, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        JLabel listLabel = new JLabel("Объекты:");
        listLabel.setFont(minecraftFont);
        listLabel.setFont(minecraftFont.deriveFont(Font.BOLD, 15f));
        listLabel.setForeground(textColor);
        add(listLabel, gbc);

        gbc.gridy++;
        list_region.setVisibleRowCount(5);
        list_region.setFixedCellWidth(120);
        list_region.setFixedCellHeight(20);
        list_region.setFont(minecraftFont);
        list_region.setBackground(new Color(70, 70, 70)); 
        list_region.setForeground(textColor);
        list_region.setSelectionBackground(new Color(114, 114, 114));

        JScrollPane listScroll = new JScrollPane(list_region,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );
        list_region.setVisibleRowCount(5);
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
        rightButtonsPanel.add(showTree);

        inventory = createMinecraftButton("Инвентарь", buttonSize, minecraftFont);
        inventory.addActionListener(new inventoryListener());
        rightButtonsPanel.add(inventory); 
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        add(rightButtonsPanel, gbc);

        setBackground(new Color(32, 32, 32)); 
    }

    /**
     * Создаёт кнопку в стиле Minecraft с указанным текстом, размером и шрифтом.
     * Добавляет эффекты наведения мыши.
     *
     * @param text текст кнопки
     * @param size размер кнопки
     * @param font шрифт кнопки
     * @return настроенная JButton
     */
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

    /**
     * Обработчик нажатия кнопки "Выбрать действие".
     * Открывает диалог с выбором действий, применяет выбранное действие к объекту,
     * обновляет список объектов и выводит результат.
     */
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
        ButtonGroup group = new ButtonGroup();
        group.add(houseB);
        group.add(treeB);
        group.add(fireB);

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
            int selectedIndex = list_region.getSelectedIndex();
            ObjectInterest selectedObj = player.getCurrentRegion().getObjectsInterestList().get(selectedIndex);
            Command action = manager.getCommandList().get(actionName);

            ActionResult result = player.makeAction(selectedObj, action);

           if (result.isDeleteObjectFromRegion()) {

                DefaultListModel<String> model = (DefaultListModel<String>) list_region.getModel();


                model.remove(selectedIndex);


                player.getCurrentRegion().getObjectsInterestList().remove(selectedIndex);


                info_obj_area.setText("");
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
    
    /**
     * Обработчик нажатия кнопки "Карта мира".
     * Закрывает текущее окно и открывает окно с картой мира.
     */
    public class mapListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            currentFrame.dispose();
            WorldMapFrame frame = new WorldMapFrame("Карта мира", regionManager, player);
        }
    }
    
    /**
     * Обработчик нажатия кнопки "Дерево ОИ".
     * Отображает окно с деревом объектов интереса текущего региона.
     */
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
    
    /**
     * Обработчик нажатия кнопки "Инвентарь".
     * Отображает окно с панелью инвентаря игрока.
     */
    public class inventoryListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            InventoryPanel panelInventory = new InventoryPanel(player);
            JOptionPane.showMessageDialog(null, panelInventory, null, JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
