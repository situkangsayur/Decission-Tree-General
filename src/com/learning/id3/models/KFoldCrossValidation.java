/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.id3.models;

import com.learning.id3.Entities.ValidationEntity;
import com.learning.id3.Main;
import com.learning.id3.services.DataSetContainer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author hendri
 */
public class KFoldCrossValidation {
    
    private int fold;
    private List<HashMap<String, String>> dataTest;
    private List<HashMap<String, String>> dataTemp;
    private Tree mainTree;
    private TreeExecute treeExec;
//    private Main mainClass;
    private List<List<ValidationEntity>> listRest;
    private int dataFold;
    
    public List<List<ValidationEntity>> getListRest() {
        return listRest;
    }
    
    public Tree getMainTree() {
        return mainTree;
    }
    
    public void setMainTree(Tree mainTree) {
        this.mainTree = mainTree;
    }
    
    public int getFold() {
        return fold;
    }
    
    public void setFold(int fold) {
        this.fold = fold;
    }
    
    public List<HashMap<String, String>> getDataTest() {
        return dataTest;
    }
    
    public void setDataTest(List<HashMap<String, String>> dataTest) {
        this.dataTest = dataTest;
    }
    
    public List<HashMap<String, String>> getDataTemp() {
        return dataTemp;
    }
    
    public void setDataTemp(List<HashMap<String, String>> dataTemp) {
        this.dataTemp = dataTemp;
    }
    
    public void setTreeExec(TreeExecute treeExec) {
        this.treeExec = treeExec;
    }
    
    public void doKFoldCrossValidation() {
        
        dataFold = DataSetContainer.DATA_SET.size() / fold;
        dataTemp = new ArrayList<HashMap<String, String>>(DataSetContainer.DATA_SET);
//        DataSetContainer.DATA_TESTS = new ArrayList<HashMap<String, String>>();
        listRest = new ArrayList<List<ValidationEntity>>();
        for (int i = 0; i < fold; i++) {
            
            for (int j = 0; j < dataFold; j++) {
                DataSetContainer.DATA_TESTS.add(DataSetContainer.DATA_SET.get(0));
                DataSetContainer.DATA_SET.remove(0);
            }
            
            System.out.println("+++++ ID3 ++++++" + mainTree);
            treeExec.createTree();
            mainTree = treeExec.getMainTree();
            
            System.out.println("\n\n");
            System.out.println("RESULT : \n");
            int no = 0;
            
            List<ValidationEntity> listTemp = new ArrayList<ValidationEntity>();
            for (HashMap<String, String> temp : DataSetContainer.DATA_TESTS) {
                boolean match = false;
                
                ValidationEntity valid = new ValidationEntity();
                valid.setOriginal(temp.get(DataSetContainer.targetClass));
                
                System.out.println("class : " + valid.getOriginal() + " resut : " + mainTree.classication(mainTree.getMainHead(), temp));
                valid.setClassification(mainTree.classication(mainTree.getMainHead(), temp));
                
                if (valid.getClassification().equals(valid.getOriginal())) {
                    match = true;
                }
                valid.setResult(match);
                listTemp.add(valid);
            }
            
            listRest.add(listTemp);
            
            System.out.println("datafold : " + dataFold + " data test : " + DataSetContainer.DATA_TESTS.size());
            for (int j = 0; j < dataFold; j++) {
                DataSetContainer.DATA_SET.add(DataSetContainer.DATA_TESTS.get(0));
                DataSetContainer.DATA_TESTS.remove(0);
            }
            
        }
        
        
    }
    
    public void showResult() {
        
        int match = 0;
        int itr = 0;
        for (List<ValidationEntity> temp : listRest) {
            System.out.println("\n\nResult fold pertama...");
            for (ValidationEntity data : temp) {
                System.out.println("classification : " + data.getClassification() + " | "
                        + " Original = " + data.getOriginal() + " | DNA : " + data.isResult());
                if (data.getOriginal().equals(data.getClassification())) {
                    match++;
                }
                itr++;
            }
        }
        
        
        
        float result = ((float) match / ((float) itr)) * 100;
        System.out.println("resul percentation of game = " + result);
        
    }
}
