/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tsl62
 */
public class Stack<O> {
    private class Node //linked-list is more efficient
    {
        public O value;
        public Node next;
        
        public Node(O value, Node next)
        {
            this.value = value;
            this.next = next;
        }
    }
    
    private Node first;
    
    public Stack()
    {
        first = null;
    }
           
    public boolean isEmpty()
    {
        return first == null;
    }
    
    public void push(O item) //put element on top
    {
        Node cache = first;
        first = new Node(item, cache);
    }
    
    public O pop() //retrieve top element
    {
        O output = first.value;
        first = first.next;
        return output;
    }
                
          
}
