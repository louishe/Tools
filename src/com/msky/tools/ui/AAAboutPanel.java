package com.msky.tools.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.msky.tools.util.GUIUtil;
import com.msky.tools.util.ToolsHelper;

public class AAAboutPanel extends JPanel{

	private JFrame jframe = null;

	public AAAboutPanel(GraphicsConfiguration gc) {
		initFrame(gc);
	}

	private void initFrame(GraphicsConfiguration gc) {
		jframe = new JFrame(gc);
		setLayout(new BorderLayout());
		
		jframe.setTitle(ToolsHelper.getMessage("ui.help.about.title"));
		jframe.setIconImage(Toolkit.getDefaultToolkit().getImage(AAAboutPanel.class.getResource("/resources/images/tools.png")));
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(new JLabel("  "), BorderLayout.WEST);
		JLabel aboutLable = new JLabel();
		aboutLable.setBounds(130,120,100,30);
		aboutLable.setText(ToolsHelper.getMessage("ui.help.about.content"));
		mainPanel.add(aboutLable, BorderLayout.CENTER);

		JPanel btnPanel = new JPanel();

		JButton cancelBtn = new JButton(ToolsHelper.getMessage("ui.help.about.close"));

		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jframe.dispose();
			}
		});

		btnPanel.add(cancelBtn);

		mainPanel.add(btnPanel, BorderLayout.SOUTH);

		jframe.getContentPane().add(mainPanel);
		jframe.setPreferredSize(new Dimension(180, 160));
		jframe.pack();
		GUIUtil.setFrameCenter(jframe);
		jframe.setVisible(true);

	}
}
