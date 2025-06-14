
package Bioms;

import ObjectInterest_MildCLimate.Forest;
import ObjectInterest_MildCLimate.Meadow;
import ObjectInterest_MildCLimate.Mountain;
import ObjectInterest_MildCLimate.Village;

/**
 * Класс, представляющий биом с умеренным климатом.
 * Содержит список типов объектов интереса, характерных для данной климатической зоны:
 * {@link Forest}, {@link Meadow}, {@link Mountain}, {@link Village}.
 */
public class MildClimate extends Biom {
    /**
     * Создаёт экземпляр биома с умеренным климатом и предопределёнными объектами интереса.
     */
    public MildClimate() {
        super(Forest.class, Meadow.class, Mountain.class, Village.class);
    }
}
