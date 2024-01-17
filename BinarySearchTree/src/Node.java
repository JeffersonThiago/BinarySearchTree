public class Node<T> {
    private Node<T> parent;
    private Node<T> leftChild;
    private Node<T> rightChild;
    private T element;
    private int key;

    public Node(int key, T element) {
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
        this.element = element;
        this.key = key;

    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}