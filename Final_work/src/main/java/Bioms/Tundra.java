/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bioms;

import ObjectInterest_Tundra.Swamp;
import ObjectInterest_Tundra.Geyser;
import ObjectInterest_Tundra.Glacier;
import ObjectInterest_Tundra.OpenWoodland;
import ObjectInterest_Tundra.ReindeerHerdersCamp;

/**
 *
 * @author Andrey
 */
public class Tundra extends Biom {
    public Tundra(){
        super(Glacier.class, Geyser.class, OpenWoodland.class, ReindeerHerdersCamp.class, Swamp.class);
    }
}
