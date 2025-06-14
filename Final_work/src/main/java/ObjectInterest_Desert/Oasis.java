
package ObjectInterest_Desert;

import static Region_Logic.InsideObjectType.*;
import Region_Logic.ObjectInterest;

/**
 * Класс, представляющий объект "Оазис" в пустынном регионе.
 * Объект содержит компоненты: пальму, дерево, озеро и пастбище.
 * В данном объекте разрешены валка деревьев, разведение огня и строительство домов.
 */
public class Oasis extends ObjectInterest {
    
    /**
     * Конструктор объекта "Оазис".
     * Инициализирует компоненты объекта и разрешения на действия:
     * валка деревьев, разведение огня и строительство домов разрешены.
     */
    public Oasis() {
        super(PALM_TREE, TREE, LAKE, PASTURE);
        this.isFellTreeAllowed = true;
        this.isFireAllowed = true;
        this.isHouseBuildingAllowed = true;
    }
    /**
     * Возвращает строковое представление типа объекта.
     * 
     * @return строка "Оазис"
     */
    @Override
    public String getObjectType() {
        return "Оазис";
    }
}