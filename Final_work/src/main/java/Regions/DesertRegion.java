
package Regions;

import Bioms.Desert;
import GUI.ResourceLoader;
import Region_Logic.ObjectInterest;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс, представляющий регион пустыни.
 * Наследует BaseRegion и реализует методы для работы с объектами интереса пустыни.
 */
public class DesertRegion extends BaseRegion {

    /** Объект пустыни, содержащий возможные объекты интереса для данного региона */
    private final Desert desert;

    /**
     * Конструктор региона пустыни.
     * Инициализирует пустыню, генерирует список объектов интереса и вызывает генерацию их содержимого.
     *
     * @throws IOException если возникает ошибка при инициализации пустыни
     */
    public DesertRegion() throws IOException {
        this.desert = new Desert() {
        };
        generateObjectsInterestList();
        for (ObjectInterest obj : getObjectsInterestList()) {
            obj.generateInsideObjectsList();
        }
    }

    /**
     * Возвращает список классов возможных объектов интереса, которые могут присутствовать в пустыне.
     *
     * @return список классов объектов интереса
     */
    @Override
    protected ArrayList<Class<? extends ObjectInterest>> getPossibleObjectsInterestList() {
        return desert.getPossibleObjectsInterestList();
    }

    /**
     * Возвращает минимальное количество объектов интереса, которые должны присутствовать в регионе.
     *
     * @return минимальное количество объектов интереса
     */
    @Override
    protected int getMinNumOfClasses() {
        return 3;
    }

    /**
     * Возвращает максимальное количество объектов интереса, которые могут присутствовать в регионе.
     *
     * @return максимальное количество объектов интереса
     */
    @Override
    protected int getMaxNumOfClasses() {
        return 6;
    }

    /**
     * Возвращает тип региона.
     *
     * @return строка "Desert"
     */
    @Override
    public String getRegionType() {
        return "Desert";
    }

    /**
     * Генерирует уникальное имя региона на основе его типа и заданного индекса.
     *
     * @param index индекс региона для уникализации имени
     */
    @Override
    public void generateUniqueRegion(int index) {
        this.uniqueName = getRegionType() + " region " + index;
    }

    /**
     * Возвращает изображение региона пустыни.
     *
     * @return изображение региона пустыни
     */
    @Override
    public BufferedImage getImage() {
        return ResourceLoader.getInstance().getImage("desert.jpg");
    }
}