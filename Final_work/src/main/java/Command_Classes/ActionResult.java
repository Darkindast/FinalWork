/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Command_Classes;

import Region_Logic.ObjectInterest;

/**
 *
 * @author Andrey
 */
public class ActionResult {

    private String message;
    private boolean status;
    private ObjectInterest placeOfAction;
    private boolean deleteObjectFromRegion = false;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setObjectInterest(ObjectInterest obj) {
        this.placeOfAction = obj;
    }

    public String getMessage() {
        return message;
    }

    public boolean getStatus() {
        return status;
    }

    public ObjectInterest getPlaceOfAction() {
        return placeOfAction;
    }

    public String getCompleteResult() {
        return message + " в объекте интереса " + placeOfAction.getObjectType();
    }
    
        public boolean isDeleteObjectFromRegion() {
        return deleteObjectFromRegion;
    }

    public void setDeleteObjectFromRegion(boolean deleteObjectFromRegion) {
        this.deleteObjectFromRegion = deleteObjectFromRegion;
    }
}