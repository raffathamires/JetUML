/*******************************************************************************
 * JetUML - A desktop application for fast UML diagramming.
 *
 * Copyright (C) 2016 by the contributors of the JetUML project.
 *
 * See: https://github.com/prmr/JetUML
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package ca.mcgill.cs.stg.jetuml.framework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 * @author JoacirJunior
 * Essa classe exibe as opções de idiomas aos usuários.
 *
 */
@SuppressWarnings("serial")
public class LanguageTab extends JInternalFrame
{
	
	private ResourceBundle aLanguagesResources;
    //private ImageIcon aLeftPanelIcon;
    //private ImageIcon aRightPanelIcon;
    
    
	/**
     *
	 */
	public LanguageTab(){
		
		aLanguagesResources = ResourceBundle.getBundle("ca.mcgill.cs.stg.jetuml.framework.Languages");
		
	    setOpaque(false);
	    setLayout(new FlowLayout());
	    
	    BasicInternalFrameUI ui = (BasicInternalFrameUI)getUI();
	    Container north = ui.getNorthPane();
	    north.remove(0);
	    north.validate();
	    north.repaint();	
	  
	    JPanel panel = new JPanel();	
	    JPanel shortcutPanel = new JPanel();
	    
	    JButton buttonPTBR = new JButton();
	    JButton buttonENUS = new JButton();
	    
	    try {
	    	
	    	Image flagPTBR = ImageIO.read(getClass().getClassLoader().getResource(aLanguagesResources.getString("language.pt.flag")));
	    	Image flagENUS = ImageIO.read(getClass().getClassLoader().getResource(aLanguagesResources.getString("language.en.flag")));
	    	
	    	buttonPTBR.setText("VERSÃO EM PORTUGUÊS");
	    	buttonPTBR.setIcon(new ImageIcon(flagPTBR));
	    	buttonPTBR.setHorizontalTextPosition(JButton.CENTER);
	    	buttonPTBR.setVerticalTextPosition(JButton.BOTTOM);
	    	buttonPTBR.setFont(new Font("Arial", Font.BOLD, 14));
	    	
	    	buttonENUS.setText("ENGLISH VERSION");
	    	buttonENUS.setIcon(new ImageIcon(flagENUS));
	    	buttonENUS.setHorizontalTextPosition(JButton.CENTER);
	    	buttonENUS.setVerticalTextPosition(JButton.BOTTOM);
	    	buttonENUS.setFont(new Font("Arial", Font.BOLD, 14));
	    		    	
	    } catch (Exception ex) {
	    	System.out.println(ex);
	    }
	    
	    
	    GridBagConstraints c = new GridBagConstraints();
	    c.anchor = GridBagConstraints.NORTH;
	    c.weightx = 1;
	    c.gridx = 0;
	    c.gridy = 1;
	    
	    panel.add(shortcutPanel, c);
	    panel.add(buttonPTBR);
	    panel.add(buttonENUS);
	
	    add(panel, BorderLayout.NORTH);
	 
	    setComponentPopupMenu( null ); // Removes the system pop-up menu full of disabled buttons.
	    
	}
	

}	
