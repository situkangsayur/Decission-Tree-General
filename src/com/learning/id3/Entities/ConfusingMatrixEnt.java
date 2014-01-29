/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.id3.Entities;

import com.learning.id3.services.DataSetContainer;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hendri
 */
public class ConfusingMatrixEnt {

    private List<String> targetVal;
    private List<Integer> restMat;
    private List<ValidationEntity> result;

    public void createConfusingMatrix() {
        targetVal = DataSetContainer.ATTRIBUTES.get(DataSetContainer.targetClass).getValues();
        restMat= new ArrayList<Integer>();
        System.out.println("");
        System.out.println("Confusing Matrix : ");
        System.out.println("");
        for (int i = 0; i < targetVal.size(); i++) {
            System.out.print(targetVal.get(i));
            System.out.println("  ");
        }

        for (int i = 0; i < result.size(); i++) {
            if(result.get(i).getOriginal().equals(result.get(i).getClassification())){
//                restMat
            }
        }

    }
}
