package berenyi_kft_GUI;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import berenyi_kft.Asteroid;

public class GamePanel extends JPanel {
	
	private List<IDrawable> drawables = new ArrayList<IDrawable>();
	private List<JButton> drawableButtons = new ArrayList<JButton>();
	
	private ButtonListener bl;
	
	private Cards cards;
	private JPanel mapPanel;
	private JLabel gameNameLabel;
	private JButton moveButton;
	private JButton mineButton;
	private JButton endGameButton;
	
	private class ButtonListener implements ActionListener {
		
		/**
		 * Kezeli a gamePanel akciógombjainak a gombnyomás-eseményeit.
		 * Az akcióparancs feldolgozása után frissít minden nézet-objektumot.
		 * 
		 * @param ae: A beérkező akcióparancs paraméterei
		 */
		public void actionPerformed(ActionEvent ae) {
			// Megszünteti az eddig kijelölt aszteroidák kiemelését.
			for (AsteroidGraphics ag : AsteroidGraphics.getAllAsteroidGraphics()) {
				ag.getAsteroid().setEmphasized(false);
			}
			
			JButton pressedButton = (JButton)ae.getSource();
			if (pressedButton == moveButton) {
				// TODO
			}
			else if (pressedButton == mineButton) {
				// TODO
			}
			else if (pressedButton == endGameButton) {
				// TODO Inkább Pause gomb legyen helyette.
				for (JButton drButton : drawableButtons)
					mapPanel.remove(drButton);
				drawableButtons.clear();
				drawables.clear();
				cards.show(Cards.endGamePanelID);
			}
			else if (ae.getActionCommand().equals(AsteroidGraphics.getCommand())) {
				AsteroidGraphics ag = (AsteroidGraphics)pressedButton;
				// Kiemeli az éppen kattintott aszteroida szomszédait.
				for (Asteroid neighbor : ag.getAsteroid().getNeighbors())
					neighbor.setEmphasized(true);
			}
			
			// Frissíti az összes nézet-objektumot, mert megváltozhatott a modell.
			drawAll();
		}
	}
	
	public void initComponents() {
		this.setLayout(new BorderLayout());
		Font font = new Font("Comic Sans MS", Font.BOLD, 20);
		
		bl = new ButtonListener();
		
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
		mapPanel.setMinimumSize(new Dimension(800, 600));
		mapPanel.setBackground(Color.YELLOW);
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
		drawableButton.addActionListener(bl);
	}
	
	public void addToMapPanel(JLabel drawableLabel) {
		mapPanel.add(drawableLabel);
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
