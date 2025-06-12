/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Region_Logic;

/**
 *
 * @author Andrey
 */
public class Inventory {
  private int numLogs;
  public Inventory(int numLogs){
    this.numLogs = numLogs;
}
  public void addToInventory(int countLogs){
      numLogs += countLogs;
  }
  public void useInventory(){
      numLogs=-1;
  }
  public int getNumLogs(){
      return numLogs;
  }
}
