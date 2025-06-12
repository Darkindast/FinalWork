/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Regions;

import Bioms.Desert;
import java.util.ArrayList;

/**
 *
 * @author Andrey
 */
public class DesertRegion extends BaseRegion {
    private final Desert desert;

    public DesertRegion() {
        this.desert = new Desert() {};
    }

    @Override
    protected ArrayList<Class<?>> getPossibleObjectsInterestList() {
        return desert.getPossibleObjectsInterestList();
    }

    @Override
    protected int getMinNumOfClasses() {
        return 2;
    }

    protected int getMaxNumOfClasses() {
        return 4;
    }
}