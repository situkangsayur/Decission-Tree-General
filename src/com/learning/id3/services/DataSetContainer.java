/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.id3.services;

import com.learning.id3.Entities.AttrEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author hendri
 */
public class DataSetContainer {

    public static HashMap<String, AttrEntity> ATTRIBUTES;
    public static List<String> ATTRNUMB;
    public static HashMap<String, Integer> TEMP_ATTR;
    public static List<HashMap<String, String>> DATA_SET;
    public static String targetClass;
    public static String gainStr;
    public static List<HashMap<String, String>> DATA_TESTS;
    public static List<HashMap<String, String>> DATA_SET_3;
    public static List<HashMap<String, String>> DATA_SET_2;
    public static String FAIL_VAL;
    public static String DATA_COMBI;

    public static boolean checkAttr(HashMap<String, Integer> atributes) {
        Set<String> keys = atributes.keySet();
        boolean stat = false;
        for (String key : keys) {
            if (atributes.get(key) == 0) {
                stat = true;
                break;
            }
        }
        return stat;
    }

    public static void showDataSet(List<HashMap<String, String>> dataSet) {
        System.out.println("Sub Data Set ");
        for (int i = 0; i < dataSet.size(); i++) {
            System.out.print(i + ". ");
            Set<String> keysData = dataSet.get(i).keySet();
            for (String key : keysData) {
                System.out.print(key + " : " + dataSet.get(i).get(key) + " | ");
            }
            System.out.println("");
        }
        System.out.println("===================================");
    }
}
