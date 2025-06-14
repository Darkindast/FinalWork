
package ObjectInterest_Tundra;

import static Region_Logic.InsideObjectType.*;
import Region_Logic.ObjectInterest;

/**
 * Класс, представляющий стоянку оленеводов в тундре.
 * Содержит оленей, оленеводов, палатки и деревья.
 * Разрешена валка деревьев и разведение огня, но строительство домов запрещено.
 */
public class ReindeerHerdersCamp extends ObjectInterest {

     /**
     * Конструктор объекта "Стоянка оленеводов".
     */
    public ReindeerHerdersCamp() {
        super(REINDEER, REINDEER_HERDER, TENT, TREE);
        this.isFellTreeAllowed = true;
        this.isFireAllowed = true;
        this.isHouseBuildingAllowed = false;
    }
    /**
     * Возвращает название типа объекта.
     *
     * @return строка "Стоянка оленеводов"
     */
    @Override
    public String getObjectType() {
        return "Стоянка оленеводов";
    }

}