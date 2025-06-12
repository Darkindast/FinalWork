/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Regions;

import Bioms.Tundra;
import Region_Logic.*;
import java.util.ArrayList;

/**
 *
 * @author Andrey
 */
public class TundraRegion extends BaseRegion {

    private final Tundra tundra;

    public TundraRegion() {
        this.tundra = new Tundra() {
        };
        generateObjectsInterestList();
        for (ObjectInterest obj : getObjectsInterestList()) {
            obj.generateInsideObjectsList();
        }
    }

    @Override
    protected ArrayList<Class<? extends ObjectInterest>> getPossibleObjectsInterestList() {
        return tundra.getPossibleObjectsInterestList();
    }

    @Override
    protected int getMinNumOfClasses() {
        return 3;
    }

    @Override
    protected int getMaxNumOfClasses() {
        return 5;
    }

    @Override
    public String getRegionType() {
        return "Tundra";
    }

    @Override
    public void generateUniqueRegion(int index) {
        this.uniqueName = getRegionType() + " region " + index;
    }
}