
package ObjectInterest_Tundra;

import static Region_Logic.InsideObjectType.*;
import Region_Logic.ObjectInterest;

/**
 * Класс, представляющий объект "Редколесье" в тундре.
 * Содержит деревья, овраги, ручьи и животных (слон, жираф).
 * Валка деревьев, разведение огня и строительство домов разрешены.
 */
public class OpenWoodland extends ObjectInterest {
    
    /**
     * Конструктор объекта "Редколесье".
     * Инициализирует компоненты объекта и разрешения на валку деревьев,
     * разведение огня и строительство домов.
     */
    public OpenWoodland() {
        super(TREE, RAVINE, STREAM, ELEPHANT, GIRAFFE);
        this.isFellTreeAllowed = true;
        this.isFireAllowed = true;
        this.isHouseBuildingAllowed = true;
    }
    
    /**
     * Возвращает строковое представление типа объекта.
     *
     * @return строка "Редколесье"
     */
    @Override
    public String getObjectType() {
        return "Редколесье";
    }

}
