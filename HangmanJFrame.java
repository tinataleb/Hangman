import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import javax.swing.ImageIcon;

public class HangmanJFrame extends JFrame
{
	//CONSTANTS
	
	public static final int WIDTH = 1900;
	public static final int HEIGHT = 1000;
	
	
	//INSTANCE VARIABELS
	
	private HangmanFontPanel wordPanel, alreadyGuessedPanel;
	private HangmanDrawerPanel drawHangmanPanel;
	private JPanel leftPanel;
	private Hangman JFrameMan;
	private JLabel wordPanelLabel, alreadyGuessedPanelLabel;
	
	
	//CONSTRUCTORS
	
	//DESCRIPTION: Sets Instance variables to default values and given Hangman
	//PRECONDITION: Instance variables must be initialized
	//POSTCONDITION: Sets Instance variables to default values and given Hangman
	public HangmanJFrame(Hangman someHangman)
	{
		super();
		String prompt;
		prompt = "Welcome to Hangman. \n I chose a random for you."+
				"Let's see if you can guess it before the man hangs " +
				"\nor hangman gets hanged or,,,      anyways..." +
				"\nDon't make it sad, you will see a happy face when you win.";
		Object[] options = { "Let's do it'", "EXIT" };
		int input = JOptionPane.showOptionDialog(null, prompt, "Welcome!", 
			JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("hangman.png"), options, options[0]);

		if(input == 1)
		{
			JOptionPane.showMessageDialog(null, "Ok maybe i'll see you later?",
					"Bye Bye!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("earlyBye.png"));
			System.exit(0);
		}
		
		//Setting JFrame properties
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("H A N G M A N");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		
		//LeftPanel
		
		//North
		wordPanelLabel = new JLabel("Guess the word I chose:");
		wordPanelLabel.setOpaque(true);
		wordPanelLabel.setBackground(new Color(204,204,204));
		
		//Center
		wordPanel = new HangmanFontPanel();
		wordPanel.setGivenString("Start Guessing");
		wordPanel.setPreferredSize(new Dimension(WIDTH/2, HEIGHT/2));
		wordPanel.setBackground(new Color(89, 227, 130));
		
		
		//SOUTH
		alreadyGuessedPanel = new HangmanFontPanel();
		alreadyGuessedPanel.setGivenString("Failed Attempts");
		alreadyGuessedPanel.setPreferredSize(new Dimension(WIDTH/2, HEIGHT/2));
		alreadyGuessedPanel.setBackground(new Color(224,132,132));
		
		//East
		JFrameMan = someHangman;
		drawHangmanPanel = new HangmanDrawerPanel(JFrameMan);
		drawHangmanPanel.setBackground(new Color(230,255,252));
		drawHangmanPanel.setPreferredSize(new Dimension(WIDTH/3, HEIGHT));
		
		//Adding everything to JFrame
		leftPanel.add(wordPanelLabel, BorderLayout.NORTH);
		leftPanel.add(wordPanel, BorderLayout.CENTER);
		leftPanel.add(alreadyGuessedPanel, BorderLayout.SOUTH);
		this.add(leftPanel, BorderLayout.WEST);
		this.add(drawHangmanPanel, BorderLayout.CENTER);
	}
	
	
	//HELPERS
	
	//DESCRIPTION: Updates guessPanel to given mistake and wordPanel to curren currentSolution
	//PRECONDITION: Instance variables must have valid values
	//POSTCONDITION: Updates guessPanel to given mistake and wordPanel to curren currentSolution
	public void updateGuessPanel(String mistake) 
	{		
		wordPanel.setGivenString(JFrameMan.getCurrentSolution().toUpperCase());
		alreadyGuessedPanel.setGivenString(mistake.toUpperCase());
	}
	
	//DESCRIPTION: Updates graphic(hangman and fonts)
	//PRECONDITION: Instance variables must have valid values
	//POSTCONDITION: Updates graphic(hangman and fonts)
	public void updateGraphics()
	{
		drawHangmanPanel.updatDrawing();
		alreadyGuessedPanel.updateFonts();
		wordPanel.updateFonts();
		updateGuessPanel(JFrameMan.getMistakesString());
		if(JFrameMan.weHaveALooser())
		{
			wordPanel.setGivenString(JFrameMan.getWordOfGame());
		}
	}
}



