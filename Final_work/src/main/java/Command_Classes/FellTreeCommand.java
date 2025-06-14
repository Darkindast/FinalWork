package Command_Classes;

import GUI.ResourceLoader;
import Region_Logic.ObjectInterest;
import Region_Logic.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * Команда, реализующая срубание дерева в объекте интереса.
 * Проверяет возможность и наличие дерева, затем заменяет его на пень и добавляет ресурс в инвентарь.
 */
public class FellTreeCommand implements Command {
    /**
     * Результат выполнения действия.
     */
    private ActionResult actionResult = new ActionResult();
    /**
     * Выполняет команду по срубанию дерева в заданном объекте интереса.
     *
     * @param obj       объект интереса, в котором производится срубка дерева
     * @param inventory инвентарь игрока, в который добавляется ресурс
     * @return {@link ActionResult} с информацией о результате действия
     */
    @Override
    public ActionResult execute(ObjectInterest obj, Inventory inventory) {
        boolean StatusIsNotNull = obj.searchForTree();
        boolean approveStatus = obj.getFellTreeAllowedStatus();
        String message;
        if (approveStatus && StatusIsNotNull) {
            message = "Вы срубили дерево!";
            obj.removeFromInsideObjectsList(InsideObjectType.TREE);
            obj.addToInsideObjectsList(InsideObjectType.STUMP);
            inventory.addToInventory(1);
            actionResult.setStatus(true);
        } else {
            if (!approveStatus) {
                message = "Срубить дерево тут нельзя!";
            } 
            else {
                message = "Тут закончились деревья!";
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
     * @return строка с названием команды — "Срубить дерево"
     */
    public static String getName() {
        return "Срубить дерево";
    }
    /**
     * Загружает и возвращает изображение, связанное с этой командой.
     *
     * @return изображение (иконка сруба дерева)
     * @throws IOException если изображение не может быть загружено
     */
    @Override
    public BufferedImage getImage() throws IOException {
        return ResourceLoader.getInstance().getImage("fellTree.jpg");
    }

}