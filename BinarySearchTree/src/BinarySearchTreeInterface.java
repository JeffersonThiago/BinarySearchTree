import java.util.Iterator;

public interface BinarySearchTreeInterface<T> {
    // Métodos genéricos
    public int size();

    public int height(Node<T> v);

    public boolean isEmpty();

    public Iterator<Node<T>>  elements();

    public Iterator<Node<T>>  nodes();

    // Métodos de acesso
    public Node<T> root();

    public Node<T> parent(Node<T> v);

    public Iterator<Node<T>> children(Node<T> v);

    // Métodos de consulta
    public boolean isInternal(Node<T> v);

    public boolean isExternal(Node<T> v);

    public boolean isRoot(Node<T> v);

    public int depth(Node<T> v);

    // Métodos de atualização
    public Node<T> replace(Node<T> v, T element);

    // Métodos específicos de árvore binária de busca
    public Node<T> insert(int key, T element);

    public Node<T> remove(int key);

    public Node<T> treeSearch(int key, Node<T> v);

    public void inOrder(Node<T> v);

    public void preOrder(Node<T> v);

    public void postOrder(Node<T> v);

    public Node<T> leftChild(Node<T> v);

    public Node<T> rightChild(Node<T> v);

    public boolean hasLeft(Node<T> v);

    public boolean hasRight(Node<T> v);
}