/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.id3.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author hendri
 */
public class FilterList<F, E, T> {

    public List<HashMap<String, String>> filterList(List<HashMap<String, String>> originalList, Filter filter, F field, E text) {
        List<HashMap<String, String>> filterList = new ArrayList<HashMap<String, String>>();
        for (HashMap<String, String> object : originalList) {
//            System.out.println(object.get(field));
            if (filter.isMatched(object, field, text)) {
                filterList.add(object);
            } else {
                continue;
            }
        }
        return filterList;
    }

    public List<HashMap<String, String>> filterList(List<HashMap<String, String>> originalList, Filter filter, F field, E text, T label) {
        List<HashMap<String, String>> filterList = new ArrayList<HashMap<String, String>>();
        for (HashMap<String, String> object : originalList) {
//            System.out.println(object.get(field));
            if (filter.isMatched(object, field, text, label)) {
                filterList.add(object);
            } else {
                continue;
            }
        }
        return filterList;
    }
}
