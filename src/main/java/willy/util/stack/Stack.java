/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package willy.util.stack;

/**
 *
 * @author informatica
 */
public interface Stack<E> {
    public E peek();
    //Allows you to retrieve the value at the top of a stack without actually removing it.
    public E pop();
    //Removes the value at the top of the stack and returns it.
    public boolean push (E element);
    //Adds a value to the top of the stack.
    
}
