import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.Math;
import javax.swing.JOptionPane;
import java.util.Arrays;

public class Hangman 
{
	//CONSTANTS
	
	public static final String DEFAULT_FILE_NAME = "WordsForHangman.txt";
	public static final char DEFAULT_CHAR = '-';
	public static final int DEFAULT_MISTAKE = 0;
	public static final int FIRST_WRONG = 1, SECOND_WRONG = 2;
	public static final int THIRED_WRONG = 3, FORTH_WRONG = 4;
	public static final int FIFTH_WRONG = 5, SIXTH_WRONG = 6;
	public static final int MAX_MISTAKES = 6;
	
	
	//INSTANCE VARIABELS
	
	char[] mistakes = {DEFAULT_CHAR,DEFAULT_CHAR,DEFAULT_CHAR,DEFAULT_CHAR,DEFAULT_CHAR,DEFAULT_CHAR}, curSol;
	String currentSolution; 
	String wordOfGame;
	int mistake;
	String[] words;


	//CONSTRUCTORS
	
	//DESCRIPTION: Sets Instance variables to given values
	//PRECONDITION: N/A
	//POSTCONDITION: Sets Instance variables to given values
	public Hangman(String[] words, int mistake)
	{
		setMistake(DEFAULT_MISTAKE);
		readWords();
		initializeCurSol(wordOfGame);
		setCurrentSolution(charArrayToString(curSol));
		this.currentSolution = charArrayToString(curSol);
	}
	
	//DESCRIPTION: Sets Instance variables to default values
	//PRECONDITION: N/A
	//POSTCONDITION: Sets Instance variables to default values
	public Hangman()
	{
		setMistake(DEFAULT_MISTAKE);
		readWords();
		initializeCurSol(wordOfGame);
		this.setCurrentSolution(charArrayToString(curSol));
	}
	
	
	//SETTERS
	
	//DESCRIPTION: Sets mistake to given int
	//PRECONDITION: Instance variable must be initialized
	//POSTCONDITION: Sets mistake to given int
	public void setMistake(int mistake) 
	{ 
		this.mistake = mistake;
	}
	
	//DESCRIPTION: Sets wordOfGame to given String
	//PRECONDITION: Instance variable must be initialized
	//POSTCONDITION: Sets wordOfGame to given String
	public void setWordOfGame(String wordOfGame) 
	{ 
		this.wordOfGame = wordOfGame;
	}
	
	//DESCRIPTION: Sets currentSolution to given String
	//PRECONDITION: Instance variable must be initialized
	//POSTCONDITION: Sets currentSolution to given String
	public void setCurrentSolution(String currentSolution) 
	{ 
		this.currentSolution = currentSolution;
	}
	
	
	//GETTERS
	
	//DESCRIPTION: Gives wordOfGame
	//PRECONDITION: Instance variable has valid value
	//POSTCONDITION: Returns wordOfGame 
	public String getWordOfGame()
	{
		return this.wordOfGame;
	}
	
	//DESCRIPTION: Gives currentSolution
	//PRECONDITION: Instance variable has valid value
	//POSTCONDITION: Returns currentSolution 
	public String getCurrentSolution()
	{
		return this.currentSolution;
	}
	
	//DESCRIPTION: Gives mistake
	//PRECONDITION: Instance variable has valid value
	//POSTCONDITION: Returns mistake 
	public int getMistake() 
	{ 
		return this.mistake; 
	}
	
	//DESCRIPTION: Gives mistakes
	//PRECONDITION: Instance variable has valid value
	//POSTCONDITION: Returns a deep copy of mistakes 
	public char[] getMistakes()
	{
		return copyArray(mistakes);
	}
	
	//DESCRIPTION: Gives mistakes
	//PRECONDITION: Instance variable has valid value
	//POSTCONDITION: Returns a String representation of mistakes 
	public String getMistakesString()
	{
		return charArrayToString(copyArray(mistakes));
	}

	//OTHER REQUIRED
	
