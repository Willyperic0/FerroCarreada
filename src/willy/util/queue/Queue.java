/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package willy.util.queue;

/**
 *
 * @author informatica
 */
public interface Queue<E> {
    public E peek();
    //Allows you to retrieve the value at the front of a queue without actually removing it,
    public E extract();
    //Removes the value at the front of the queue and returns it.
    public boolean insert (E element);
    
}
