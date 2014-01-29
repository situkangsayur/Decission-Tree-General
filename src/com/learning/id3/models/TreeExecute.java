/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.id3.models;

import com.learning.id3.services.DataSetContainer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hendri
 */
public class TreeExecute {

    private String url;
    private String urlOutPut;
    private FileParser parser;
    private Tree mainTree;
    private List<String> resultList;
    private String fileName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlOutPut() {
        return urlOutPut;
    }

    public void setUrlOutPut(String urlOutPut) {
        this.urlOutPut = urlOutPut;
    }

    public FileParser getParser() {
        return parser;
    }

    public void setParser(FileParser parser) {
        this.parser = parser;
    }

    public Tree getMainTree() {
        return mainTree;
    }

    public void setMainTree(Tree mainTree) {
        this.mainTree = mainTree;
    }

    public List<String> getResultList() {
        return resultList;
    }

    public void setResultList(List<String> resultList) {
        this.resultList = resultList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void loadDataSet(int targetClass) {

        FileParser parser = new FileParser();

        parser.setPath(url);
        try {
            parser.parserProcess(targetClass);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadDataSet(int targetClass, List<Integer> except) {

        FileParser parser = new FileParser();

        parser.setPath(url);
        try {
            parser.parserProcess(targetClass, except);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void showAttribute() {

        System.out.println("Atribut : ");

        Set<String> keys = DataSetContainer.ATTRIBUTES.keySet();

        for (String key : keys) {
            System.out.print(DataSetContainer.ATTRIBUTES.get(key).getNumber() + " . ");

            System.out.print(key + " : ");

            List<String> vals = DataSetContainer.ATTRIBUTES.get(key).getValues();
            for (int i = 0; i < vals.size(); i++) {
                System.out.print(vals.get(i) + ",");
            }
            System.out.println("");
        }

        System.out.println("");

    }

    public void showDataSet() {
        System.out.println("Data Set ");
        for (int i = 0; i < DataSetContainer.DATA_SET.size(); i++) {
            System.out.print(i + ". ");
            Set<String> keysData = DataSetContainer.DATA_SET.get(i).keySet();
            for (String key : keysData) {
                System.out.print(key + " : " + DataSetContainer.DATA_SET.get(i).get(key) + " | ");
            }
            System.out.println("");
        }
    }

    public void createTree() {

        System.out.println("\n\n");
        System.out.println("----------------- START ID TREE --------------");
        System.out.println("");
        mainTree = new Tree();
        mainTree.constructTree();

        System.out.println("\n--------------------");
        System.out.println("Tree bentukan:");
        mainTree.printTree();

    }

    public void dataSetLabeling() {
        CreateFile fileOut = new CreateFile();
        fileOut.setPath(urlOutPut);
        fileOut.setResultList(resultList);
        try {
            fileOut.parserProcess();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Documents.printOutFile(fileName, fileOut.getMargeList());
    }

    public void kFoldValidation(int k) {
        System.out.println("\n\n");
        System.out.println("K-Fold Cross Validation");
        KFoldCrossValidation validation = new KFoldCrossValidation();
        validation.setFold(k);
        validation.setTreeExec(this);
        validation.setMainTree(mainTree);
        validation.doKFoldCrossValidation();
        validation.showResult();
    }

    public void splitValidation() {
        System.out.println("\n\n");
        System.out.println("Split Validation");
        SplitTest split = new SplitTest();
        split.setSplit(30);
        split.setTreeExec(this);
        split.setMainTree(mainTree);
        split.doKSplitValidation();
        split.showResult();
    }
}
