/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Command_Classes;

import Region_Logic.Inventory;
import Region_Logic.ObjectInterest;
import java.util.Random;

/**
 *
 * @author Andrey
 */
public class MakeFireCommand implements Command {

    private ActionResult actionResult = new ActionResult();

    @Override
    public ActionResult execute(ObjectInterest obj, Inventory inventory) {
        boolean approveStatus = obj.getFireAllowedStatus();
        String message;
        if (approveStatus && checkResources(1, inventory)) {
            obj.addToInsideObjectsList(InsideObjectType.BONFIRE);
            if (burnObjectInterest()) {
                message = "Вы развели костер и сожгли объект интереса в этом регионе!";
            } else {
                message = "Вы развели костер!";
            }
            inventory.useInventory(1);
            actionResult.setStatus(true);
        } else {
            if (!approveStatus) {
                message = "Развести костер тут нельзя!";
            } else {
                message = "У вас недостаточно ресурсов!";
            }
            actionResult.setStatus(false);
        }
        actionResult.setMessage(message);
        actionResult.setObjectInerest(obj);
        return actionResult;
    }

    @Override
    public String getName() {
        return "Развести костер";
    }

    public boolean burnObjectInterest() {
        Random random = new Random();
        double probability = 0.01;
        return (random.nextDouble() <= probability);
    }
}
