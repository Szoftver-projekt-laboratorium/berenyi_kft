package berenyi_kft_GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import berenyi_kft.Asteroid;
import berenyi_kft.Settler;
import berenyi_kft.Sun;

/**
 * A telepesek képernyőre rajzolásáért felelős grafikus csomagoló osztály.
 * A telepesek képet tartalmazó címkeként jelennek meg a gamePanel mapPanel-jén.
 * 
 * @author berenyi_kft
 */
public class SettlerGraphics extends JLabel implements IDrawable {
	
	/**
	 * A játékpanel, amelynek a mapPanel-jén az telepesek is megjelennek
	 */
	private static GamePanel gamePanel = null;
	
	/**
	 * A telepesek közös képfájljának relatív elérési útja a projektben
	 */
	private static final String iconPath = "src\\berenyi_kft_GUI\\Icons\\settler.png";
	
	/**
	 * A aktualis telepesek képfájljának relatív elérési útja a projektben
	 */
	private static final String actualIconPath
			= "src\\berenyi_kft_GUI\\Icons\\settler_emphasized.png";


	/**
	 * A telepes-ikonok kívánt konstans szélessége
	 */
	private static final int preferredWidth = 75;

	/**
	 * A telepesek megjelenő ikonja (képe)
	 */
	private static Icon icon;
	private static Image image;
	
	private static Icon actualIcon;
	private static Image actualImage;

	/**
	 * Statikus inicializáló blokk a telepesek ikonjának betöltéséhez és
	 * beállításához. Ha a képfájlt nem sikerül betölteni, IOException keletkezik,
	 * amit azonnal lekezelünk.
	 */
	static {
		try {
			image = ImageIO.read(new File(iconPath));
			image = image.getScaledInstance(preferredWidth, -1, Image.SCALE_DEFAULT);
			icon = new ImageIcon(image, "Settler");
			
			actualImage = ImageIO.read(new File(actualIconPath));
			actualImage = actualImage.getScaledInstance(preferredWidth, -1, Image.SCALE_DEFAULT);
			actualIcon = new ImageIcon(actualImage, "Actual Settler");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Beállítja az osztálynak az aszteroidamezőt megjelenítő játékpanelt.
	 * 
	 * @param gamePanel	- a beállítandó játékpanel
	 */
	public static void setGamePanel(GamePanel gamePanel) {
		SettlerGraphics.gamePanel = gamePanel;
	}

	/**
	 * A modellbeli telepes objektum, amit ki kell rajzolni a képernyőre
	 */
	private final Settler settler;

	/**
	 * Új, telepest ábrázoló címke jön létre. A grafikus osztály
	 * konstruktorparaméterben átveszi a kirajzolandó telepest.
	 * 
	 * A telepesnek beállítja az ikont, és a nézet-objektum méreteit hozzáigazítja
	 * az ikon méretéhez. A címke hátterét átlátszóra állítja be. Nem rajzoltatja ki
	 * a címke keretét sem.
	 * 
	 * @param s	- a telepes, amelyet a képernyőn meg kell jeleníteni
	 */
	public SettlerGraphics(Settler s) {
		this.settler = s;
		
		this.setIcon(icon);
		this.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		this.setBorder(null);
		this.setOpaque(false);
		// this.setLocation(new Point(0, 0));
	}

	/**
	 * Visszatér a modellbeli telepessel.
	 * 
	 * @return A telepes modell-objektuma
	 */
	public Settler getSettler() {
		return this.settler;
	}

	/**
	 * Frissíti a telepes nézetének pozícióját az aszteroidája állapota alapján.
	 */
	@Override
	public void draw() {
		if (settler.isDead()) {
			this.setIcon(null);
			gamePanel.removeDrawable(this);
			gamePanel.removeFromMapPanel(this);
			// this.settler = null;
			return;
		}
		
		// this.setLocation(AsteroidGraphics.getCharacterPos(this.settler));
		Point pos = AsteroidGraphics.getCharacterPos(this.settler);
		
		Asteroid place = settler.getPlace();
		Sun sun = place.getSun();
		if (sun.isCloseToSun(place)
				&& sun.getTimeToSunStorm() < 1 && place.isMined()) {
			this.setIcon(null);
		} else if (settler.isActualSettler()) {
			this.setIcon(actualIcon);
		
		} else {
			this.setIcon(icon);
		}
		this.setBounds(new Rectangle(pos.x, pos.y,
				icon.getIconWidth(), icon.getIconHeight()));
		this.repaint();
	}

}
