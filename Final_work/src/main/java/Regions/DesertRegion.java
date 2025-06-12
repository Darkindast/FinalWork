/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Regions;

import Bioms.Desert;
import Region_Logic.ObjectInterest;
import java.util.ArrayList;

/**
 *
 * @author Andrey
 */
public class DesertRegion extends BaseRegion {

    private final Desert desert;

    public DesertRegion() {
        this.desert = new Desert() {
        };
        generateObjectsInterestList();
        for (ObjectInterest obj : getObjectsInterestList()) {
            obj.generateInsideObjectsList();
        }
    }

    @Override
    protected ArrayList<Class<? extends ObjectInterest>> getPossibleObjectsInterestList() {
        return desert.getPossibleObjectsInterestList();
    }

    @Override
    protected int getMinNumOfClasses() {
        return 2;
    }

    @Override
    protected int getMaxNumOfClasses() {
        return 4;
    }

    public String getRegionType() {
        return "Desert";
    }

    @Override
    public void generateUniqueRegion(int index) {
        this.uniqueName = getRegionType() + " region " + index;
    }

}