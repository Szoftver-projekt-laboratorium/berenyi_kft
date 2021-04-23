package berenyi_kft_GUI;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

import javax.swing.*;

public class MenuPanel extends JPanel {
	
	private ArrayList<String> playerNames = new ArrayList<String>();

	private AddPlayersPanel addPlayersPanel;
	private JLabel nameLabel;
	private JButton addPlayersButton;
	private JButton startGameButton;
	private JButton loadGameButton;
	private JButton exitButton;
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			JButton pressedButton = (JButton)ae.getSource();
			if (pressedButton == addPlayersButton) {
				addPlayersPanel.setPlayerNames(playerNames);
				// setVisible(false);
				addPlayersPanel.setVisible(true);
			}
			else if (pressedButton == startGameButton) {
				
			}
			else if (pressedButton == loadGameButton) {
				
			}
			else if (pressedButton == exitButton) {
				// setVisible(false);
				// MainFrame mainFrame = (MainFrame)getParent();
				System.exit(0);
			}
		}
	}
	
	public void initComponents() {
		addPlayersPanel = new AddPlayersPanel();
		addPlayersPanel.setMenuPanel(this);
		this.add(addPlayersPanel, BorderLayout.SOUTH);
		
		Font titleFont = new Font("Comic Sans MS", Font.BOLD, 40);
		Font font = new Font("Comic Sans MS", Font.BOLD, 20);
		
		nameLabel = new JLabel("Asteroid Miner");
		nameLabel.setFont(titleFont);
		
		ButtonListener bl = new ButtonListener();
		
		addPlayersButton = new JButton("Add Players");
		addPlayersButton.setFont(font);
		addPlayersButton.addActionListener(bl);
		
		startGameButton = new JButton("New Game");
		startGameButton.setFont(font);
		startGameButton.addActionListener(bl);
		
		loadGameButton = new JButton("Load Game");
		loadGameButton.setFont(font);
		loadGameButton.addActionListener(bl);
		
		exitButton = new JButton("Exit");
		exitButton.setFont(font);
		exitButton.addActionListener(bl);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(nameLabel);
		buttonsPanel.add(addPlayersButton);
		buttonsPanel.add(startGameButton);
		buttonsPanel.add(loadGameButton);
		buttonsPanel.add(exitButton);
		this.add(buttonsPanel, BorderLayout.NORTH);
	}
	
	public MenuPanel() {
		this.initComponents();
		// this.setMinimumSize(new Dimension(300, 200));
		this.setVisible(true);
	}
}
