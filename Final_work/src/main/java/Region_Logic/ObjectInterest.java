
package Region_Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Абстрактный класс, представляющий интересующий объект в регионе.
 * Объект содержит возможные и текущие внутренние объекты (InsideObjectType),
 * а также различные разрешения на действия, такие как разведение огня,
 * строительство домов и валка деревьев.
 */
public abstract class ObjectInterest {

    /** Список возможных типов внутренних объектов */
    private ArrayList<InsideObjectType> possibleInsideObjects = new ArrayList<>();
    /** Текущий список внутренних объектов */
    private ArrayList<InsideObjectType> insideObjectsList = new ArrayList<>();
    /** Разрешено ли разводить огонь */
    protected boolean isFireAllowed;
    /** Разрешено ли строить дома */
    protected boolean isHouseBuildingAllowed;
    /** Разрешена ли валка деревьев */
    protected boolean isFellTreeAllowed;
    /** Статус "живости" объекта */
    private boolean isAlive = true;
    /** Генератор случайных чисел */
    private final Random random = new Random();

    
    /**
     * Конструктор объекта, инициализирующий список возможных внутренних объектов.
     *
     * @param types переменное количество типов внутренних объектов
     */
    public ObjectInterest(InsideObjectType... types) {
        possibleInsideObjects.addAll(Arrays.asList(types));
    }

    /**
     * Генерирует текущий список внутренних объектов,
     * добавляя 0-4 экземпляров каждого возможного типа.
     */
    public void generateInsideObjectsList() {
        int size = possibleInsideObjects.size();
        for (InsideObjectType type : possibleInsideObjects) {
            int randomIndex = random.nextInt(5) + 1;
            for (int i = 1; i < randomIndex; i++) {
                insideObjectsList.add(type);
            }
        }
    }

     /**
     * Возвращает список возможных внутренних объектов.
     *
     * @return список InsideObjectType, которые могут находиться внутри
     */
    public ArrayList<InsideObjectType> getPossibleInsideObjects() {
        return possibleInsideObjects;
    }

    /**
     * Возвращает текущий список внутренних объектов.
     *
     * @return список InsideObjectType, находящихся внутри
     */
    public ArrayList<InsideObjectType> getInsideObjects() {
        return insideObjectsList;
    }

    /**
     * Проверяет, разрешено ли разводить огонь.
     *
     * @return true, если огонь разрешён, иначе false
     */
    public boolean getFireAllowedStatus() {
        return isFireAllowed;
    }

    /**
     * Проверяет, разрешено ли строить дома.
     *
     * @return true, если строительство домов разрешено, иначе false
     */
    public boolean getHouseBuildingAllowedStatus() {
        return isHouseBuildingAllowed;
    }
    
    /**
     * Проверяет, разрешена ли валка деревьев.
     *
     * @return true, если валка деревьев разрешена, иначе false
     */
    public boolean getFellTreeAllowedStatus() {
        return isFellTreeAllowed;
    }


 
    /**
     * Устанавливает разрешение на разведение огня.
     *
     * @param status новое значение разрешения
     */
    public void setFireAllowedStatus(boolean status) {
        this.isFireAllowed = status;
    }

    /**
     * Устанавливает разрешение на строительство домов.
     *
     * @param status новое значение разрешения
     */
    public void setHouseBuildingAllowedStatus(boolean status) {
        this.isHouseBuildingAllowed = status;
    }
    /**
     * Устанавливает разрешение на валку деревьев.
     *
     * @param status новое значение разрешения
     */
    public void setFellTreeAllowedStatus(boolean status) {
        this.isFellTreeAllowed = status;
    }

    /**
     * Возвращает статус "живости" объекта.
     *
     * @return true, если объект "жив", иначе false
     */
    public boolean getAliveStatus() {
        return isAlive;
    }

    /**
     * Устанавливает статус "живости" объекта.
     *
     * @param status новое значение статуса
     */
    public void setAliveStatus(boolean status) {
        this.isAlive = status;
    }

    /**
     * Добавляет тип внутреннего объекта в текущий список.
     *
     * @param type тип объекта для добавления
     */
    public void addToInsideObjectsList(InsideObjectType type) {
        insideObjectsList.add(type);
    }

    /**
     * Удаляет тип внутреннего объекта из текущего списка.
     *
     * @param type тип объекта для удаления
     */
    public void removeFromInsideObjectsList(InsideObjectType type) {
        insideObjectsList.remove(type);
    }

    /**
     * Проверяет, содержится ли дерево в текущем списке внутренних объектов.
     *
     * @return true, если дерево присутствует, иначе false
     */
    public boolean searchForTree() {
        return (insideObjectsList.contains(InsideObjectType.TREE));
    }

    /**
     * Абстрактный метод для получения типа объекта.
     * Должен быть реализован в подклассах.
     *
     * @return название типа объекта
     */
    public abstract String getObjectType();

    /**
     * Генерирует уникальное имя объекта на основе индекса.
     *
     * @param index индекс объекта
     * @return уникальное имя объекта
     */
    public String generateObjectUniqueName(int index) {
        return getObjectType() + "номер " + index;
    }
}