
package ObjectInterest_Tundra;

import static Region_Logic.InsideObjectType.*;
import Region_Logic.ObjectInterest;

/**
 * Класс, представляющий объект "Ледник" в регионе тундры.
 * Содержит элементы пещеры, белого медведя и снежной дюны.
 * Валка деревьев, разведение огня и строительство домов запрещены.
 */
public class Glacier extends ObjectInterest {

    /**
     * Конструктор объекта "Ледник".
     * Инициализирует компоненты объекта и устанавливает ограничения на валку деревьев,
     * разведение огня и строительство домов.
     */
    public Glacier() {
        super(CAVE, POLAR_BEAR, SNOW_DUNE);
        this.isFellTreeAllowed = false;
        this.isFireAllowed = false;
        this.isHouseBuildingAllowed = false;
    }
    /**
     * Возвращает строковое представление типа объекта.
     *
     * @return строка "Ледник"
     */
    @Override
    public String getObjectType() {
        return "Ледник";
    }
}