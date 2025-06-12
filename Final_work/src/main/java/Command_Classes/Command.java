/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Command_Classes;

import Region_Logic.Inventory;
import Region_Logic.ObjectInterest;

/**
 *
 * @author Andrey
 */
public interface Command {

    ActionResult execute(ObjectInterest objectInterest, Inventory inventory);

    String getName();

    default boolean checkResources(int numLogs, Inventory inventory) {
        return (inventory.getNumLogs() >= numLogs);
    }
}
