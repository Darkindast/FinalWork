package Region_Logic;

import Command_Classes.*;
import Regions.*;
import java.util.ArrayList;

/**
 * Класс, представляющий игрока в игре.
 * Игрок имеет инвентарь, историю действий и текущий регион.
 */
public class Player {

    /** Инвентарь игрока */
    private Inventory inventory;
    /** История действий игрока */
    private ArrayList<ActionResult> actionHistory;
    /** Текущий регион, в котором находится игрок */
    private BaseRegion currentRegion;

    /**
     * Конструктор игрока.
     * Инициализирует инвентарь с нулевым количеством ресурсов.
     */
    public Player() {
        this.inventory = new Inventory(0);
    }

     /**
     * Возвращает инвентарь игрока.
     *
     * @return объект Inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Возвращает список результатов действий игрока.
     *
     * @return список ActionResult, представляющий историю действий
     */
    public ArrayList<ActionResult> getActionList() {
        return actionHistory;
    }

    /**
     * Устанавливает инвентарь игрока.
     *
     * @param invent новый объект Inventory для установки
     */
    public void setInventory(Inventory invent) {
        this.inventory = invent;
    }

    /**
     * Устанавливает историю действий игрока.
     *
     * @param history список ActionResult для установки в качестве истории действий
     */
    public void setActionList(ArrayList<ActionResult> history) {
        this.actionHistory = history;
    }

    /**
     * Выполняет действие игрока над объектом интереса.
     *
     * @param obj объект интереса, над которым выполняется действие
     * @param action команда действия, которая будет выполнена
     * @return результат выполнения действия
     */
    public ActionResult makeAction(ObjectInterest obj, Command action) {
        return action.execute(obj, inventory);
    }

    /**
     * Устанавливает текущий регион, в котором находится игрок.
     *
     * @param region новый текущий регион
     */
    public void setCurrentRegion(BaseRegion region) {
        this.currentRegion = region;
    }

    /**
     * Возвращает текущий регион, в котором находится игрок.
     *
     * @return объект BaseRegion текущего региона
     */
    public BaseRegion getCurrentRegion() {
        return currentRegion;
    }
}