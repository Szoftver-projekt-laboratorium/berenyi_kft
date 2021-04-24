package berenyi_kft_GUI;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class AddPlayersPanel extends JPanel {
	
	private ArrayList<String> playerNames = new ArrayList<String>();
	
	private Cards cards;
	private MenuPanel menuPanel;
	private JLabel nameLabel;
	private JTextField nameField;
	private JButton addButton;
	private JButton okButton;
	
	public ArrayList<String> getPlayerNames() {
		return playerNames;
	}
	
	public void setPlayerNames(ArrayList<String> playerNames) {
		this.playerNames = playerNames;
	}
	
	public void setMenuPanel(MenuPanel menuPanel) {
		this.menuPanel = menuPanel;
	}
	
	private class ButtonListener implements ActionListener {		
		public void actionPerformed(ActionEvent ae) {
			JButton pressedButton = (JButton)ae.getSource();
			if (pressedButton == addButton) {
				if (playerNames.size() >= 6) {
					System.out.println("A jatekosok szama legfeljebb 6 lehet!");
				}
				else if (! nameField.getText().matches("[a-zA-Z0-9]+")) {
					System.out.println("A neved csak ekezet nelkuli alfanumerikus "
							+ "karaktereket tartalmazhat.");
				}
				else {
					playerNames.add(nameField.getText());
				}
			}
			else if (pressedButton == okButton) {
				if (!playerNames.isEmpty() & playerNames.size() <= 6) {
					for (String str : playerNames) {
						System.out.println(str);
					}
					cards.show(Cards.menuPanelID);
				}
				else {
					System.out.println("A jatekosok szama 1 es 6 kozott kell, hogy legyen!");
				}
			}
		}
	}
	
	public void initComponents() {
		Font font = new Font("Comic Sans MS", Font.BOLD, 20);
		
		nameLabel = new JLabel("Játékos: ");
		nameLabel.setFont(font);
		
		nameField = new JTextField("Player1");
		nameField.setFont(font);
		
		ButtonListener bl = new ButtonListener();
		
		addButton = new JButton("Add");
		addButton.setFont(font);
		addButton.addActionListener(bl);
		
		okButton = new JButton("OK");
		okButton.setFont(font);
		okButton.addActionListener(bl);
		
		JPanel addPanel = new JPanel();
		addPanel.add(nameLabel);
		addPanel.add(nameField);
		addPanel.add(addButton);
		this.add(addPanel, BorderLayout.NORTH);
		
		JPanel okPanel = new JPanel();
		okPanel.add(okButton);
		this.add(okPanel, BorderLayout.SOUTH);
	}
	
	public AddPlayersPanel(Cards cards) {
		this.cards = cards;
		
		this.initComponents();
		// this.setMinimumSize(new Dimension(800, 600));
		this.setVisible(true);
	}
	
}
