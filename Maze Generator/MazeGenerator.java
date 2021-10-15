/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tsl62
 */

/*
Idea of algorithm:
1. Start with all interior edges of the maze (ones between 2 cells, not on edge)
2. Select a random edge
3. If the 2 cells are not connected, remove the edge (use union-find)
4. Repeat until all cells are connected
*/
import java.util.*;

public class MazeGenerator { 
    private class quickUF
    {
        int[] group; //value of group[n] is the group the nth object belongs to
        int numGroups;
        int[] groupSizes; //size of the nth group stored at index n
        
        public quickUF(int size)
        {
            group = new int[size];
            numGroups = size;
            groupSizes = new int[size];
            //init each object in separate group
            for(int i = 0; i < size; i++)
            {
                groupSizes[i] = 1;
                group[i] = i;
            }
        }
        
        int find(int p) //returns group p belongs to
        {
            while(p != group[p])
            {
                p = group[p];
            }
            return p;
        }
    
        boolean connected(int p, int q)
        {
            return find(p) == find(q);
        }

        void union(int p, int q)
        {
            int i = find(p);
            int j = find(q);

            if(i == j)
            {
                return;
            }
            //quick union-find: always merge the smaller group
            if(groupSizes[i] < groupSizes[j])
            {
                group[i] = j;
                groupSizes[j] += groupSizes[i];
            }
            else
            {
                group[j] = i;
                groupSizes[i] += groupSizes[j];
            }
            numGroups--;
        }
    }
    
    private quickUF maze;
    private int height;
    private int width;
    private ArrayList<Edge> edges;
    
    public MazeGenerator(int height, int width)
    {
        this.height = height;
        this.width = width;
        reset();
    }
    
    public void reset() //regenerate union-find and all edges between 2 cells
    {   
        maze = new quickUF(width*height);
        edges = new ArrayList<>();
        //to use union-find, cells are numbered with 1d coords
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                Edge edge;
                if(j < width-1) //check that it is not the right-most cell 
                {
                    //generate edge between cell and one to the right
                    edge = new Edge((width*i+j), (width*i+j+1), width); //edge with cell to the right
                    edges.add(edge);
                }
                if(i < height-1) //check it is not in bottom row
                {
                    //generate edge between cell and one below
                    edge = new Edge((width*i+j), (width*(i+1)+j), width); // edge with cell below
                    edges.add(edge);
                }
            }
        }
    }
    
    public ArrayList<Edge> generateMaze()
    {
        ArrayList<Edge> edgesToRM = new ArrayList<>();
        while(maze.numGroups > 1) //until all cells are connected
        {
            int edgeToCheck = (int)(Math.random()*edges.size());
            int cellA = edges.get(edgeToCheck).cellA;
            int cellB = edges.get(edgeToCheck).cellB;
            
            if(!maze.connected(cellA, cellB)) //remove edge if cells not already connected
            {
                edgesToRM.add(edges.remove(edgeToCheck));
                maze.union(cellA, cellB);
            }
        }
        
        return edgesToRM;
    }
    
    public ArrayList<Edge> getEdges()
    {
        return edges;
    }
}
