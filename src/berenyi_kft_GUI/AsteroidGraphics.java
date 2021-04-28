package berenyi_kft_GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import berenyi_kft.Asteroid;

public class AsteroidGraphics extends JButton implements IDrawable {
	
	private final Asteroid asteroid;
	
	public AsteroidGraphics(Asteroid a) {	
		asteroid = a;
		
		try {
			String path = "src\\berenyi_kft_GUI\\Icons\\asteroid.png";
			BufferedImage img = ImageIO.read(new File(path));
			ImageIcon icon = new ImageIcon(img, "Asteroid");
			this.setIcon(icon);
			// this.setSize(65/4, 60/4);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw() {
		System.out.println("Asteroida kép");
		
		// TODO gomb atpozicionalasa
		
		Random random = new Random();
		this.setSize(65/4, 60/4);
		this.setLocation(random.nextInt(400), random.nextInt(200));
	}
	
}
