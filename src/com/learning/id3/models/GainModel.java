/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.id3.models;

import com.learning.id3.Entities.AttrEntity;
import com.learning.id3.Entities.Entropy;
import com.learning.id3.Entities.GainEnt;
import com.learning.id3.Entities.Node;
import com.learning.id3.filter.FilteringService;
import com.learning.id3.services.DataSetContainer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author hendri
 */
public class GainModel {

    private GainEnt gainEnt;
    private Node node;
    private Double gain;
    private List<HashMap<String, String>> listMaster;
    private List<GainEnt> resultList;
    private GainEnt maxGain;
    private EntropyModel mainEnt;
    private String strSigma;
    private String strAns;

    public EntropyModel getMainEnt() {
        return mainEnt;
    }

    public GainEnt getMaxGain() {
        return maxGain;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public GainEnt getGainEnt() {
        return gainEnt;
    }

    public void setGainEnt(GainEnt gainEnt) {
        this.gainEnt = gainEnt;
    }

    public void countRootGain() {
        DecimalFormat fm = new DecimalFormat("#.##");
        listMaster = node.getSplitData();
        resultList = new ArrayList<GainEnt>();
        mainEnt = new EntropyModel();
        Entropy entropy = null;
        entropy = new Entropy();

        strAns = "Gains :\n ";
        DataSetContainer.showDataSet(node.getSplitData());
//        System.out.println("size listmaster " + listMaster.size() + " ; NOde : " + node.getSplitData().size() + " target : " + DataSetContainer.targetClass);

        Set<String> attributes = DataSetContainer.ATTRIBUTES.keySet();

        AttrEntity attr = DataSetContainer.ATTRIBUTES.get(DataSetContainer.targetClass);
        List<HashMap<String, String>> listTemp = new ArrayList<HashMap<String, String>>(node.getSplitData());


        HashMap< String, Integer> labels = new HashMap<String, Integer>();

        int count = 0;

        for (String val : attr.getValues()) {
            listTemp = FilteringService.getFiltering(listMaster, DataSetContainer.targetClass, val);
//            System.out.println(" data label : " + val + " = " + listTemp.size());
            labels.put(val, listTemp.size());
        }

        entropy.setLabels(labels);
        entropy.setAllData(listMaster.size());

        mainEnt.setEntPi(entropy);
        System.out.println(" entropy main gain");
        mainEnt.countEntropy();
        System.out.println("===================");

        node.setEntropy(mainEnt);

        if (node.getEntropy().getEntropy() != 0.0D) {

            for (String keyAttr : attributes) {
                if (keyAttr.equals(DataSetContainer.targetClass)) {
                    continue;
                }
                if (node.getAtributes().get(keyAttr).intValue() == 1) {
                    continue;
                }
                strAns += "--" + keyAttr + "\n";
                gain = new Double(mainEnt.getEntropy() - sigmaGain(keyAttr));
                GainEnt temp = new GainEnt(new Double(gain), keyAttr);
                strAns += "---Gain = " + fm.format(mainEnt.getEntropy()) + " - " + strSigma + " = " + fm.format(gain) + "\n";
                resultList.add(temp);
            }

//            for (int i = 0; i < resultList.size(); i++) {
//                System.out.println(resultList.get(i).getAttribute() + " :" + resultList.get(i).getGain());
//            }


            this.maxGain = getMax(resultList);
            System.out.println("" + node.getAtributes());

            strAns += "-----------Maksimum result : " + this.maxGain.getAttribute();
        } else {
            String maxAttr = "";
            if (node.getSplitData().size() != 0) {
                Set<String> temps = node.getEntropy().getEntPi().getLabels().keySet();
                Integer max = new Integer(0);

                strAns += "Main Entropy = 0 \n";

                for (String key : temps) {

                    if (node.getEntropy().getEntPi().getLabels().get(key).intValue() > max.intValue()) {
                        max = node.getEntropy().getEntPi().getLabels().get(key);
                        maxAttr = key;

                    }
                }
            } else {
                maxAttr = DataSetContainer.FAIL_VAL;
            }

            System.out.println("result : " + maxAttr);
//            strAns += "-------result = " + maxAttr;
            node.setResult(maxAttr);
        }

        System.out.println(strAns);
    }

    public GainEnt getMax(List<GainEnt> gainEnt) {
        GainEnt max = null;
        System.out.println("list " + gainEnt.size());
        for (GainEnt temp : gainEnt) {
            if (max == null) {
                max = temp;
            } else {
                if (temp.getGain() > max.getGain()) {
                    max = temp;
                }
            }
        }
        return max;
    }

    public Double sigmaGain(String attr) {

        List<String> vals = DataSetContainer.ATTRIBUTES.get(attr).getValues();
        List<HashMap<String, String>> filterData = null;
        List<HashMap<String, String>> filterDataLabel = null;
        DecimalFormat fm = new DecimalFormat("#.##");

        double count = 0;
        strSigma += "(";
        for (String val : vals) {

            EntropyModel model = new EntropyModel();
            filterData = FilteringService.getFiltering(listMaster, attr, val);

            int allData = listMaster.size();
            int result = filterData.size();
            int subRest = 0;

            List<String> labels = DataSetContainer.ATTRIBUTES.get(DataSetContainer.targetClass).getValues();

            Entropy entropy = new Entropy();
            HashMap<String, Integer> tempV = null;

            tempV = new HashMap<String, Integer>();
            for (String temp : labels) {
                filterDataLabel = FilteringService.getFiltering(listMaster, attr, val, temp);
                tempV.put(temp.toString(), new Integer(filterDataLabel.size()));
            }

            entropy.setAllData(result);
            entropy.setLabels(tempV);

            model.setEntPi(entropy);

            model.countEntropy();

            double v = 0;


            if (result > 0) {
                strSigma += "(" + fm.format(result) + "/" + fm.format(allData) + ") ";
                v = (double) result / (double) allData;

            } else {
                strSigma += "(" + result + "/" + allData + ") ";
            }
            strSigma += "* " + fm.format(model.getEntropy());
            count += (v * model.getEntropy());
        }
//        strSigma += " = " + fm.format(count);
        return count;
//        }
    }
}
