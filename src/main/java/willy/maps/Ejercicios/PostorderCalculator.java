package willy.maps.Ejercicios;


import willy.maps.binaryTree.bintree.Bintree;
import willy.maps.binaryTree.node.Node;

public class PostorderCalculator {

    private static StringBuilder postorder;

    // Método para calcular el recorrido en postorden dado el recorrido en preorden e inorden
    public static void calculatePostorder(char[] preorder, char[] inorder) {
        Bintree<Character> tree = constructTree(preorder, inorder);
        postorder = new StringBuilder();
        generatePostorder(tree.root);
        System.out.println("El postorden calculado es: " + postorder.toString());
    }

    // Método para construir el árbol binario a partir de los recorridos en preorden e inorden
    private static Bintree<Character> constructTree(char[] preorder, char[] inorder) {
        Bintree<Character> tree = new Bintree<>();
        tree.root = buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        return tree;
    }

    // Método auxiliar para construir el árbol binario recursivamente
    private static Node<Character> buildTree(char[] preorder, char[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) return null;

        char rootValue = preorder[preStart];
        Node<Character> rootNode = new Node<>(rootValue);

        int rootIndex = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootValue) {
                rootIndex = i;
                break;
            }
        }

        int leftSubtreeSize = rootIndex - inStart;

        rootNode.setLeft(buildTree(preorder, inorder, preStart + 1, preStart + leftSubtreeSize, inStart, rootIndex - 1));
        rootNode.setRight(buildTree(preorder, inorder, preStart + leftSubtreeSize + 1, preEnd, rootIndex + 1, inEnd));

        return rootNode;
    }
    

    public static void drawTree(char[] preorder, char[] inorder) {
        Bintree<Character> tree = constructTree(preorder, inorder);
        draw(tree.root, 0);
    }
    private static void draw(Node<Character> node, int level) {
        if (node == null) return;
    
        draw(node.getRight(), level + 1);
        for (int i = 0; i < level; i++) {
            System.out.print("   ");
        }
        System.out.println(node.getRoot());
        draw(node.getLeft(), level + 1);
    }
    

    // Método para generar el recorrido en postorden del árbol binario
    private static void generatePostorder(Node<Character> root) {
        if (root == null) return;
        generatePostorder(root.getLeft());
        generatePostorder(root.getRight());
        postorder.append(root.getRoot());
    }

    public static void main(String[] args) {
        char[] preorder = {'G', 'E', 'A', 'I', 'B', 'M', 'C', 'L', 'D', 'F', 'K', 'J', 'H'};
        char[] inorder = {'I', 'A', 'B', 'E', 'G', 'L', 'D', 'C', 'F', 'M', 'K', 'H', 'J'};

        System.out.println("El preorden es: " + new String(preorder));
        System.out.println("El inorden es: " + new String(inorder));

        calculatePostorder(preorder, inorder);
        drawTree(preorder, inorder);
    }
}
