package berenyi_kft_GUI;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import berenyi_kft.Asteroid;

public class GamePanel extends JPanel {
	
	private List<IDrawable> drawables = new ArrayList<IDrawable>();
	private List<JButton> drawableButtons = new ArrayList<JButton>();
	
	private ButtonListener bl;
	
	//altalanos gombmeret beallitasa:
	private Dimension buttonsize = new Dimension(250, 40);
	
	private Dimension textarea_size = new Dimension(250, 300);
	
	private Cards cards;
	private JPanel mapPanel;
	private JLabel gameNameLabel;
	private JButton moveButton;
	private JButton drillButton;
	private JButton mineButton;
	private JButton restoreButton;
	private JButton createrobotButton;
	private JButton createteleportButton;
	private JButton placeteleportButton;
	private JButton passButton;
	private JButton endGameButton;
	
	
	private JTextArea messages = new JTextArea("Welcome in the game!");
	
	private class ButtonListener implements ActionListener {		
		public void actionPerformed(ActionEvent ae) {
			for (AsteroidGraphics ag : AsteroidGraphics.getAllAsteroidGraphics())
				ag.setForeground(new Color(0, 0, 0, 0));
			
			JButton pressedButton = (JButton)ae.getSource();
			if (pressedButton == moveButton) {
				drawAll();
				writeToMessageBoard("moveButton had been pushed");
			}
			else if (pressedButton == mineButton) {
				// TODO
				writeToMessageBoard("mineButton had been pushed");
			}
			else if (pressedButton == endGameButton) {
				for (JButton drButton : drawableButtons)
					mapPanel.remove(drButton);
				drawableButtons.clear();
				drawables.clear();
				
				cards.show(Cards.endGamePanelID);
			}
			else if (ae.getActionCommand() == AsteroidGraphics.getCommand()) {
				AsteroidGraphics ag = (AsteroidGraphics)pressedButton;
				ag.setForeground(Color.RED);
				drawAll();
			}
		}
	}
	
	//uzenofal szovegnek bovitese
	public void writeToMessageBoard(String mess){
	        String tmp = messages.getText();
	        String[] tmps = tmp.split("\n");
	        String coms = "";
	        if (tmps.length > 15) {
	            for (int i=tmps.length-15;i<tmps.length; i++){
	                coms += tmps[i] + "\r\n";
	            }
	            coms += "\r\n" + mess + "\r\n";
	        } else {
	            tmp += "\r\n" + mess + "\r\n";
	            coms = tmp;
	        }

	        messages.setText(coms);
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
				
		moveButton = new JButton("Move");
		moveButton.setFont(font);
		moveButton.setPreferredSize(buttonsize);
		moveButton.setMinimumSize(buttonsize);
		moveButton.setMaximumSize(buttonsize);
		moveButton.addActionListener(bl);
		
		drillButton = new JButton("Drill");
		drillButton.setFont(font);
		drillButton.setMinimumSize(buttonsize);
		drillButton.setMaximumSize(buttonsize);
		drillButton.addActionListener(bl);
		
		mineButton = new JButton("Mine");
		mineButton.setFont(font);
		mineButton.setMinimumSize(buttonsize);
		mineButton.setMaximumSize(buttonsize);
		mineButton.addActionListener(bl);
		
		restoreButton = new JButton("Restore");
		restoreButton.setFont(font);
		restoreButton.setMinimumSize(buttonsize);
		restoreButton.setMaximumSize(buttonsize);
		restoreButton.addActionListener(bl);
		
		createrobotButton = new JButton("Create Robot");
		createrobotButton.setFont(font);
		createrobotButton.setMinimumSize(buttonsize);
		createrobotButton.setMaximumSize(buttonsize);
		createrobotButton.addActionListener(bl);
		
		createteleportButton = new JButton("Create Teleport");
		createteleportButton.setFont(font);
		createteleportButton.setMinimumSize(buttonsize);
		createteleportButton.setMaximumSize(buttonsize);
		createteleportButton.addActionListener(bl);
		
		placeteleportButton = new JButton("Place Teleport");
		placeteleportButton.setFont(font);
		placeteleportButton.setMinimumSize(buttonsize);
		placeteleportButton.setMaximumSize(buttonsize);
		placeteleportButton.addActionListener(bl);
		
		passButton = new JButton("Pass");
		passButton.setFont(font);
		passButton.setMinimumSize(buttonsize);
		passButton.setMaximumSize(buttonsize);
		passButton.addActionListener(bl);
		
		endGameButton = new JButton("End game");
		endGameButton.setFont(font);
		endGameButton.setMinimumSize(buttonsize);
		endGameButton.setMaximumSize(buttonsize);
		endGameButton.addActionListener(bl);
		
		//
		JPanel controlPanel = new JPanel();
		
		controlPanel.add(moveButton);
		controlPanel.add(drillButton);
		controlPanel.add(moveButton);
		controlPanel.add(restoreButton);
		controlPanel.add(createrobotButton);
		controlPanel.add(createteleportButton);
		controlPanel.add(placeteleportButton);
		controlPanel.add(passButton);
		controlPanel.add(endGameButton);
		
		//tolteleknek vettem fel, hogy ne legyen olyan egyben az egesz control panel (buttonok koze ilyet nem lehetett tenni sajnos)
		controlPanel.add(new JLabel(" "));
		
		messages.setBackground(Color.decode("#BFFBD3"));
		messages.setMinimumSize(textarea_size);
		messages.setMaximumSize(textarea_size);
		controlPanel.add(messages);
		
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
		controlPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		controlPanel.setSize(200, 400);
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
