
package ObjectInterest_Tundra;

import static Region_Logic.InsideObjectType.*;
import Region_Logic.ObjectInterest;

/**
 * Класс, представляющий объект "Гейзер" в регионе тундры.
 * Содержит элементы холма, термального источника и ручья.
 * Валка деревьев, разведение огня и строительство домов запрещены.
 */
public class Geyser extends ObjectInterest {
    
    /**
     * Конструктор объекта "Гейзер".
     * Инициализирует компоненты объекта и устанавливает ограничения на валку деревьев,
     * разведение огня и строительство домов.
     */
    public Geyser() {
        super(HILL, THERMAL_POOL, STREAM);
        this.isFellTreeAllowed = false;
        this.isFireAllowed = false;
        this.isHouseBuildingAllowed = false;
    }
    
    /**
     * Возвращает строковое представление типа объекта.
     *
     * @return строка "Гейзер"
     */
    @Override
    public String getObjectType() {
        return "Гейзер";
    }

}