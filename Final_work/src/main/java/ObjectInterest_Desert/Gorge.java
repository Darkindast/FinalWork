
package ObjectInterest_Desert;

import static Region_Logic.InsideObjectType.*;
import Region_Logic.ObjectInterest;

/**
 * Класс, представляющий объект "Ущелье" в пустынном регионе.
 * Ущелье содержит объекты: ручей (STREAM), валун (BOULDER) и кактус (CACTUS).
 * Действия с ущельем ограничены: нельзя валить деревья и строить дома, но можно разводить огонь.
 * 
 * @author
 */
public class Gorge extends ObjectInterest {
    /**
     * Создаёт объект "Ущелье" с установленными разрешениями на действия.
     * В ущелье разрешено разводить костёр, запрещено валить деревья и строить дома.
     */
    public Gorge() {
        super(STREAM, BOULDER, CACTUS);
        this.isFellTreeAllowed = false;
        this.isFireAllowed = true;
        this.isHouseBuildingAllowed = false;
 
    }
    /**
     * Возвращает тип объекта как строку.
     * 
     * @return строка "Ущелье"
     */
    @Override
    public String getObjectType() {
        return "Ущелье";
    }
}
