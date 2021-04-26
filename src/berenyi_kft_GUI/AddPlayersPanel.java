package berenyi_kft_GUI;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class AddPlayersPanel extends JPanel {
	
	private ArrayList<String> playerNames = new ArrayList<String>();
	
	private Cards cards;
	private JLabel nameLabel;
	private JTextArea playerNamesField;
	private JTextField nameField;
	private JButton addPlayerButton;
	private JButton startGameButton;
	
	public ArrayList<String> getPlayerNames() {
		return playerNames;
	}
	
	public void setPlayerNames(ArrayList<String> playerNames) {
		this.playerNames = playerNames;
	}
	
	private class ButtonListener implements ActionListener {		
		public void actionPerformed(ActionEvent ae) {
			JButton pressedButton = (JButton)ae.getSource();
			if (pressedButton == addPlayerButton) {
				if (playerNames.size() >= 6) {
					System.out.println("A jatekosok szama legfeljebb 6 lehet!");
				}
				else if (! nameField.getText().matches("[a-zA-Z0-9]+")) {
					System.out.println("A neved csak ekezet nelkuli "
							+ "alfanumerikus karaktereket tartalmazhat.");
				}
				else {
					playerNames.add(nameField.getText());
					StringBuilder sb = new StringBuilder();
					for (String name : playerNames) {
					    sb.append(name);
					    sb.append(System.getProperty("line.separator"));
					}
					playerNamesField.setText(sb.toString());
				}
			}
			else if (pressedButton == startGameButton) {
				if (playerNames.isEmpty() | playerNames.size() > 6) {
					System.out.println("A jatekosok szama 1 es 6 kozott "
													+ "kell, hogy legyen!");
				}
				else {
					for (String str : playerNames) {
						System.out.println(str);
					}
					cards.show(Cards.gamePanelID);
				}
			}
		}
	}
	
	public void initComponents() {
		this.setLayout(new BorderLayout());
		Font font = new Font("Comic Sans MS", Font.BOLD, 20);
		
		nameLabel = new JLabel("Játékos: ");
		nameLabel.setFont(font);
		
		nameField = new JTextField("Player1");
		nameField.setFont(font);
		
		ButtonListener bl = new ButtonListener();
		
		addPlayerButton = new JButton("Add");
		addPlayerButton.setFont(font);
		addPlayerButton.addActionListener(bl);
		
		startGameButton = new JButton("Start Game!");
		startGameButton.setFont(font);
		startGameButton.addActionListener(bl);
		
		JPanel addPanel = new JPanel();
		addPanel.add(nameLabel);
		addPanel.add(nameField);
		addPanel.add(addPlayerButton);
		this.add(addPanel, BorderLayout.NORTH);
		
		JPanel playersPanel = new JPanel();
		playerNamesField = new JTextArea(7, 15);
		playerNamesField.setEditable(false);
		playerNamesField.setAlignmentX(CENTER_ALIGNMENT);
		playerNamesField.setFont(font);
		playerNamesField.setWrapStyleWord(true);
		playersPanel.add(playerNamesField);
		this.add(playersPanel, BorderLayout.CENTER);
		
		JPanel startPanel = new JPanel();
		startPanel.add(startGameButton);
		this.add(startPanel, BorderLayout.SOUTH);
	}
	
	public AddPlayersPanel(Cards cards) {
		this.cards = cards;
		this.initComponents();
		this.setVisible(true);
	}
	
}
