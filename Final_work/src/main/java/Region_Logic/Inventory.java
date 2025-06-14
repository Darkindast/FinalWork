
package Region_Logic;


import GUI.ResourceLoader;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Класс Inventory представляет инвентарь игрока, содержащий количество бревен.
 * Также предоставляет возможность получить изображение инвентаря.
 */
public class Inventory {

    /** Количество бревен в инвентаре */
    private int numLogs;

    /**
     * Конструктор класса Inventory.
     *
     * @param numLogs начальное количество бревен
     */
    public Inventory(int numLogs) {
        this.numLogs = numLogs;
    }

    /**
     * Добавляет указанное количество бревен в инвентарь.
     *
     * @param countLogs количество бревен для добавления
     */
    public void addToInventory(int countLogs) {
        numLogs += countLogs;
    }

    /**
     * Использует (удаляет) указанное количество бревен из инвентаря.
     *
     * @param countLogs количество бревен для использования
     */
    public void useInventory(int countLogs) {
        numLogs -= countLogs;
    }

    /**
     * Возвращает текущее количество бревен в инвентаре.
     *
     * @return количество бревен
     */
    public int getNumLogs() {
        return numLogs;
    }

    /**
     * Загружает и возвращает изображение инвентаря.
     *
     * @return изображение инвентаря в виде BufferedImage
     * @throws IOException если произошла ошибка при загрузке изображения
     */
    public BufferedImage getImage() throws IOException {
        return ResourceLoader.getInstance().getImage("inventory.jpg");
    }

}