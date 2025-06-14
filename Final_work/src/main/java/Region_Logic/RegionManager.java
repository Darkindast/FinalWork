
package Region_Logic;

import Regions.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Класс для управления списком регионов.
 * Позволяет создавать регионы, перемещаться между ними и получать доступ к текущим регионам.
 */
public class RegionManager {

    /** Список всех регионов */
    private final ArrayList<BaseRegion> regions = new ArrayList<>();
    /** Текущая позиция в списке регионов */
    private int currentPosition;

    /**
     * Конструктор RegionManager.
     * Инициализирует текущую позицию в регионах в значение 0.
     */
    public RegionManager() {
        this.currentPosition = 0;
    }

    /**
     * Возвращает регион по заданной позиции в списке.
     *
     * @param regionPosition индекс региона в списке
     * @return объект BaseRegion по указанной позиции
     */
    public BaseRegion getRegion(int regionPosition) {
        return regions.get(regionPosition);
    }

    /**
     * Возвращает позицию региона в списке.
     *
     * @param region объект BaseRegion, позицию которого нужно найти
     * @return индекс региона в списке, или -1 если регион не найден
     */
    public int getRegionPosition(BaseRegion region) {
        return regions.indexOf(region);
    }

    /**
     * Возвращает список доступных для перехода регионов,
     * основываясь на текущем регионе (следующий и предыдущий регионы).
     *
     * @param currentRegion текущий регион
     * @return список из двух регионов: следующего и предыдущего
     */
    public ArrayList<BaseRegion> checkAvailableRegions(BaseRegion currentRegion) {
        this.currentPosition = regions.indexOf(currentRegion);
        ArrayList<BaseRegion> availableRegions = new ArrayList<>();
        availableRegions.add(moveNext());
        availableRegions.add(movePrevious());
        return availableRegions;
    }

    /**
     * Возвращает следующий регион по отношению к текущей позиции.
     * При достижении конца списка происходит переход к первому региону (циклично).
     *
     * @return следующий BaseRegion
     */
    public BaseRegion moveNext() {
        return getRegion((currentPosition + 1) % regions.size());
    }

    /**
     * Возвращает предыдущий регион по отношению к текущей позиции.
     * При достижении начала списка происходит переход к последнему региону (циклично).
     *
     * @return предыдущий BaseRegion
     */
    public BaseRegion movePrevious() {
        return getRegion((currentPosition - 1 + regions.size()) % regions.size());
    }

    /**
     * Генерирует регионы заданных типов и количеств.
     * Создаются объекты классов TundraRegion, DesertRegion и MildClimateRegion,
     * каждому присваивается уникальный номер.
     *
     * @param countTundra количество регионов тундры для генерации
     * @param countDesert количество регионов пустыни для генерации
     * @param countMildClimate количество регионов мягкого климата для генерации
     * @throws IOException в случае ошибки при генерации регионов
     */
    public void generateRegions(int countTundra, int countDesert, int countMildClimate) throws IOException {
        for (int i = 0; i < countTundra; i++) {
            BaseRegion region = new TundraRegion();
            region.generateUniqueRegion(i + 1);
            regions.add(region);
        }
        for (int i = 0; i < countDesert; i++) {
            BaseRegion region = new DesertRegion();
            region.generateUniqueRegion(i + 1);
            regions.add(region);
        }
        for (int i = 0; i < countMildClimate; i++) {
            BaseRegion region = new MildClimateRegion();
            region.generateUniqueRegion(i + 1);
            regions.add(region);
        }
    }

    /**
     * Возвращает список всех регионов.
     *
     * @return список объектов BaseRegion
     */
    public ArrayList<BaseRegion> getRegions() {
        return regions;
    }
}