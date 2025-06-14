/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrey
 */
public class ResourceLoader {
    private static ResourceLoader instance; 

    private final Map<String, BufferedImage> loadedImages = new HashMap<>();

    private ResourceLoader() {
     
    }

    public static ResourceLoader getInstance() {
        if (instance == null) {
            instance = new ResourceLoader();
        }
        return instance;
    }

    public void loadRequiredResourcesFromFolder() {
        List<String> requiredFiles = Arrays.asList(
            "background.png",
            "desert.jpg",
            "fellTree.jpg",
            "fire.jpg",
            "house.jpg",
            "inventory.jpg",
            "mildClimate.jpg",
            "map.jpg",
            "tundra.jpg"
        );
        File baseFolder = null;

        JFileChooser folderChooser = new JFileChooser();
        folderChooser.setDialogTitle("Выберите базовую папку");
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        folderChooser.setAcceptAllFileFilterUsed(false);


        while (baseFolder == null) {
            int result = folderChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                baseFolder = folderChooser.getSelectedFile();
                System.out.println("Выбрана папка: " + baseFolder.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(null, "Папка не выбрана. Пожалуйста, выберите папку.");
                
            }
        }

        
        for (String fileName : requiredFiles) {
            File file = new File(baseFolder, fileName);
            if (file.exists()) {
                try {
                    BufferedImage img = ImageIO.read(file);
                    if (img != null) {
                        loadedImages.put(fileName, img);
                        System.out.println("Загружено из папки: " + fileName);
                    } else {
                        System.out.println("Файл есть, но не удалось загрузить как изображение: " + fileName);
                    }
                } catch (IOException e) {
                    System.out.println("Ошибка при загрузке файла " + fileName + ": " + e.getMessage());
                }
            }
        }

      
        while (loadedImages.size() < requiredFiles.size()) {
            List<String> missingFiles = new ArrayList<>();
            for (String fileName : requiredFiles) {
                if (!loadedImages.containsKey(fileName)) {
                    missingFiles.add(fileName);
                }
            }

            String message = "Не все файлы загружены.\nТребуются следующие файлы:\n";
            for (String missing : missingFiles) {
                message += missing + "\n";
            }
            message += "\nПожалуйста, выберите файл для: " + missingFiles.get(0);

            JOptionPane.showMessageDialog(null, message, "Недостающие файлы", JOptionPane.WARNING_MESSAGE);

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Выберите файл: " + missingFiles.get(0));
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result2 = chooser.showOpenDialog(null);

            if (result2 == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                String expectedFileName = missingFiles.get(0);
                if (!selectedFile.getName().equalsIgnoreCase(expectedFileName)) {
                    JOptionPane.showMessageDialog(null,
                        "Вы выбрали неправильный файл.\nОжидался: " + expectedFileName,
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
                    continue; 
                }

                try {
                    BufferedImage img = ImageIO.read(selectedFile);
                    if (img != null) {
                        loadedImages.put(expectedFileName, img);
                        System.out.println("Загружено вручную: " + expectedFileName);
                    } else {
                        JOptionPane.showMessageDialog(null,
                            "Не удалось загрузить выбранный файл как изображение.",
                            "Ошибка загрузки", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null,
                        "Ошибка при загрузке файла: " + e.getMessage(),
                        "Ошибка загрузки", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                    "Файл не выбран. Загрузка отменена.",
                    "Отмена", JOptionPane.WARNING_MESSAGE);
                break; // выход, если пользователь отказался выбирать файл
            }
        }

        if (loadedImages.size() == requiredFiles.size()) {
            JOptionPane.showMessageDialog(null,
                "Все файлы успешно загружены!",
                "Успех", JOptionPane.INFORMATION_MESSAGE);
         
        } else {
            JOptionPane.showMessageDialog(null,
                "Не все файлы загружены.",
                "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public BufferedImage getImage(String fileName) {
        return loadedImages.get(fileName);
    }
}