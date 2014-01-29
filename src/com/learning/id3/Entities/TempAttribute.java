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
public class TempAttribute {

    private HashMap<String, Integer> tempAttr;
    private int jumlah;
    
    public void putTempAttr(Integer val){
        
    }

    public HashMap<String, Integer> getTempAttr() {
        return tempAttr;
    }

    public void setTempAttr(HashMap<String, Integer> tempAttr) {
        this.tempAttr = tempAttr;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
