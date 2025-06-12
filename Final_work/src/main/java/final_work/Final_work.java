/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package final_work;

import Regions.BaseRegion;
import Regions.DesertRegion;
import Regions.MildClimateRegion;
import Regions.TundraRegion;

/**
 *
 * @author Andrey
 */
public class Final_work {

    public static void main(String[] args) {
        BaseRegion tundraRegion = new TundraRegion();
        tundraRegion.generateObjectsInterestList();
        BaseRegion desertRegion = new DesertRegion();
        desertRegion.generateObjectsInterestList();
        BaseRegion mildClimateRegion = new MildClimateRegion();
        mildClimateRegion.generateObjectsInterestList();
        for (Object obj : tundraRegion.getObjectsInterestList()) {
            System.out.println(obj.toString());
        }
        for (Object obj : desertRegion.getObjectsInterestList()) {
            System.out.println(obj.toString());
        }
        for (Object obj : mildClimateRegion.getObjectsInterestList()) {
            System.out.println(obj.toString());
        }
    }
}
