package com.msky.tools.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import com.msky.tools.util.Utilities;
import javax.swing.border.EmptyBorder;

public class AAContainerPanel extends JPanel{

	private JPanel containerPanel = null;
	private JSplitPane mainJsplit = null;
	private JSplitPane subJsplit = null;
	private JScrollPane scrollPane = null;
	private final List<CollapsiblePanel> collapsePanels = new ArrayList<CollapsiblePanel>();
	private static final Border chiselBorder = new ChiselBorder();
	private static final Border categoryBorder = new CompoundBorder(chiselBorder,new EmptyBorder(0,0,10,0));  
	private static final Border buttonBorder = new CompoundBorder(new DemoButtonBorder(), new EmptyBorder(0, 18, 0, 0));
	private final ActionListener demoActionListener = new DemoActionListener();
	private Color visitedForeground;
	 private Color failedForeground;
	 private Demo selectedDemo;
	 private ButtonGroup group;
	 private int buttonHeight = 0;

	public AAContainerPanel(AAMainPanel jnMainPanel) {

		visitedForeground = new Color(100, 100, 150);
		group = new ButtonGroup();
		containerPanel = new JPanel();
		containerPanel.setLayout(new BorderLayout());
		containerPanel.setBorder(BorderFactory.createEmptyBorder());
		
		mainJsplit = new JSplitPane();
		subJsplit = new JSplitPane();
		
		mainJsplit.setBorder(BorderFactory.createEmptyBorder());
		subJsplit.setBorder(BorderFactory.createEmptyBorder());
		
		subJsplit.setLeftComponent(null);
		JComponent selector = createCategory();
		scrollPane = new JScrollPane(selector);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainJsplit.setLeftComponent(scrollPane);
		
		containerPanel.add(mainJsplit, BorderLayout.CENTER);
	}

	public JPanel getContainerPanel() {
		return containerPanel;
	}

	public void setDividerLocation(){
		mainJsplit.setDividerLocation(0.4);
		subJsplit.setDividerLocation(0.25);
	}
	
	public JComponent createCategory()
	{
		JPanel selectorPanel = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
	    selectorPanel.setLayout(gridbag);
	    GridBagConstraints c = new GridBagConstraints(); 
	    c.gridx = c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        HashMap<String,JPanel> categoryMap = new HashMap<String,JPanel>();
        GridBagLayout categoryGridbag = null;
        GridBagConstraints cc = new GridBagConstraints();
        cc.gridx = cc.gridy = 0;
        cc.weightx = 1;
        cc.fill = GridBagConstraints.HORIZONTAL;
        CollapsiblePanel collapsePanel;
       List<AAEntries> entries = EntriesCreator.createEntries();
//       for(AAEntries entry : entries)
//       {
//    	   String category = entry.getCategory();
//    	   JPanel categoryPanel = categoryMap.get(category);
//    	   if (categoryPanel == null) {
//               categoryPanel = new JPanel();
//               categoryGridbag = new GridBagLayout();
//               categoryPanel.setLayout(categoryGridbag);                
//               collapsePanel = new CollapsiblePanel(categoryPanel, category,"click to expand or contract category");
//               collapsePanels.add(collapsePanel);
//               collapsePanel.setBorder(categoryBorder);
//               categoryMap.put(category, categoryPanel);
//               gridbag.addLayoutComponent(collapsePanel, c);
//               selectorPanel.add(collapsePanel);
//               c.gridy++;
//           }
//       }
       List<Demo> demoSet = new ArrayList<Demo>();
       for(Demo demo: demoSet) {
           String category = demo.getCategory();
           JPanel categoryPanel = categoryMap.get(category);
           if (categoryPanel == null) {
               categoryPanel = new JPanel();
               categoryGridbag = new GridBagLayout();
               categoryPanel.setLayout(categoryGridbag);                
               collapsePanel = new CollapsiblePanel(categoryPanel, category, "click to expand or contract category");
               collapsePanels.add(collapsePanel);
               collapsePanel.setBorder(categoryBorder);
               categoryMap.put(category, categoryPanel);
               gridbag.addLayoutComponent(collapsePanel, c);
               selectorPanel.add(collapsePanel);
               c.gridy++;
           }
           DemoButton demoButton = new DemoButton(demo);
           categoryGridbag.addLayoutComponent(demoButton, cc);
           cc.gridy++;
           group.add(demoButton);
           categoryPanel.add(demoButton);
           if (buttonHeight == 0) {
               buttonHeight = demoButton.getPreferredSize().height;
           }
       }
       JPanel trailer = new JPanel();
       c.weighty = 1.0;
       gridbag.addLayoutComponent(trailer, c);
       selectorPanel.add(trailer);
       return selectorPanel;
	}
	
