import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HangmanFontPanel extends JPanel
{
	//CONSTANTS
	
	public static final int DEFAULT_SIZE = 60;
	
	
	//INSTANCE VARIABELS
	
	private int size;
	private Font font;
	private String givenString;
	
	
	//CONSTRUCTORS
	
	//DESCRIPTION: Sets Instance variables to default values
	//PRECONDITION: N/A
	//POSTCONDITION: Sets Instance variables to default values
	public HangmanFontPanel() 
	{
		super();
		font = new Font("SansSerif", Font.BOLD, DEFAULT_SIZE);
		this.setBackground(Color.WHITE);
	}
	
	
	//SETTERS
	
	//DESCRIPTION: Sets givenString to given String
	//PRECONDITION: Instance variable must be initialized
	//POSTCONDITION: Sets givenString to given String
	public void setGivenString(String s)
	{
		this.givenString = s;
	}
	
	//HELPERS
	//DESCRIPTION: Draws left panels using font and given String
	//PRECONDITION: Instance variable must be initialized\given
	//POSTCONDITION: Draws left panels using font and given String
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		int x,y;
		x = this.getWidth();
		y = this.getHeight();
		g.setColor(Color.BLACK);
		g.setFont(font);
		if(!givenString.equals(""))
		{
			g.drawString(givenString, x/3-(x/4), y/2);
		}
		else
		{
			givenString = "Cannot find String";
		}
	}
	
	//DESCRIPTION: Updates two left panels
	//PRECONDITION: instance variables must be initialized
	//POSTCONDITION: Updates two left panels
	public void updateFonts()
	{
		repaint();
	}
}
