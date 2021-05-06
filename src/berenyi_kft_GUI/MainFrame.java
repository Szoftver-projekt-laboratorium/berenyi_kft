package berenyi_kft_GUI;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MainFrame extends JFrame {
	// private static final int preferredWindowWidth = 1280;
	// private static final String bgImagePath
	// 	= "src\\berenyi_kft_GUI\\Icons\\start_16_9.png";
	// private static Image bgImage;
	private Cards cards;
	
	public MainFrame() {
		super("Asteroid Mining");
		// TODO Szálbiztos?
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	cards = new Cards(getContentPane());
        		
            	/*try {
	            	bgImage = ImageIO.read(new File(bgImagePath));
	            	bgImage = bgImage.getScaledInstance(
	            			preferredWindowWidth, -1, Image.SCALE_DEFAULT);
            	}
            	catch (IOException e) {
        			e.printStackTrace();
        		}*/
            	
        		setMinimumSize(new Dimension(1280, 750));
            	
        		setDefaultCloseOperation(EXIT_ON_CLOSE);
                setVisible(true);
            }
        });
	}
	
	/*@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bgImage, 0, 0, null);
	}*/
	
	public static void main(String[] args) {
		try {
			MainFrame mainFrame = new MainFrame();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
