package com.msky.tools.ui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.msky.tools.util.ToolsHelper;

public class AATray {

	private Image icon;
	private TrayIcon trayIcon;
	private SystemTray systemTray;
	private PopupMenu pop = new PopupMenu();
	private MenuItem exit = new MenuItem(ToolsHelper.getMessage("ui.key.quit"));
	private MenuItem show = new MenuItem(ToolsHelper.getMessage("ui.key.openTools"));

	private JFrame mainWindow;

	public AATray(final JFrame mainWindow) {
		this.mainWindow = mainWindow;

		icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/images/tools.png"));

		if (SystemTray.isSupported()) {
			systemTray = SystemTray.getSystemTray();
			trayIcon = new TrayIcon(icon,ToolsHelper.getMessage("ui.key.toolsTitle"), pop);
			pop.add(show);
			pop.add(exit);

			try {
				systemTray.add(trayIcon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
			mainWindow.addWindowListener(new WindowAdapter() {
				public void windowIconified(WindowEvent e) {
					mainWindow.dispose();
				}
			});

			trayIcon.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1
							&& e.getButton() != MouseEvent.BUTTON3) {
						mainWindow.setVisible(true);
						mainWindow.setExtendedState(JFrame.NORMAL);
					}
				}
			});

			show.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainWindow.setVisible(true);
					mainWindow.setExtendedState(JFrame.NORMAL);
				}
			});
			exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
	}
}
