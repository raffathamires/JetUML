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
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import ca.mcgill.cs.stg.jetuml.UMLEditor;
import ca.mcgill.cs.stg.jetuml.diagrams.ClassDiagramGraph;
import ca.mcgill.cs.stg.jetuml.diagrams.ObjectDiagramGraph;
import ca.mcgill.cs.stg.jetuml.diagrams.SequenceDiagramGraph;
import ca.mcgill.cs.stg.jetuml.diagrams.StateDiagramGraph;
import ca.mcgill.cs.stg.jetuml.diagrams.UseCaseDiagramGraph;
import ca.mcgill.cs.stg.jetuml.graph.Edge;
import ca.mcgill.cs.stg.jetuml.graph.Graph;
import ca.mcgill.cs.stg.jetuml.graph.GraphElement;
import ca.mcgill.cs.stg.jetuml.graph.Node;

/**
 * Frame de seleção do idioma
 * 
 * @author Joacir Junior
 */
@SuppressWarnings("serial")
public class LanguageFrame extends JFrame {
	
	private Locale locale;	

	private static final int FRAME_GAP = 20;
	private static final int ESTIMATED_FRAMES = 5;
	private static final int MAX_RECENT_FILES = 8;
	private static final int MARGIN_SCREEN = 8; // Fraction of the screen to leave around the sides
	private static final int MARGIN_IMAGE = 2; // Number of pixels to leave around the graph when exporting it as an image
	private static final int HELP_MENU_TEXT_WIDTH = 10; //Number of pixels to give to the width of the  text area of the Help Menu.
	private static final int HELP_MENU_TEXT_HEIGHT = 40; //Number of pixels to give to the height of the text area of the Help Menu.
		
	private ResourceBundle aLanguagesResources;
	
	private MenuFactory aAppFactory;
	private ResourceBundle aAppResources;
	private ResourceBundle aVersionResources;
	private ResourceBundle aEditorResources;
	private JTabbedPane aTabbedPane;
	private ArrayList<JInternalFrame> aTabs = new ArrayList<>();
	private JMenu aNewMenu;
	private Clipboard aClipboard = new Clipboard();
	
	private RecentFilesQueue aRecentFiles = new RecentFilesQueue();
	private JMenu aRecentFilesMenu;
	
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
