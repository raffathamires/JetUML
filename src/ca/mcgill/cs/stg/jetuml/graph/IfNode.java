
package ca.mcgill.cs.stg.jetuml.graph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import ca.mcgill.cs.stg.jetuml.framework.Grid;
import ca.mcgill.cs.stg.jetuml.framework.MultiLineString;

/**
 *  A note node in a UML diagram.
 */
public class IfNode extends RectangularNode
{
	private static final int DEFAULT_WIDTH = 40;
	private static final int DEFAULT_HEIGHT = 40;
	private static final Color DEFAULT_COLOR = new Color(0.9f, 0.9f, 0.6f);
	
	private MultiLineString aName;
	private Color aColor;

	/**
     * Construct a state node with a default size.
	 */
	public IfNode()
	{
		aName = new MultiLineString();
		aColor = Color.WHITE;
		setBounds(new Rectangle2D.Double(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT));
	}

	@Override
	public void draw(Graphics2D pGraphics2D)
	{
		pGraphics2D.setBackground(aColor);
		//pGraphics2D.rotate(Math.toRadians(45));
		super.draw(pGraphics2D);
		Color oldColor = pGraphics2D.getColor();
		pGraphics2D.setColor(DEFAULT_COLOR);
		
		Shape path = getShape();
		pGraphics2D.fill(path);
		pGraphics2D.setColor(oldColor);
		pGraphics2D.draw(path);
		//pGraphics2D.rotate(Math.toRadians(45));
		pGraphics2D.draw(getShape());
		//https://stackoverflow.com/questions/28982008/how-to-draw-a-diamond-shape-in-java
		aName.draw(pGraphics2D, getBounds());
	}
   
	@Override	
	public void layout(Graph pGraph, Graphics2D pGraphics2D, Grid pGrid)
	{
		Rectangle2D b = aName.getBounds(pGraphics2D);
		b = new Rectangle2D.Double(getBounds().getX(), getBounds().getY(), 
				Math.max(b.getWidth(), DEFAULT_WIDTH), Math.max(b.getHeight(), DEFAULT_HEIGHT));
		pGrid.snap(b);
		setBounds(b);
	}

	/**
     * Sets the name property value.
     * @param pName the new state name
	 */
	public void setName(MultiLineString pName)
	{
		aName = pName;
	}

	/**
     * Gets the name property value.
     * @return the state name
	 */
	public MultiLineString getName()
	{
		return aName;
	}
	
	/**
     * Sets the color property value.
     * @param pColor the new state color
	 */
	public void setColor(Color pColor)
	{
		aColor = pColor;
	}

	/**
     * Gets the color property value.
     * @return the state color
	 */
	public Color getColor()
	{
		return aColor;
	}

	@Override
	public IfNode clone()
	{
		IfNode cloned = (IfNode)super.clone();
		cloned.aName = aName.clone();
		cloned.aColor = aColor;
		return cloned;
	}
}