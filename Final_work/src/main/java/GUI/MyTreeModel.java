package GUI;



import Region_Logic.*;
import java.util.ArrayList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Andrey
 */
public class MyTreeModel implements TreeModel {

    private Node root;
    private Node objectsNode;

    public MyTreeModel() {
        root = new Node("Корневая запись");
        objectsNode = new Node("Объекты интереса");
        root.addChild(objectsNode);

    }

    public void addObjectsList(ArrayList<ObjectInterest> list) {
        addNode(list);
    }

    public void addNode(ArrayList<ObjectInterest> list) {
        for (ObjectInterest obj : list) {
            Node node = new Node(obj.getObjectType());
            objectsNode.addChild(node);
            for (InsideObjectType type : obj.getInsideObjects()) {
                node.addChild(new Node(type.toString()));
            }
        }

    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public int getChildCount(Object parent) {
        Node node = (Node) parent;
        return node.getChildren().size();
    }

    @Override
    public Object getChild(Object parent, int index) {
        Node node = (Node) parent;
        return node.getChildren().get(index);
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        Node node = (Node) parent;
        return node.getChildren().indexOf(child);
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((Node) node).getChildren().isEmpty();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
    }
}
