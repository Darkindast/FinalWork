/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bioms;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Andrey
 */
public abstract class Biom {

    private final ArrayList<Class<?>> possibleObjectsInterestList = new ArrayList<>();

    protected Biom(Class<?>... classes) {
        possibleObjectsInterestList.addAll(Arrays.asList(classes));
    }

    public ArrayList<Class<?>> getPossibleObjectsInterestList() {
        return possibleObjectsInterestList;
    }
}