	// DESCRIPTION:	Returns boolean is "this" ReadMyFile is equal to parameter
	// PRE-CONDITION: Instance variables have valid values for both ReadMyFile objects
	// POST-CONDITION: Returns true if both are exactly the same, false otherwise
	@Override
	public boolean equals(Object other)
	{
		if(other == null || !(other instanceof Hangman)) 
		{
            return false;
        } else 
        {
            Hangman otherHangman = (Hangman)other;
            return this.getMistakesString().equals(otherHangman.getMistakesString()) &&
					arraysAreEqual(this.words, otherHangman.words) &&
					this. mistake == otherHangman.mistake &&
					this.wordOfGame.equals(otherHangman.wordOfGame);
        }
	}
	
	//DESCRIPTION: Gives back a deep copy of object
	//PRECONDITION: Object must have valid parameters
	//POSTCONDITION: Returns a deep copy of item
	@Override
	public Object clone()
	{
		try 
		{
			Hangman copy;
			copy = (Hangman)super.clone();
			copy.words = (String[])this.words.clone();
			copy.wordOfGame = this.wordOfGame;
			copy.mistake = this.mistake;
			copy.mistakes = (char[]) this.mistakes.clone();
			copy.currentSolution = this.currentSolution;
			copy.curSol = (char[])this.curSol;
			return copy;
		} 
		catch (CloneNotSupportedException e) 
		{
			return null; 
		}
	}
	
	
	//HELPERS
	
	//DESCRIPTION: Reads words from file and puts them into words array
	//PRECONDITION: Instant variables must be initialized
	//POSTCONDITION: Reads words from file and puts them into words array
	public void readWords()
	{
		Scanner inputStream;
		
		int size;
		try
		{
			inputStream = new Scanner(new FileInputStream(DEFAULT_FILE_NAME));
			size = inputStream.nextInt();
			inputStream.nextLine();
			words = new String[size];
			for(int i=0; i < size; i++)
			{
				String h;
				h=inputStream.nextLine().trim().toUpperCase();
				words[i] = h;
			}
			System.out.println("Done getting word from file.");
			inputStream.close();
			wordOfGame = getRandomWord(words);
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("Error: File " + DEFAULT_FILE_NAME + 
								" not found or could not be opened.");
			System.exit(0);
		} 		
	} 
	
	//DESCRIPTION: Makes a deep copy of given array
	//PRECONDITION: Array must be declared and initialized
	//POSTCONDITION: Returns a deep copy of given array
	public static String[] copyArray(String[] a)
	{
		String[] copy;
		copy = new String[a.length];
		for(int i=0; i < a.length; i++)
		{
			copy[i] = a[i];
		}
		return copy;
	}
	
	// DESCRIPTION:	Makes a deep copy of given array
	// PRE-CONDITION: Arrays have valid values 
	// POST-CONDITION: Returns a deep copy of given array
	public static char[] copyArray(char[] a)
	{
		char[] copy;
		copy = new char[a.length];
		for(int i=0; i < a.length; i++)
		{
			copy[i] = a[i];
		}
		return copy;
	}
	
	// DESCRIPTION:	Returns a boolean representation of if both array are equal
	// PRE-CONDITION: Arrays have valid values 
	// POST-CONDITION: Returns true if both are exactly the same, false otherwise
	public static boolean arraysAreEqual(String[] a, String[] b)
	{
		boolean theyAreEqual;
		theyAreEqual = true;
		for(int i=0; i < a.length; i++)
		{
			theyAreEqual = (a[i].equals(b[i]));
		}
		return theyAreEqual;
	}	
	
	//DESCRIPTION: Checks if given arrays are identical
	//PRECONDITION: Array must be declared and initialized
	//POSTCONDITION: Returns true if they are identical
	public static boolean arraysAreEqual(char[] a, char[] b)
	{
		boolean theyAreEqual;
		theyAreEqual = true;
		for(int i=0; i < a.length; i++)
		{
			theyAreEqual = (a[i] == b[i]);
		}
		return theyAreEqual;
	}	
	//DESCRIPTION: Makes a String representation of array
	//PRECONDITION: Array must be declared and initialized
	//POSTCONDITION: Returns a String representation of array
	public static String stringArray(String[] a)
	{
		String result = "";
		for(int i=0; i < a.length; i++)
		{
			result += a[i] + "\n";
		}
		return result;
	}
	
	//DESCRIPTION: Gives a String representation of given char array
	//PRECONDITION: Instant variables must be initialized
	//POSTCONDITION: Gives a String representation of given char array
	public static String charArrayToString(char[] c)
	{
		String result = "";
		for(int i=0; i < c.length; i++)
		{
			result += " " + c[i];
		}
		return result;
	}
	
