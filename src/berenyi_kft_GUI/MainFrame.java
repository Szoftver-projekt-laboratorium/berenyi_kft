package berenyi_kft_GUI;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	MenuPanel menuPanel;
	GamePanel gamePanel;
	JPanel endGamePanel; /*pauseGamePanel*/
	
	public void initComponents() {
		menuPanel = new MenuPanel();
		this.add(menuPanel, BorderLayout.NORTH);
		
		gamePanel = new GamePanel();
		
		menuPanel.setVisible(true);
	}
	
	public MainFrame() {
		super("Asteroid Miners");
		initComponents();
		
		this.setMinimumSize(new Dimension(900, 600));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	public static void main(String[] args) {
		try {
			MainFrame mainFrame = new MainFrame();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
