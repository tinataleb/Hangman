/***************************************
* AUTHOR: Tina Taleb
* COURSE: CS 112 Intro to CS II
* SECTION: F 1-4:50pm
* HOMEWORK #: Final Project
* PROJECT #: -
* LAST MODIFIED: 05/13/2016
***************************************/
/*****************************************************************************
* ALL IMPORTED PACKAGES NEEDED AND PURPOSE:
* ArrayList = used for array of doubles
* Scanner = used for console input
* JOptionPane = used for interaction with user
* ImageIcon = used for adding icons to dialogues
*****************************************************************************/
import java.util.ArrayList; 
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
public class HangmanTester
{
	private static ArrayList<Character> listOfGuesses = new ArrayList<Character>(10);
	public static void main (String args[]) 
	{	
		Hangman person = new Hangman();
		HangmanJFrame game = new HangmanJFrame(person);
		game.setVisible(true);
		boolean gameEnds = false, wantsToPlayAgain = true;
		while(wantsToPlayAgain)
		{
			System.out.println(gameEnds);
			while (!gameEnds)
			{
				char guess = getInputFromPlayer();
				String mistake = person.updateGuess(guess);
				listOfGuesses.add(guess);
				game.updateGuessPanel(mistake);
				game.updateGraphics();
				gameEnds = person.weHaveAWinner() || person.weHaveALooser();
				person.getMistake();
			}
			
			//asking user
			Object[] options = {"Hell Yeah!",
				"Sorry, I gotta go :("};
			int choice = JOptionPane.showOptionDialog(null,
				"Do you wanna play again?",
				"A Silly Question",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,     
				options,  
				options[0]); 
			if(choice  == 0)
			{
				wantsToPlayAgain = true;
				gameEnds = false;
				listOfGuesses.clear();
				person.reset();
				game.updateGraphics();
			}
			else if(choice == 1)
			{
				wantsToPlayAgain = false;
				JOptionPane.showMessageDialog(null, "It was nice seeing you!",
					"Bye Bye!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("bye.png"));
			}
		}
	}
		
	public static char getInputFromPlayer()
	{
		char input = ' ';
		String in;
		boolean inputWasOk = true;
		do
		{
			try
			{
				in = JOptionPane.showInputDialog("What is your guess?");
				if(in != null && !in.isEmpty())
				{
					in = in.toUpperCase();
					input = in.charAt(0);
				}
				else
				{
					inputWasOk = false;
					throw new EmptyInputException();
				}
					
				if(((in.charAt(0)>= 65) && (in.charAt(0) <= 90)) && (!in.isEmpty()))
				{
					input = in.charAt(0);
					inputWasOk = true;
				}
				else
				{
					inputWasOk = false;
					throw new BadInputException();
				}
				if(listOfGuesses.contains(new Character(input)))
				{
					System.out.println("cyn");
					inputWasOk = false;
					throw new AlreadyEnteredException();
				}
				
			}
			catch(BadInputException e)
			{
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, e.getMessage(),
					"CAREFUL!",
					JOptionPane.WARNING_MESSAGE);
				
			}
			catch(EmptyInputException e)
			{
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, e.getMessage(),
					"ERROR!", JOptionPane.ERROR_MESSAGE);
			}
			catch(AlreadyEnteredException e)
			{
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, e.getMessage(),
					"ERROR!", JOptionPane.ERROR_MESSAGE);
			}
		}while(!inputWasOk);
		
		return input;
	}

}

