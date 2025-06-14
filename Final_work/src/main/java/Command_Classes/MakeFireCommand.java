package Command_Classes;

import GUI.ResourceLoader;
import Region_Logic.*;
import Region_Logic.ObjectInterest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Команда, реализующая разведение костра в объекте интереса.
 * Существует шанс того, что весь объект интереса будет уничтожен огнём.
 */
public class MakeFireCommand implements Command {
    /**
     * Результат выполнения команды.
     */
    private ActionResult actionResult = new ActionResult();
    
    /**
     * Выполняет команду по разведению костра.
     * Проверяет разрешение, наличие ресурсов и случайно определяет,
     * будет ли объект интереса уничтожен огнём.
     *
     * @param obj       объект интереса, в котором выполняется команда
     * @param inventory инвентарь игрока
     * @return {@link ActionResult} с информацией о результате действия
     */
    @Override
    public ActionResult execute(ObjectInterest obj, Inventory inventory) {
        boolean approveStatus = obj.getFireAllowedStatus();
        String message;
        if (approveStatus && checkResources(1, inventory)) {
            obj.addToInsideObjectsList(InsideObjectType.BONFIRE);
            if (burnObjectInterest()) {
                message = "Вы сожгли объект интереса в этом регионе!";
                obj.setAliveStatus(false);
                
                for (InsideObjectType insideObj : new ArrayList<>(obj.getInsideObjects())) {
                    obj.removeFromInsideObjectsList(insideObj);
                }
                actionResult.setDeleteObjectFromRegion(true);
            } else {
                actionResult.setDeleteObjectFromRegion(false);
                message = "Вы развели костер!";
            }
            inventory.useInventory(1);
            actionResult.setStatus(true);
        } else {
            actionResult.setDeleteObjectFromRegion(false);
            if (!approveStatus) {
                message = "Развести костер тут нельзя!";
            } else {
                message = "У вас недостаточно ресурсов, чтобы развести костер!";
            }
            actionResult.setStatus(false);
        }
        actionResult.setMessage(message);
        actionResult.setObjectInterest(obj);
        return actionResult;
    }
    /**
     * Возвращает название команды.
     *
     * @return строка с названием команды — "Развести костер"
     */
    public static String getName() {
        return "Развести костер";
    }
    /**
     * Определяет, уничтожится ли объект интереса от огня.
     *
     * @return true, если объект сгорел (вероятность 10%), иначе false
     */
    public boolean burnObjectInterest() {
        Random random = new Random();
        double probability = 0.1;
        return (random.nextDouble() <= probability);
    }
    /**
     * Загружает и возвращает изображение, связанное с данной командой.
     *
     * @return изображение (иконка костра)
     * @throws IOException если изображение не может быть загружено
     */
    @Override
    public BufferedImage getImage() throws IOException {
        return ResourceLoader.getInstance().getImage("fire.jpg");
    }
}