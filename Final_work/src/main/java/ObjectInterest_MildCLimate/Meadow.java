
package ObjectInterest_MildCLimate;

import static Region_Logic.InsideObjectType.*;
import Region_Logic.ObjectInterest;

/**
 * Класс, представляющий объект "Луг" в регионе со смешанным климатом.
 * Объект включает кусты, озеро, ручей, пастбище и скирду сена.
 * Валка деревьев запрещена, разведение огня и строительство домов разрешены.
 */
public class Meadow extends ObjectInterest {
    
    /**
     * Конструктор объекта "Луг".
     * Инициализирует компоненты объекта и устанавливает разрешения:
     * валка деревьев запрещена, разведение огня и строительство домов разрешены.
     */
    public Meadow() {
        super(BUSH, LAKE, STREAM, PASTURE, HAYSTACK);
        this.isFellTreeAllowed = false;
        this.isFireAllowed = true;
        this.isHouseBuildingAllowed = true;
    }
    
    /**
     * Возвращает строковое представление типа объекта.
     * 
     * @return строка "Луг"
     */
    @Override
    public String getObjectType() {
        return "Луг";
    }

}
