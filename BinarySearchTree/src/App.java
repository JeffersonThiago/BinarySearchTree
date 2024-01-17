public class App {
    public static void main(String[] args) throws Exception {
        BinarySearchTree<Object> a = new BinarySearchTree<Object>(); 

        a.insert(10, "cinquenta");
        a.insert(5, "vinte");
        a.insert(22, "noventa");
        a.insert(2, "dez");
        a.insert(8, "quarenta");
        a.insert(15, "trinta");
        a.insert(25, "trinta");

        a.printTreeMatrix();
        a.remove(5);
        a.printTreeMatrix();
        a.inOrder(a.root());
        System.out.print(a.size());
    }
}
