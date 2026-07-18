package wordgamedefault;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUI
{
	/* DATA */
	
	// SWING Components
	private JFrame wordFrame = new JFrame("Word Game");
	private JLabel playerLabel = new JLabel("Player List:");
	private JLabel hostNameLabel = new JLabel("Host Name:");
	private JLabel playingPhrase = new JLabel();
	private JButton startPlayingButton = new JButton("Start");
	private JButton submitButton = new JButton("Submit");
	static public JTextArea dialogueArea = new JTextArea(13,20);
	private JTextField letterTextField = new JTextField(10);
	private JScrollPane scrollPane = new JScrollPane(dialogueArea);
	static public JOptionPane infoPane = new JOptionPane();
	static public JOptionPane restartPane = new JOptionPane();
	static public JOptionPane newPhrase = new JOptionPane();
	private JOptionPane layoutPane = new JOptionPane();
	private JPanel playerPanel = new JPanel();
	private JPanel wordPanel = new JPanel();
	private JPanel dialoguePanel = new JPanel();
	private JPanel textPanel = new JPanel();
	private JCheckBox saveCheckBox = new JCheckBox("Save messages.");
	
	// Creating Menu Bar
	private JMenuBar menuBar = new JMenuBar();
	private JMenu gameMenu = new JMenu("Game");
	private JMenuItem addPlayerItem = new JMenuItem("Add Player");
	private JMenuItem addHostItem = new JMenuItem("Add Host");
	private JMenu aboutMenu = new JMenu("About");
	private JMenuItem layoutItem = new JMenuItem("Layout");
	
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
		wordFrame.setSize(500,500);
		wordFrame.setLayout(new GridLayout(2,2));
		wordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		wordFrame.setLocationRelativeTo(null);
		wordFrame.setResizable(false);
		
		wordFrame.add(playerPanel);
		wordFrame.add(dialoguePanel);
		wordFrame.add(textPanel);
		wordFrame.add(wordPanel);
		
		playerPanel.setBackground(Color.PINK);
		wordPanel.setBackground(Color.PINK);
		dialoguePanel.setBackground(Color.PINK);
		textPanel.setBackground(Color.PINK);
		
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
		dialoguePanel.setLayout(new BoxLayout(dialoguePanel, BoxLayout.Y_AXIS));
		wordPanel.setLayout(new BorderLayout());
		textPanel.setLayout(new FlowLayout());
		
		wordPanel.setBorder(new EmptyBorder(10,50,10,15));
		wordPanel.add(playingPhrase, BorderLayout.CENTER);
		
		playerPanel.setBorder(new EmptyBorder(10,15,10,15));
		playerPanel.add(playerLabel);
		playerPanel.add(Box.createVerticalStrut(15));
		playerPanel.add(hostNameLabel);
		playerPanel.add(Box.createVerticalStrut(15));
		playerPanel.add(startPlayingButton);
		playerLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		dialogueArea.setLineWrap(true);
		dialogueArea.setWrapStyleWord(true);
		dialogueArea.setEditable(false);
		dialoguePanel.add(scrollPane);
		dialoguePanel.setBorder(new EmptyBorder(10,15,10,15));
		dialoguePanel.add(Box.createVerticalStrut(3));
		dialoguePanel.add(saveCheckBox);
		saveCheckBox.setToolTipText("Saves the messages in the text area");
		
		textPanel.setBorder(new EmptyBorder(100,15,10,15));
		textPanel.add(letterTextField);
		textPanel.add(submitButton);
		letterTextField.setEnabled(false);
		submitButton.setEnabled(false);
		
		// Creating the Menu Bar
		gameMenu.add(addPlayerItem);
		gameMenu.add(addHostItem);
		aboutMenu.add(layoutItem);
		menuBar.add(gameMenu);
		menuBar.add(aboutMenu);
		wordFrame.setJMenuBar(menuBar);
		gameMenu.setMnemonic('G');
		aboutMenu.setMnemonic('A');
		
		// Sets the font for the playing phrase
		playingPhrase.setFont(new Font("Display", Font.BOLD, 18));
		
		// Button Action Events
		
		layoutItem.addActionListener(e->{
			String layoutMessage = "When creating the layout for this GUI, I made sure to have"
					+ " small frame so that the components wouldn't be far apart and leave a lot of space.\n"
					+ "I seperated the components in four quadrants, where each has it's own purpose.\n"
					+ "In the to top left, the names of both the host and players are displayed along with "
					+ "the start button, so the players can easily start the game.\nIn the top right, "
					+ "the text area is displayed along with the 'save messages' check box, so that the player "
					+ "knows that the check box is for the text area.\nIn the bottom right, I put the text field"
					+ " and submit button together, so the players can know that when they type a letter they use "
					+ "the submit button and not the start button.\nEven though the bottom left looks empty"
					+ ", when a phrase is chosen the underlines of the phrase will be displayed there for the "
					+ "player to look at.";
			
			layoutPane.showMessageDialog(wordFrame, layoutMessage);
		});
		
		addPlayerItem.addActionListener(e ->{
			setNewPlayerButton();
		});
		
		addHostItem.addActionListener(e -> {
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
			String winnerMessage = "The winner: " + winnerPlayer.getFirstName() + "\n"
									+ "Money won: $" + winnerPlayer.getCurrentMoney() + "\n";
			
			dialogueArea.append(winnerMessage);
			
			infoPane.showMessageDialog(wordFrame, winnerMessage);
			
			submitButton.setEnabled(false);
			boolean playAgain = host.playAgain(didWin);
			if (playAgain)
			{
				newPhrase();
				
				if (!(saveCheckBox.isSelected()))
				{
					dialogueArea.setText("");
				}
				
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
		addPlayerItem.setEnabled(false);
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
			addPlayerItem.setEnabled(false);
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
				addHostItem.setEnabled(false);
				
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
