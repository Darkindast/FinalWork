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
    ArrayList<InsideObjectType> possibleInsideObjects = new ArrayList<>();
    ArrayList<InsideObjectType> insideObjectsList = new ArrayList<>();
    boolean isFireAllowed=true;
    boolean isHouseBuildingAllowed=true;
    boolean isTreeFellingAllowed=true;
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
    
}