	//DESCRIPTION: Chooses a random word from given String array
	//PRECONDITION: Instant variables must be initialized
	//POSTCONDITION: Returns a random word from given String array
	public static String getRandomWord(String[] a)
	{
		int randomIndex;
		randomIndex = (int) (Math.random() * a.length);
		return a[randomIndex];
	}
	
	//DESCRIPTION: Turns a String to char array
	//PRECONDITION: Instant variables must be initialized
	//POSTCONDITION: Turns a String to char array
	public static char[] wordToCharArray(String word)
	{
		char[] arrayedWord;
		arrayedWord = new char[word.length()];
		for(int i=0; i < word.length(); i++)
		{
			arrayedWord[i] = word.charAt(i);
		}
		return arrayedWord;
	}
	
	//DESCRIPTION: Adds to mistakes when called
	//PRECONDITION: Instant variables must be initialized
	//POSTCONDITION: Increaments mistakes and returns the new value
	public int increamentMistake()
	{
		return this.mistake++;
	}
	
	//DESCRIPTION: Checks if the given char is a part of wordOfGame, and
	//			updates current Solution. Elsewise adds it to mistakes
	//			array if there is any index with '-' sign
	//PRECONDITION: Instant variables must be initialized
	//POSTCONDITION: Returns a String of updated mistakes
	public String updateGuess(char guess)
	{
		boolean wasUpdated = false, wasInMyWord = false;
		do
		{
			for(int i=0; i<wordOfGame.length(); i++)
			{
				if(wordOfGame.charAt(i) == guess)
				{
					curSol[i] = guess;
					wasUpdated = true;
					wasInMyWord = true;
					currentSolution = charArrayToString(curSol);
					System.out.println(curSol);
				}
			}
			if(!wasInMyWord)
			{
				this.mistake++;
				int j = 0;
				while(mistakes[j] != DEFAULT_CHAR && j<mistakes.length-1)
				{
					j++;
				}
				mistakes[j] = guess;
				wasUpdated = true;
				System.out.println("was not in word");
			}
		}while(!wasUpdated);
		
		return charArrayToString(mistakes);
	}
	
	//DESCRIPTION: Returns a boolean representaion of having a winner
	//PRECONDITION: Instant variables must be initialized
	//POSTCONDITION: Returns true if win
	public boolean weHaveAWinner()
	{
		int i =0;
		boolean areIdentical = true;
		
		while (areIdentical && i<curSol.length)
		{
			areIdentical = (curSol[i] == wordOfGame.charAt(i));
			i++;
		}
		return areIdentical;
	}	
	
	//DESCRIPTION: Returns a boolean representaion of having a looser
	//PRECONDITION: Instant variables must be initialized
	//POSTCONDITION: Returns true if lose
	public boolean weHaveALooser()
	{
		return (mistake == MAX_MISTAKES);
	}
	
	//DESCRIPTION: Resets everything to start a new game and gets a new
	//				word for the next game
	//PRECONDITION: Instant variables must be initialized
	//POSTCONDITION: Resets mistake, curSol, currentSolution and mistakes.
	//				And gets a new word for the next game
	public void reset()
	{
		mistake = 0;
		setWordOfGame(getRandomWord(words));
		this.curSol = new char[wordOfGame.length()];
		for(int i=0; i<wordOfGame.length(); i++ )
		{
			this.curSol[i] = DEFAULT_CHAR;
		}
		currentSolution = charArrayToString(curSol);
		this.mistakes = new char[MAX_MISTAKES];
		for(int j=0; j<MAX_MISTAKES; j++)
		{
			mistakes[j] = DEFAULT_CHAR;
		}
	}
	
	//DESCRIPTION: Initializes curSol
	//PRECONDITION: Instant variables must be initialized
	//POSTCONDITION: Sets all the indexes to '-'
	public void initializeCurSol(String wordOfGame)
	{
		curSol = new char[wordOfGame.length()];
		for(int i=0; i<wordOfGame.length(); i++ )
		{
			curSol[i] = DEFAULT_CHAR;
		}
		setCurrentSolution(charArrayToString(curSol));
		System.out.println(currentSolution);
	}
}


		
