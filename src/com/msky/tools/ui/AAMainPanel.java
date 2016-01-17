package com.msky.tools.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.msky.tools.util.ComponentUtil;
import com.msky.tools.util.GUIUtil;
import com.msky.tools.util.ToolsHelper;

public class AAMainPanel extends JPanel {
	
	private JFrame jframe;

	private static final int DEFAULT_PANEL_WIDTH = 1000;
	
	private static final int DEFAULT_PANEL_HEIGHT = 700;
	/**
	 * Create the panel.
	 */
	public AAMainPanel(GraphicsConfiguration gc) {
		initFrame(gc);
		initCompotent();
	}
	
	private void initFrame(GraphicsConfiguration gc)
	{
		jframe = new JFrame(gc);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setMinimumSize(new Dimension(100, 100));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(DEFAULT_PANEL_WIDTH, DEFAULT_PANEL_HEIGHT));
		jframe.setTitle(ToolsHelper.getMessage("ui.key.toolsTitle"));
		jframe.setIconImage(Toolkit.getDefaultToolkit().getImage(AATools.class.getResource("/resources/images/tools.png")));
		ComponentUtil.setComponent("mainpanel", this);
		jframe.getContentPane().add(this, BorderLayout.CENTER);
		jframe.pack();
		GUIUtil.setFrameCenter(jframe);
		AAStatusPanel.setStatusMessage(ToolsHelper.getMessage("ui.key.status.loading"));
		new AATray(jframe);
	}
	
	private void initCompotent() {
		//menu bar
		AAMenuBar menuBar = new AAMenuBar();
		add(menuBar.getMenuBar(), BorderLayout.NORTH);

		//container panel
		AAContainerPanel containerPanel = new AAContainerPanel(this);
		add(containerPanel.getContainerPanel(), BorderLayout.CENTER);

		// status panel
		AAStatusPanel statusPanel = new AAStatusPanel(this);
		add(statusPanel.getStatusPanel(), BorderLayout.SOUTH);
		showFrame();
		containerPanel.setDividerLocation();

	}
	
	private void showFrame() {
		getFrame().setVisible(true);
	}

	public JFrame getFrame() {
		return jframe;
	}

}
