/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.id3.Entities;

import java.util.HashMap;

/**
 *
 * @author hendri
 */
public class Entropy {

    private HashMap<String, Integer> labels;
    private Integer allData;

    public Integer getAllData() {
        return allData;
    }

    public void setAllData(Integer allData) {
        this.allData = allData;
    }

    public HashMap<String, Integer> getLabels() {
        return labels;
    }

    public void setLabels(HashMap<String, Integer> labels) {
        this.labels = labels;
    }
}
