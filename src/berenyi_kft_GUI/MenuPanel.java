package berenyi_kft_GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import berenyi_kft.Controller;
import berenyi_kft.Game;
import berenyi_kft.Proto;

public class MenuPanel extends JPanel {
	
	/**
	 * A legutóbbi pályakonfigurációt tartalmazó input-output szövegfájl.
	 * A konfiguráció megfelel a prototípus programbeli fájlformátumnak.
	 */
	private static final String persistentFilePath
			= "src\\berenyi_kft_GUI\\last_level_config.txt";
	
	/**
	 * Visszatér az utolsó pályakonfigurációt tároló fájl elérési útjával.
	 * 
	 * @return a perzisztáló bemeneti-kimeneti fájl elérési útja
	 */
	public static String getPersistentFilePath() {
		return persistentFilePath;
	}
	
	private Cards cards;
	private JLabel nameLabel;
	private JButton newGameButton;
	private JButton loadGameButton;
	private JButton exitButton;
	private BufferedImage img;
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			JButton pressedButton = (JButton)ae.getSource();
			if (pressedButton == newGameButton) {
				cards.show(Cards.addPlayersPanelID);
			}
			else if (pressedButton == loadGameButton) {
				GamePanel gamePanel = cards.getGamePanel();
				Proto.setGamePanel(gamePanel);
				
				try {
					Proto.load(persistentFilePath);
				}
				catch (IOException e) {
					// (TODO .out vagy legyen .err?)
					System.out.println("Input configuration file not found.");
					e.printStackTrace();
				}
				
				Controller controller = Proto.getAllObjects().getController();
				Game game = controller.getGame();
				gamePanel.setController(controller);
				controller.setGamePanel(gamePanel);
				game.setController(controller);
				game.setGamePanel(gamePanel);
				
				controller.nextPlayer();
				gamePanel.drawAll();
				game.getTimer().start();
				
				cards.show(Cards.gamePanelID);
			}
			else if (pressedButton == exitButton) {
				System.exit(0);
			}
		}
	}
	
	public void initComponents() {
		this.setLayout(new BorderLayout());
		Font titleFont = new Font("teko semibold", Font.BOLD, 40);
		Font font = new Font("teko semibold", Font.BOLD, 20);
		
		nameLabel = new JLabel("Asteroid Miner");
		nameLabel.setFont(titleFont);
		nameLabel.setForeground(Color.YELLOW);
		
		ButtonListener bl = new ButtonListener();
		
		
		JPanel buttonPanel=new JPanel();
		buttonPanel.setOpaque(true);
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(new Color(0, 0, 0, 0));
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		newGameButton = new JButton("New Game");
		newGameButton.setFont(font);
		newGameButton.addActionListener(bl);
		newGameButton.setMinimumSize(new Dimension(300, 200));
		newGameButton.setMaximumSize(new Dimension(300, 200));
		this.setButton(newGameButton);
	
		buttonPanel.add(newGameButton);
		
		loadGameButton = new JButton("Load Game");
		loadGameButton.setFont(font);
		loadGameButton.addActionListener(bl);
		this.setButton(loadGameButton);
		
		buttonPanel.add(loadGameButton);
		
		exitButton = new JButton("Exit Game");
		exitButton.setFont(font);
		exitButton.addActionListener(bl);
		this.setButton(exitButton);
		
		buttonPanel.add(exitButton);
		
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(nameLabel, BorderLayout.NORTH);
		
		/*try {	
			String path = "src\\berenyi_kft_GUI\\Icons\\start.png";
			Image img = ImageIO.read(new File(path));
			//img = img.getScaledInstance(700, 462, Image.SCALE_DEFAULT);
			ImageIcon icon = new ImageIcon(img, "Asteroid");
			JLabel background = new JLabel(icon);
			this.add(background, BorderLayout.CENTER);
		}catch(Exception e) {
			e.printStackTrace();
		}*/

		String path = "src\\berenyi_kft_GUI\\Icons\\start.png";
		try {
			img=ImageIO.read(new File(path));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setButton(JButton button) {
		Color color = new Color(9, 28, 87);
		
		Dimension buttonSize=new Dimension(250, 40);
		
		Border buttonBorder = new LineBorder(Color.YELLOW, 3);
	
		button.setMinimumSize(buttonSize);
		button.setMaximumSize(buttonSize);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setForeground(Color.YELLOW);
		button.setBackground(color);
		button.setBorder(buttonBorder);
	}
	
	@Override
    public Dimension getPreferredSize()
    {
        return (new Dimension(img.getWidth(), img.getHeight()));
    }
	
	@Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
    }
	
	public MenuPanel(Cards cards) {
		this.cards = cards;
		this.initComponents();
		this.setVisible(true);
	}
}
