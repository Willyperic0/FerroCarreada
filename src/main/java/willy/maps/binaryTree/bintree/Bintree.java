/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package willy.maps.binaryTree.bintree;

import willy.maps.binaryTree.node.Node;
import willy.queue.list.Queue;

/**
 *
 * @author Usuario
 */
public class Bintree<E> {
    private Node<E> root;
    
    public Bintree(E element){
        this.root = new Node<E>(element);
    }
    
    public Bintree(){
        this.root = null;
    }
    
    public String preOrder(Node<E> root) {
    if (root != null) {
        return "";
    }
    String P = root.getRoot().toString();
    P += preOrder(root.getLeft());
    P += preOrder(root.getRight());
    return P;
    }
    
    public String inOrder(Node<E> root) {
    if (root != null) {
        return "";
    }
    String P = inOrder(root.getLeft());
    P += root.getRoot().toString();
    P += inOrder(root.getRight());
    return P;
    }
    
    public String postOrder(Node<E> root) {
    if (root != null) {
        return "";
    }
    String P = postOrder(root.getLeft());
    P += postOrder(root.getRight());
    P += root.getRoot().toString();
    return P;
    }
    
    
    //Busqueda en anchura
    public void bfs(){
        if (root != null){
            /*inicializar cola
            cola.insert(this);
            while (!cola.isEmpty()){
            Bintree<E> actual = cola.poll();
            if (!actual.isEmpty()){
                
                }
            }
            */
        }
    }
    
    //Siguiente calcular altura
    
    //Siguiente calcular si el arbol esta completo
    
    //Metodo de insercion con cualquiera de estos 4 
    //Metodo para eliminar

    //LIBRO QUE EXPLICA ARBOLES OSWALDO ESTRUCTURAD DE DATOS DE OSWALDO 
}
