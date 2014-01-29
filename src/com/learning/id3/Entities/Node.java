/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.id3.Entities;

import com.learning.id3.models.EntropyModel;
import com.learning.id3.models.GainModel;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author hendri
 */
public class Node {

    private Node parent;
    private String name;
    private String branch;
    private List<Node> childs;
    private HashMap<String, Integer> atributes;
    private List<HashMap<String, String>> splitData;
    private EntropyModel entropy;
    private GainModel gain;
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Node() {
        this.parent = null;
        this.name = null;
        this.branch = null;
        this.childs = null;
        this.atributes = null;
        this.splitData = null;
        this.entropy = null;
        this.gain = null;
        this.result = null;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public GainModel getGain() {
        return gain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChilds() {
        return childs;
    }

    public void setChilds(List<Node> childs) {
        this.childs = childs;
    }

    public HashMap<String, Integer> getAtributes() {
        return atributes;
    }

    public void setAtributes(HashMap<String, Integer> atributes) {
        this.atributes = atributes;
    }

    public List<HashMap<String, String>> getSplitData() {
        return splitData;
    }

    public void setSplitData(List<HashMap<String, String>> splitData) {
        this.splitData = splitData;
    }

    public EntropyModel getEntropy() {
        return entropy;
    }

    public void setEntropy(EntropyModel entropy) {
        this.entropy = entropy;
    }

    public void printNode(int level) {
        //print 
        //print -- sesuai level

        for (int i = 0; i < level - 1; i++) {
//            if (i == level - 2) {
//                System.out.print("| ");
//            } else {
                System.out.print("  ");
//            }
        }
        System.out.print("|___");
        //print branch: name
        if (this.branch != null) {
            System.out.print(this.branch + ":");
        }
        if (this.name != null) {
            System.out.print(this.name);
        }

        //if result != null print result
        if (this.result != null) {
            System.out.print(this.result);
        }

        System.out.println("");

        //if child nya > 0 print child
        if (this.childs != null) {
            for (int i = 0; i < this.childs.size(); i++) {
                this.childs.get(i).printNode(level + 1);
            }
        }
    }
}
