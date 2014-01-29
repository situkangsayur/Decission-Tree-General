/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.learning.id3.models;

import com.learning.id3.Entities.AttrEntity;
import com.learning.id3.Entities.Entropy;
import com.learning.id3.Entities.Node;
import com.learning.id3.filter.FilteringService;
import com.learning.id3.services.DataSetContainer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author hendri
 */
public class Tree {

    private Node head;
    private Node mainHead;

    public Tree() {
        this.head = new Node();

        this.head.setAtributes(new HashMap<String, Integer>(DataSetContainer.TEMP_ATTR));
        this.head.setParent(null);
        this.head.setSplitData(DataSetContainer.DATA_SET);
        this.head.setName(null);
        this.head.setChilds(null);
        this.head.setEntropy(null);
        this.mainHead = head;
    }

    public Node getMainHead() {
        return mainHead;
    }

    public Node getHead() {
        return head;
    }

    public void constructTree() {
        if (head.getParent() == null) {
            startTree();
        }
    }

    public void forward(Node nodes, String pos) {
        List<Node> childs = nodes.getChilds();

        for (Node cursor : childs) {
            System.out.println("\n\n");
            System.out.println("------------------------------------------------------------");
            System.out.println("NODE : " + nodes.getName() + " | Branch " + cursor.getBranch());
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println(" node dataset : " + cursor.getSplitData().size());
            if (DataSetContainer.checkAttr(cursor.getAtributes())) {
                GainModel gainModel = new GainModel();
                gainModel.setNode(cursor);
                gainModel.countRootGain();


                if (cursor.getEntropy().getEntropy() != 0.0D) {
                    cursor.setName(gainModel.getMaxGain().getAttribute());

                    cursor.setChilds(new ArrayList<Node>());
                    cursor.setEntropy(gainModel.getMainEnt());
                    cursor.getAtributes().put(gainModel.getMaxGain().getAttribute(), new Integer(1));

//                pos += cursor.getName() + "\n|_";
//
//                Set<String> statAttr = cursor.getAtributes().keySet();
//                for (String key : statAttr) {
//                    System.out.println(key + " : " + cursor.getAtributes().get(key));
//                }



                    List<String> vals = DataSetContainer.ATTRIBUTES.get(gainModel.getMaxGain().getAttribute()).getValues();
                    List<Node> nextChilds = new ArrayList<Node>();

                    for (String val : vals) {
                        Node child = new Node();
                        child.setParent(cursor);
                        child.setBranch(val);
                        child.setSplitData(FilteringService.getFiltering(cursor.getSplitData(),
                                cursor.getName(), val));

                        child.setAtributes(new HashMap<String, Integer>(cursor.getAtributes()));
                        nextChilds.add(child);
                    }
                    cursor.setChilds(nextChilds);
//            if(cursor)

                    forward(cursor, pos);
                } else {
//                    forward(cursor, pos);
                }
            } else {
                cursor.setResult(DataSetContainer.FAIL_VAL);
                System.out.println("result : " + cursor.getResult() + "==============================================================================");
            }
        }
//        }

    }

    public void startTree() {
        String pos = "";
        GainModel gainModel = new GainModel();

        gainModel.setNode(head);

        gainModel.countRootGain();

        head.setName(gainModel.getMaxGain().getAttribute());
        head.setChilds(new ArrayList<Node>());
        head.setEntropy(gainModel.getMainEnt());
        head.getAtributes().put(gainModel.getMaxGain().getAttribute(), new Integer(1));
        List<String> vals = DataSetContainer.ATTRIBUTES.get(gainModel.getMaxGain().getAttribute()).getValues();
        List<Node> childs = new ArrayList<Node>();
        pos += head.getName() + "\n|_";

        for (String val : vals) {
            Node child = new Node();
            child.setParent(head);
            child.setBranch(val);
            child.setSplitData(FilteringService.getFiltering(head.getSplitData(),
                    head.getName(), val));
            child.setAtributes(new HashMap<String, Integer>(head.getAtributes()));
            childs.add(child);
        }
        head.setChilds(childs);
        forward(head, pos);


    }

    public void printTree() {
        mainHead.printNode(1);
    }

    public String classication(Node tree, HashMap<String, String> instance) {
        List<Node> listNode = tree.getChilds();
        String lastRest = "";
        for (Node temp : listNode) {
            if (instance.get(tree.getName()).equals(temp.getBranch())) {
//                tree = temp;
                if (temp.getResult() != null) {
                    lastRest = temp.getResult();
                    return lastRest;
                }

                return classication(temp, instance);
            } else {
                continue;
            }
        }
        return lastRest;
    }

    public void classication(Node tree, List<HashMap<String, String>> instances) {
    }
}
