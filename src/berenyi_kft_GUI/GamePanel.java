package berenyi_kft_GUI;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GamePanel extends JPanel {
	
	private List<IDrawable> drawables = new ArrayList<IDrawable>();
	private List<JButton> drawableButtons = new ArrayList<JButton>();
	
	private Cards cards;
	private JPanel mapPanel;
	private JLabel gameNameLabel;
	private JButton moveButton;
	private JButton mineButton;
	private JButton endGameButton;
	
	private class ButtonListener implements ActionListener {		
		public void actionPerformed(ActionEvent ae) {
			JButton pressedButton = (JButton)ae.getSource();
			if (pressedButton == moveButton) {
				drawAll();
			}
			else if (pressedButton == mineButton) {
				// TODO
			}
			else if (pressedButton == endGameButton) {
				for (JButton drButton : drawableButtons)
					mapPanel.remove(drButton);
				drawableButtons.clear();
				drawables.clear();
				
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
		mapPanel = new JPanel();
		inventoryPanel.setMinimumSize(new Dimension(400, 200));
		mapPanel.setBackground(Color.BLUE);
		this.add(mapPanel, BorderLayout.CENTER);
	}
	
	public GamePanel(Cards cards) {
		this.cards = cards;
		this.initComponents();
		this.setVisible(true);
	}
	
	public void addToMapPanel(JButton drawableButton) {
		mapPanel.add(drawableButton);
		drawableButtons.add(drawableButton);
	}
	
	public void addDrawable(IDrawable d) {
		drawables.add(d);
	}
	
	public void removeDrawable(IDrawable d) {
		drawables.remove(d);
	}
	
	public void drawAll() {
		for (IDrawable d : drawables)
			d.draw();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.drawAll();
	}
	
}
