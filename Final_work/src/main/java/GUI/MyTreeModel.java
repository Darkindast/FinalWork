package GUI;

import Region_Logic.*;
import java.util.ArrayList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * Класс {@code MyTreeModel} реализует интерфейс {@link TreeModel} для отображения структуры объектов интереса
 * и их внутренних объектов (например, дом, костёр и т.д.) в древовидной форме.
 */
public class MyTreeModel implements TreeModel {
    /** Корневой узел дерева. */
    private Node root;
    
    /** Узел, содержащий список объектов интереса. */
    private Node objectsNode;
    
    /**
     * Конструктор, инициализирующий корень дерева и добавляющий подузел для объектов интереса.
     */ 
    public MyTreeModel() {
        root = new Node("Корневая запись");
        objectsNode = new Node("Объекты интереса");
        root.addChild(objectsNode);

    }
    /**
     * Добавляет в дерево список объектов интереса и их вложенные объекты.
     *
     * @param list список объектов интереса
     */
    public void addObjectsList(ArrayList<ObjectInterest> list) {
        addNode(list);
    }
    
    /**
     * Добавляет узлы объектов интереса и их вложенные объекты в дерево.
     *
     * @param list список объектов интереса
     */
    public void addNode(ArrayList<ObjectInterest> list) {
        for (ObjectInterest obj : list) {
            Node node = new Node(obj.getObjectType());
            objectsNode.addChild(node);
            for (InsideObjectType type : obj.getInsideObjects()) {
                node.addChild(new Node(type.toString()));
            }
        }

    }
    /**
     * Возвращает корневой узел дерева.
     *
     * @return объект, представляющий корень дерева
     */
    @Override
    public Object getRoot() {
        return root;
    }
    /**
     * Возвращает количество дочерних узлов у заданного узла.
     *
     * @param parent родительский узел
     * @return количество детей у узла
     */
    @Override
    public int getChildCount(Object parent) {
        Node node = (Node) parent;
        return node.getChildren().size();
    }
    /**
     * Возвращает дочерний элемент по индексу у указанного родителя.
     *
     * @param parent родительский узел
     * @param index  индекс дочернего узла
     * @return дочерний узел по указанному индексу
     */
    @Override
    public Object getChild(Object parent, int index) {
        Node node = (Node) parent;
        return node.getChildren().get(index);
    }
    
    /**
     * Возвращает индекс дочернего узла относительно родителя.
     *
     * @param parent родительский узел
     * @param child  дочерний узел
     * @return индекс дочернего узла
     */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        Node node = (Node) parent;
        return node.getChildren().indexOf(child);
    }
    /**
     * Проверяет, является ли указанный узел листом (не имеет дочерних узлов).
     *
     * @param node проверяемый узел
     * @return true, если узел — лист; false в противном случае
     */
    @Override
    public boolean isLeaf(Object node) {
        return ((Node) node).getChildren().isEmpty();
    }
    /**
     * Метод не реализован. Изменение значения по пути не поддерживается.
     *
     * @param path     путь к изменяемому элементу
     * @param newValue новое значение (игнорируется)
     */
    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
    }
    
     /**
     * Метод не реализован. Добавление слушателя модели не поддерживается.
     *
     * @param l слушатель дерева
     */
    @Override
    public void addTreeModelListener(TreeModelListener l) {
    }
    
    /**
     * Метод не реализован. Удаление слушателя модели не поддерживается.
     *
     * @param l слушатель дерева
     */
    @Override
    public void removeTreeModelListener(TreeModelListener l) {
    }
}
