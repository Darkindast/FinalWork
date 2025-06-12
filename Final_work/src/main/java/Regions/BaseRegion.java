/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Regions;

import Region_Logic.ObjectInterest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Andrey
 */
public abstract class BaseRegion {

    private final ArrayList<ObjectInterest> objectsInterestList = new ArrayList<>();
    private final Random random = new Random();
    String uniqueName = null;
    protected BufferedImage image;

    protected abstract ArrayList<Class<? extends ObjectInterest>> getPossibleObjectsInterestList();

    protected abstract int getMinNumOfClasses();

    protected abstract int getMaxNumOfClasses();

    public void generateObjectsInterestList() {
        ArrayList<Class<? extends ObjectInterest>> possibleObjectsInterestList = getPossibleObjectsInterestList();
        int size = possibleObjectsInterestList.size();

        int numOfClasses = random.nextInt(getMaxNumOfClasses() - getMinNumOfClasses() + 1) + getMinNumOfClasses();

        for (int i = 0; i < numOfClasses; i++) {
            int randomIndex = random.nextInt(size);
            Class<? extends ObjectInterest> clazz = possibleObjectsInterestList.get(randomIndex);
            try {
                ObjectInterest obj = clazz.getDeclaredConstructor().newInstance();
                objectsInterestList.add(clazz.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                ex.getStackTrace();
            }

        }

    }

    public ArrayList<ObjectInterest> getObjectsInterestList() {
        return objectsInterestList;
    }

    public abstract String getRegionType();

    public void generateUniqueRegion(int index) {
        this.uniqueName = getRegionType() + " region " + index;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public abstract BufferedImage getImage() throws IOException;
}