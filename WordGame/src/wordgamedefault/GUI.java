package wordgamedefault;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI
{
	/* DATA */
	
	// SWING Components
	private JFrame wordFrame = new JFrame("Word Game");
	private JLabel playerLabel = new JLabel("Player List:");
	private JLabel hostNameLabel = new JLabel("Host Name:");
	private JLabel playingPhrase = new JLabel();
	private JButton newPlayerButton = new JButton("New Player!");
	private JButton hostNameButton = new JButton("New Host!");
	private JButton startPlayingButton = new JButton("Start");
	private JButton submitButton = new JButton("Submit");
	static public JTextArea dialogueArea1 = new JTextArea(13,20);
	private JTextField letterTextField = new JTextField(10);
	private JScrollPane scrollPane = new JScrollPane(dialogueArea1);
	static public JOptionPane infoPane = new JOptionPane();
	static public JOptionPane restartPane = new JOptionPane();
	static public JOptionPane newPhrase = new JOptionPane();
	private JPanel playerPanel = new JPanel();
	private JPanel hostPanel = new JPanel();
	private JPanel dialoguePanel = new JPanel();
	private JPanel textPanel = new JPanel();
	
	// Object instantiation
	Phrases phraseClass = new Phrases();
	Players[] currentPlayers = new Players[3];
	Hosts host;
	Turn turn = new Turn();
	Physical physical = new Physical();
	
	// Variables
	String letter = "";
	boolean didWin = false;
	private int clickCount = 0;
	private final int MAX_CLICKS = 3;
	private Players currPlayer; 
	private Players winnerPlayer;
	private int currPlayerNum = 0;
	
	// Default Constructor
	public GUI()
	{
		initialize();
	}
	
	/* GUI METHODS*/
	
	// Method arranges the Swing Components
	// and sets the Action Listeners of each button
	private void initialize()
	{
		wordFrame.setSize(800,500);
		wordFrame.setLayout(new GridLayout(2,2));
		wordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		wordFrame.setLocationRelativeTo(null);
		wordFrame.setResizable(false);
		
		wordFrame.add(playerPanel);
		wordFrame.add(hostPanel);
		wordFrame.add(dialoguePanel);
		wordFrame.add(textPanel);
		
		playerPanel.setBackground(Color.PINK);
		hostPanel.setBackground(Color.PINK);
		dialoguePanel.setBackground(Color.PINK);
		textPanel.setBackground(Color.PINK);
		
		playerPanel.add(playerLabel);
		playerPanel.add(newPlayerButton);
		newPlayerButton.setFocusable(false);
		
		hostPanel.add(hostNameLabel);
		hostPanel.add(hostNameButton, BorderLayout.SOUTH);
		hostNameButton.setFocusable(false);

		dialogueArea1.setLineWrap(true);
		dialogueArea1.setWrapStyleWord(true);
		dialogueArea1.setEditable(false);
		dialoguePanel.add(scrollPane);
		dialoguePanel.add(startPlayingButton);
		
		textPanel.add(playingPhrase);
		textPanel.add(letterTextField);
		textPanel.add(submitButton);
		letterTextField.setEnabled(false);
		submitButton.setEnabled(false);
		
		// Button Action Events
		newPlayerButton.addActionListener(e ->{
			setNewPlayerButton();
		});
		
		hostNameButton.addActionListener(e ->{
			setHostNameButton();
		});
		
		startPlayingButton.addActionListener(e-> 
		{
			setStartPlayingButton();
		});
		
		submitButton.addActionListener(e -> 
		{
			setSubmitButtonAction();
		});
	}
	
	//BUTTON ACTION METHODS
	
	// Method gets the letter in the text field,
	// cycles between the players in the list,
	// and displays a congratulation method when someone wins. 
	public void setSubmitButtonAction()
	{
		letter = letterTextField.getText().trim();
		letterTextField.setText("");
		currPlayer = currentPlayers[currPlayerNum];
		winnerPlayer = currPlayer;
		
		startGame();
		
		currPlayerNum++;
		
		if (currPlayerNum >= clickCount)
		{
			currPlayerNum = 0;
		}
		
		if (didWin)
		{
			infoPane.showMessageDialog(wordFrame, "The winner: " + winnerPlayer.getFirstName() + "\n"
											+ "Money won: $" + winnerPlayer.getCurrentMoney() + "\n");
			
			submitButton.setEnabled(false);
			boolean playAgain = host.playAgain(didWin);
			if (playAgain)
			{
				newPhrase();
				
				didWin = false;
				letterTextField.setText("");
				submitButton.setEnabled(true);
				startPlayingButton.setEnabled(false);
			}
		}
	}
	
	// Method enables or disables some buttons.
	// However if there is no host or at least one player,
	// it will ask to create them before beginning.
	public void setStartPlayingButton()
	{
		if (host == null)
		{
			infoPane.showMessageDialog(wordFrame, "Please create a host first.");
			return;
		}
		
		if (currentPlayers[0] == null)
		{
			infoPane.showMessageDialog(wordFrame, "Please add at least one player.");
			return;
		}
		
		letterTextField.setEnabled(true);
		submitButton.setEnabled(true);
		startPlayingButton.setEnabled(false);
		newPlayerButton.setEnabled(false);
	}
	
	// Displays the host's name in the frame
	public void setHostNameButton()
	{
		if (createHost())
		{
			hostNameLabel.setText(hostNameLabel.getText() + " " + host.getFirstName());
		}
	}
	
	// JOptionPane is displayed
	// asking for the player's name
	public void setNewPlayerButton()
	{
		int playerNum = clickCount;
		getPlayersName(playerNum);
		if (currentPlayers[playerNum] != null)
		{
			playerLabel.setText(playerLabel.getText() + " " + currentPlayers[playerNum].getFirstName());
			clickCount++;
		}
		
		if (clickCount >= MAX_CLICKS)
		{
			newPlayerButton.setEnabled(false);
			newPlayerButton.setText("No More Players.");
		}
	}
	
	
	// GAME SET UP METHODS
	
	// Method sets the player's name and displays it in the frame
	public void getPlayersName(int playerNum)
	{
		while (true)
		{
			JTextField firstNameField = new JTextField();
			JTextField lastNameField = new JTextField();
			
			JComponent[] nameInputs = new JComponent[]
			{
				new JLabel("What is your first Name?  "), firstNameField,
				new JLabel("What is your lastName? "), lastNameField
					
			};
			
			int result = JOptionPane.showConfirmDialog(null, nameInputs, 
					"Please enter the following fields: ", 
					JOptionPane.OK_CANCEL_OPTION, 
		            JOptionPane.PLAIN_MESSAGE);
			
			if (result != JOptionPane.OK_OPTION)
			{
				return;
			}
			
			String firstName = firstNameField.getText().trim();
			String lastName = lastNameField.getText().trim();
			
			if (firstName.isBlank())
			{
				infoPane.showMessageDialog(null, "Please insert information again", "Input Error", JOptionPane.ERROR_MESSAGE);
				continue;
			}
				
			currentPlayers[playerNum] = new Players(firstName, lastName);
			return;
		}
	}
	
	// Method creates a host, sets the phrase of the game, and displays
	// both in the frame
	private boolean createHost()
	{
		while (true)
		{
			JTextField hostNameField = new JTextField();
			JTextField phraseField = new JTextField();
			
			JComponent[] hostInputs = new JComponent[]
			{
				new JLabel("What is the host's name?  "), hostNameField,
				new JLabel("What is the phrase for this game? "), phraseField
			};
			
			int result = JOptionPane.showConfirmDialog(null, hostInputs, 
					"Please enter the following fields: ", 
					JOptionPane.OK_CANCEL_OPTION, 
		            JOptionPane.PLAIN_MESSAGE);
			
			if (result != JOptionPane.OK_OPTION)
			{
				return false;
			}
			

			String hostName = hostNameField.getText();
			String phrase = phraseField.getText();
			
			if (!(phrase == null || phrase.isEmpty() || phrase.isBlank() || hostName == null || hostName.isEmpty() || hostName.isBlank()))
			{
				host = new Hosts(hostName);
				host.setGamePhrase(phrase);
				phraseClass.setPlayingPhrase(phrase);
				playingPhrase.setText(phraseClass.getPlayingPhrase());
				hostNameButton.setEnabled(false);
				hostNameButton.setText("Host & phrase added!");
				
				return true;
			}
			else
			{
				infoPane.showMessageDialog(null, "Please insert information again", "Input Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	// Method displays a JOptionPane that asks for a new phrase
	public void newPhrase()
	{
		JTextField phraseField = new JTextField();
		
		JComponent[] phraseInput = new JComponent[]
		{
			new JLabel("What is the new phrase? "), phraseField	
		};
		
		int result = JOptionPane.showConfirmDialog(null, phraseInput,"Please enter the following field",
				JOptionPane.OK_CANCEL_OPTION, 
	            JOptionPane.PLAIN_MESSAGE);
		
		if (result == JOptionPane.OK_OPTION)
		{
			String phrase = phraseField.getText();
			host.setGamePhrase(phrase);
			phraseClass.setPlayingPhrase(phrase);
			playingPhrase.setText(phraseClass.getPlayingPhrase());
		}
	}
	
	// Method starts the players turn and sets/updates the playingPhrase
	public void startGame()
	{
		didWin = turn.takeTurn(currPlayer, host, letter, phraseClass);
		playingPhrase.setText(phraseClass.getPlayingPhrase());
	}
	
	// Method displays the frame
	public void show()
	{
		wordFrame.setVisible(true);
	}
	
}
