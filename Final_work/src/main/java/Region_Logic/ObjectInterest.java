/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Region_Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Andrey
 */
public abstract class ObjectInterest {
    private ArrayList<InsideObjectType> possibleInsideObjects = new ArrayList<>();
    private ArrayList<InsideObjectType> insideObjectsList = new ArrayList<>();
    private boolean isFireAllowed=true;
    private boolean isHouseBuildingAllowed=true;
    private boolean isTreeFellingAllowed=true;
    private boolean isAlive = true;
    private final Random random = new Random();    
    protected ObjectInterest(InsideObjectType... types) {
        possibleInsideObjects.addAll(Arrays.asList(types));
    }
    public void generateInsideObjectsList() {
        int size = possibleInsideObjects.size();
        for (InsideObjectType type: possibleInsideObjects) {
            int randomIndex = random.nextInt(5) + 1 ;
            for (int i=1;i<randomIndex;i++){
                insideObjectsList.add(type);
            }
        }
    }
    public ArrayList<InsideObjectType> getPossibleInsideObjects(){
        return possibleInsideObjects;
    }
    public boolean getFireAllowedStatus(){
        return isFireAllowed;
    }
    public boolean getHouseBuildingAllowedStatus(){
        return isHouseBuildingAllowed;
    }
    public boolean getTreeFellingAllowedStatus(){
        return isTreeFellingAllowed;
    }
    public boolean getAliveStatus(){
        return isAlive;
    }
}