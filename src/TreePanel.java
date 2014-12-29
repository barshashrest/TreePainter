// awt
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;


// swing 

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TreePanel extends JPanel
{

    private TreePainter paintTree;
    private JPanel instructionPanel;
    public TreePanel()

    {
/** 
	 * Create GUI components.
	 * returns the created main panel
	 **/
              
             // call super constructor, passing title of frame
		super( );
		
	
		
			//set a new layout with border layout
		this.setLayout (new BorderLayout());

		// first, create panel to hold input 
		// and add to north
		this.add( createMainPanel());
		
	
	
	
    }
    public JPanel createMainPanel()
    {
	       
	// create panel to hold everything, using a border layout
		JPanel mainPanel = new JPanel( new BorderLayout() );//set a new layout with border layout
		mainPanel.setOpaque(true);
		mainPanel.setBackground(Color.BLACK);
		// first, create panel to put the instructions 
		// and add to north
		mainPanel.add( createInstructionPanel(), BorderLayout.NORTH );
		
		// then, create panel for drawing the tree
		// and add to center
		mainPanel.add(  createTree(), BorderLayout.CENTER );
		
		return mainPanel;
    }

    public JLabel createInstructionPanel()
    {
	JLabel instructionPanel= new JLabel("Click and drag to add a tree painting. Be patient as tree painting take a lot of work!");
	instructionPanel.setOpaque(true);
	
			     return instructionPanel;
    }

     public TreePainter createTree()
    {
	
      	paintTree= new TreePainter();
	paintTree.setOpaque(true);

	return paintTree;
    }



}
