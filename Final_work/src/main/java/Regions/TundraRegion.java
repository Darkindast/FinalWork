/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Regions;

import Bioms.Tundra;
import java.util.ArrayList;

/**
 *
 * @author Andrey
 */
public class TundraRegion extends BaseRegion {
    private final Tundra tundra;
    public TundraRegion() {
        this.tundra = new Tundra() {};
    }
    @Override
    protected ArrayList<Class<?>> getPossibleObjectsInterestList() {
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
}