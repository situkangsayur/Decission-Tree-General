/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.id3.models;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author hendri
 */
public class Documents {

//    private static ConcurrentHashMap<String, DocumentEntity> documents;
    private static RandomAccessFile fstream;
    private static BufferedWriter out;

    /**
     * @author Hendri : to write the results of sorted hashmap to the file, and
     * manage the string of results for each values in hashmap.
     * @param index
     * @param url
     * @param messageId
     */
    public static void printOutFile(String index, List<String> resultList) {

        String str = "@data\n";
        for (int i = 0; i < resultList.size(); i++) {
//            System.out.println("" + resultList.get(i));
            str = str + resultList.get(i) + "\n";


        }
        System.out.println(str);
        printToFile(index + ".arff", str);
    }

    /**
     * @author Hendri : main method to write the string to the file.txt
     * @param fileName
     * @param text
     */
    private static void printToFile(String fileName, String text) {
        try {
            if (fstream == null) {
                fstream = new RandomAccessFile(fileName, "rw");
            }
            fstream.writeBytes(text);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     */
    public static void closeProntTolFile() {
        try {
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
