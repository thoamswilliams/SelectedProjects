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

//State: print + move + newState

public class TuringHead {
    private String stateA0; //6 possible turing machine states
    private String stateA1;
    private String stateB0;
    private String stateB1;
    private String stateC0;
    private String stateC1;
    
    private Character currentState;
    private Tape record;
    private int numSteps = 0;
    
    public TuringHead(String a0, String a1, String b0, String b1, String c0, String c1)
    {
        currentState = 'A';
        record = new Tape();
        stateA0 = a0;
        stateB0 = b0;
        stateC0 = c0;
        stateA1 = a1;
        stateB1 = b1;
        stateC1 = c1;
    }
    
    private String getInstruction() //select instruction based on current state and number read
    {
        String zeroState = "";
        String oneState = "";
        String instruction = "";
        switch(currentState)
        {
            case 'A':
                zeroState = stateA0;
                oneState = stateA1;
                break;
            case 'B':
                zeroState = stateB0;
                oneState = stateB1;
                break;
            case 'C':
                zeroState = stateC0;
                oneState = stateC1;
                break;
        }
        int curVal = record.read();
        switch(curVal)
        {
            case 0:
                instruction = zeroState;
                break;
            case 1:
                instruction = oneState;
                break;
        }
        
        return instruction;
    }
    
    private void step()//run 1 cycle of the machine (read, write, move, change state)
    {
        numSteps ++;
        String info = getInstruction();
        record.write(Character.getNumericValue(info.charAt(0)));
        if(info.charAt(1) == 'R')
        {
            record.moveRight();
        }
        else if(info.charAt(1) == 'L')
        {
            record.moveLeft();
        }
        currentState = info.charAt(2);
    }
    
    public void execute()
    {
        while(!(currentState == 'H') && numSteps < 25) //run until halting or time-out
        {
            step();
        }
        if(numSteps > 24)
        {
            numSteps = -1;
        }
    }
    
    public int countOnes()
    {
        if(numSteps == -1)
        {
            return -1;
        }
        else
        {
            return record.numOnes();   
        }
    }
    
    public int countSteps()
    {
        return numSteps;
    }
    
    public String toString()
    {
        String output = "A0 |" + stateA0 + "\nA1 |" + stateA1 + "\nB0 |" + stateB0 +
                "\nB1 |" + stateB1 + "\nC0 |" + stateC0 + "\nC1 |" + stateC1;
        
        return output;
    }
}
