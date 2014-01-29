/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.id3.filter;

import java.util.HashMap;

/**
 *
 * @author hendri
 */
public interface Filter< F, E, T> {

    public boolean isMatched(HashMap<String, String> object, F field, E text);
    public boolean isMatched(HashMap<String, String> object, F field, E text, T label);
}
