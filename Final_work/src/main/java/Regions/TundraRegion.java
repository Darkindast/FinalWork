
package Regions;

import Bioms.Tundra;
import GUI.ResourceLoader;
import Region_Logic.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс, представляющий регион тундры.
 * Наследует BaseRegion и реализует методы для работы с объектами интереса тундры.
 */
public class TundraRegion extends BaseRegion {

    /** Объект тундры, содержащий возможные объекты интереса для данного региона */
    private final Tundra tundra;

    /**
     * Конструктор региона тундры.
     * Инициализирует объект тундры, генерирует список объектов интереса и их содержимое.
     *
     * @throws IOException если возникла ошибка при загрузке ресурсов
     */
    public TundraRegion() throws IOException {
        this.tundra = new Tundra() {
        };
        generateObjectsInterestList();
        for (ObjectInterest obj : getObjectsInterestList()) {
            obj.generateInsideObjectsList();
        }
    }

    /**
     * Возвращает список классов возможных объектов интереса, которые могут присутствовать в регионе тундры.
     *
     * @return список классов объектов интереса
     */
    @Override
    protected ArrayList<Class<? extends ObjectInterest>> getPossibleObjectsInterestList() {
        return tundra.getPossibleObjectsInterestList();
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
     * @return строка "Tundra"
     */
    @Override
    public String getRegionType() {
        return "Tundra";
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
     * Возвращает изображение региона тундры.
     *
     * @return изображение региона
     * @throws IOException если возникла ошибка при загрузке изображения
     */
    @Override
    public BufferedImage getImage() throws IOException {
        return ResourceLoader.getInstance().getImage("tundra.jpg");
    }
}