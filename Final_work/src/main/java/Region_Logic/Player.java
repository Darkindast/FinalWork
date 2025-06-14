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

    private ArrayList<ActionResult> actionHistory;
    private BaseRegion currentRegion;

    public Player() {

    }

    public ArrayList<ActionResult> getActionList() {
        return actionHistory;
    }

    public void setActionList(ArrayList<ActionResult> history) {
        this.actionHistory = history;
    }

    public ActionResult makeAction(ObjectInterest obj, Command action) {
        return action.execute(obj);
    }

    public void setCurrentRegion(BaseRegion region) {
        this.currentRegion = region;
    }

    public BaseRegion getCurrentRegion() {
        return currentRegion;
    }
}