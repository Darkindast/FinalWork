/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Command_Classes;

import Region_Logic.*;
import Region_Logic.Inventory;
import Region_Logic.ObjectInterest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

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
                obj.setAliveStatus(false);
                for (InsideObjectType insideObj : new ArrayList<>(obj.getInsideObjects())) {
                                  obj.removeFromInsideObjectsList(insideObj);
   }

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
        actionResult.setObjectInterest(obj);
        return actionResult;
    }

    public static String getName() {
        return "Развести костер";
    }

    public boolean burnObjectInterest() {
        Random random = new Random();
        double probability = 0.01;
        return (random.nextDouble() <= probability);
    }

    @Override
    public BufferedImage getImage() throws IOException {
        return ImageIO.read(new File("src\\main\\resources\\fire.jpg"));
    }
}