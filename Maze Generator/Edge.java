/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tsl62
 */
public class Edge {
    //edge between two cells
    public int cellA;
    public int cellB;
    public int width; //width of maze, in # of cells
    public int aX;
    public int aY;
    public int bX;
    public int bY;
    public Edge(int a, int b, int width)
    {
        this.cellA = a;
        this.cellB = b;
        this.width = width;
        //translate from 1d (numbered sequentially from upper left) to 2d coords
        aX = a % width;
        aY = a / width;
        bX = b % width;
        bY = b / width;
    }
    public String toString()
    {
        return "Edge separating (" + aX + ", " + aY + ") and (" + bX + ", " + bY + ")";
    }
}