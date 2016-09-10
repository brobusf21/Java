
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

/** DrawFrame with action listeners **/
public class DrawFrame extends JFrame implements ItemListener, ActionListener {
	private static final long serialVersionUID = 1L;

	private JRadioButtonMenuItem colorPickerRadioButton;
	private JRadioButtonMenuItem redRadioButton;
	private JRadioButtonMenuItem yellowRadioButton;
	private JRadioButtonMenuItem blueRadioButton;
	private JRadioButtonMenuItem eraserRadioButton;

	// Array of possible pen sizes
	private String[] penSizeSellections = { "Basic", "Medium", "Large", "Flood" };
	private DrawPanel drawPanel;              // drawing panel
	private JButton undoButton;               // button to remove last line drawn
	private JButton clearButton;              // button to clear all drawing
	private JButton drawOvalButton;              
	private JButton drawRectButton; 
	private JComboBox<String> penSizeChoices; // combo box for selecting pen sizes

	private final Color DEFAULT_BACKGROUND_COLOR = Color.GRAY;
	private final Color DEFAULT_FOREGROUND_COLOR = Color.WHITE;

	// DrawFrame constructor with no parameters
	public DrawFrame() {
		super("Draw Something!");

		// create menu panel radio buttons, labels and action listeners
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(DEFAULT_BACKGROUND_COLOR);

		colorPickerRadioButton = new JRadioButtonMenuItem("Pick Color");
		colorPickerRadioButton.addActionListener(this);
		colorPickerRadioButton.setBackground(DEFAULT_BACKGROUND_COLOR);
		colorPickerRadioButton.setForeground(DEFAULT_FOREGROUND_COLOR);
		
		redRadioButton = new JRadioButtonMenuItem("Red", true);
		redRadioButton.addActionListener(this);
		redRadioButton.setBackground(Color.RED);
		redRadioButton.setForeground(DEFAULT_FOREGROUND_COLOR);

		yellowRadioButton = new JRadioButtonMenuItem("Yellow");
		yellowRadioButton.addActionListener(this);
		yellowRadioButton.setBackground(DEFAULT_BACKGROUND_COLOR);
		yellowRadioButton.setForeground(DEFAULT_FOREGROUND_COLOR);
		
		blueRadioButton = new JRadioButtonMenuItem("Blue");
		blueRadioButton.addActionListener(this);
		blueRadioButton.setBackground(DEFAULT_BACKGROUND_COLOR);
		blueRadioButton.setForeground(DEFAULT_FOREGROUND_COLOR);
		
		eraserRadioButton = new JRadioButtonMenuItem("Eraser");
		eraserRadioButton.addActionListener(this);
		eraserRadioButton.setBackground(DEFAULT_BACKGROUND_COLOR);
		eraserRadioButton.setForeground(DEFAULT_FOREGROUND_COLOR);

		// Create the button group.
		ButtonGroup group = new ButtonGroup();
		group.add(colorPickerRadioButton);
		group.add(redRadioButton);
		group.add(yellowRadioButton);
		group.add(blueRadioButton);
		group.add(eraserRadioButton);

		// Add button group to the panel.
		menuPanel.add(colorPickerRadioButton);
		menuPanel.add(redRadioButton);
		menuPanel.add(yellowRadioButton);
		menuPanel.add(blueRadioButton);
		menuPanel.add(eraserRadioButton);

		// Create a combo box for selecting pen sizes
		penSizeChoices = new JComboBox<String>(penSizeSellections);
		penSizeChoices.addItemListener(this);
		menuPanel.add(penSizeChoices);

		// undoButton for deleting last line drawn
		undoButton = new JButton("Undo");
		undoButton.addActionListener(this);
		menuPanel.add(undoButton);

		// clearButton for deleting last line drawn
		clearButton = new JButton("Clear");
		clearButton.addActionListener(this);
		menuPanel.add(clearButton);
      
		// drawOvalButton 
		drawOvalButton = new JButton("Draw Oval");
		drawOvalButton.addActionListener(this);
		menuPanel.add(drawOvalButton);

		// drawOvalButton 
		drawRectButton = new JButton("Draw Rect");
		drawRectButton.addActionListener(this);
		menuPanel.add(drawRectButton);
		
		add(menuPanel, BorderLayout.SOUTH);       // Add the menuPanel to the frame
		JLabel statusLabel = new JLabel("(0,0)"); // Create a label for the status bar
		add(statusLabel, BorderLayout.NORTH);     // Add the status bar at the top
		drawPanel = new DrawPanel(statusLabel);   // create the DrawPanel with the status bar label
		add(drawPanel);                           // add the drawPanel to the frame

	} // end DrawFrame constructor

	/** itemStateChanged method to handle the pen sizes selections **/
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == penSizeChoices)
			drawPanel.setPenSizeIndex(penSizeChoices.getSelectedIndex());
	}

	/** reset background colors **/
	public void resetBagroundColors() {
		redRadioButton.setBackground(DEFAULT_BACKGROUND_COLOR);
		yellowRadioButton.setBackground(DEFAULT_BACKGROUND_COLOR);
		blueRadioButton.setBackground(DEFAULT_BACKGROUND_COLOR);
		eraserRadioButton.setBackground(DEFAULT_BACKGROUND_COLOR);
		colorPickerRadioButton.setBackground(DEFAULT_BACKGROUND_COLOR);

		/**
		 * Reset yellowRadioButton and eraserRadioButton background colors
		 * back to WHITE when not in use.
		 **/
		yellowRadioButton.setForeground(Color.WHITE);
		eraserRadioButton.setForeground(Color.WHITE);
	}

	// button click handlers
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == undoButton) {
			drawPanel.clearLastLineDrawn();
		} else if(e.getSource() == clearButton) {
			drawPanel.clearDrawing();
		} else if(e.getSource() == drawRectButton) {
			drawPanel.chooseShapeType("Rectangle");
		} else if(e.getSource() == drawOvalButton) {
			drawPanel.chooseShapeType("Oval");        
		} else if(colorPickerRadioButton.isSelected()) {
			resetBagroundColors();
			drawPanel.openColorPicker(Color.GREEN);
			colorPickerRadioButton.setBackground(drawPanel.getDrawingColor());
      } else if(redRadioButton.isSelected()) {
			resetBagroundColors();
			redRadioButton.setBackground(Color.RED);
			drawPanel.setDrawingColor(Color.RED);	
      } else if(yellowRadioButton.isSelected()) {
			resetBagroundColors();
			yellowRadioButton.setBackground(Color.YELLOW);			
			drawPanel.setDrawingColor(Color.YELLOW);
         yellowRadioButton.setForeground(Color.GRAY);      
      } else if(blueRadioButton.isSelected()) {
			resetBagroundColors();
			blueRadioButton.setBackground(Color.BLUE);
			drawPanel.setDrawingColor(Color.BLUE);
		} else if(eraserRadioButton.isSelected()) {
			resetBagroundColors();
			eraserRadioButton.setBackground(Color.BLACK);
			eraserRadioButton.setForeground(Color.WHITE);
			drawPanel.eraseDrawing();
		}
	}

} // end class DrawFrame
