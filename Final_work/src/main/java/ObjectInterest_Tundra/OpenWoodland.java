/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjectInterest_Tundra;

import static Region_Logic.InsideObjectType.*;
import Region_Logic.ObjectInterest;

/**
 *
 * @author Andrey
 */
public class OpenWoodland extends ObjectInterest {

    public OpenWoodland() {
        super(TREE, RAVINE, STREAM, ELEPHANT, GIRAFFE);
        this.isFireAllowed = true;
        this.isHouseBuildingAllowed = true;
    }
    @Override
    public String getObjectType() {
        return "Редколесье";
    }

}
