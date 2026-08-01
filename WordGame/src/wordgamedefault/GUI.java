package wordgamedefault;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame
{
	/* DATA */
	
	// SWING Components
	private JFrame wordFrame = new JFrame("Word Game");
	private JLabel playerLabel = new JLabel("Player List:");
	private JLabel hostNameLabel = new JLabel("Host Name:");
	private JLabel playingPhrase = new JLabel();
	private JLabel prizesWonLabel = new JLabel("Prizes Won:");
	private JButton startPlayingButton = new JButton("Start");
	private JButton submitButton = new JButton("Submit");
	static public JTextArea dialogueArea = new JTextArea(13,20);
	private JTextField letterTextField = new JTextField(10);
	private JScrollPane scrollPane = new JScrollPane(dialogueArea);
	static public JOptionPane infoPane = new JOptionPane();
	static public JOptionPane restartPane = new JOptionPane();
	static public JOptionPane newPhrase = new JOptionPane();
	private JOptionPane aboutPane = new JOptionPane();
	private JPanel playerPanel = new JPanel();
	private JPanel wordPanel = new JPanel();
	private JPanel dialoguePanel = new JPanel();
	private JPanel textPanel = new JPanel();
	private JPanel prizePanel = new JPanel();
	private JPanel animationPanel = new JPanel();
	private JCheckBox saveCheckBox = new JCheckBox("Save messages.");
	
	// Creating Menu Bar
	private JMenuBar menuBar = new JMenuBar();
	private JMenu gameMenu = new JMenu("Game");
	private JMenuItem addPlayerItem = new JMenuItem("Add Player");
	private JMenuItem addHostItem = new JMenuItem("Add Host");
	private JMenu aboutMenu = new JMenu("About");
	private JMenuItem layoutItem = new JMenuItem("Layout");
	private JMenu attributionItem = new JMenu("Attribution");
	private JMenuItem visualSubItem = new JMenuItem("Visuals");
	private JMenuItem audioSubItem = new JMenuItem("Audio");
	
	// Adding Prize Images
	ImageIcon imageIcon;
	
	// Object instantiation
	Phrases phraseClass = new Phrases();
	Players[] currentPlayers = new Players[3];
	Hosts host;
	Physical physical = new Physical();
	Turn turn = new Turn(physical);
	Clip clip;
	Circle circlePanel = new Circle();
	
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
		wordFrame.setSize(900,500);
		wordFrame.setLayout(new GridLayout(2,3));
		wordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wordFrame.setLocationRelativeTo(null);
		wordFrame.setResizable(false);
		
		wordFrame.add(playerPanel);
		wordFrame.add(dialoguePanel);
		wordFrame.add(prizePanel);
		wordFrame.add(textPanel);
		wordFrame.add(wordPanel);
		wordFrame.add(circlePanel);
		
		playerPanel.setBackground(Color.PINK);
		wordPanel.setBackground(Color.PINK);
		dialoguePanel.setBackground(Color.PINK);
		textPanel.setBackground(Color.PINK);
		prizePanel.setBackground(Color.PINK);
		animationPanel.setBackground(Color.PINK);
		
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
		dialoguePanel.setLayout(new BoxLayout(dialoguePanel, BoxLayout.Y_AXIS));
		wordPanel.setLayout(new BorderLayout());
		textPanel.setLayout(new FlowLayout());
		
		prizePanel.add(prizesWonLabel);
		
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
		attributionItem.add(visualSubItem);
		attributionItem.add(audioSubItem);
		gameMenu.add(addPlayerItem);
		gameMenu.add(addHostItem);
		aboutMenu.add(layoutItem);
		aboutMenu.addSeparator();
		aboutMenu.add(attributionItem);
		menuBar.add(gameMenu);
		menuBar.add(aboutMenu);
		wordFrame.setJMenuBar(menuBar);
		gameMenu.setMnemonic('G');
		aboutMenu.setMnemonic('A');
		
		// Sets the font for the playing phrase
		playingPhrase.setFont(new Font("Display", Font.BOLD, 18));
		
		// Action Listener events
		visualSubItem.addActionListener(e ->{
			String visualAttributionMessage = 
					"Artbook - \r\n"
					+ "Image by <a href=\"https://pixabay.com/users/jkoets-13686196/?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=8716431\">\n"
					+ "jkoets</a> from <a href=\"https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=8716431\">Pixabay</a>\r\n"
					+ "\r\n"
					+ "Tickets - \r\n"
					+ "Image by <a href=\"https://pixabay.com/users/barelydevi-14723734/?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=5957841\n"
					+ "\">BarelyDevi</a> from <a href=\"https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=5957841\">Pixabay</a>\r\n"
					+ "\r\n"
					+ "Dictionary -\r\n"
					+ "Image by <a href=\"https://pixabay.com/users/openclipart-vectors-30363/?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=155951\n"
					+ "\">OpenClipart-Vectors</a> from <a href=\"https://pixabay.com//?utm_source=link-attribution&utm_medium=referral&utm_campaign=image&utm_content=155951\">Pixabay</a>\r\n"
					+ "\r\n"
					+ "Blanket - \r\n"
					+ "Photo by <a href=\"https://unsplash.com/@jordanbigs?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText\">Jordan Bigelow</a> on \n"
					+ "<a href=\"https://unsplash.com/photos/white-and-blue-knit-textile-53BjYSxca5g?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText\">Unsplash</a>\r\n"
					+ "\r\n"
					+ "Hoodie - \r\n"
					+ "Photo by <a href=\"https://unsplash.com/@mediamodifier?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText\">Mediamodifier</a> on \n"
					+ "<a href=\"https://unsplash.com/photos/white-zip-up-jacket-hanging-on-brown-wooden-clothes-hanger-kJXGTOY1wLQ?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText\">Unsplash</a>\r\n"
					+ "";
			
			aboutPane.showMessageDialog(wordFrame, visualAttributionMessage);
		});
		
		audioSubItem.addActionListener(e -> {
			String audioAttributionMessage = "Background Music -\r\n"
					+ "Music by Amit Katzengold https://artlist.io/royalty-free-music/song/pass-the-mayo/134756 from \n"
					+ "Artlist.io https://artlist.io/?utm_source=google&utm_medium=cpc&utm_campaign=23890389036&utm_\n"
					+ "content=200762436687&ad=811234562597&matchtype=a&device=c&gad_source=1&gad_campaignid=23890389036&gclid=\n"
					+ "CjwKCAjwyabTBhBFEiwAM3mNUNpCMKVFC215AP2J1UDbS-ZqsMQBOKVd1FZUYC37ikmrtSvfYEruJBoCfecQAvD_BwE\n";
			
			aboutPane.showMessageDialog(wordFrame, audioAttributionMessage);
		});
		
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
			
			aboutPane.showMessageDialog(wordFrame, layoutMessage);
		});
		
		attributionItem.addActionListener(e -> {
			String attributionMessage = " jello!";
			
			aboutPane.showMessageDialog(wordFrame, attributionMessage);
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
		
		wordFrame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				if (clip != null)
				{
					clip.stop();
					clip.close();
				}
			}
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
		
		if (!(saveCheckBox.isSelected()))
		{
			dialogueArea.setText("");
		}
		
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
				
				didWin = false;
				dialogueArea.setText("");
				letterTextField.setText("");
				submitButton.setEnabled(true);
				startPlayingButton.setEnabled(false);
				physical.setEmptyReward();
				prizePanel.removeAll();
				prizePanel.add(prizesWonLabel); // keep the title
				prizePanel.revalidate();
				prizePanel.repaint();
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
	
	// Method starts and loops an audio
	public void startAudio()
	{
		try 
		{
			if (clip == null)
			{
				File audioFile = new File("src\\Amit Katzengold - Pass the Mayo.wav");
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
				clip = AudioSystem.getClip();
				clip.open(audioStream);
				clip.setFramePosition(0);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			else
			{
				clip.setFramePosition(0);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
		}
		catch (UnsupportedAudioFileException e) {
            System.out.println("Error: This audio format is not supported.");
        } catch (LineUnavailableException e) {
            System.out.println("Error: Audio line is unavailable.");
        } catch (IOException e) {
            System.out.println("Error: File could not be read.");
        }
	}
	
	// Method displays the physical prizes on the GUI
	public void displayPhysicalPrize()
	{
		int scaleSize = 50;
		
		if ("Artbook".equals(physical.getPhysicalReward()))
		{
			Image img = new ImageIcon("src/artbook.png").getImage();
			Image scaled = img.getScaledInstance(scaleSize, scaleSize, Image.SCALE_SMOOTH);
			
			JLabel label = new JLabel(new ImageIcon(scaled));
			prizePanel.add(label);
		}
		else if ("Hawaii Tickets".equals(physical.getPhysicalReward()))
		{
			Image img = new ImageIcon("src/ticket.png").getImage();
			Image scaled = img.getScaledInstance(scaleSize, scaleSize, Image.SCALE_SMOOTH);
			
			JLabel label = new JLabel(new ImageIcon(scaled));
			prizePanel.add(label);
		}
		else if ("Dictionary".equals(physical.getPhysicalReward()))
		{
			Image img = new ImageIcon("src/dictionary.png").getImage();
			Image scaled = img.getScaledInstance(scaleSize, scaleSize, Image.SCALE_SMOOTH);
			
			JLabel label = new JLabel(new ImageIcon(scaled));
			prizePanel.add(label);
		}
		else if ("Hoodie".equals(physical.getPhysicalReward()))
		{
			Image img = new ImageIcon("src/hoodie.jpg").getImage();
			Image scaled = img.getScaledInstance(scaleSize, scaleSize, Image.SCALE_SMOOTH);
			
			JLabel label = new JLabel(new ImageIcon(scaled));
			prizePanel.add(label);
		}
		else if ("Blanket".equals(physical.getPhysicalReward()))
		{
			Image img = new ImageIcon("src/blanket.jpg").getImage();
			Image scaled = img.getScaledInstance(scaleSize, scaleSize, Image.SCALE_SMOOTH);
			
			JLabel label = new JLabel(new ImageIcon(scaled));
			prizePanel.add(label);
		}
		
		 prizePanel.revalidate();
		 prizePanel.repaint();
	}
	
	// Method sets the player's name and displays it in the frame
	public void getPlayersName(int playerNum)
	{
		while (true)
		{
			JTextField firstNameField = new JTextField();
			JTextField lastNameField = new JTextField();
			
			JComponent[] nameInputs = new JComponent[]
			{
				new JLabel("What is your first name?  "), firstNameField,
				new JLabel("What is your last name? "), lastNameField
					
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
		
		if ((physical.getPhysicalReward() != null || physical.getPhysicalReward() != "none") && Turn.prizeGuessedCorrect == true)
		{
			displayPhysicalPrize();
		}
	}
	
	// Method displays the frame and starts the background music
	public void showGUI()
	{
		wordFrame.setVisible(true);
		startAudio();
	}
	
}
