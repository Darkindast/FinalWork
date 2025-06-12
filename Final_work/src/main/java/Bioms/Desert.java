/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bioms;

import ObjectInterest_Desert.Gorge;
import ObjectInterest_Desert.Oasis;
import ObjectInterest_Desert.PyramidRuins;
import ObjectInterest_Desert.Saline;

/**
 *
 * @author Andrey
 */
public abstract class Desert extends Biom {
    public Desert() {
        super(Gorge.class, Oasis.class, PyramidRuins.class, Saline.class);
    }
}
