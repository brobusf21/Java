
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
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class DrawPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int selectedPenSizeIndex; // pen size selection index
	private int selectedPenSizeValue; // selected pen size value
	private Color currentColor;       // the color of the shape
   private Color PANEL_BACKGROUND_COLOR = Color.WHITE;
   
   private boolean startStopDrawing = false;

   // pen sizes
	private int BASIC = 3; 
	private int MEDIUM = 8;
	private int LARGE = 18;
	private int EX_LARGE = 36;

	private JLabel statusLabel; // label displaying mouse coordinates

	// ArrayList to store last line drawn
   private ArrayList<MyPen> lines = new ArrayList<MyPen>();
	private MyPen currentLine;

   private boolean drawMyRect = false;
   private boolean drawMyOval = false;
   private boolean drawMyLines = false;
 
	/** Constructor for DrawPanel **/
	public DrawPanel(JLabel status) {

		setPenSizeIndex(0);                    // default pen size
		setDrawingColor(Color.RED);            // start drawing with red
		setBackground(PANEL_BACKGROUND_COLOR); // set background
		
      // set the status label for displaying mouse coordinates
      statusLabel = status;               
		statusLabel.setText(String.format("Pen position "));

		// Add the mouse listeners
		MouseHandler mouseHandler = new MouseHandler();
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
	}
   
	/** Overridden paint component **/
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); 
		g.setColor(currentColor);
		for (MyPen line : lines) {
			if(drawMyOval)
				line.drawOval(g);
			else
				line.drawLines(g);        
		}
	}
   
  /** Select shape type **/
   public void chooseShapeType(String s) {
      if (s.equals("Rectangle")) {
         drawMyRect = true;
      } else if (s.equals("Oval")) {
         drawMyOval = true;
      } else {
         drawMyLines = true; 
      }
   }

	/** Setter for selected pen size **/
	public void setPenSizeIndex(int selectedPenSizeIndex) {
		
      if(selectedPenSizeIndex < 0 || selectedPenSizeIndex > 3) {
			selectedPenSizeIndex = 0;
		}
      this.selectedPenSizeIndex = selectedPenSizeIndex;
	}

	/** Setter for drawing color **/
	public void setDrawingColor(Color c) {
		currentColor = c;
	}

	/** Getter for drawing color **/
	public Color getDrawingColor() {
		return currentColor;
	}

	/** Clear the last line drawn **/
	public void clearLastLineDrawn() {
		if (lines.size() > 0) {
			lines.remove(lines.size() - 1);
			repaint();
		} else {
			JOptionPane.showMessageDialog(null, "There is nothing to undo!");
		}
	}

	/** Clear all drawings **/
	public void clearDrawing() {
		if (lines.size() > 0) {
			lines.clear();
			repaint();
		} else {
			JOptionPane.showMessageDialog(null, "The panel is clear!");
		}
	}

	/** Erase at mouse position **/
	public void eraseDrawing() {
		setDrawingColor(PANEL_BACKGROUND_COLOR);
	}

	/** Optional color chooser **/
	public void openColorPicker(Color color) {
		Color selectedColor;
		selectedColor = JColorChooser.showDialog(null, "Select Drawing Color", color);
		setDrawingColor(selectedColor);
	}

	// Mouse event handler
	private class MouseHandler extends MouseAdapter implements MouseMotionListener {

		/**
		 * Method mouseClicked keeps track of whether user clicked the mouse to
		 * start or to stop drawing. Initially ,and when user clicks to stop
		 * drawing, 'clicks' is set to -1 to indicate that user is at the second
		 * point of the previous starting point.
		 **/
		public void mouseClicked(MouseEvent e) {

			// If this is the first set of clicks, set pen size based on user's choice.
			if (!startStopDrawing) {

				switch (selectedPenSizeIndex) {
				case 0:
					selectedPenSizeValue = BASIC;
					break;
				case 1:
					selectedPenSizeValue = MEDIUM;
					break;
				case 2:
					selectedPenSizeValue = LARGE;
					break;
				case 3:
					selectedPenSizeValue = EX_LARGE; // flood (a huge line)
					break;
				}

				// Begin adding pen position to the addPoint method
				currentLine = new DrawLines(e.getX(), e.getY(), selectedPenSizeValue, currentColor);
				lines.add(currentLine);
            currentLine.addPoint(e.getX(), e.getY());
				startStopDrawing = true;
			} else {				
				startStopDrawing = false; // to stop drawing.
			}
		}

		/** Update status bar and update pen position as the mouse moves **/
		public void mouseMoved(MouseEvent e) {
			statusLabel.setText(String.format("Pen position (%d,%d)", e.getX(), e.getY()));
         if(startStopDrawing) {               
            currentLine.addPoint(e.getX(), e.getY());
            repaint(); 
         } 
		}

	} // end class MouseHandler

} // end class DrawPanel
