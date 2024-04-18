package willy.maps.Ejercicios;

import willy.maps.binaryTree.bintreeSearch.BinTreeSearch;
import willy.maps.binaryTree.node.Node;

public class ExpressionEvaluator {
 /*
  Supongamos que tenemos una función valor tal que dado un valor de tipo char(una letra del alfabeto)
  devuelve un valor entero asociado a dicho identificador.Supongamos tambien la existencia de un árbol 
  de expresión T cuyos nodos hoja son letras del alfabeto y cuyos nodos interiores son los caracteres *,+,-,/.
  Diseñar una función que tome como parámetros un nodo y un árbol binario y devuelva el resultado entero 
  de la evaluación de la expresión representada. 
  */
    // Función para evaluar una expresión representada por un árbol binario de búsqueda
    public static int evaluateExpression(Node<Character> node, BinTreeSearch<Character> tree) {
        if (node == null) {
            // Si el nodo es nulo, devolvemos 0
            return 0;
        }

        // Si el nodo es una hoja (es decir, una letra), devolvemos su valor numérico
        if (node.getLeft() == null && node.getRight() == null) {
            return getValue(node.getRoot());
        }

        // Evaluamos las subexpresiones izquierda y derecha recursivamente
        int leftValue = evaluateExpression(node.getLeft(), tree);
        int rightValue = evaluateExpression(node.getRight(), tree);

        // Realizamos la operación indicada por el nodo actual y devolvemos el resultado
        switch (node.getRoot()) {
            case '+':
                return leftValue + rightValue;
            case '-':
                return leftValue - rightValue;
            case '*':
                return leftValue * rightValue;
            case '/':
                // Manejamos la división por cero
                if (rightValue == 0) {
                    throw new ArithmeticException("División por cero");
                }
                return leftValue / rightValue;
            default:
                throw new IllegalArgumentException("Operador inválido: " + node.getRoot());
        }
    }

    // Función para obtener el valor numérico asociado a un carácter
    private static int getValue(char c) {
        // Aquí podríamos implementar la lógica para obtener el valor asociado a cada letra
        // Por simplicidad, asumiremos que el valor de 'A' es 1, 'B' es 2, 'C' es 3, etc.
        return c - 'A' + 1;
    }
        // Función para imprimir el árbol de expresión
        public static void printExpressionTree(Node<Character> node, int nivel) {
            if (node != null) {
                printExpressionTree(node.getRight(), nivel + 1);
                for (int i = 0; i < nivel; i++) {
                    System.out.print("\t");
                }
                System.out.println(node.getRoot());
                printExpressionTree(node.getLeft(), nivel + 1);
            }
        }

    public static void main(String[] args) {
        // Creamos el árbol de expresión
        BinTreeSearch<Character> expressionTree = new BinTreeSearch<>();

        // Creamos los nodos del árbol
        Node<Character> rootNode = new Node<>('+');
        Node<Character> leftNode = new Node<>('*');
        Node<Character> rightNode = new Node<>('-');
        Node<Character> leftLeftNode = new Node<>('A');
        Node<Character> leftRightNode = new Node<>('B');
        Node<Character> rightLeftNode = new Node<>('C');
        Node<Character> rightRightNode = new Node<>('D');

        // Establecemos las relaciones entre los nodos
        rootNode.setLeft(leftNode);
        rootNode.setRight(rightNode);
        leftNode.setLeft(leftLeftNode);
        leftNode.setRight(leftRightNode);
        rightNode.setLeft(rightLeftNode);
        rightNode.setRight(rightRightNode);

        // Agregamos los nodos al árbol uno por uno
        expressionTree.insert(rootNode.getRoot());
        expressionTree.insert(leftNode.getRoot());
        expressionTree.insert(rightNode.getRoot());
        expressionTree.insert(leftLeftNode.getRoot());
        expressionTree.insert(leftRightNode.getRoot());
        expressionTree.insert(rightLeftNode.getRoot());
        expressionTree.insert(rightRightNode.getRoot());

        // Imprimimos el árbol de expresión
        printExpressionTree(rootNode, 0);

        // Evaluamos la expresión y mostramos el resultado
        int result = evaluateExpression(rootNode, expressionTree);
        System.out.println("El resultado de la evaluación es: " + result);
    }
}


