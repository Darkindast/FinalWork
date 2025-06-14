
package Bioms;

import ObjectInterest_Desert.Gorge;
import ObjectInterest_Desert.Oasis;
import ObjectInterest_Desert.PyramidRuins;
import ObjectInterest_Desert.Saline;

/**
 * Класс, представляющий биом типа "Пустыня".
 * Содержит список типов объектов интереса, характерных для пустыни:
 * {@link Gorge}, {@link Oasis}, {@link PyramidRuins}, {@link Saline}.
 */
public class Desert extends Biom {
    /**
     * Создаёт экземпляр пустынного биома с предопределёнными объектами интереса.
     */
    public Desert() {
        super(Gorge.class, Oasis.class, PyramidRuins.class, Saline.class);
    }
}
