/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Regions;

import Bioms.MildClimate;
import java.util.ArrayList;

/**
 *
 * @author Andrey
 */
public class MildClimateRegion extends BaseRegion{
    private final MildClimate mildClimate;

    public MildClimateRegion() {
        this.mildClimate = new MildClimate() {
        };
    }

    @Override
    protected ArrayList<Class<?>> getPossibleObjectsInterestList() {
        return mildClimate.getPossibleObjectsInterestList();
    }

    @Override
    protected int getMinNumOfClasses() {
        return 3;
    }

   
    protected int getMaxNumOfClasses() {
        return 5;
    }
}