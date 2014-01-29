/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.id3.models;

import com.learning.id3.Entities.Entropy;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

/**
 *
 * @author hendri
 */
public class EntropyModel {

    private Entropy entPi;
    private double entropy;

    public double getEntropy() {
        return entropy;
    }

    public void setEntropy(double entropy) {
        this.entropy = entropy;
    }

    public Entropy getEntPi() {
        return entPi;
    }

    public void setEntPi(Entropy entPi) {
        this.entPi = entPi;
    }

    public void countEntropy() {
        Set<String> temp = entPi.getLabels().keySet();
        DecimalFormat fm = new DecimalFormat("#.##");
        double ent = 0;
        double tempEnt = 0;
        String str = "-----entropy = ";
        for (String key : temp) {
            str += " - ";
//            System.out.println(";labels : " + key+" = "+entPi.getLabels().get(key).intValue());
            if (entPi.getLabels().get(key).intValue() > 0) {

                tempEnt = ((double) entPi.getLabels().get(key) / (double) entPi.getAllData());
                str += entPi.getLabels().get(key) + "/" + entPi.getAllData() + " * Log2(" + entPi.getLabels().get(key) + "/" + entPi.getAllData() + ")";
                ent = ent + (-((tempEnt) * (Math.log(tempEnt) / Math.log(2))));
            } else {
                str += entPi.getLabels().get(key) + "/" + entPi.getAllData() + " * Log2(" + entPi.getLabels().get(key) + "/" + entPi.getAllData() + ")";
                ent += 0;
            }
//            System.out.println("ent === " + ent);

//            System.out.println("total === " + ent);

        }
        str += " = " + fm.format(ent);
        this.entropy = ent;
        System.out.println(str);
    }
}
