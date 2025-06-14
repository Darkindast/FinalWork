
package Region_Logic;

/**
 * Перечисление InsideObjectType содержит типы объектов, которые могут находиться внутри регионов.
 * Каждый тип связан с русским названием объекта для удобства отображения.
 */
public enum InsideObjectType {
   CAVE("Пещера"),
    POLAR_BEAR("Полярный медведь"),
    SNOW_DUNE("Снежный бархан"),
    TREE("Дерево"),
    RAVINE("Овраг"),
    STREAM("Ручей"),
    ELEPHANT("Слон"),
    GIRAFFE("Жираф"),
    REINDEER("Олень"),
    REINDEER_HERDER("Оленевод"),
    TENT("Палатка"),
    BUSH("Кустарник"),
    HILL("Холм"),
    THERMAL_POOL("Термальный бассейн"),
    LAKE("Озеро"),
    FLOWERS("Цветы"),
    HAYSTACK("Стог сена"),
    HARE("Заяц"),
    BEAR("Медведь"),
    MOOSE("Лось"),
    HOUSE("Дом"),
    PERSON("Житель"),
    SHOP("Магазин"),
    CHURCH("Церковь"),
    RIVER("Река"),
    WATERFALL("Водопад"),
    PALM_TREE("Пальма"),
    OLIVE_TREE("Оливковое дерево"),
    PASTURE("Пастбище"),
    BOULDER("Валун"),
    CACTUS("Кактус"),
    PYRAMID("Пирамида"),
    TOMB("Гробница"),
    CLIFF("Скала"),
    SNAKE("Змея"),
    LIZARD("Ящерица"),
    HALOPHYTE("Галофит"),
    BONFIRE("Костер"),
    STUMP("Пень");
    
    private final String value;

    /**
     * Конструктор перечисления.
     *
     * @param value Название объекта
     */
    InsideObjectType(String value) {
        this.value = value;
    }
    
    /**
     * Возвращает русское название объекта.
     *
     * @return строка с названием объекта
     */
    @Override
    public String toString() {
        return value;
    }
}