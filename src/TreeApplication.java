/** 
 * Main application to show a TreePanel 
 **/
import javax.swing.JFrame;
//class
public class TreeApplication
{
	/**
	 * main method starts the program
	 **/
	public static void main( String[] args )
	{
	// create a new JFrame to hold TreePanel
		JFrame treeFrame = new JFrame();
 
		// set size
		treeFrame.setSize( 600, 400 );
 
		// create a TreePanel and add it
		treeFrame.add( new TreePanel() );
 
		// exit normally on closing the window
		treeFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
 
		// show frame
		treeFrame.setVisible( true );
	}
}