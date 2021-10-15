/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tsl62
 */
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

/* 
 * This is like the FontDemo applet in volume 1, except that it 
 * uses the Java 2D APIs to define and render the graphics and text.
 */

public class MazeDrawer extends JApplet {

    final static Color black = Color.black;
    final static Color red = Color.red;
    final static Color white = Color.white;
    final static BasicStroke stroke = new BasicStroke(4.0f);
    final static BasicStroke wideStroke = new BasicStroke(8.0f);

    
    Dimension totalSize; 
    FontMetrics fontMetrics;
    private ArrayList<Edge> edges;
    private int numWidth;
    private int numHeight;
    private int cellWidth;
    private int cellHeight;
    
    public MazeDrawer(ArrayList<Edge> edges, int numWidth, int numHeight)
    {
        this.edges = edges;
        this.numWidth = numWidth;
        this.numHeight = numHeight;
    }
    
    public void init() {
        //Initialize drawing colors
        setBackground(white);
        setForeground(black);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension d = getSize();
        int cellWidth = d.width/numWidth;
        int cellHeight = d.height/numHeight;
        g2.clearRect(0,0,d.width,d.height);
        g2.setStroke(wideStroke);
        g2.draw(new Rectangle2D.Double(0, 0, d.width, d.height));
        g2.setStroke(stroke);
        
        for(Edge edge: edges)
        {
            if(edge.aY == edge.bY) //check if cells are left-right or top-bottom aligned
            {
                int startX = (1+edge.aX)*cellWidth; //draw vertical line separating cells
                int endX = startX;
                int startY = edge.aY*cellHeight;
                int endY = startY + cellHeight;
                g2.draw(new Line2D.Double(startX, startY, endX, endY));
                
            }
            else //draw horizontal line separating cells
            {
                int startX = edge.aX*cellWidth;
                int endX = startX + cellWidth;
                int startY = (1+edge.aY)*cellHeight;
                int endY = startY;
                g2.draw(new Line2D.Double(startX, startY, endX, endY));
            }
        }
        
        //draw starting and ending points
        g2.setPaint(red);
        g2.fill(new Ellipse2D.Double(d.width - cellWidth*3/4, d.height - cellHeight*3/4, cellWidth/2, cellHeight/2));
        g2.fill(new Rectangle2D.Double(cellWidth*1/4, cellHeight*1/4, cellWidth/2, cellHeight/2));
    }

}
