/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bioms;

import ObjectInterest_MildCLimate.Forest;
import ObjectInterest_MildCLimate.Meadow;
import ObjectInterest_MildCLimate.Mountain;
import ObjectInterest_MildCLimate.Swamp;
import ObjectInterest_MildCLimate.Village;

/**
 *
 * @author Andrey
 */
public class MildClimate extends Biom {
    public MildClimate() {
        super(Forest.class, Meadow.class, Mountain.class, Swamp.class, Village.class);
    }
}
