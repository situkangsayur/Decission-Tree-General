/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.id3;

import com.learning.id3.Entities.AttrEntity;
import com.learning.id3.Entities.Entropy;
import com.learning.id3.filter.Filter;
import com.learning.id3.filter.FilterList;
import com.learning.id3.filter.FilteringService;
import com.learning.id3.models.CreateFile;
import com.learning.id3.models.DataTrainingParser;
import com.learning.id3.models.Documents;
import com.learning.id3.models.EntropyModel;
import com.learning.id3.models.FileParser;
import com.learning.id3.models.KFoldCrossValidation;
import com.learning.id3.models.SplitTest;
import com.learning.id3.models.Tree;
import com.learning.id3.models.TreeExecute;
import com.learning.id3.services.DataSetContainer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hendri
 */
public class Main {

    private static Tree mainTree;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        String url = "weather.nominal.arff";
//        String urlTest = "dataset3.arff";
//        String urlDT3 = "dataset3label.arff";
//        String urlDT2 = "dataset2.arff";
        String urltictac = "sampleTicTacToe.arff";
        String urlJamur = "datajamur.arff";
        String urlBreast = "breast-cancer.arff";
        String urlvote = "vote.arff";
        String urlTic = "sampleTicTacToe.arff";

        //initsialization
        DataSetContainer.ATTRIBUTES = new HashMap<String, AttrEntity>();
        DataSetContainer.DATA_SET = new ArrayList<HashMap<String, String>>();
        DataSetContainer.DATA_TESTS = new ArrayList<HashMap<String, String>>();
        DataSetContainer.DATA_SET_2 = new ArrayList<HashMap<String, String>>();
        DataSetContainer.DATA_SET_3 = new ArrayList<HashMap<String, String>>();
        DataSetContainer.ATTRNUMB = new ArrayList<String>();
        DataSetContainer.TEMP_ATTR = new HashMap<String, Integer>();

        TreeExecute execute = new TreeExecute();

//        DataSetContainer.FAIL_VAL = "negative";
//        execute.setUrl(urlTic);
//        execute.loadDataSet(-1);
//        execute.showAttribute();
//        execute.showDataSet();
//        execute.createTree();

//        execute.kFoldValidation(3);
//        execute.splitValidation();

        execute.setUrl(urlBreast);
        DataSetContainer.FAIL_VAL = "2";
        List<Integer> except = new ArrayList<Integer>();
        except.add(new Integer(1));
        execute.loadDataSet(-1, except);
        execute.showAttribute();
//        execute.showDataSet();
//        execute.createTree();
//        execute.kFoldValidation(3);
        execute.splitValidation();

//
//        FileParser parser = new FileParser();
//
//        parser.setPath(urlJamur);
//        try {
//            parser.parserProcess(0);
//        } catch (FileNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
////show atribute
//        System.out.println("Atribut : ");
//
//        Set<String> keys = DataSetContainer.ATTRIBUTES.keySet();
//
//        for (String key : keys) {
//            System.out.print(DataSetContainer.ATTRIBUTES.get(key).getNumber() + " . ");
//
//            System.out.print(key + " : ");
//
//            List<String> vals = DataSetContainer.ATTRIBUTES.get(key).getValues();
//            for (int i = 0; i < vals.size(); i++) {
//                System.out.print(vals.get(i) + ",");
//            }
//            System.out.println("");
//        }
//
//        System.out.println("");
//
//        for (int i = 0; i < DataSetContainer.DATA_SET.size(); i++) {
//            System.out.print(i + ". ");
//            Set<String> keysData = DataSetContainer.DATA_SET.get(i).keySet();
//            for (String key : keysData) {
//                System.out.print(key + " : " + DataSetContainer.DATA_SET.get(i).get(key) + " | ");
//            }
//            System.out.println("");
//        }
//
//
//        treeCreator();
//

//        DataTrainingParser trainParser = new DataTrainingParser();
//        trainParser.setPath(url);
//        trainParser.parserProcess();
//       
//        int no = 0;
//        System.out.println("\n\n");
//
//        List<String> resultList = new ArrayList<String>();
//
//        for (HashMap<String, String> temp : DataSetContainer.DATA_TESTS) {
//            resultList.add(mainTree.classication(mainTree.getMainHead(), temp));
//       
////            System.out.println(++no + "result : " + mainTree.classication(mainTre/e.getMainHead(), temp));
//        }

        //Dataset 3 labeling
//
//        CreateFile fileOut = new CreateFile();
//        fileOut.setPath(urlTest);
//        fileOut.setResultList(resultList);
//        fileOut.parserProcess();
//        Documents.printOutFile("dataset3labelFromModel2", fileOut.getMargeList());
//
//


        System.out.println("\n\n");

//        generateAttribute(0, "");
//        System.out.println(DataSetContainer.DATA_COMBI);

//        try {
//          File file = new File("example.txt");
//          BufferedWriter output = new BufferedWriter(new FileWriter(file));
//          output.write(DataSetContainer.DATA_COMBI);
//          output.close();
//        } catch ( IOException e ) {
//           e.printStackTrace();
//        }

    }

    public static void createDataSet3() {
        Set<String> keySet = DataSetContainer.ATTRIBUTES.keySet();

        DataSetContainer.DATA_SET_3 = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> dataSet = new HashMap<String, String>();

        for (String key : keySet) {
            AttrEntity ent = DataSetContainer.ATTRIBUTES.get(key);
            for (String val : ent.getValues()) {
                for (String subKey : keySet) {
                    AttrEntity entSub = DataSetContainer.ATTRIBUTES.get(subKey);
//            Set<String> entKey = ent.getValues();
                    for (String subVal : entSub.getValues()) {

                        dataSet.put(subKey, subVal);

                    }

                }
            }
        }
    }
    
    public static void generateAttribute(int num, String temp) {
        boolean found = false;
        if (num == 0) {
            DataSetContainer.DATA_COMBI = "";
        }
        Set<String> keys = DataSetContainer.ATTRIBUTES.keySet();
        String prev = temp;
        int keySize = keys.size();
        for (String key : keys) {
            //System.out.println(!key.equals(DataSetContainer.targetClass));
//            System.out.println(DataSetContainer.ATTRIBUTES.get(key).getNumber());
            
            if((DataSetContainer.ATTRIBUTES.get(key).getNumber() == num)
                    ) {
                found = true;
                List<String> vals = DataSetContainer.ATTRIBUTES.get(key).getValues();
                for (int i = 0; i < vals.size(); i++) {
                    //System.out.println("aaaa");
                    prev = temp;
                    if (key.equals(DataSetContainer.targetClass)) {
                        generateAttribute(num+1, prev);
                    }else{
                        prev += vals.get(i);
                        if (num < keySize-1) {
                            prev +=  ",";
                            generateAttribute(num+1, prev);
                        }else {
                            DataSetContainer.DATA_COMBI += prev+"\n";
                            //System.out.println(prev);
                            break;
                        }
                    }
                    //System.out.println(vals.get(i) + ",");
                }
            }
        }
        if (!found) {
            prev +=  num+",";
            generateAttribute(num+1, prev);
        }
    }
}
