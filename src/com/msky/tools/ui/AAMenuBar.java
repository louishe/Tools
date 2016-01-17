package com.msky.tools.ui;

import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import com.msky.tools.util.ToolsHelper;

public class AAMenuBar {

	private JMenuBar menuBar = null;

	public AAMenuBar() {
		menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu(ToolsHelper.getMessage("ui.key.file"));
		menuBar.add(fileMenu);
		JMenuItem openMenuitem = new JMenuItem(ToolsHelper.getMessage("ui.file.chooseFolder"));
		fileMenu.add(openMenuitem);
		
		JMenuItem exitItem = new JMenuItem(ToolsHelper.getMessage("ui.file.exit"));
		fileMenu.add(exitItem);
		
		JMenu helpMenu = new JMenu(ToolsHelper.getMessage("ui.key.help"));
		menuBar.add(helpMenu);
		
		JMenuItem aboutItem = new JMenuItem(ToolsHelper.getMessage("ui.help.about"));
		helpMenu.add(aboutItem);
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						AAAboutPanel aboutFrame = new AAAboutPanel(
								GraphicsEnvironment
										.getLocalGraphicsEnvironment()
										.getDefaultScreenDevice()
										.getDefaultConfiguration());
					}
				});
			}
		});
		
		exitItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}
}
