
package Command_Classes;


import Region_Logic.*;
import GUI.ResourceLoader;
import java.awt.image.BufferedImage;

import java.io.IOException;


/**
 * Команда, реализующая постройку дома в объекте интереса.
 * Проверяет возможность строительства и наличие ресурсов, после чего выполняет действие.
 */
public class BuildHouseCommand implements Command {
    /**
     * Результат выполнения действия.
     */
    private ActionResult actionResult = new ActionResult();
    /**
     * Выполняет команду по строительству дома в заданном объекте интереса.
     *
     * @param obj       объект интереса, в котором предполагается строительство
     * @param inventory инвентарь игрока, из которого списываются ресурсы
     * @return {@link ActionResult} с информацией о результате действия
     */
    @Override
    public ActionResult execute(ObjectInterest obj, Inventory inventory) {
        boolean approveStatus = obj.getHouseBuildingAllowedStatus();
        String message;
        if (approveStatus && checkResources(10, inventory)) {
            obj.addToInsideObjectsList(InsideObjectType.HOUSE);
            message = "Вы построили дом!";
            inventory.useInventory(10);
            actionResult.setStatus(true);
        } else {
            if (!approveStatus) {
                message = "Построить дом в этом месте нельзя!";
            } else {
                message = "У вас недостаточно ресурсов, чтобы постоить дом!";
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
     * @return строка с названием команды — "Построить дом"
     */
    public static String getName() {
        return "Построить дом";
    }
    /**
     * Загружает и возвращает изображение, связанное с этой командой.
     *
     * @return изображение (иконка дома)
     * @throws IOException если изображение не может быть загружено
     */
    @Override
    public BufferedImage getImage() throws IOException {
        return ResourceLoader.getInstance().getImage("house.jpg");
    }

}