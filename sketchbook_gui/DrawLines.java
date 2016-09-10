
/**
 * Program DrawSomething draws lines of four different sizes depending on user's choice.
 * It also gives user the option of choosing the color of the pen, the option to undo 
 * last drawing or deletion as well as to clear all drawings from the panel. 
 * Input: none.
 * Output: none.
 * Authors: Atakelti Wubneh, Brandon Robinson, Josiah Meyer
 * Last modified: 07/25/2016
**/

import java.awt.*;
import java.util.*;

/** The DrawLines class inherits pen size and color from parent class MyPen **/
public class DrawLines extends MyPen {
   
   private int x,y;
   private Color color;
   private int penSize;
   private boolean isFilled;

	// Constructor with two parameters
	public DrawLines(int x, int y, int penSize, Color color) {
      super(x, y, penSize,color);
      this.color = color;
      this.penSize = penSize;
      isFilled = false;
	}
   // Getters and Setters
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
   
	// draw using 2D graphics
   @Override
	public void drawLines(Graphics g) {
		for (int i = 0; i < xList.size() - 1; ++i) {
   			Graphics2D g2 = (Graphics2D) g;
   			g2.setStroke(new BasicStroke(getPenSize()));
   			g2.setColor(color);
   			g2.drawLine((int) xList.get(i), (int) yList.get(i + 1), (int) xList.get(i + 1), (int) yList.get(i + 1));
		}
	}
   @Override
	public void drawOval(Graphics g) {
		for (int i = 0; i < xList.size() - 1; ++i) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(getPenSize()));
			g2.setColor(color);
         if (isFilled)
            g.fillOval((int) xList.get(i), (int) yList.get(i + 1), 850, 600);
         else
            g.drawOval((int) xList.get(i), (int) yList.get(i + 1), 850, 600);
		}
	}
//   @Override
//	public void drawRectangle(Graphics g) {
//		for (int i = 0; i < xList.size() - 1; ++i) {
//			Graphics2D g2 = (Graphics2D) g;
//			g2.setStroke(new BasicStroke(getPenSize()));
//			g2.setColor(color);
//         if (isFilled)
//         g.fillRect(getX1(), getY1(), getWidth(), getHeight());
//         else
//         g.drawRect((getX1(), getY1(), getWidth(), getHeight());
//		}
//	}
}


