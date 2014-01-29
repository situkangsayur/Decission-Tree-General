/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.id3.Entities;

import com.learning.id3.models.EntropyModel;
import java.util.List;

/**
 *
 * @author hendri
 */
public class GainEnt {

    private Double gain;
    private String attribute;

    public GainEnt(Double gain, String attribute) {
        this.gain = gain;
        this.attribute = attribute;
    }

    public Double getGain() {
        return gain;
    }

    public void setGain(Double gain) {
        this.gain = gain;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
