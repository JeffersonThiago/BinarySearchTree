import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinarySearchTree<T> implements BinarySearchTreeInterface<T> {
    public Node<T> root;
    public int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public int size() {
        // returns the number of nodes in a tree
        return this.size;
    }

    @Override
    public int height(Node<T> v) {
        // returns the height of the node to the root

        if (isExternal(v)) {
            return 0;
        } else {
            int h = 0;
            Iterator<Node<T>> children = children(v);
            while (children.hasNext()) {
                h = Math.max(h, height((Node<T>) children.next()));

            }
            return h + 1;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Iterator<Node<T>> elements() {
         
        throw new UnsupportedOperationException("Unimplemented method 'elements'");
    }

    @Override
    public Iterator<Node<T>> nodes() {
         
        throw new UnsupportedOperationException("Unimplemented method 'nodes'");
    }

    @Override
    public Node<T> root() {
       return this.root;
    }

    @Override
    public Node<T> parent(Node<T> v) {
        return v.getParent();
    }

    @Override
    public Iterator<Node<T>> children(Node<T> v) {
        List<Node<T>> childrenList = new ArrayList<>();
        if (v.getLeftChild() != null) {
            childrenList.add(v.getLeftChild());
        }
        if (v.getRightChild() != null) {
            childrenList.add(v.getRightChild());
        }
        return childrenList.iterator();
    }
    @Override
    public boolean isInternal(Node<T> v) {
        return v.getLeftChild() != null || v.getRightChild() != null;
    }

    @Override
    public boolean isExternal(Node<T> v) {
        return v.getLeftChild() == null && v.getRightChild() == null;
    }

    @Override
    public boolean isRoot(Node<T> v) {
        return v.getParent() == null;
    }

    @Override
    public int depth(Node<T> v) {
        if (isRoot(v)) {
            return 0;
        }
        return 1 + depth(v.getParent());

    }

    @Override
    public Node<T> replace(Node<T> v, T element) {
        v.setElement(element);
        return v;
    }

    @Override
    public Node<T> insert(int key, T element) {
        // if the root is null, create a new node and set the root equal to the new node and increase the size
        if (this.root == null) {
            this.root = new Node<T>(key, element);
            ++this.size;
            return this.root;
        }

        Node<T> currentNode = this.root;
        Node<T> parent = null;

        while (currentNode != null) {
            parent = currentNode;

            if (key < currentNode.getKey()) {
                currentNode = currentNode.getLeftChild();
            } else if (key > currentNode.getKey()) {
                currentNode = currentNode.getRightChild();
            } else {
                
                return null;
            }
        }

        Node<T> newNode = new Node<T>(key, element);
        newNode.setParent(parent);

        if (key < parent.getKey()) {
            parent.setLeftChild(newNode);
        } else {
            parent.setRightChild(newNode);
        }

        ++this.size;
        return newNode;
    }

    @Override
    public Node<T> remove(int key) {
         
        Node<T> search = treeSearch(key, this.root);
        
        if (search == null) {
            System.out.println("Chave não encontrada!");
            return null;
        }

        Node<T> parent = search.getParent();
        
        if (isRoot(search)) {
            if(isExternal(search)){
                this.root=null;
            }
            else if (search.getLeftChild() != null && search.getRightChild() == null){
               this.root.setLeftChild(null);
               search.setParent(null);
               this.root = search;
            }
            else if (search.getLeftChild() == null && search.getRightChild() != null){
                this.root.setRightChild(null);
                search.setParent(null);
                this.root = search;
            }
            else if (search.getLeftChild() != null && search.getRightChild() != null){
                Node<T> sucessor = this.root.getRightChild();
                this.root.setRightChild(null);
                sucessor.setParent(null);
                search.setElement(sucessor.getElement());
                search.setKey(sucessor.getKey());
                
            }
        }

        else if (isExternal(search)) {
            if (search.getParent().getKey() > search.getKey()) {
                search.getParent().setLeftChild(null);
                search.setParent(null);
            } else if (search.getParent().getKey() < search.getKey()) {
                search.getParent().setRightChild(parent);
                search.setParent(null);
            }
        }

        else if (search.getLeftChild() != null && search.getRightChild() == null){
            search.getLeftChild().setParent(search.getParent());
            if (search.getKey() > parent.getKey()) {
                parent.setRightChild(search.getLeftChild());
                search.setRightChild(null);

            } else {
                parent.setLeftChild(search.getLeftChild());
                search.setLeftChild(null);
            }
            search.setParent(null);

        }

        else if (search.getLeftChild() == null && search.getRightChild() != null){
            search.getRightChild().setParent(search.getParent());
            if (search.getKey() > search.getRightChild().getKey()) {
                parent.setRightChild(search.getRightChild());
                search.setRightChild(null);

            } else {
                parent.setLeftChild(search.getRightChild());
                search.setLeftChild(null);
            }
            search.setParent(null);
            
        }

        else if (search.getLeftChild() != null && search.getRightChild() != null) {
            Node<T> sucessor = successor(search.getRightChild());

            if (sucessor.getParent().getKey() > sucessor.getKey()) {
                sucessor.getParent().setLeftChild(null);
                sucessor.setParent(null);
            } else if (sucessor.getParent().getKey() < sucessor.getKey()) {
                sucessor.getParent().setRightChild(null);
                sucessor.setParent(null);
            }
            search.setElement(sucessor.getElement());
            search.setKey(sucessor.getKey());

        }
        --this.size;
        return search;
    }

    public Node<T> successor(Node<T> v) {
        while (v.getLeftChild() != null) {
            v = v.getLeftChild();
        }
        return v;
    }

    @Override
    public Node<T> treeSearch(int key, Node<T> v) {

        if (v == null || key == v.getKey()) {
            return v;
        }
        if (key < v.getKey()) {
            return treeSearch(key, v.getLeftChild());
        }
        return treeSearch(key, v.getRightChild());
    }
    @Override
    public void inOrder(Node<T> v) {
        if (v != null) {
            inOrder(v.getLeftChild());
            System.out.println("Chave:" + v.getKey() + " Elemento:" + v.getElement());
            inOrder(v.getRightChild());
        }
    }

    @Override
    public void preOrder(Node<T> v) {
        if (v != null) {
            System.out.println("Chave:" + v.getKey() + " Elemento:" + v.getElement());
            preOrder(v.getLeftChild());
            preOrder(v.getRightChild());
        }
    }

    @Override
    public void postOrder(Node<T> v) {
        if (v != null) {
            postOrder(v.getLeftChild());
            postOrder(v.getRightChild());
            System.out.println("Chave:" + v.getKey() + " Elemento:" + v.getElement());
        }
    }

    public Node<T> leftChild(Node<T> v) {
        
        if (v != null) {
            return v.getLeftChild();
        }
        return null;
    }

    @Override
    public Node<T> rightChild(Node<T> v) {
        if (v != null) {
            return v.getRightChild();
        }
        return null;
    }

    @Override
    public boolean hasLeft(Node<T> v) {
        return v != null && v.getLeftChild() != null;
    }

    @Override
    public boolean hasRight(Node<T> v) {
        return v != null && v.getRightChild() != null;
    }
    
    public int populateMatrixInOrder(Node<T> currentNode, Node<T>[][] matrix, int currentColumn) {
        if (currentNode != null) {
            currentColumn = populateMatrixInOrder(currentNode.getLeftChild(), matrix, currentColumn);
            int currentRow = depth(currentNode);
            matrix[currentRow][currentColumn] = currentNode;
            ++currentColumn;
            return populateMatrixInOrder(currentNode.getRightChild(), matrix, currentColumn);
        }
        return currentColumn;
    }
    
    public void printTreeMatrix() {
        int numColumns = size();
        int numRows = height(this.root()) + 1;

        @SuppressWarnings("unchecked")
        Node<T>[][] matrix = new Node[numRows][numColumns];
        populateMatrixInOrder(this.root(), matrix, 0); // Pass matrix as Node<T>[][]
    
        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                if (matrix[row][column] == null) {
                    System.out.print("\t");
                } else {
                    System.out.print(
                            matrix[row][column].getKey() + "\t");
                }
            }
            System.out.println();
        }
    }
}
