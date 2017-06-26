package ca.mcgill.cs.stg.jetuml.graph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
//import java.awt.geom.RoundRectangle2D;

import ca.mcgill.cs.stg.jetuml.framework.Grid;
import ca.mcgill.cs.stg.jetuml.framework.MultiLineString;

/**
 *  A note node in a UML diagram.
 */
public class ForkJoinNode extends RectangularNode
{
	private static final int DEFAULT_WIDTH = 1;
	private static final int DEFAULT_HEIGHT = 200;
	private static final Color DEFAULT_COLOR = new Color(0.0f, 0.0f, 0.0f);
	
	private MultiLineString aName;

	/**
     * Construct a state node with a default size.
	 */
	public ForkJoinNode()
	{
		aName = new MultiLineString();
		setBounds(new Rectangle2D.Double(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT));
	}

	@Override
	public void draw(Graphics2D pGraphics2D)
	{
		super.draw(pGraphics2D);
		Color oldColor = pGraphics2D.getColor();
		pGraphics2D.setColor(DEFAULT_COLOR);
		
		Shape path = getShape();
		pGraphics2D.fill(path);
		pGraphics2D.setColor(oldColor);
		pGraphics2D.draw(path);
		pGraphics2D.draw(getShape());
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
     * Gets the name property value.
     * @return the state name
	 */
	public MultiLineString getName()
	{
		return aName;
	}
	
		@Override
	public ForkJoinNode clone()
	{
		ForkJoinNode cloned = (ForkJoinNode)super.clone();
		cloned.aName = aName.clone();
		return cloned;
	}
}
