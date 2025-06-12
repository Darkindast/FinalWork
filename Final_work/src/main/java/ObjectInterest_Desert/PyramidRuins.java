/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ObjectInterest_Desert;

import static Region_Logic.InsideObjectType.*;
import Region_Logic.ObjectInterest;

/**
 *
 * @author Andrey
 */
public class PyramidRuins extends ObjectInterest {

    public PyramidRuins() {
        super(PYRAMID, TOMB);
        this.isFireAllowed = true;
        this.isHouseBuildingAllowed = false;

    }
    @Override
    public String getObjectType() {
        return "Руины пирамид";
    }

}