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
public class DataTrainingParser {

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

    public void parserProcess() throws FileNotFoundException, IOException {
        file = new File(this.path);

        fstream = new FileInputStream(file);
        in = new DataInputStream(fstream);
        br = new BufferedReader(new InputStreamReader(in));
        isData = false;
        int pos = 0;
        while ((strLine = br.readLine()) != null) {

            if ((strLine.indexOf("@data") > -1)) {
                isData = true;
                continue;
            }

            String[] valParse = strLine.split(",");
            HashMap<String, String> listMap = new HashMap<String, String>();

            for (int i = 0; i < valParse.length; i++) {
                listMap.put(DataSetContainer.ATTRNUMB.get(i).toString(), valParse[i]);
            }

            DataSetContainer.DATA_TESTS.add(listMap);


        }
    }

    public void parserProcessDataTrue() throws FileNotFoundException, IOException {
        file = new File(this.path);

        fstream = new FileInputStream(file);
        in = new DataInputStream(fstream);
        br = new BufferedReader(new InputStreamReader(in));
        isData = false;
        int pos = 0;
        while ((strLine = br.readLine()) != null) {

            if ((strLine.indexOf("@data") > -1)) {
                isData = true;
                continue;
            }

            String[] valParse = strLine.split(",");
            HashMap<String, String> listMap = new HashMap<String, String>();

            for (int i = 0; i < valParse.length; i++) {
                listMap.put(DataSetContainer.ATTRNUMB.get(i).toString(), valParse[i]);
            }

            DataSetContainer.DATA_SET_3.add(listMap);


        }
    }
}
