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
public class TMSimulator {

    /**
     * @param args the command line arguments
     */
    /*
    When the turing machine reads a number on the tape, it has 3 actions: 
    printing a number on the tape (a), moving left or right (b), and changing
    its state(c), which is represented as a string abc
    
    The action a turing machine takes is dependent on the number it reads, and
    its current state. We simulate a 3-state turing machine, so it has 6 possible
    sets of actions (starting states a,b,c, and reading 0 or 1)
    */
    static String[] genTransStates() //generate all possible actions for the turing machine
    {
        Character[] printState = new Character[]{'0','1'}; //print a value
        Character[] moveState = new Character[]{'L','R'}; //moves right or left
        Character[] nextState = new Character[]{'A','B','C','H'}; //changes state to A,B,C, or HALT
        String[] transStates = new String[16];
        int index = 0;
        for(Character i:printState)
        {
            for(Character j:moveState)
            {
                for(Character k:nextState)
                {
                    transStates[index] = String.valueOf(i) + j + k;
                    index++;
                }
            }
        }
        return transStates;
    }
    
    public static TuringHead iterateAll(String[] transStates) //run all possible turing machines
    {
        for(String a0:transStates)
        {
            for(String a1:transStates)
            {
                for(String b0:transStates)
                {
                    for(String b1:transStates)
                    {
                        for(String c0:transStates)
                        {
                            for(String c1:transStates)
                            {
                                TuringHead test = new TuringHead(a0,a1,b0,b1,c0,c1);
                                test.execute();
                                if(test.countOnes() ==6 && test.countSteps() == 21) //look for busy beavers with known values
                                {
                                    return test;
                                }
                            }
                        }
                    }
                }
            }  
        }
        return null;
    }
    
    public static void main(String[] args) {
        long currentTime = System.nanoTime();
        String[] transStates = genTransStates();
        TuringHead result = iterateAll(transStates);
        
        if(!(result == null))
        {
            System.out.println(result);
        }
        else
        {
            System.out.println("No matches found");
        }
        
        System.out.println((System.nanoTime() - currentTime)/10e8);
    }
    
}
