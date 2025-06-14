
package ObjectInterest_Desert;

import static Region_Logic.InsideObjectType.*;
import Region_Logic.ObjectInterest;

/**
 * Класс, представляющий объект "Руины пирамид" в пустынном регионе.
 * Объект содержит элементы: пирамиду (PYRAMID) и гробницу (TOMB).
 * В данном объекте запрещено валить деревья и строить дома, 
 * но разрешено разводить огонь.
 * 
 */
public class PyramidRuins extends ObjectInterest {
    /**
     * Конструктор объекта "Руины пирамид".
     * Устанавливает компоненты и разрешения на действия:
     * валка деревьев и строительство домов запрещены,
     * разведение огня разрешено.
     */
    public PyramidRuins() {
        super(PYRAMID, TOMB);
        this.isFellTreeAllowed = false;
        this.isFireAllowed = true;
        this.isHouseBuildingAllowed = false;
    }
    /**
     * Возвращает строковое представление типа объекта.
     * 
     * @return строка "Руины пирамид"
     */
    @Override
    public String getObjectType() {
        return "Руины пирамид";
    }
}