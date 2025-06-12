/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Region_Logic;

/**
 *
 * @author Andrey
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
    HALOPHYTE("Галофит");
    
    
    private final String value;

    InsideObjectType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}