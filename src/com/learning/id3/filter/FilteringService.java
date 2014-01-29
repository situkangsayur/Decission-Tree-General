/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.id3.filter;

import com.learning.id3.services.DataSetContainer;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author hendri
 */
public class FilteringService {

    private static Filter<String, String, String> filter;
    private static List<HashMap<String, String>> sortEmpList;

    public static void check() {
        if (filter == null) {
            filter = new Filter<String, String, String>() {
                public boolean isMatched(HashMap<String, String> object, String field, String text) {
                    return object.get(field).startsWith(text);
                }

                public boolean isMatched(HashMap<String, String> object, String field, String text, String label) {
                    return object.get(field).startsWith(text) && object.get(DataSetContainer.targetClass).startsWith(label);
                }
            };
        }
    }

    public static List<HashMap<String, String>> getFiltering(List<HashMap<String, String>> dataSet, String field, String keyWord) {
        check();
//        System.out.println(DataSetContainer.DATAS.get(1).get(DataSetContainer.ATTRNUMB.get(3)) + "{");
        sortEmpList = new FilterList().filterList(dataSet, filter, field, keyWord);

        return sortEmpList;

    }

    public static List<HashMap<String, String>> getFiltering(List<HashMap<String, String>> dataSet, String field, String keyWord, String label) {
        check();
        //        System.out.println(DataSetContainer.DATAS.get(1).get(DataSetContainer.ATTRNUMB.get(3)) + "{");
        sortEmpList = new FilterList().filterList(dataSet, filter, field, keyWord, label);

        return sortEmpList;

    }
}
