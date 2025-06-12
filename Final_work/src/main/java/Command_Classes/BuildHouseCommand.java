/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Command_Classes;


import Region_Logic.*;

/**
 *
 * @author Andrey
 */
public class BuildHouseCommand implements Command {

    private ActionResult actionResult= new ActionResult();
    private Player player = new Player();
    @Override
    public ActionResult execute(ObjectInterest obj) {
       boolean approveStatus = obj.getHouseBuildingAllowedStatus();
       if (approveStatus == true){
           actionResult.setMessage("Вы построили дерево!");
           checkResources();
       }
       else{
           actionResult.setMessage("Построить дом в этом месте нельзя!");
           actionResult.setStatus(false);
       }
       actionResult.setObjectInerest(obj);
       return actionResult;
    }
    public void checkResources(){
       if (player.getInventory().getNumLogs()>=10){
           player.getInventory().useInventory(10);
             actionResult.setStatus(true);
       }
       else{
           actionResult.setMessage("Построить дом невозможно! У вас нет ресурсов!");
             actionResult.setStatus(false);
       }
       
   }
    
}
