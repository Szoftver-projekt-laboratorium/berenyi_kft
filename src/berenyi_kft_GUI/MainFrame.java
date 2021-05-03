package berenyi_kft_GUI;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	private Cards cards;
	
	public MainFrame() {
		super("Asteroid Miners");
		
		// TODO Hogyan lesz szálbiztos?
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	cards = new Cards(getContentPane());
        		
        		setMinimumSize(new Dimension(1300, 700));
        		setDefaultCloseOperation(EXIT_ON_CLOSE);
                setVisible(true);
            }
        });
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
