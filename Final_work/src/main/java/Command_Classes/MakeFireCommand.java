/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Command_Classes;

import GUI.ResourceLoader;
import Region_Logic.*;
//import Region_Logic.Inventory;
import Region_Logic.ObjectInterest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Andrey
 */
public class MakeFireCommand implements Command {

    private ActionResult actionResult = new ActionResult();

    @Override
    public ActionResult execute(ObjectInterest obj) {
        boolean approveStatus = obj.getFireAllowedStatus();
        String message;
        if (approveStatus) {
            obj.addToInsideObjectsList(InsideObjectType.BONFIRE);
            if (burnObjectInterest()) {
                message = "Вы сожгли объект интереса в этом регионе!";
                obj.setAliveStatus(false);
                
                for (InsideObjectType insideObj : new ArrayList<>(obj.getInsideObjects())) {
                    obj.removeFromInsideObjectsList(insideObj);
                }
                actionResult.setDeleteObjectFromRegion(true);
            } else {
                message = "Вы развели костер!";
            }
            actionResult.setStatus(true);
        } else {
            
            message = "Развести костер тут нельзя!";
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
        double probability = 0.1;
        return (random.nextDouble() <= probability);
    }

    @Override
    public BufferedImage getImage() throws IOException {
        return ResourceLoader.getInstance().getImage("fire.jpg");
    }
}