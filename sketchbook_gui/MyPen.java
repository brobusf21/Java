
/**
 * Program DrawSomething draws lines of four different sizes depending on user's choice.
 * It also gives user the option of choosing the color of the pen, the option to undo 
 * last drawing and/or the option to delete all drawings from the panel. 
 * Input: none.
 * Output: none.
 * Authors: Atakelti Wubneh, Brandon Robinson, Josiah Meyer
 * Last modified: 07/25/2016
**/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public abstract class MyPen extends Point {
	
   //Instance data
	protected Color color;
	protected int penSize;
   
	protected int x; // x coordinate
	protected int y; // y coordinate 
     
   protected ArrayList<Integer> xList;  // List of x-coord
   protected ArrayList<Integer> yList;  // List of x-coord
   
	// ArrayList to store last line drawn
   private ArrayList<MyPen> lines = new ArrayList<MyPen>();
	private MyPen currentLine;
   
	/** MyPen constructor one that takes four parameters **/
	public MyPen(int x, int y, int penSize, Color color) {
      
      xList = new ArrayList<Integer>();
      yList = new ArrayList<Integer>();
      
      this.x = x;
      this.y = y;
      this.penSize = penSize;
      this.color = color;
	}

   public void addPoint(int x, int y) {    
      xList.add(x);
      yList.add(y);
   }
   
   //Getters and Setters
	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

	public void setPenSize(int penSize) {
		this.penSize = penSize;
	}

	public int getPenSize() {
		return this.penSize;
	}

   @Override
   public String toString() {
      return "There is nothing to output!";
   }
   
	// method "DrawLines' must implement the graphics method 'draw'
	public abstract void drawLines(Graphics g);
	public abstract void drawOval(Graphics g);
	//public abstract void drawRectangle(Graphics g);
}