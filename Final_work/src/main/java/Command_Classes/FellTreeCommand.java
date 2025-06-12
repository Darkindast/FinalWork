/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Command_Classes;

import Region_Logic.ObjectInterest;
import Region_Logic.*;

/**
 *
 * @author Andrey
 */
public class FellTreeCommand implements Command {
    
    private ActionResult actionResult = new ActionResult();
    
    @Override
    public ActionResult execute(ObjectInterest obj, Inventory inventory) {
        boolean approveStatus = obj.searchForTree();
        String message;
        if (approveStatus) {
            message = "Вы срубили дерево!";
            obj.removeFromInsideObjectsList(InsideObjectType.TREE);
            inventory.addToInventory(1);
            actionResult.setStatus(true);
        } else {
            message = "Срубить дерево тут нельзя!";
            actionResult.setStatus(false);
        }
        actionResult.setMessage(message);
        actionResult.setObjectInerest(obj);
        return actionResult;
    }
    
    @Override
    public String getName() {
        return "Срубить дерево";
    }
}
