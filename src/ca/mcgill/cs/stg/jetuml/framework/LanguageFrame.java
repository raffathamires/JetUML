/*******************************************************************************
 * JetUML - A desktop application for fast UML diagramming.
 *
 * Copyright (C) 2015-2017 by the contributors of the JetUML project.
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

import java.util.Locale;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.mcgill.cs.stg.jetuml.UMLEditor;
import ca.mcgill.cs.stg.jetuml.diagrams.ClassDiagramGraph;
import ca.mcgill.cs.stg.jetuml.diagrams.ObjectDiagramGraph;
import ca.mcgill.cs.stg.jetuml.diagrams.SequenceDiagramGraph;
import ca.mcgill.cs.stg.jetuml.diagrams.StateDiagramGraph;
import ca.mcgill.cs.stg.jetuml.diagrams.UseCaseDiagramGraph;


/**
 * Frame de seleção do idioma
 * 
 * @author Joacir Junior
 */
@SuppressWarnings("serial")
public class LanguageFrame extends JFrame {
	
	private Locale locale;	

	private static final int MARGIN_SCREEN = 8; // Fraction of the screen to leave around the sides

	private ResourceBundle aLanguagesResources;

	public LanguageFrame(){
	 	this.init();
	}
	
	public void init(){
		
		setLocationRelativeTo( null );
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
        setTitle("SELECIONE O SEU IDIOMA:");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(204, 204, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
		int screenWidth = (int)screenSize.getWidth();
		int screenHeight = (int)screenSize.getHeight();

		setBounds(screenWidth / (MARGIN_SCREEN*2), screenHeight / (MARGIN_SCREEN*2), (screenWidth * (MARGIN_SCREEN-1)) / MARGIN_SCREEN, 
				(screenHeight * (MARGIN_SCREEN-1))/MARGIN_SCREEN);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        pack();
        
        aLanguagesResources = ResourceBundle.getBundle("ca.mcgill.cs.stg.jetuml.framework.Languages");
		
	    setLayout(new FlowLayout());
	
	    JPanel panel = new JPanel();	
	    JPanel shortcutPanel = new JPanel();
	    
	    JButton buttonPTBR = new JButton();
	    JButton buttonENUS = new JButton();
	    
	    buttonPTBR.setText("VERSÃO EM PORTUGUÊS");
	    buttonENUS.setText("ENGLISH VERSION");
	    
	    try {
	    	
	    	Image flagPTBR = ImageIO.read(getClass().getClassLoader().getResource(aLanguagesResources.getString("language.pt.flag")));
	    	Image flagENUS = ImageIO.read(getClass().getClassLoader().getResource(aLanguagesResources.getString("language.en.flag")));
	    	
	    	buttonPTBR.setIcon(new ImageIcon(flagPTBR));
	    	buttonPTBR.setHorizontalTextPosition(JButton.CENTER);
	    	buttonPTBR.setVerticalTextPosition(JButton.BOTTOM);
	    	buttonPTBR.setFont(new Font("Arial", Font.BOLD, 14));
	    		    	
	    	buttonENUS.setIcon(new ImageIcon(flagENUS));
	    	buttonENUS.setHorizontalTextPosition(JButton.CENTER);
	    	buttonENUS.setVerticalTextPosition(JButton.BOTTOM);
	    	buttonENUS.setFont(new Font("Arial", Font.BOLD, 14));
	    		    	
	    } catch (Exception ex) {
	    	System.out.println(ex);
	    }
	    
	    
	    buttonPTBR.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent pEvent){
				
				setLocale(new Locale("pt","BR"));
				Locale.setDefault(new Locale("pt","BR"));
				System.out.println("Idioma alterado para PT-BR");
				
				EditorFrame frame = new EditorFrame(UMLEditor.class, getLocale());
				//			frame.addLanguageTab();
				frame.addGraphType("class_diagram", ClassDiagramGraph.class);
				frame.addGraphType("sequence_diagram", SequenceDiagramGraph.class);
				frame.addGraphType("state_diagram", StateDiagramGraph.class);
				frame.addGraphType("object_diagram", ObjectDiagramGraph.class);
				frame.addGraphType("usecase_diagram", UseCaseDiagramGraph.class);
				frame.setVisible(true);
				// 			frame.readArgs(arguments);
				frame.addWelcomeTab();
				frame.setIcon();
				
				setVisible(false);
				
			}
		}); 
	    
	    buttonENUS.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent pEvent){
				
				setLocale(new Locale("en","US"));
				Locale.setDefault(new Locale("en","US"));
				// System.out.println("Idioma alterado para EN-US");
				
				EditorFrame frame = new EditorFrame(UMLEditor.class, getLocale());
				//			frame.addLanguageTab();
				frame.addGraphType("class_diagram", ClassDiagramGraph.class);
				frame.addGraphType("sequence_diagram", SequenceDiagramGraph.class);
				frame.addGraphType("state_diagram", StateDiagramGraph.class);
				frame.addGraphType("object_diagram", ObjectDiagramGraph.class);
				frame.addGraphType("usecase_diagram", UseCaseDiagramGraph.class);
				frame.setVisible(true);
				// 			frame.readArgs(arguments);
				frame.addWelcomeTab();
				frame.setIcon();
				
				setVisible(false);
				
			}
		});
	    	    	    
	    GridBagConstraints c = new GridBagConstraints();
	    c.anchor = GridBagConstraints.NORTH;
	    c.weightx = 1;
	    c.gridx = 0;
	    c.gridy = 1;
	    
	    
	    panel.add(shortcutPanel, c);
	    panel.add(buttonPTBR);
	    panel.add(buttonENUS);
	
	    add(panel, BorderLayout.NORTH);
	 
        
	}
	
	public void setLocale(Locale locale){
		this.locale = locale;
	}
	
	public Locale getLocale(){
		return this.locale;
	}
	
}
