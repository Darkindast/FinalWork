package GUI;

import Region_Logic.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;



/**
 * Панель выбора параметров генерации мира.
 * Позволяет задать количество регионов каждого типа (тундра, пустыня, смешанный лес),
 * а также начать новую игру с выбранными параметрами.
 */
public class Panel extends JPanel {

    /**
     * Фоновое изображение панели.
     */
    private BufferedImage image;

    /**
     * Кэш загруженных изображений (имя -> изображение).
     */
    private Map<String, BufferedImage> loadedImages = new HashMap<>();

    /**
     * Основная надпись панели.
     */
    JLabel mainLabelPanel;

    /**
     * Метки для типов регионов.
     */
    JLabel labelTundra;
    JLabel labelDesert;
    JLabel labelMildClimate;

    /**
     * Поля ввода для количества регионов каждого типа.
     */
    TextField countTundra;
    TextField countDesert;
    TextField countMildClimate;

    /**
     * Кнопка для начала новой игры.
     */
    JButton goButton;

    /**
     * Менеджер регионов для генерации и хранения регионов.
     */
    RegionManager regionManager = new RegionManager();

    /**
     * Менеджер команд игры (пока не используется явно).
     */
    CommandManager commandManager = new CommandManager();

    /**
     * Игрок, для которого создаётся мир.
     */
    Player player = new Player();

    /**
     * Текущее окно JFrame, необходимое для управления отображением окон.
     */
    JFrame currentFrame;
    
    /**
     * Конструктор панели выбора регионов.
     * Загружает необходимые ресурсы, инициализирует компоненты и настраивает интерфейс.
     * 
     * @param frame текущее окно JFrame, в котором находится панель
     * @throws IOException в случае ошибки загрузки ресурсов
     */
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
    
    /**
     * Загружает и возвращает шрифт Minecraft указанного размера.
     * Если загрузка кастомного шрифта не удалась, возвращает моноширинный шрифт по умолчанию.
     *
     * @param size размер шрифта в пунктах
     * @return объект Font с заданным размером
     */
    private Font getMinecraftFont(float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/Minecraft.ttf")).deriveFont(size);
        } catch (Exception e) {
            return new Font("Monospaced", Font.BOLD, (int)size); 
        }
    }
    
    /**
     * Переопределённый метод рисования панели.
     * Отрисовывает фоновое изображение.
     * 
     * @param g графический контекст для рисования
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    /**
     * Применяет стиль к переданной метке,
     * используя шрифт Minecraft и настраивая цвета и бордер.
     * 
     * @param label JLabel, к которому применяется стиль
     */
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

 
    /**
     * Настраивает стили для компонентов в стиле Minecraft,
     * включая фон, цвет текста, шрифты и бордеры.
     */
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
    
    /**
     * Добавляет и размещает компоненты панели с помощью GridBagLayout.
     */
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
    
    /**
     * Обработчик нажатия кнопки "Новая игра".
     * Проверяет введённые данные и запускает генерацию мира с указанными регионами.
     */
    public class goButtonListener implements ActionListener {

        /**
         * Вызывается при нажатии кнопки "Новая игра".
         * Проверяет корректность введённых чисел регионов, 
         * предупреждает пользователя о некорректных данных,
         * и при успешной проверке инициализирует генерацию регионов и создание новой игровой панели.
         * 
         * @param e событие нажатия кнопки
         */
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
        /**
         * Проверяет, что хотя бы один регион выбран (больше нуля).
         * 
         * @param countTundra количество тундр
         * @param countDesert количество пустынь
         * @param countMildClimate количество смешанных лесов
         * @return true, если хотя бы один регион выбран, иначе false
         */
        public boolean checkChoice(int countTundra, int countDesert, int countMildClimate) {
            boolean result = true;
            if (countTundra == 0 && countDesert == 0 && countMildClimate == 0) {
                JOptionPane.showMessageDialog(null, "Ошибка! Введите количество регионов больше нуля хотя бы одного биома", null, JOptionPane.WARNING_MESSAGE);
                result = false;
            }
            return result;
        }
        /**
         * Проверяет валидность ввода в поле TextField.
         * Проверяет, что введено неотрицательное число и не больше 7.
         * 
         * @param count поле ввода количества регионов
         * @return true, если ввод корректен, иначе false
         */
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