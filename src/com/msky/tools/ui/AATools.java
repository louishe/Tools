package com.msky.tools.ui;

import java.awt.GraphicsEnvironment;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class AATools {
	/**
	 * Launch the AA TOOLS.
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
		BeautyEyeLNFHelper.launchBeautyEyeLNF();
		UIManager.put("RootPane.setupButtonVisible", false);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				AAMainPanel mainPanel = new AAMainPanel(GraphicsEnvironment.getLocalGraphicsEnvironment()
						.getDefaultScreenDevice().getDefaultConfiguration());
			}
		});
	}
}
