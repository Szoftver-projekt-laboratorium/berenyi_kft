package berenyi_kft_GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import berenyi_kft.Asteroid;
import berenyi_kft.Controller;
import berenyi_kft.ISteppable;
import berenyi_kft.PlayerCommand;
import berenyi_kft.Proto;
import berenyi_kft.State;
import berenyi_kft.TeleportingGate;

public class GamePanel extends JPanel {
	
	private Controller controller = null;
	
	private List<IDrawable> drawables = new ArrayList<IDrawable>();
	private List<JButton> drawableButtons = new ArrayList<JButton>();
	private List<JLabel> drawableLabels = new ArrayList<JLabel>();
	private List<Point> asteroidPoints=new ArrayList<Point>();
	
	private AsteroidGraphics latestSelectedAsteroid = null;
	// private AsteroidGraphics latestSelectedResourceGraphics? = null;
	
	private ButtonListener bl;

	// altalanos gombmeret beallitasa:
	private Dimension buttonsize = new Dimension(250, 40);
	
	private Dimension resourcebuttonsize = new Dimension(40, 40);

	private Dimension textarea_size = new Dimension(250, 300);

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(300, 100));
	}

	private Cards cards;
	private JPanel mapPanel;
	private JPanel inventoryPanel;
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
	
	private CoalGraphics CoalButton;
	private IceGraphics IceButton;
	private UraniumGraphics UraniumButton;
	private IronGraphics IronButton;
	
	private JLabel coalLabel;
	private JLabel iceLabel;
	private JLabel uraniumLabel;
	private JLabel ironLabel;
	
	private BufferedImage img;
	private BufferedImage img_inventory;

	private JTextArea messages = new JTextArea("Welcome in the game!\n");
	private JScrollPane scrollPane;
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	private class ButtonListener implements ActionListener {

		/**
		 * Kezeli a gamePanel akciógombjainak a gombnyomás-eseményeit. Az akcióparancs
		 * feldolgozása után frissít minden nézet-objektumot.
		 * 
		 * @param ae: A beérkező akcióparancs paraméterei
		 */
		public void actionPerformed(ActionEvent ae) {
			// Megszünteti az eddig kijelölt aszteroidák kiemelését.
			for (AsteroidGraphics ag : AsteroidGraphics.getAllAsteroidGraphics()) {
				ag.getAsteroid().setEmphasized(false);
			}
			for (TeleportingGateGraphics tgg
					: TeleportingGateGraphics.getAllTeleportingGateGraphics()) {
				tgg.getTeleportingGate().setEmphasized(false);
			}

			JButton pressedButton = (JButton) ae.getSource();
			if (pressedButton == moveButton) {
				writeToMessageBoard("Moving..");
				if (latestSelectedAsteroid != null) {
					writeToMessageBoard("Choose an astroid to move!");
					
					// Mozgás történik! TODO: majd külön függvénybe tegyük.
					Asteroid place = controller.getActPlayer().getSettler().getPlace();
					int idx = place.getNeighbors().indexOf(latestSelectedAsteroid.getAsteroid());
					if (idx != -1) {
						Object[] params = {"move", Integer.toString(idx)};
						controller.getActPlayer().actOnSettler(PlayerCommand.MOVE, params);
						controller.nextPlayer();
						
						// új kattintásra fogunk várni, a lépés után
						// nincs default kiválasztott
						latestSelectedAsteroid = null;
					}
					
				}
			}
			else if (pressedButton == drillButton) {
				//writeToMessageBoard("drillButton has been pushed");
				writeToMessageBoard("Drilling one layer...");
				
				Object[] params = {"drill"};
				controller.getActPlayer().actOnSettler(PlayerCommand.DRILL, params);
				controller.nextPlayer();
			}
			else if (pressedButton == mineButton) {
				//writeToMessageBoard("mineButton has been pushed");
				writeToMessageBoard("Mining...");
				
				Object[] params = {"mine"};
				controller.getActPlayer().actOnSettler(PlayerCommand.MINE, params);
				controller.nextPlayer();
			}
			else if (pressedButton == restoreButton) {
				//writeToMessageBoard("restoreButton has been pushed");
				writeToMessageBoard("Choose a resource to restore");
				
				// Resource...
				Object[] params = {"restore" /*, latestSelectedResource ID*/};
				controller.getActPlayer().actOnSettler(PlayerCommand.RESTORE, params);
				controller.nextPlayer();
			}
			else if (pressedButton == createrobotButton) {
				//writeToMessageBoard("createrobotButton has been pushed");
				writeToMessageBoard("Creating a new robot...");
				
				Object[] params = {"create_robot"};
				controller.getActPlayer().actOnSettler(PlayerCommand.CREATE_ROBOT, params);
				controller.nextPlayer();
			}
			else if (pressedButton == createteleportButton) {
				//writeToMessageBoard("createteleportButton has been pushed");
				writeToMessageBoard("Creating a new gate pair...");
				
				Object[] params = {"create_gate_pair"};
				controller.getActPlayer().actOnSettler(PlayerCommand.CREATE_GATE_PAIR, params);
				controller.nextPlayer();
			}
			else if (pressedButton == placeteleportButton) {
				//writeToMessageBoard("placeteleportButton has been pushed");
				writeToMessageBoard("Placing a teleporting gate available...");
				
				Object[] params = {"release_gate"};
				controller.getActPlayer().actOnSettler(PlayerCommand.RELEASE_GATE, params);
				controller.nextPlayer();
			}
			else if (pressedButton == passButton) {
				//writeToMessageBoard("passButton has been pushed");
				writeToMessageBoard("You passed.");
				controller.nextPlayer();
			}
			else if (pressedButton == endGameButton) {
				writeToMessageBoard("endGameButton has been pushed");
				writeToMessageBoard("Stop playing, end game...");
				
				// timer leállítása
				berenyi_kft.Timer timer = controller.getGame().getTimer();
				timer.cancel();
				
				// TODO Inkább Pause gomb legyen helyette.
				for (JButton drButton : drawableButtons)
					mapPanel.remove(drButton);
				drawableButtons.clear();
				for (JLabel drLabel : drawableLabels)
					mapPanel.remove(drLabel);
				drawableLabels.clear();
				drawables.clear();
				removeAsteroidPoints();
				drawAll();
				
				try {
					Proto.save(MenuPanel.getPersistentFilePath());
				}
				catch (FileNotFoundException e) {
					// (TODO .out vagy .err?)
					System.err.println("Persistent file not found.");
					e.printStackTrace();
				}
				
				//cards.show(Cards.endGamePanelID);
				cards.show(Cards.menuPanelID);
			}
			else if (ae.getActionCommand().equals(AsteroidGraphics.getCommand())) {
				AsteroidGraphics ag = (AsteroidGraphics) pressedButton;
				latestSelectedAsteroid = ag;
				for (Asteroid neighbor : ag.getAsteroid().getNeighbors())
					neighbor.setEmphasized(true);
				for (TeleportingGate neighborGate
						: ag.getAsteroid().getNeighboringGatePairs())
					neighborGate.setEmphasized(true);
			}

			// Frissíti az összes nézet-objektumot, mert megváltozhatott a modell.
			drawAll();
		}
	}
	
	// uzenofal szovegnek bovitese
	public void writeToMessageBoard(String mess) {
		String tmp = messages.getText();
		String[] tmps = tmp.split("\n");
		String coms = "";
		//egyelore 30 uzit tart meg, de bovitheto lehet
		if (tmps.length > 30) {
			for (int i = tmps.length - 30; i < tmps.length; i++) {
				coms += tmps[i] + "\r\n";
			}
			coms +=  mess + "\r\n";
		} else {
			tmp +=  mess + "\r\n";
			coms = tmp;
		}

		messages.setText(coms);
	}

	public void initComponents() {
		this.setLayout(new BorderLayout());
		// Font font = new Font("Comic Sans MS", Font.BOLD, 20);

		Font font = new Font("teko semibold", Font.BOLD, 20);
		Font smallerfont = new Font("teko semibold", Font.BOLD, 15);
		Border buttonBorder = new LineBorder(Color.YELLOW, 3);

		bl = new ButtonListener();
		Color color = new Color(9, 28, 87);

		// Fejlecpanel (felso)
		JPanel titlePanel = new JPanel();
		gameNameLabel = new JLabel("Asteroid Mining");
		gameNameLabel.setFont(font);
		gameNameLabel.setForeground(Color.YELLOW);
		titlePanel.setBackground(color);
		titlePanel.add(gameNameLabel);
		this.add(titlePanel, BorderLayout.NORTH);

		// Vezerlopanel (keleti)

		moveButton = new JButton("Move");
		moveButton.setFont(font);
		moveButton.setPreferredSize(buttonsize);
		moveButton.setMinimumSize(buttonsize);
		moveButton.setMaximumSize(buttonsize);
		moveButton.addActionListener(bl);
		moveButton.setForeground(Color.YELLOW);
		moveButton.setBackground(color);
		moveButton.setBorder(buttonBorder);

		drillButton = new JButton("Drill");
		drillButton.setFont(font);
		drillButton.setMinimumSize(buttonsize);
		drillButton.setMaximumSize(buttonsize);
		drillButton.addActionListener(bl);
		drillButton.setForeground(Color.YELLOW);
		drillButton.setBackground(color);
		drillButton.setBorder(buttonBorder);

		mineButton = new JButton("Mine");
		mineButton.setFont(font);
		mineButton.setMinimumSize(buttonsize);
		mineButton.setMaximumSize(buttonsize);
		mineButton.addActionListener(bl);
		mineButton.setForeground(Color.YELLOW);
		mineButton.setBackground(color);
		mineButton.setBorder(buttonBorder);

		restoreButton = new JButton("Restore");
		restoreButton.setFont(font);
		restoreButton.setMinimumSize(buttonsize);
		restoreButton.setMaximumSize(buttonsize);
		restoreButton.addActionListener(bl);
		restoreButton.setForeground(Color.YELLOW);
		restoreButton.setBackground(color);
		restoreButton.setBorder(buttonBorder);

		createrobotButton = new JButton("Create Robot");
		createrobotButton.setFont(font);
		createrobotButton.setMinimumSize(buttonsize);
		createrobotButton.setMaximumSize(buttonsize);
		createrobotButton.addActionListener(bl);
		createrobotButton.setForeground(Color.YELLOW);
		createrobotButton.setBackground(color);
		createrobotButton.setBorder(buttonBorder);

		createteleportButton = new JButton("Create Teleport");
		createteleportButton.setFont(font);
		createteleportButton.setMinimumSize(buttonsize);
		createteleportButton.setMaximumSize(buttonsize);
		createteleportButton.addActionListener(bl);
		createteleportButton.setForeground(Color.YELLOW);
		createteleportButton.setBackground(color);
		createteleportButton.setBorder(buttonBorder);

		placeteleportButton = new JButton("Place Teleport");
		placeteleportButton.setFont(font);
		placeteleportButton.setMinimumSize(buttonsize);
		placeteleportButton.setMaximumSize(buttonsize);
		placeteleportButton.addActionListener(bl);
		placeteleportButton.setForeground(Color.YELLOW);
		placeteleportButton.setBackground(color);
		placeteleportButton.setBorder(buttonBorder);

		passButton = new JButton("Pass");
		passButton.setFont(font);
		passButton.setMinimumSize(buttonsize);
		passButton.setMaximumSize(buttonsize);
		passButton.addActionListener(bl);
		passButton.setForeground(Color.YELLOW);
		passButton.setBackground(color);
		passButton.setBorder(buttonBorder);

		endGameButton = new JButton("End game");
		endGameButton.setFont(font);
		endGameButton.setMinimumSize(buttonsize);
		endGameButton.setMaximumSize(buttonsize);
		endGameButton.addActionListener(bl);
		endGameButton.setForeground(Color.YELLOW);
		endGameButton.setBackground(color);
		endGameButton.setBorder(buttonBorder);

		//
		JPanel controlPanel = new JPanel();

		controlPanel.add(moveButton);
		controlPanel.add(drillButton);
		controlPanel.add(mineButton);
		controlPanel.add(restoreButton);
		controlPanel.add(createrobotButton);
		controlPanel.add(createteleportButton);
		controlPanel.add(placeteleportButton);
		controlPanel.add(passButton);
		controlPanel.add(endGameButton);

		// tolteleknek vettem fel, hogy ne legyen olyan egyben az egesz control panel
		// (buttonok koze ilyet nem lehetett tenni sajnos)
		controlPanel.add(new JLabel(" "));
		
		//gorgo 
		scrollPane = new JScrollPane();
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		controlPanel.add(scrollPane);
		scrollPane.setViewportView(messages);
		scrollPane.setBackground(Color.yellow);
		
		scrollPane.getVerticalScrollBar().setBackground(color);
		messages.setBackground(Color.yellow);
		messages.setMinimumSize(textarea_size);
		messages.setMaximumSize(textarea_size);
		messages.setFont(smallerfont);
		
		//added scroll:
		
		messages.setEditable ( false );
		messages.setLineWrap(true);
		messages.setVisible(true);
		
		controlPanel.setBackground(color);
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
		controlPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		controlPanel.setSize(200, 400);

		this.add(controlPanel, BorderLayout.EAST);

		// Raktarpanel (also)
		
		//Gombok szépek, hozzáadva meg minden, de nincsenek bekövte buttonlistenernek,
		//a rajtuk megjelenő számok is invalidak egyelőre
		
		CoalButton	= new CoalGraphics(null, resourcebuttonsize);
		CoalButton.setBorder(buttonBorder);
		
		IceButton = new IceGraphics(null, resourcebuttonsize);
		IceButton.setBorder(buttonBorder);
		
		UraniumButton = new UraniumGraphics(null, resourcebuttonsize);
		UraniumButton.setBorder(buttonBorder);
		
		IronButton = new IronGraphics(null, resourcebuttonsize,"3");
		IronButton.setBorder(buttonBorder);
		
		coalLabel=new JLabel();
		coalLabel.setForeground(Color.YELLOW);
		
		ironLabel=new JLabel();
		ironLabel.setForeground(Color.YELLOW);
		
		uraniumLabel=new JLabel();
		uraniumLabel.setForeground(Color.YELLOW);
		
		iceLabel=new JLabel();
		iceLabel.setForeground(Color.YELLOW);
		
		CoalButton.setLayout(new BorderLayout());
		CoalButton.add(coalLabel, BorderLayout.SOUTH);
		coalLabel.setHorizontalAlignment(JLabel.CENTER);
		coalLabel.setFont(font);
		
		IronButton.setLayout(new BorderLayout());
		IronButton.add(ironLabel, BorderLayout.CENTER);
		ironLabel.setHorizontalAlignment(JLabel.CENTER);
		ironLabel.setFont(font);
		
		UraniumButton.setLayout(new BorderLayout());
		UraniumButton.add(uraniumLabel, BorderLayout.CENTER);
		uraniumLabel.setHorizontalAlignment(JLabel.CENTER);
		uraniumLabel.setFont(font);
		
		IceButton.setLayout(new BorderLayout());
		IceButton.add(iceLabel, BorderLayout.CENTER);
		iceLabel.setHorizontalAlignment(JLabel.CENTER);
		iceLabel.setFont(font);
		/*
		 * BUGOS
		 * 
		TeleportingGateGraphics TGateButton = new TeleportingGateGraphics(null);
		TGateButton.setBorder(buttonBorder);
		TGateButton.setMinimumSize(resourcebuttonsize);
		TGateButton.setMaximumSize(resourcebuttonsize);
		*/
		inventoryPanel = new JPanel();
		inventoryPanel.setMinimumSize(new Dimension(800, 200));
		inventoryPanel.setMaximumSize(new Dimension(800, 200));
		inventoryPanel.setSize(new Dimension(800, 200));
		inventoryPanel.setBackground(color);
		
		
		JLabel inventorytitleLabel = new JLabel("Spaceship's inventory:");
		inventorytitleLabel.setFont(font);
		inventorytitleLabel.setForeground(Color.YELLOW);
		inventorytitleLabel.setBackground(color);
		JLabel toltelek1 = new JLabel("                    ");
		JLabel toltelek2 = new JLabel("                    ");
		inventoryPanel.add(inventorytitleLabel);
		inventoryPanel.add(toltelek1);
		
		inventoryPanel.add(CoalButton);
		inventoryPanel.add(IronButton);
		inventoryPanel.add(UraniumButton);
		inventoryPanel.add(IceButton);
		
		inventoryPanel.add(toltelek2);
		//inventoryPanel.add(TGateButton);
		
		this.add(inventoryPanel, BorderLayout.SOUTH);

		// Jatekpanel (kozepso)
		
		mapPanel = new JPanel();
		
		// TODO: Átgondolni, hogy hogyan szabad/érdemes a rajzolást csinálni:
		// Layout-tal, automatikus elrendezéssel, vagy layout nélkül,
		// abszolút pozíciókkal, setBounds() és repaint() függvények hívásával.
		/* -- ! Ilyen módon LayoutManager nélkül használjuk a kirajzolást.
		 * A pozíciókat így kedvünk szerint beállíthatjuk, de nem biztos, hogy
		 * az ablak mozgatható-átméretezhető, illetve a program hordozható lesz ! -- */
		mapPanel.setLayout(null);
		/* -- ! ----- ! --*/
		
		mapPanel.setMinimumSize(new Dimension(800, 600));
		mapPanel.setMaximumSize(new Dimension(800, 600));
		mapPanel.setSize(new Dimension(800, 600));
		mapPanel.setBackground(Color.black);

		String path = "src\\berenyi_kft_GUI\\Icons\\background1.png";
		try {
			img = ImageIO.read(new File(path));
			//img = img.getScaledInstance(400, -1, Image.SCALE_DEFAULT);
		} catch (Exception e) {
			e.printStackTrace();
		}

		mapPanel.setOpaque(false);

		this.add(mapPanel, BorderLayout.CENTER);
		
		AsteroidGraphics.setGamePanel(this);
		SettlerGraphics.setGamePanel(this);
		AIRobotGraphics.setGamePanel(this);
		UFOGraphics.setGamePanel(this);
		TeleportingGateGraphics.setGamePanel(this);
	}

	public GamePanel(Cards cards) {
		this.cards = cards;
		this.initComponents();
		this.setVisible(true);
	}

	public void addToMapPanel(JButton drawableButton) {
		mapPanel.add(drawableButton);
		// Az új komponens előrehozása:
		mapPanel.setComponentZOrder(drawableButton, 0);
		drawableButtons.add(drawableButton);
		drawableButton.addActionListener(bl);
	}

	public void addToMapPanel(JLabel drawableLabel) {
		mapPanel.add(drawableLabel);
		// Az új komponens előrehozása:
		mapPanel.setComponentZOrder(drawableLabel, 0);
		drawableLabels.add(drawableLabel);
	}
	
	public void removeFromMapPanel(JButton drawableButton) {
		drawableButton.removeActionListener(bl);
		drawableButtons.remove(drawableButton);
		mapPanel.remove(drawableButton);
	}
	
	public void removeFromMapPanel(JLabel drawableLabel) {
		drawableLabels.remove(drawableLabel);
		mapPanel.remove(drawableLabel);
	}
	
	public void addToInventoryPanel(JButton drawableButton) {
		inventoryPanel.add(drawableButton);
		// Az új komponens előrehozása:
		inventoryPanel.setComponentZOrder(drawableButton, 0);
		drawableButtons.add(drawableButton);
		drawableButton.addActionListener(bl);
	}

	public void addToMInventoryPanel(JLabel drawableLabel) {
		inventoryPanel.add(drawableLabel);
		// Az új komponens előrehozása:
		inventoryPanel.setComponentZOrder(drawableLabel, 0);
		drawableLabels.add(drawableLabel);
		
	}

	public void addDrawable(IDrawable d) {
		drawables.add(d);
	}

	public void removeDrawable(IDrawable d) {
		drawables.remove(d);
	}

	public void drawAll() {
		int i = 0;
		while (i < drawables.size()) {
			IDrawable d = drawables.get(i);
			if (d != null)
				d.draw();
			
			// i = steppables.indexOf(is) + 1; // elvileg jo lenne
			i++; // halal eseten problemas lehet, de nem olyan veszes,
				 // max lesz egy masodperces kesleltetes (?)
		}
	}
	
	public void drawNumbOfResources() {
		if(controller==null) {
			ironLabel.setText("0");
			coalLabel.setText("0");
			iceLabel.setText("0");
			uraniumLabel.setText("0");
		}
		
		if(controller.getActPlayer()!=null) {
			Integer[] resourceArray=controller.getActPlayer().getSettler().getNumbOfResources();
			coalLabel.setText(resourceArray[0].toString());
			ironLabel.setText(resourceArray[1].toString());
			uraniumLabel.setText(resourceArray[2].toString());
			iceLabel.setText(resourceArray[3].toString());
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// vigyázat!
		// this.drawAll();
		g.drawImage(img, 0, 0, mapPanel);
		g.drawImage(img_inventory, 75, 600, inventoryPanel);
		this.drawNumbOfResources();

	}
	
	public Cards getCards() {
		return cards;
	}
	
	public Controller getController() {
		return controller;
	}
	
	public void addPoint(Point p) {
		asteroidPoints.add(p);
	}
	
	public List<Point> getPoints() {
		return asteroidPoints;
	}
	
	public void removeAsteroidPoints() {
		asteroidPoints.clear();
	}
	
	public void removeDrawableButtons() {
		for (JButton drButton : drawableButtons)
			mapPanel.remove(drButton);
		drawableButtons.clear();
	}
	
	public void removeDrawableLabels() {
		for (JLabel drLabel : drawableLabels)
			mapPanel.remove(drLabel);
		drawableLabels.clear();
	}

}
