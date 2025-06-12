/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Structure_Classes;

import java.util.ArrayList;

/**
 *
 * @author Andrey
 */
public abstract class ObjectInterest {
    ArrayList<InsideObjectType> possibleInsideObjects = new ArrayList<>();
    ArrayList<InsideObjectType> insideObjectsList = new ArrayList<>();
    boolean isFireAllowed;
    boolean isHouseBuildingAllowed;
    boolean isTreeFellingAllowed;
}
