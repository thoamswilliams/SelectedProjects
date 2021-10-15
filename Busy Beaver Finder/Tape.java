/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmsimulator;

/**
 *
 * @author tsl62
 */
public class Tape { //models the infinite tape with doubly-linked list
    private class Node
    {
        int value;
        Node right;
        Node left;
        
        public Node()
        {
            value = 0;
        }
    }
    
    private Node current;
    
    public Tape()
    {
        current = new Node();
    }
    
    public void write(int newValue)
    {
        current.value = newValue;
    }
    
    public int read()
    {
        return current.value;
    }
    
    public void moveRight()
    {
        if(current.right == null)
        {
            Node newRight = new Node();
            newRight.left = current;
            current.right = newRight;
            current = newRight;
        }
        else
        {
            current = current.right;
        }
    }
    
    public void moveLeft()
    {
        if(current.left == null)
        {
            Node newLeft = new Node();
            newLeft.right = current;
            current.left = newLeft;
            current = newLeft;
        }
        else
        {
            current = current.left;
        }
    }
        
    public int numOnes()
    {
        Node reader = current;
        int numOnes = 0;
        
        while(!(reader == null))
        {
            if(reader.value == 1)
            {
                numOnes ++;
            }
            reader = reader.left;
        }
        
        reader = current.right;
        while(!(reader == null))
        {
            if(reader.value == 1)
            {
                numOnes ++;
            }
            reader = reader.right;
        }   
        
        return numOnes;
    }
    
    public String toString()
    {
        Node reader = current;
        String output = "";
        
        while(!(reader.left == null))
        {
            reader = reader.left;
        }
        while(!(reader == null))
        {
            output += reader.value;
            output += " ";
            reader = reader.right;
        }
        return output;
    }
}
