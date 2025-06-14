
package Regions;

import Region_Logic.ObjectInterest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Абстрактный класс, представляющий базовый регион.
 * Содержит список объектов интереса, методы для генерации объектов и получения информации о регионе.
 */
public abstract class BaseRegion {

       /** Список объектов интереса, присутствующих в регионе */
    private final ArrayList<ObjectInterest> objectsInterestList = new ArrayList<>();
    
    /** Генератор случайных чисел для выбора объектов интереса */
    private final Random random = new Random();
    
    /** Уникальное имя региона */
    String uniqueName = null;
    
    /** Изображение региона */
    protected BufferedImage image;
    

    /**
     * Метод, который должен быть реализован в подклассе.
     * Возвращает список классов возможных объектов интереса для данного региона.
     *
     * @return список классов объектов интереса
     */
    protected abstract ArrayList<Class<? extends ObjectInterest>> getPossibleObjectsInterestList();

    /**
     * Метод, который должен быть реализован в подклассе.
     * Возвращает минимальное количество объектов интереса, которые могут быть в регионе.
     *
     * @return минимальное количество объектов интереса
     */
    protected abstract int getMinNumOfClasses();

    /**
     * Метод, который должен быть реализован в подклассе.
     * Возвращает максимальное количество объектов интереса, которые могут быть в регионе.
     *
     * @return максимальное количество объектов интереса
     */
    protected abstract int getMaxNumOfClasses();

    /**
     * Генерирует список объектов интереса для региона.
     * Количество объектов выбирается случайным образом в диапазоне между минимальным и максимальным.
     * Объекты создаются с помощью рефлексии, используя конструктор без параметров.
     */
    public void generateObjectsInterestList() {
        ArrayList<Class<? extends ObjectInterest>> possibleObjectsInterestList = getPossibleObjectsInterestList();
        int size = possibleObjectsInterestList.size();

        int numOfClasses = random.nextInt(getMaxNumOfClasses() - getMinNumOfClasses() + 1) + getMinNumOfClasses();

        for (int i = 0; i < numOfClasses; i++) {
            int randomIndex = random.nextInt(size);
            Class<? extends ObjectInterest> classs = possibleObjectsInterestList.get(randomIndex);
            try {
                ObjectInterest obj = classs.getDeclaredConstructor().newInstance();
                objectsInterestList.add(classs.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                ex.getStackTrace();
            }

        }

    }

    /**
     * Возвращает список объектов интереса, присутствующих в регионе.
     *
     * @return список объектов интереса
     */
    public ArrayList<ObjectInterest> getObjectsInterestList() {
        return objectsInterestList;
    }

    /**
     * Абстрактный метод, возвращающий тип региона в виде строки.
     * Должен быть реализован в подклассах.
     *
     * @return тип региона
     */
    public abstract String getRegionType();

    /**
     * Генерирует уникальное имя региона на основе его типа и переданного индекса.
     *
     * @param index индекс региона для уникализации имени
     */
    public void generateUniqueRegion(int index) {
        this.uniqueName = getRegionType() + " region " + index;
    }

    /**
     * Возвращает уникальное имя региона.
     *
     * @return уникальное имя региона
     */
    public String getUniqueName() {
        return uniqueName;
    }

    /**
     * Абстрактный метод для получения изображения региона.
     * Может выбросить IOException при загрузке изображения.
     *
     * @return изображение региона
     * @throws IOException если произошла ошибка при загрузке изображения
     */
    public abstract BufferedImage getImage() throws IOException;
}