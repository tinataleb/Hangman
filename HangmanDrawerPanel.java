import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class HangmanDrawerPanel extends JPanel
{
	//INSTANCE VARIABELS
	
	private int expression;
	private Hangman myHangman;
	
	
	//CONSTRUCTOR
	
	//DESCRIPTION: Sets Instance variables to default values
	//PRECONDITION: N/A
	//POSTCONDITION: Sets Instance variables to default values
	public HangmanDrawerPanel(Hangman man) 
	{
		super();
		myHangman = man;
	}
	

	//OTHER REQUIRED
	
	// DESCRIPTION:	Returns string representation of object
	// PRE-CONDITION:	Instance variables have valid values
	// POST-CONDITION:	Returns string with all instance variables 
	@Override
	public String toString() 
	{
		return super.toString();
	}
	
	//HELPERS
	
	//DESCRIPTION: Draws the hangman panel regarding to which situation 
	//				the game is at
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Draws the hangman panel regarding to which  
	//				situation the game is at
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		int width = getWidth(); 
		int height = getHeight();
		
		boolean win;
		win = false;
		
		//Top
		g.fillRect(width/50, 0, width/2, height/50);
		//Left
		g.fillRect(width/50, 0, width/50, height);
		//Center
		g.fillRect(width/2, 0, width/50, height/4);
		//Bottom
		g.fillRect(0, height-height/50, width, height/50);
		//Left Corner
		drawLeftCorner(g);
		
		if (myHangman.weHaveAWinner())
		{
		drawFree(g);
		}
		else if (myHangman.getMistake() == 1)
		{
			drawNutral(g);
		}
		else if (myHangman.getMistake() == 2)
		{
			drawBody(g);
			drawNutral(g);
		}
		else if (myHangman.getMistake() == 3)
		{
			drawBody(g);
			drawLeftHand(g);
			drawNutral(g);
		}
		else if (myHangman.getMistake() == 4)
		{
			drawBody(g);
			drawLeftHand(g);
			drawRightHand(g);
			drawNutral(g);
		}
		else if (myHangman.getMistake() == 5)
		{
			drawBody(g);
			drawLeftHand(g);
			drawRightHand(g);
			drawLeftLeg(g);
			drawNutral(g);
		}
		else if (myHangman.getMistake() == 6)
		{
			drawBody(g);
			drawSadLeftHand(g);
			drawSadRightHand(g);
			drawLeftLeg(g);
			drawRightLeg(g);
			drawSad(g);
		}
	}
	
	//DESCRIPTION: Updates hangman panel
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Updates hangman panel
	public void updatDrawing()
	{
		revalidate();
		repaint();
	}
	
	//DESCRIPTION: Draws face
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Draws face
	private void drawFace(Graphics g)
	{
		int xCenter = getWidth() / 2; 
		int yCenter = getHeight() / 4;
		int radius = (int) (Math.min(getWidth(), getHeight()) * 0.1);
		int border = radius / 20;
		
		double diameterOuter = radius * 2.5;
		int xOuter = xCenter - radius;
		int yOuter = yCenter - radius;
		
		int diameterInner = (int)(diameterOuter * 0.9);
		int xInner = (int)(xCenter - radius*0.9);
		int yInner = (int)(yCenter - radius*0.9);
		g.setColor(Color.BLACK);
		g.fillOval(xOuter - border, yOuter - border, 2 * (radius + border), 2 * (radius + border));
		g.setColor(Color.WHITE);
		g.fillOval(xOuter - (border-5), yOuter - (border-5), 2 * (radius + (border-5)), 2 * (radius + (border-5)));
	}
	
	//DESCRIPTION: Draws eyes
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Draws eyes
	public void drawEyes(Graphics g) 
	{
		int xCenter = getWidth() / 2; 
		int yCenter = getHeight() / 4;
		int eyeRadius = (int) (Math.min(getWidth(), getHeight()) * 0.1);
		int leftX = (eyeRadius / 5); 
		int rightX = (eyeRadius / 5) * 3;
		int leftY = (eyeRadius / 2);
		int rightY = leftY;
		g.setColor(Color.BLACK);
		g.fillOval(xCenter - leftX, yCenter, (eyeRadius / 8), (eyeRadius / 5));
		g.fillOval(xCenter + leftX, yCenter, (eyeRadius / 8), (eyeRadius / 5));
	}
	
	//DESCRIPTION: Draws smile
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Draws smile
	public void drawSmile(Graphics g) 
	{
		int yCenter = getHeight() / 2;
		int xCenter = getWidth() / 4; 
		int mouthRadius = (int) (Math.min(getWidth(), getHeight()) * 0.1);
		g.fillArc(xCenter*2 - (mouthRadius/2), yCenter + (mouthRadius/4),  mouthRadius,  mouthRadius/2,180,180);
	}
	
	//DESCRIPTION: Draws sad face
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Draws sad face
	public void drawSad(Graphics g) 
	{
		int yCenter = getHeight() / 4;
		int xCenter = getWidth() / 4; 
		int mouthRadius = (int) (Math.min(getWidth(), getHeight()) * 0.1);
		drawFace(g);
		drawEyes(g);
		g.drawArc(xCenter*2 - (mouthRadius/5), yCenter + (mouthRadius/4), mouthRadius/2, mouthRadius/2,0,180);
	}
	
	//DESCRIPTION: Draws nutral face
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Draws nutral face
	public void drawNutral(Graphics g)
	{
		int yCenter = getHeight() / 4;
		int xCenter = getWidth() / 2; 
		int mouthRadius = (int) (Math.min(getWidth(), getHeight()) * 0.1);
		drawFace(g);
		drawEyes(g);
		g.fillOval(xCenter - (mouthRadius/4), yCenter + (mouthRadius/3), mouthRadius/2, mouthRadius/3);
	}
	
	//DESCRIPTION: Draws body
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Draws body
	public void drawBody(Graphics g)
	{
		int yStart = getHeight() / 3;
		int xStart = getWidth() / 2; 
		g.fillRect(xStart, yStart - (yStart/2), xStart/40, yStart + (yStart*11/20));
	}
	
	//DESCRIPTION: Draws left leg
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Draws left leg
	public void drawLeftLeg(Graphics g)
	{
		int yStart = getHeight() * 2 / 3;
		int xStart = getWidth() / 2; 
		for ( int i = 1; i < yStart/4; i++ )
		{
			g.fillRect( xStart-i, yStart+i, 5, 5);
		}
	}
	
	//DESCRIPTION: Draws right leg
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Draws right leg
	public void drawRightLeg(Graphics g)
	{
		int yStart = getHeight() * 2 / 3;
		int xStart = getWidth() / 2; 
		for ( int i = 1; i < yStart/4; i++ )
		{
			g.fillRect( xStart+i, yStart+i, 5, 5);
		}
	}
	
	//DESCRIPTION: Draws left hand
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Draws left hand
	public void drawLeftHand(Graphics g)
	{
		int yStart = getHeight() / 2;
		int xStart = getWidth() / 2; 
		for ( int i = 1; i < yStart/4; i++ )
		{
			g.fillRect( xStart-i, yStart-i, 5, 5);
		}
	}
	
	//DESCRIPTION: Draws right hand
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Draws right hand
	public void drawRightHand(Graphics g)
	{
		int yStart = getHeight() /2;
		int xStart = getWidth() / 2; 
		for ( int i = 1; i < yStart/4; i++ )
		{
			g.fillRect( xStart+i, yStart-i, 5, 5);
		}
	}
	
	//DESCRIPTION: Draws sad left hand
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Draws sad left hand
	public void drawSadLeftHand(Graphics g)
	{
		int yStart = getHeight() / 2;
		int xStart = getWidth() / 2; 
		for ( int i = 1; i < yStart/4; i++ )
		{
			g.fillRect( xStart-i, yStart+i, 5, 5);
		}
	}
	
	//DESCRIPTION: Draws sad right hand
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Draws sad right hand
	public void drawSadRightHand(Graphics g)
	{
		int yStart = getHeight() /2;
		int xStart = getWidth() / 2; 
		for ( int i = 1; i < yStart/4; i++ )
		{
			g.fillRect( xStart+i, yStart+i, 5, 5);
		}
	}
	
	//DESCRIPTION: Draws left corner
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Draws left corner
	public void drawLeftCorner(Graphics g)
	{
		int yStart = getHeight() / 5;
		int xStart = getWidth()/50; 
		for ( int i = 1; i < xStart * 50; i++ )
		{
			g.fillRect( xStart+i, yStart-i, 5, 5);
		}
	}
	
	//DESCRIPTION: Draws a free hangman
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Draws a free hangman
	public void drawFree(Graphics g)
	{
		//Lines of falling
		g.fillRect(getWidth()*5/8, getHeight()/3, getWidth()/90, getHeight()/20);
		g.fillRect(getWidth()/2, getHeight()*2/7, getWidth()/90, getHeight()/20);
		g.fillRect(getWidth()*3/8, getHeight()/3, getWidth()/90, getHeight()/20);	
		
		//Body
		int yStartBody = getHeight() / 10;
		int xStartBody = getWidth() / 2; 
		g.fillRect(xStartBody, yStartBody*6 - (yStartBody*2/3), xStartBody/40, 7* yStartBody/3);
		
		//Hands
		int yStartHands = getHeight() * 2 /3 ;
		int xStartHands = getWidth() / 2; 
		for ( int i = 1; i < yStartHands/4; i++ )
		{
			g.fillRect( xStartHands+i, yStartHands-i, 5, 5);
			g.fillRect( xStartHands-i, yStartHands-i, 5, 5);
		}
		//Legs
		
		int yStartLegs = getHeight() * 3 / 4;
		int xStartLegs = getWidth() / 2; 
		for ( int i = 1; i < yStartLegs/4; i++ )
		{
			g.fillRect( xStartLegs+i, yStartLegs+i, 5, 5);
			g.fillRect( xStartLegs-i, yStartLegs+i, 5, 5);
		}

		//Face
		drawHappyFace(g);
	}
	
	//DESCRIPTION: Draws happy face
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Draws happy face
	public void drawHappyFace(Graphics g)
	{
		//Face
		int xCenter = getWidth() / 2; 
		int yCenter = getHeight() / 2;
		int radius = (int) (Math.min(getWidth(), getHeight()) * 0.1);
		int border = radius / 20;
		
		double diameterOuter = radius * 2.5;
		int xOuter = xCenter - radius;
		int yOuter = yCenter - radius;
		
		int diameterInner = (int)(diameterOuter * 0.9);
		int xInner = (int)(xCenter - radius*0.9);
		int yInner = (int)(yCenter - radius*0.9);
		g.setColor(Color.BLACK);
		g.fillOval(xOuter - border, yOuter - border, 2 * (radius + border), 2 * (radius + border));
		g.setColor(Color.WHITE);
		g.fillOval(xOuter - (border-5), yOuter - (border-5), 2 * (radius + (border-5)), 2 * (radius + (border-5)));
		
		//Eyes
		int eyeRadius = (int) (Math.min(getWidth(), getHeight()) * 0.1);
		int leftX = (eyeRadius / 5); 
		int rightX = (eyeRadius / 5) * 3;
		int leftY = (eyeRadius / 2);
		int rightY = leftY;
		g.setColor(Color.BLACK);
		g.fillOval(xCenter - leftX, yCenter, (eyeRadius / 8), (eyeRadius / 5));
		g.fillOval(xCenter + leftX, yCenter, (eyeRadius / 8), (eyeRadius / 5));
		
		//Mouth
		drawSmile(g);
	}
}
