/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Region_Logic;

import Regions.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Andrey
 */
public class RegionManager {

    private final ArrayList<BaseRegion> regions = new ArrayList<>();
    private int currentPosition;

    public RegionManager() {
        this.currentPosition = 0;
    }

    public BaseRegion getRegion(int regionPosition) {
        return regions.get(regionPosition);
    }

    public int getRegionPosition(BaseRegion region) {
        return regions.indexOf(region);
    }

    public ArrayList<BaseRegion> checkAvailableRegions(BaseRegion currentRegion) {
        this.currentPosition = regions.indexOf(currentRegion);
        ArrayList<BaseRegion> availableRegions = new ArrayList<>();
        availableRegions.add(moveNext());
        availableRegions.add(movePrevious());
        return availableRegions;
    }

    public BaseRegion moveNext() {
        return getRegion((currentPosition + 1) % regions.size());
    }

    public BaseRegion movePrevious() {
        return getRegion((currentPosition - 1 + regions.size()) % regions.size());
    }

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

    public ArrayList<BaseRegion> getRegions() {
        return regions;
    }
}