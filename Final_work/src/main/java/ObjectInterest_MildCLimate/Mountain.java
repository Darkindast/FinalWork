/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjectInterest_MildCLimate;

import static Region_Logic.InsideObjectType.*;
import Region_Logic.ObjectInterest;

/**
 *
 * @author Andrey
 */
public class Mountain extends ObjectInterest {

    public Mountain() {
        super(CLIFF, TREE, STREAM, SNAKE, LIZARD);
        this.isFireAllowed = true;
        this.isHouseBuildingAllowed = true;
    }
    @Override
    public String getObjectType() {
        return "Гора";
    }

}