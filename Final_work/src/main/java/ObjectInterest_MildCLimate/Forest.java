
package ObjectInterest_MildCLimate;

import static Region_Logic.InsideObjectType.*;
import Region_Logic.ObjectInterest;

/**
 * Класс, представляющий объект "Лес" в регионе со смешанным климатом.
 * Объект включает деревья, медведей, цветы, кусты, зайцев и лосей.
 * Валка деревьев, разведение огня и строительство домов разрешены.
 */
public class Forest extends ObjectInterest {
    
    /**
     * Конструктор объекта "Лес".
     * Инициализирует компоненты объекта и устанавливает разрешения:
     * валка деревьев, разведение огня и строительство домов разрешены.
     */
    public Forest() {
        super(TREE, BEAR, FLOWERS, BUSH, HARE, MOOSE);
        this.isFellTreeAllowed = true;
        this.isFireAllowed = true;
        this.isHouseBuildingAllowed = true;
    }
    
    /**
     * Возвращает строковое представление типа объекта.
     * 
     * @return строка "Лес"
     */
    @Override
    public String getObjectType() {
        return "Лес";
    }
}
