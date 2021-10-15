/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tsl62
 */
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import javax.swing.JApplet;
import javax.swing.JFrame;

public class MazeTester {
    public static void main(String[] args)
    {
        MazeGenerator test = new MazeGenerator(15,15);
        ArrayList<Edge> knockedDown = test.generateMaze();
        ArrayList<Edge> edges = test.getEdges();
        System.out.println("Knock down the following walls:");
        for(Edge i: knockedDown)
        {
            System.out.println(i);
        }
        
        //draw maze
        JFrame f = new JFrame("Maze Drawing");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        JApplet applet = new MazeDrawer(edges, 15, 15);
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();
        f.setSize(new Dimension(900, 900));
        f.setVisible(true);
    }
}
