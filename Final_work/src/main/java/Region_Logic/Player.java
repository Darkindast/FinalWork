/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Region_Logic;

import Command_Classes.*;
import Regions.*;
import java.util.ArrayList;

/**
 *
 * @author Andrey
 */
public class Player {

    private Inventory inventory;
    private ArrayList<ActionResult> actionHistory;
    private BaseRegion currentRegion;

    public Player() {
        this.inventory = new Inventory(0);
    }
    
    public Inventory getInventory() {
        return inventory;
    }

    public ArrayList<ActionResult> getActionList() {
        return actionHistory;
    }

    public void setInventory(Inventory invent) {
        this.inventory = invent;
    }

    public void setActionList(ArrayList<ActionResult> history) {
        this.actionHistory = history;
    }

    public ActionResult makeAction(ObjectInterest obj, Command action) {
        return action.execute(obj, inventory);
    }

    public void setCurrentRegion(BaseRegion region) {
        this.currentRegion = region;
    }

    public BaseRegion getCurrentRegion() {
        return currentRegion;
    }
}