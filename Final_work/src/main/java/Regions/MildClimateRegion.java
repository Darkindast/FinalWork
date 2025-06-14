
package Regions;

import Bioms.*;
import GUI.ResourceLoader;
import Region_Logic.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс, представляющий регион с умеренным климатом.
 * Наследует BaseRegion и реализует методы для работы с объектами интереса умеренного климата.
 */
public class MildClimateRegion extends BaseRegion {

    /** Объект умеренного климата, содержащий возможные объекты интереса для данного региона */
    private final MildClimate mildClimate;

    /**
     * Конструктор региона с умеренным климатом.
     * Инициализирует объект умеренного климата, генерирует список объектов интереса и их содержимое.
     */
    public MildClimateRegion() {
        this.mildClimate = new MildClimate() {
        };
        generateObjectsInterestList();
        for (ObjectInterest obj : getObjectsInterestList()) {
            obj.generateInsideObjectsList();
        }

    }

    /**
     * Возвращает список классов возможных объектов интереса, которые могут присутствовать в регионе с умеренным климатом.
     *
     * @return список классов объектов интереса
     */
    @Override
    protected ArrayList<Class<? extends ObjectInterest>> getPossibleObjectsInterestList() {
        return mildClimate.getPossibleObjectsInterestList();
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
     * @return строка "MildClimate"
     */
    @Override
    public String getRegionType() {
        return "MildClimate";
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
     * Возвращает изображение региона с умеренным климатом.
     *
     * @return изображение региона
     * @throws IOException если возникла ошибка при загрузке изображения
     */
    @Override
    public BufferedImage getImage() throws IOException {
        return ResourceLoader.getInstance().getImage("mildClimate.jpg");
    }
}