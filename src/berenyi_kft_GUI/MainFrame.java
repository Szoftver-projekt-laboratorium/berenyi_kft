package berenyi_kft_GUI;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	private Cards cards;
	
	public MainFrame() {
		super("Asteroid Miners");
		cards = new Cards();
        cards.initComponents(this.getContentPane());
		
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
