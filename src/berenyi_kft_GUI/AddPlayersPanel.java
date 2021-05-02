package berenyi_kft_GUI;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;

import berenyi_kft.Controller;
import berenyi_kft.Proto;

public class AddPlayersPanel extends JPanel {
	
	private ArrayList<String> playerNames = new ArrayList<String>();
	private Controller controller;
	
	private Cards cards;
	private JLabel nameLabel;
	private JTextArea playerNamesField;
	private JTextField nameField;
	private JButton addPlayerButton;
	private JButton startGameButton;
	private BufferedImage img;
	private Color color;
	
	public ArrayList<String> getPlayerNames() {
		return playerNames;
	}
	
	public void setPlayerNames(ArrayList<String> playerNames) {
		this.playerNames = playerNames;
	}
	
	private class ButtonListener implements ActionListener {		
		public void actionPerformed(ActionEvent ae) {
			JButton pressedButton = (JButton)ae.getSource();
			
			String message=null;
			
			if (pressedButton == addPlayerButton) {
				if (playerNames.size() >= 6) {
					JOptionPane.showMessageDialog(null, "A jatekosok szama legfeljebb 6 lehet!");
				}
				else if (! nameField.getText().matches("[a-zA-Z0-9]+")) {
					JOptionPane.showMessageDialog(null, "A neved csak ekezet nelkuli "
							+ "alfanumerikus karaktereket tartalmazhat.");
				}
				else {
					playerNames.add(nameField.getText());
					StringBuilder sb = new StringBuilder();
					for (String name : playerNames) {
					    sb.append(name);
					    sb.append(System.getProperty("line.separator"));
					}
					playerNamesField.setText(sb.toString());
				}
			}
			else if (pressedButton == startGameButton) {
				if (playerNames.isEmpty() | playerNames.size() > 6) {
					System.out.println("A jatekosok szama 1 es 6 kozott "
													+ "kell, hogy legyen!");
					JOptionPane.showMessageDialog(null, "A jatekosok szama 1 es 6 kozott "
							+ "kell, hogy legyen!");
				}
				else {
					for (String str : playerNames) {
						System.out.println(str);
					}
					
					Proto.allObjects = new Proto.Objects();
					controller = new Controller();
					controller.startGame(cards.getGamePanel(), playerNames);
					
					cards.show(Cards.gamePanelID);
				}
			}
		}
	}
	
	public void initComponents() {
		this.setLayout(new BorderLayout());
		Font font = new Font("teko semibold", Font.BOLD, 20);
	    color = new Color(9, 28, 87);
		
		nameLabel = new JLabel("Játékos: ");
		nameLabel.setFont(font);
		nameLabel.setForeground(Color.YELLOW);
		
		nameField = new JTextField("Player1");
		nameField.setFont(font);
		nameField.setBackground(Color.YELLOW);
		
		ButtonListener bl = new ButtonListener();
		
		addPlayerButton = new JButton("Add Player");
		addPlayerButton.setFont(font);
		this.setButton(addPlayerButton);
		addPlayerButton.addActionListener(bl);
		
		startGameButton = new JButton("Start Game!");
		startGameButton.setFont(font);
		this.setButton(startGameButton);
		startGameButton.addActionListener(bl);
		
		JPanel addPanel = new JPanel();
		addPanel.setOpaque(true);
		addPanel.add(nameLabel);
		addPanel.add(nameField);
		addPanel.add(addPlayerButton);
		addPanel.setBackground(new Color(0, 0, 0, 0));
		this.add(addPanel, BorderLayout.NORTH);
		
		JPanel playersPanel = new JPanel();
		playersPanel.setOpaque(true);
		playerNamesField = new JTextArea(7, 15);
		playerNamesField.setEditable(false);
		playerNamesField.setAlignmentX(CENTER_ALIGNMENT);
		playerNamesField.setFont(font);
		playerNamesField.setWrapStyleWord(true);
		playerNamesField.setBackground(Color.YELLOW);
		playersPanel.add(playerNamesField);
		playersPanel.setBackground(new Color(0, 0, 0, 0));
		this.add(playersPanel, BorderLayout.CENTER);
		
		JPanel startPanel = new JPanel();
		startPanel.setOpaque(true);
		startPanel.setBackground(new Color(0, 0, 0, 0));
		startPanel.add(startGameButton);
		this.add(startPanel, BorderLayout.SOUTH);
		
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
	
	public AddPlayersPanel(Cards cards) {
		this.cards = cards;
		this.initComponents();
		this.setVisible(true);
	}
	
}
