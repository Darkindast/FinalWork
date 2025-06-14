
package ObjectInterest_Tundra;

import static Region_Logic.InsideObjectType.*;
import Region_Logic.ObjectInterest;


/**
 * Класс, представляющий объект "Болото" в тундровой зоне.
 * Содержит деревья и кустарники.
 * Валка деревьев разрешена, огонь и строительство домов запрещены.
 */
public class Swamp extends ObjectInterest {
    
    /**
     * Конструктор объекта "Болото".
     * Инициализирует допустимые внутренние объекты и правила.
     */
    public Swamp() {
        super(TREE, BUSH);
        this.isFellTreeAllowed = true;
        this.isFireAllowed = false;
        this.isHouseBuildingAllowed = false;
    }
    /**
     * Возвращает название типа объекта.
     * @return "Болото"
     */
    @Override
    public String getObjectType() {
        return "Болото";
    }

}