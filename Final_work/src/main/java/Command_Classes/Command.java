package Command_Classes;

import Region_Logic.*;
import Region_Logic.ObjectInterest;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Интерфейс, представляющий команду, которую можно выполнить над объектом интереса.
 * Команды могут взаимодействовать с инвентарём, выполнять действия и возвращать изображения.
 */
public interface Command {
    /**
     * Выполняет команду над заданным объектом интереса с учётом содержимого инвентаря.
     *
     * @param objectInterest объект интереса, над которым выполняется команда
     * @param inventory      инвентарь игрока
     * @return {@link ActionResult} с результатом выполнения команды
     */
    ActionResult execute(ObjectInterest objectInterest, Inventory inventory);

    /**
     * Проверяет, достаточно ли у игрока ресурсов (брёвен) для выполнения действия.
     *
     * @param numLogs   необходимое количество брёвен
     * @param inventory инвентарь игрока
     * @return true, если ресурсов достаточно, иначе false
     */
    default boolean checkResources(int numLogs, Inventory inventory) {
        return (inventory.getNumLogs() >= numLogs);
    }
    /**
     * Возвращает изображение, связанное с данной командой (например, иконку).
     *
     * @return изображение команды
     * @throws IOException если изображение не может быть загружено
     */
    BufferedImage getImage() throws IOException;
}