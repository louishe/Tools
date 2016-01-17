package com.msky.tools.util;

import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GUIUtil {

	/**
	 * Set frame to the center of the screen.
	 * 
	 * @param frame
	 */
	public static void setFrameCenter(JFrame frame) {
		Rectangle screenRect = frame.getGraphicsConfiguration().getBounds();
		Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(
				frame.getGraphicsConfiguration());
		int centerWidth = screenRect.width < frame.getSize().width ? screenRect.x
				: screenRect.x + screenRect.width / 2 - frame.getSize().width
						/ 2;
		int centerHeight = screenRect.height < frame.getSize().height ? screenRect.y
				: screenRect.y + screenRect.height / 2 - frame.getSize().height
						/ 2;

		centerHeight = centerHeight < screenInsets.top ? screenInsets.top
				: centerHeight;

		frame.setLocation(centerWidth, centerHeight);
		
	}
}