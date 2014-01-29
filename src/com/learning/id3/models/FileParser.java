/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.id3.models;

import com.learning.id3.Entities.AttrEntity;
import com.learning.id3.services.DataSetContainer;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author hendri
 */
public class FileParser {

    private File file;
    private FileInputStream fstream;
    private DataInputStream in;
    private BufferedReader br;
    private String path;
    private String strLine;
    private boolean isData;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void parserProcess(int label) throws FileNotFoundException, IOException {
        file = new File(this.path);

        fstream = new FileInputStream(file);
        in = new DataInputStream(fstream);
        br = new BufferedReader(new InputStreamReader(in));
        isData = false;
        int pos = 0;
        while ((strLine = br.readLine()) != null) {

            if (strLine.indexOf("@relation") > -1) {
                continue;
            }
            if ((strLine.indexOf("@data") > -1)) {
                isData = true;
                continue;
            }

            if (!isData) {
                if (strLine.indexOf("@attribute") > -1) {
                    AttrEntity attr = new AttrEntity();
                    attr.setName(strLine.substring(strLine.indexOf(" "), strLine.indexOf("{") - 1).trim());
//                    System.out.println(attr.getName());
                    String values = strLine.substring(strLine.indexOf("{") + 1, strLine.indexOf("}")).replaceAll(" ", "");
                    String[] valParse = values.split(",");
                    List<String> listVal = new ArrayList<String>();
                    for (int i = 0; i < valParse.length; i++) {
//                        System.out.println(valParse[i] + ",");
                        listVal.add(valParse[i]);
                    }
                    attr.setValues(listVal);
                    attr.setNumber(pos);

                    DataSetContainer.ATTRNUMB.add(attr.getName().toString());
                    DataSetContainer.TEMP_ATTR.put(attr.getName(), new Integer(0));
                    DataSetContainer.ATTRIBUTES.put(attr.getName().toString(), attr);
                    if (label > -1) {
                        if (label == pos) {
                            DataSetContainer.targetClass = attr.getName();
                        }
                    } else {
                        DataSetContainer.targetClass = attr.getName();
                    }
                    pos++;
                }
            } else {

                String[] valParse = strLine.split(",");
                HashMap<String, String> listMap = new HashMap<String, String>();

//                System.out.println("size atribute" + DataSetContainer.ATTRNUMB.size() + " string " + valParse.length);
                for (int i = 0; i < valParse.length; i++) {
                    listMap.put(DataSetContainer.ATTRNUMB.get(i).toString(), valParse[i]);
                }

                DataSetContainer.DATA_SET.add(listMap);

            }
        }
        DataSetContainer.TEMP_ATTR.remove(DataSetContainer.targetClass);
    }

    public void parserProcess(int label, List<Integer> except) throws FileNotFoundException, IOException {
        file = new File(this.path);

        fstream = new FileInputStream(file);
        in = new DataInputStream(fstream);
        br = new BufferedReader(new InputStreamReader(in));
        isData = false;
        int pos = 0;
        while ((strLine = br.readLine()) != null) {

            if (strLine.indexOf("@relation") > -1) {
                continue;
            }
            if ((strLine.indexOf("@data") > -1)) {
                isData = true;
                continue;
            }

            if (!isData) {
                if (strLine.indexOf("@attribute") > -1) {
                    AttrEntity attr = new AttrEntity();
                    attr.setName(strLine.substring(strLine.indexOf(" "), strLine.indexOf("{") - 1).trim());
//                    System.out.println(attr.getName());
                    String values = strLine.substring(strLine.indexOf("{") + 1, strLine.indexOf("}")).replaceAll(" ", "");
                    String[] valParse = values.split(",");
                    List<String> listVal = new ArrayList<String>();
                    for (int i = 0; i < valParse.length; i++) {
//                        System.out.println(valParse[i] + ",");
                        listVal.add(valParse[i]);
                    }
                    attr.setValues(listVal);
                    attr.setNumber(pos);

                    DataSetContainer.ATTRNUMB.add(attr.getName().toString());
                    DataSetContainer.TEMP_ATTR.put(attr.getName(), new Integer(0));
                    DataSetContainer.ATTRIBUTES.put(attr.getName().toString(), attr);
                    if (label > -1) {
                        if (label == pos) {
                            DataSetContainer.targetClass = attr.getName();
                        }
                    } else {
                        DataSetContainer.targetClass = attr.getName();
                    }
                    pos++;
                }
            } else {

                String[] valParse = strLine.split(",");
                HashMap<String, String> listMap = new HashMap<String, String>();

//                System.out.println("size atribute" + DataSetContainer.ATTRNUMB.size() + " string " + valParse.length);
                for (int i = 0; i < valParse.length; i++) {
                    listMap.put(DataSetContainer.ATTRNUMB.get(i).toString(), valParse[i]);
                }

                DataSetContainer.DATA_SET.add(listMap);

            }
        }
        DataSetContainer.TEMP_ATTR.remove(DataSetContainer.targetClass);
        if (except != null) {
            for (int i = 0; i < except.size(); i++) {
                DataSetContainer.ATTRIBUTES.remove(DataSetContainer.ATTRNUMB.get(i));
                DataSetContainer.TEMP_ATTR.remove(DataSetContainer.ATTRNUMB.get(i));
            }
        }
    }
}
