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
public class ReindeerHerdersCamp extends ObjectInterest {

    public ReindeerHerdersCamp() {
        super(REINDEER, REINDEER_HERDER, TENT, TREE);
        this.isFireAllowed = true;
        this.isHouseBuildingAllowed = false;
    }
    @Override
    public String getObjectType() {
        return "Стоянка оленеводов";
    }

}