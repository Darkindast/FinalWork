
package ObjectInterest_Desert;

import static Region_Logic.InsideObjectType.*;
import Region_Logic.ObjectInterest;

/**
 * Класс, представляющий объект "Солончак" в пустынном регионе.
 * Объект включает компоненты: озеро, куст, кактус и галофиты.
 * Валка деревьев и строительство домов не разрешены, а разведение огня разрешено.
 */
public class Saline extends ObjectInterest {
    
    /**
     * Конструктор объекта "Солончак".
     * Инициализирует компоненты объекта и устанавливает разрешения:
     * валка деревьев и строительство домов запрещены, разведение огня разрешено.
     */
    public Saline() {
        super(LAKE, BUSH, CACTUS, HALOPHYTE);
        this.isFellTreeAllowed = false;
        this.isFireAllowed = true;
        this.isHouseBuildingAllowed = false;
    }
    
    /**
     * Возвращает строковое представление типа объекта.
     * 
     * @return строка "Солончак"
     */
    @Override
    public String getObjectType() {
        return "Солончак";
    }
}