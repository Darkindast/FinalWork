/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Region_Logic;

import Command_Classes.*;
import java.util.ArrayList;

/**
 *
 * @author Andrey
 */
public class Player {
   private Inventory inventory;
    private ArrayList<ActionResult> actionHistory;
    private Command buildHouseAction;
    private Command fellTreeAction;
    private Command makeFireAction;
    
    public Inventory getInventory(){
        return inventory;
    }
    public ArrayList<ActionResult> getActionList(){
        return actionHistory;
    }
    public void setInventory(Inventory invent){
        this.inventory = invent;
    }
    public void setActionList(ArrayList<ActionResult> history){
        this.actionHistory = history;
    }
    public ActionResult buildHouse(ObjectInterest obj){
        return buildHouseAction.execute(obj, this);
    }
    public ActionResult fellTree(ObjectInterest obj){
        return fellTreeAction.execute(obj, this);
    }
    public ActionResult makeFire(ObjectInterest obj){
        return makeFireAction.execute(obj, this);
    }
    public ActionResult makeAction(ObjectInterest obj, Command action){
        return action.execute(obj, this);
    }
}