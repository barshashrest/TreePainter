//@author Barsha Shrestha

// awt

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

// swing

import javax.swing.JComponent;

//class that extends JComponents and implements MouseListener
public class TreePainter extends JComponent implements MouseListener
{
    /** INSTANT VARIABLES**/

    /** Number of generations to create branches.  **/
	public static final int NUM_GENERATIONS = 8;
 
	/** Number of children for each branch. **/
	public static final int NUM_CHILDREN = 3; 
 
	/** Golden ratio makes the child branches aesthetically appealing **/
	public static final double GOLDEN_RATIO = 1.618;
 
	/** Maximum branching angle of children from a parent stick **/
	public static final double MAX_BRANCHING_ANGLE = .5*Math.PI;
 
	/** Diameter of the blossoms. **/
	public static final int BLOSSOM_DIAM = 4;

    //int to store the generation number
    private int generation= NUM_GENERATIONS;

    
    //Point2D to store the startPoint i.e when the mouse is pressed
    private Point2D startPoint=new Point2D.Double(0,0);
    //Point2D to store when the mouse is released
    private Point2D endPoint=new Point2D.Double(0,0);

    //Point2D to store the end point before the blossom is drawn
    private Point2D getEndPointForBlossom = new Point2D.Double(0,0);

    
    //boolean to start the paint method only after the mouse is released
    private boolean getMethods=false;
  
    //Constructor 
    public TreePainter()
    {
	//add Mouse listening ability to the panel
	addMouseListener(this);


    }
    /** 
	 * Compute the point that is length away from p at the angle.
	 * Uses cosine to get the new x coordinate, sine to get the new y coordinate.
	 **/
    public Point2D computeEndpoint( Point2D p, double length, double angle )
	{
		return new Point2D.Double( 	p.getX() + length*Math.cos(angle), // x is cos
                				p.getY() + length*Math.sin(angle));	// y is sin
	}

    /**recursive function
       gets the grahic object, the start point, an angle to draw each child from, the length of each child and the generation

     **/
    
    public void drawSticksAndBlossoms(Graphics2D g, Point2D startingPoint, double angle, double length, int gen)
    {
	//base case
	//when generation is 0, the blossom is drawn
	if(gen==0)
	    {
		//get x and y coordinates of the end point where the blossom is drawn
		double xCo =getEndPointForBlossom.getX();
		double yCo =getEndPointForBlossom.getY();
		drawBlossom(g, xCo, yCo);
		
		
	    }
	//if not base case
	else
	    {
		//get new starting point for each child
		Point2D newStartPoint= startingPoint;
		
	
			
		//compute the end point using the start point, the length passed and the angle passed
		Point2D	newEndPoint= computeEndpoint( newStartPoint, length , angle  );
		//set color for the child to be drawn
		g.setColor(new Color(0f, .25f*gen/NUM_GENERATIONS + .25f, 0f));
		//draw the child (line) using the start and end points
		g.draw(new Line2D.Double(newStartPoint,newEndPoint)); 
		System.out.println("Gen is "+ gen);
	
			

			
			       	
			
		//for each child		
		for (int i=0; i< NUM_CHILDREN;i++)
		    {
			//get a new angle that is within the 180 degrees rotation of the branch
			double angleOfChildren = angle+ angleOfChildrenWOmainAngle();
		
			
		
			//save the end point in case the end point is the last end point ie for when generation=0	
			getEndPointForBlossom=newEndPoint;
		
			//call the recursive function using the graphics object, the start point (which is the last end point, the new angle calculated, the new length calculated using the golden ratio and the generation 
			drawSticksAndBlossoms(g, newEndPoint, angleOfChildren, (1/GOLDEN_RATIO)*length,gen-1);
			
		    } 
			
				
		
	
	
	
	

	    }



    }
    //method to get brnaches to the left or right of the parent branch
    //random left or right children
    //the parent's angle is not yet considered
    public double angleOfChildrenWOmainAngle()
    {
	//get a random number which is 0 or 1
	int negativeOrPositive =(int)Math.floor(Math.random()*2);
	//if the number is 0
	if (negativeOrPositive==0)
	    {
		//get a negative angle ie branch is drawn to the left of the parent branch	 	
	    return (-1)*Math.random()*MAX_BRANCHING_ANGLE;
	    }
	else
	    {
		//get a positive angle ie branch is drawn to the right of the parent branch	
	    return Math.random()*MAX_BRANCHING_ANGLE;
	    }
 
    }
    //return length of the sticks/child
    public double lengthOfSticks(Point2D a, Point2D b)
    {
	//get the distance between 2 x coordinates and square
	double distanceOfX=Math.pow( a.getX()-b.getX(), 2);

	//get the distance between 2 Y coordinates and square
	double distanceOfY=Math.pow(a.getY()-b.getY(), 2);
	//get the length by using the formula
	double lengthOf = Math.sqrt(distanceOfX+distanceOfY);
       

	return lengthOf;
    }
    //calculate angle of eachStick
    public double angleOfStick(Point2D a, Point2D b)
    {
	//calculate the distance (y2-y1)
	double differenceOfX= b.getX()-a.getX();
	//calculate the difference (x2-x1)
	double differenceOfY=b.getY()-a.getY();
	//return the angle of each stick using Math.atan2
	return Math.atan2(differenceOfY,differenceOfX);
    }
    //method to draw blossom when generation is 0
    public void drawBlossom(Graphics2D g, double x, double y)
    {
	//set the color of the blossom
	g.setColor(new Color((float) (Math.random()*.6f + .3f), // more red
			     (float)(.1f+.5f*Math.random()), // some green
			     (float)(.1f+.1f*Math.random()))); // low blue
	//draw a blossom
	g.draw(new Ellipse2D.Double(x, y, BLOSSOM_DIAM, BLOSSOM_DIAM));


    }
    //method that is invoked when the mouse is pressed
    public void mousePressed(MouseEvent e)
    {
	//get the start point
	startPoint = new Point2D.Double( e.getX(), e.getY()) ;

 
     
    }
    //method that is invoked when mouse is released
    public void mouseReleased(MouseEvent e)
    {
	endPoint = new Point2D.Double( e.getX(), e.getY()) ;  
	//set getMethods to true such that the paint method can be invoked
	getMethods=true;
	//invoke the paint method
	repaint();
	
    }
    //methods that are part of the MouseEvent functionality
    public void mouseExited(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseClicked(MouseEvent e)
    {
    }
    //paint method that is invoked when mouse is released
    public void paint(Graphics g)
    {
	//when the boolean is true
	if(getMethods)
	    {
	 //cast the grapphics to be 2D
	Graphics2D g2=(Graphics2D)g;
	//set the color of the trunk
	g2.setColor(Color.GREEN);
	
	
	//call the methods to draw the branches using initial values of the trunk
	drawSticksAndBlossoms(g2, startPoint,angleOfStick(startPoint, endPoint), lengthOfSticks(startPoint,endPoint), generation);
	
	    }
	
    }
    
}