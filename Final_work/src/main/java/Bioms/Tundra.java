
package Bioms;

import ObjectInterest_Tundra.Swamp;
import ObjectInterest_Tundra.Geyser;
import ObjectInterest_Tundra.Glacier;
import ObjectInterest_Tundra.OpenWoodland;
import ObjectInterest_Tundra.ReindeerHerdersCamp;

/**
 * Класс, представляющий биом "Тундра".
 * Содержит список типов объектов интереса, характерных для тундровой зоны:
 * {@link Glacier}, {@link Geyser}, {@link OpenWoodland}, {@link ReindeerHerdersCamp}, {@link Swamp}.
 */
public class Tundra extends Biom {
    /**
     * Создаёт экземпляр тундрового биома с предопределёнными объектами интереса.
     */
    public Tundra(){
        super(Glacier.class, Geyser.class, OpenWoodland.class, ReindeerHerdersCamp.class, Swamp.class);
    }
}
