package berenyi_kft_GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class EndGamePanel extends JPanel {
	
	private static final String wonString =
			"Settlers, you have won the game! Congratulations!";
	private static final String lostString =
			"Settlers, you have lost the game! Never mind, try again!";
	
	private Cards cards;
	private JTextArea resultField;
	private JButton backToMenuButton;
	
	private class ButtonListener implements ActionListener {		
		public void actionPerformed(ActionEvent ae) {
			JButton pressedButton = (JButton)ae.getSource();
			if (pressedButton == backToMenuButton) {
				cards.show(Cards.menuPanelID);
			}
		}
	}
	
	public void showResult(boolean won) {
		resultField.setText(won ? wonString : lostString);
	}
	
	public void initComponents() {
		this.setLayout(new BorderLayout());
		Font font = new Font("Comic Sans MS", Font.BOLD, 20);
		
		ButtonListener bl = new ButtonListener();

		resultField = new JTextArea(7, 15);
		resultField.setEditable(false);
		resultField.setAlignmentX(CENTER_ALIGNMENT);
		resultField.setFont(font);
		resultField.setWrapStyleWord(true);
		
		backToMenuButton = new JButton("Back to Menu");
		backToMenuButton.setFont(font);
		backToMenuButton.addActionListener(bl);
		
		JPanel resultPanel = new JPanel();
		resultPanel.add(resultField);
		resultPanel.add(backToMenuButton);
		this.add(resultPanel, BorderLayout.CENTER);
	}
	
	public EndGamePanel(Cards cards) {
		this.cards = cards;
		this.initComponents();
		this.setVisible(true);
	}
	
}