	private static class ChiselBorder implements Border {
        private Insets insets = new Insets(1, 0, 1, 0);
        
        public ChiselBorder() {            
        }
        
        public Insets getBorderInsets(Component c) {
            return insets;
        }
        public boolean isBorderOpaque() {
            return true;
        }
         public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Color color = c.getBackground();
            // render highlight at top
            g.setColor(Utilities.deriveColorHSB(color, 0, 0, .2f));
            g.drawLine(x, y, x + width, y);
            // render shadow on bottom
            g.setColor(Utilities.deriveColorHSB(color, 0, 0, -.2f));
            g.drawLine(x, y + height - 1, x + width, y + height - 1);
        }
    }
	

    private class DemoButton extends JToggleButton {
        private Demo demo;
        public DemoButton(Demo demo) {
            super();
            this.demo = demo;
            String demoName = demo.getName();
            if (demoName.endsWith("Demo")) {
                setText(demoName.substring(0, demoName.indexOf("Demo")));
            } else {
                setText(demoName);
            }
            setIcon(demo.getIcon());
            setIconTextGap(10);
            setHorizontalTextPosition(JToggleButton.TRAILING);
            setHorizontalAlignment(JToggleButton.LEADING);
            setOpaque(false);
            setBorder(buttonBorder);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setToolTipText(demo.getShortDescription());
            addActionListener(demoActionListener);
        }
        
        @Override
        public void updateUI() {
            super.updateUI();
            // some look and feels replace our border, so take it back
            setBorder(buttonBorder);
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            if (isSelected()) {
                setBackground(UIManager.getColor("Tree.selectionBackground"));
                g.setColor(UIManager.getColor("Tree.selectionBackground"));
                Dimension size = getSize();
                g.fillRect(0, 0, size.width, size.height); 
                setForeground(UIManager.getColor("Tree.selectionForeground"));
            } else {
                setBackground(UIManager.getColor("ToggleButton.background"));
                Color foreground = UIManager.getColor("ToggleButton.foreground");
                switch(demo.getState()) {
                    case STOPPED: {
                        foreground = visitedForeground;
                        break;
                    }
                    case FAILED: {
                        foreground = failedForeground;
                    }
                }
                setForeground(foreground);
            }
            super.paintComponent(g);
        }
        
        public Demo getDemo() {
            return demo;
        }
    }
    
    protected void setSelectedDemo(Demo demo) {
        Demo oldSelectedDemo = selectedDemo;
        selectedDemo = demo;
        firePropertyChange("selectedDemo", oldSelectedDemo, demo);
    }
    
    private static class DemoButtonBorder implements Border {
        private Insets insets = new Insets(2, 1, 1, 1);
        
        public DemoButtonBorder() {            
        }
        
        public Insets getBorderInsets(Component c) {
            return insets;
        }
        public boolean isBorderOpaque() {
            return true;
        }
         public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            AbstractButton b = (AbstractButton)c;
            if (b.isSelected()) {
                Color color = c.getBackground();
                g.setColor(Utilities.deriveColorHSB(color, 0, 0, -.20f));
                g.drawLine(x, y, x + width, y);
                g.setColor(Utilities.deriveColorHSB(color, 0, 0, -.10f));
                g.drawLine(x, y + 1, x + width, y + 1);
                g.drawLine(x, y + 2, x, y + height - 2);
                g.setColor(Utilities.deriveColorHSB(color, 0, 0, .24f));
                g.drawLine(x, y + height - 1, x + width, y + height-1);
            }
        }
    }
    
    private class DemoActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            DemoButton demoButton = (DemoButton)event.getSource();
            setSelectedDemo(demoButton.getDemo());
        }
    }
    
}
