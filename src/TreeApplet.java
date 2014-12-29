// awt
import java.awt.BorderLayout;
//swing
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TreeApplet extends JApplet
{
	
	
	
	/**
	 * Constructor
	 **/
	public TreeApplet()
	{
		// call super constructor
		super();
		
	}
	
	/** 
	 * Special method that will be invoked when applet is created
	 **/

	public void start()
	{
		// create an instance of a TreePanel and add it
		add( new TreePanel() );
	}

	
	
	
}
