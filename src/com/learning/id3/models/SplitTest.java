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
public class SplitTest {

    private int split;
    private List<HashMap<String, String>> dataTest;
    private List<HashMap<String, String>> dataTemp;
    private Tree mainTree;
    private TreeExecute treeExec;
    private List<ValidationEntity> listRest;
    private int dataSplit;

    public List<ValidationEntity> getListRest() {
        return listRest;
    }

    public void setTreeExec(TreeExecute treeExec) {
        this.treeExec = treeExec;
    }

    public Tree getMainTree() {
        return mainTree;
    }

    public void setMainTree(Tree mainTree) {
        this.mainTree = mainTree;
    }

    public int getSplit() {
        return split;
    }

    public void setSplit(int split) {
        this.split = split;
    }

    public int getDataFold() {
        return dataSplit;
    }

    public void setDataFold(int dataFold) {
        this.dataSplit = dataFold;
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

    public void doKSplitValidation() {


        dataSplit = (int) (((float) split / 100) * DataSetContainer.DATA_SET.size());

        dataTemp = new ArrayList<HashMap<String, String>>(DataSetContainer.DATA_SET);
        DataSetContainer.DATA_TESTS = new ArrayList<HashMap<String, String>>();
        listRest = new ArrayList<ValidationEntity>();
//        System.out.println("fold :" + fold);
        int sizeList = DataSetContainer.DATA_SET.size();
        for (int j = sizeList - 1; j > (sizeList - dataSplit - 1); j--) {
            DataSetContainer.DATA_TESTS.add(DataSetContainer.DATA_SET.get(j));
            DataSetContainer.DATA_SET.remove(j);
            System.out.println("j " + j);
        }

        System.out.println("+++++ ID3 ++++++ datatest" + DataSetContainer.DATA_SET.size());
        treeExec.createTree();
        mainTree = treeExec.getMainTree();
        System.out.println(" tree " + mainTree);
        System.out.println("\n\n");
        System.out.println("RESULT : \n");
        int no = 0;

        listRest = new ArrayList<ValidationEntity>();

        for (HashMap<String, String> temp : DataSetContainer.DATA_TESTS) {
            boolean match = false;
            ValidationEntity valid = new ValidationEntity();
            valid.setOriginal(temp.get(DataSetContainer.targetClass));
            valid.setClassification(mainTree.classication(mainTree.getMainHead(), temp));
            if (valid.getClassification().equals(valid.getOriginal())) {
                match = true;
            }
            valid.setResult(match);
            listRest.add(valid);
        }

//        for (int j = 0; j < dataSplit; j++) {
//            DataSetContainer.DATA_SET.add(DataSetContainer.DATA_SET.get(j));
//            DataSetContainer.DATA_TESTS.remove(j);
//        }
//        listRest.add(listTemp);
//        }
    }

    public void validation() {
    }

    public void showResult() {

        int match = 0;
        int itr = 0;

        System.out.println("\n\nResult :... ");
        for (ValidationEntity data : listRest) {
            System.out.println("classification : " + data.getClassification() + " | "
                    + " Original = " + data.getOriginal() + " | DNA : " + data.isResult());
            if (data.getOriginal().equals(data.getClassification())) {
                match++;
            }
            itr++;
        }

        float result = ((float) match / itr) * 100;
        System.out.println("resul percentation of game = " + result);

    }
}
