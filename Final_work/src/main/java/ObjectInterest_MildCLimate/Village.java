
package ObjectInterest_MildCLimate;

import static Region_Logic.InsideObjectType.*;
import Region_Logic.ObjectInterest;

/**
 * Класс, представляющий объект "Деревня" в регионе со смешанным климатом.
 * Включает дома, жителей, магазины и церковь.
 * Валка деревьев запрещена, разведение огня и строительство домов разрешены.
 */
public class Village extends ObjectInterest {
    
    /**
     * Конструктор объекта "Деревня".
     * Инициализирует компоненты объекта и устанавливает разрешения на валку деревьев,
     * разведение огня и строительство домов.
     */
    public Village() {
        super(HOUSE, PERSON, SHOP, CHURCH);
        this.isFellTreeAllowed = false;
        this.isFireAllowed = true;
        this.isHouseBuildingAllowed = true;
    }
    
    /**
     * Возвращает строковое представление типа объекта.
     *
     * @return строка "Деревня"
     */
    @Override
    public String getObjectType() {
        return "Деревня";
    }

}