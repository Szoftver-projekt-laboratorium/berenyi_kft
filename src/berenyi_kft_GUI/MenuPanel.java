package berenyi_kft_GUI;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

import javax.swing.*;

public class MenuPanel extends JPanel {
	
	private Cards cards;
	private JLabel nameLabel;
	private JButton newGameButton;
	private JButton loadGameButton;
	private JButton exitButton;
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			JButton pressedButton = (JButton)ae.getSource();
			if (pressedButton == newGameButton) {
				cards.show(Cards.addPlayersPanelID);
			}
			else if (pressedButton == loadGameButton) {
				// TODO
			}
			else if (pressedButton == exitButton) {
				System.exit(0);
			}
		}
	}
	
	public void initComponents() {
		this.setLayout(new BorderLayout());
		Font titleFont = new Font("Comic Sans MS", Font.BOLD, 40);
		Font font = new Font("Comic Sans MS", Font.BOLD, 20);
		
		nameLabel = new JLabel("Asteroid Miner");
		nameLabel.setFont(titleFont);
		
		ButtonListener bl = new ButtonListener();
		
		newGameButton = new JButton("New Game");
		newGameButton.setFont(font);
		newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		newGameButton.addActionListener(bl);
		
		loadGameButton = new JButton("Load Game");
		loadGameButton.setFont(font);
		loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loadGameButton.addActionListener(bl);
		
		exitButton = new JButton("Exit");
		exitButton.setFont(font);
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitButton.addActionListener(bl);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		buttonsPanel.add(nameLabel);
		buttonsPanel.add(newGameButton);
		buttonsPanel.add(loadGameButton);
		buttonsPanel.add(exitButton);
		this.add(buttonsPanel, BorderLayout.CENTER);
	}
	
	public MenuPanel(Cards cards) {
		this.cards = cards;
		this.initComponents();
		this.setVisible(true);
	}
}
