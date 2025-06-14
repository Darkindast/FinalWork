
package ObjectInterest_MildCLimate;

import static Region_Logic.InsideObjectType.*;
import Region_Logic.ObjectInterest;

/**
 * Класс, представляющий объект "Гора" в регионе со смешанным климатом.
 * Включает в себя скалы, деревья, ручьи, змей и ящериц.
 * Валка деревьев, разведение огня и строительство домов разрешены.
 */
public class Mountain extends ObjectInterest {

    /**
     * Конструктор объекта "Гора".
     * Инициализирует компоненты объекта и устанавливает разрешения на валку деревьев,
     * разведение огня и строительство домов.
     */
    public Mountain() {
        super(CLIFF, TREE, STREAM, SNAKE, LIZARD);
        this.isFellTreeAllowed = true;
        this.isFireAllowed = true;
        this.isHouseBuildingAllowed = true;
    }
    /**
     * Возвращает строковое представление типа объекта.
     *
     * @return строка "Гора"
     */
    @Override
    public String getObjectType() {
        return "Гора";
    }

}