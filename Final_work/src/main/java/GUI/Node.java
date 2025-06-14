
package GUI;

import java.util.ArrayList;


/**
 * Класс {@code Node} представляет узел в древовидной структуре.
 * Каждый узел содержит имя и список дочерних узлов.
 */
class Node {

    /** Имя узла, используемое для отображения. */
    private String name;

    /** Список дочерних узлов. */
    private ArrayList<Node> children;

    /**
     * Создаёт новый узел с указанным именем.
     *
     * @param name имя узла
     */
    public Node(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    /**
     * Добавляет дочерний узел к текущему узлу.
     *
     * @param child дочерний узел для добавления
     */
    public void addChild(Node child) {
        children.add(child);
    }

    /**
     * Возвращает список всех дочерних узлов.
     *
     * @return список дочерних узлов
     */
    public ArrayList<Node> getChildren() {
        return children;
    }

    /**
     * Возвращает строковое представление узла (его имя).
     *
     * @return имя узла
     */
    @Override
    public String toString() {
        return name;
    }

}
