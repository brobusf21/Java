
/**
 * Program DrawSomething draws lines of four different sizes depending on user's choice.
 * It also gives user the option of choosing the color of the pen, the option to undo 
 * last drawing or deletion as well as to clear all drawings from the panel. 
 * Input: none.
 * Output: none.
 *
 * Authors: Atakelti Wubneh, Brandon Robinson, Josiah Meyer
 * Last modified: 07/25/2016
**/

import javax.swing.*;
import java.awt.*;

public class DrawSomethingMain {

	public static void main(String[] args) {
		DrawFrame application = new DrawFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setSize(850, 600);
		application.setVisible(true);
	}

}