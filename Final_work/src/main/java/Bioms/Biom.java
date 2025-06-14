
package Bioms;

import Region_Logic.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Абстрактный класс, представляющий биом с набором возможных типов объектов интереса.
 * Используется как базовый класс для различных конкретных биомов.
 */
public abstract class Biom {
    
    /**
     * Список возможных классов объектов интереса, которые могут находиться в данном биоме.
     */
    private final ArrayList<Class<? extends ObjectInterest>> possibleObjectsInterestList = new ArrayList<>();

    /**
     * Конструктор для создания биома с указанными типами объектов интереса.
     *
     * @param classes массив классов, расширяющих {@link ObjectInterest}, которые могут присутствовать в биоме.
     */
    protected Biom(Class<? extends ObjectInterest>... classes) {
        possibleObjectsInterestList.addAll(Arrays.asList(classes));
    }
    /**
     * Возвращает список возможных классов объектов интереса для данного биома.
     *
     * @return список классов, расширяющих {@link ObjectInterest}.
     */
    public ArrayList<Class<? extends ObjectInterest>> getPossibleObjectsInterestList() {
        return possibleObjectsInterestList;
    }
}
