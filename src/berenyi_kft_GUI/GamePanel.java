package berenyi_kft_GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GamePanel extends JPanel {
	
	private Cards cards;
	private JLabel gameNameLabel;
	private JButton moveButton;
	private JButton mineButton;
	private JButton endGameButton;
	
	private class ButtonListener implements ActionListener {		
		public void actionPerformed(ActionEvent ae) {
			JButton pressedButton = (JButton)ae.getSource();
			if (pressedButton == moveButton) {
				// TODO
			}
			else if (pressedButton == mineButton) {
				// TODO
			}
			else if (pressedButton == endGameButton) {
				cards.show(Cards.endGamePanelID);
			}
		}
	}
	
	public void initComponents() {
		this.setLayout(new BorderLayout());
		Font font = new Font("Comic Sans MS", Font.BOLD, 20);
		
		ButtonListener bl = new ButtonListener();
		
		// Fejlecpanel (felso)
		JPanel titlePanel = new JPanel();
		gameNameLabel = new JLabel("Asteroid Mining");
		gameNameLabel.setFont(font);
		titlePanel.add(gameNameLabel);
		this.add(titlePanel, BorderLayout.NORTH);
		
		// Vezerlopanel (keleti)
		endGameButton = new JButton("End game");
		endGameButton.setFont(font);
		endGameButton.addActionListener(bl);
		moveButton = new JButton("Move");
		moveButton.setFont(font);
		moveButton.addActionListener(bl);
		mineButton = new JButton("Mine");
		mineButton.setFont(font);
		mineButton.addActionListener(bl);
		
		JPanel controlPanel = new JPanel();
		controlPanel.add(endGameButton);
		controlPanel.add(moveButton);
		controlPanel.add(mineButton);
		this.add(controlPanel, BorderLayout.EAST);
		
		// Raktarpanel (also)
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setMinimumSize(new Dimension(200, 100));
		this.setBackground(Color.BLACK);
		this.add(inventoryPanel, BorderLayout.SOUTH);
		
		// Jatekpanel (kozepso)
		JPanel gamePanel = new JPanel();
		inventoryPanel.setMinimumSize(new Dimension(400, 200));
		gamePanel.add(endGameButton);
		this.setBackground(Color.BLUE);
		this.add(gamePanel, BorderLayout.CENTER);
	}
	
	public GamePanel(Cards cards) {
		this.cards = cards;
		this.initComponents();
		this.setVisible(true);
	}
	
}
