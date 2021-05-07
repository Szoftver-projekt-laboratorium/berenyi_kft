package berenyi_kft_GUI;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import berenyi_kft.Sun;
import berenyi_kft.UFO;

/**
 * Az ufók képernyőre rajzolásáért felelős grafikus csomagoló osztály.
 * Az ufók képet tartalmazó címkeként jelennek meg a gamePanel mapPanel-jén.
 * 
 * @author berenyi_kft
 */
public class UFOGraphics extends JLabel implements IDrawable {
	
	/**
	 * A játékpanel, amelynek a mapPanel-jén az ufók is megjelennek
	 */
	private static GamePanel gamePanel = null;
	
	/**
	 * Az ufók közös képfájljának relatív elérési útja a projektben
	 */
	private static final String iconPath = "src\\berenyi_kft_GUI\\Icons\\ufo.png";

	/**
	 * Az ufó-ikonok kívánt konstans szélessége
	 */
	private static final int preferredWidth = 60;

	/**
	 * Az ufók megjelenő ikonja (képe)
	 */
	private static Icon icon;

	/**
	 * Statikus inicializáló blokk az ufók ikonjának betöltéséhez és
	 * beállításához. Ha a képfájlt nem sikerül betölteni, IOException keletkezik,
	 * amit azonnal lekezelünk.
	 */
	static {
		try {
			Image img = ImageIO.read(new File(iconPath));
			img = img.getScaledInstance(preferredWidth, -1, Image.SCALE_DEFAULT);
			icon = new ImageIcon(img, "UFO");
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
		UFOGraphics.gamePanel = gamePanel;
	}

	/**
	 * A modellbeli ufó objektum, amit ki kell rajzolni a képernyőre
	 */
	private final UFO ufo;

	/**
	 * Új, ufót ábrázoló címke jön létre. A grafikus osztály
	 * konstruktorparaméterben átveszi a kirajzolandó ufót.
	 * 
	 * Az ufónak beállítja az ikont, és a nézet-objektum méreteit hozzáigazítja
	 * az ikon méretéhez. A címke hátterét átlátszóra állítja be. Nem rajzoltatja ki
	 * a címke keretét sem.
	 * 
	 * @param ufo	- az ufó, amelyet a képernyőn meg kell jeleníteni
	 */
	public UFOGraphics(UFO ufo) {
		this.ufo = ufo;
		
		this.setIcon(icon);
		this.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		this.setBorder(null);
		this.setOpaque(false);
		this.setLocation(new Point(0, 0));
	}

	/**
	 * Visszatér a modellbeli ufóval.
	 * 
	 * @return Az ufó modell-objektuma
	 */
	public UFO getUFO() {
		return this.ufo;
	}

	/**
	 * Frissíti az ufó nézetének pozícióját az aszteroidája állapota alapján.
	 */
	@Override
	public void draw() {
		if (ufo.isDead()) {
			this.setIcon(null);
			gamePanel.removeDrawable(this);
			gamePanel.removeFromMapPanel(this);
			// this.ufo = null;
			return;
		}
		
		// this.setLocation(AsteroidGraphics.getCharacterPos(this.ufo));
		Point pos = AsteroidGraphics.getCharacterPos(this.ufo);
		
		Sun sun = ufo.getPlace().getSun();
		if (sun.getTimeToSunStorm() < 1 && ufo.getPlace().isMined()) {
			this.setIcon(null);
		}
		else {
			this.setIcon(icon);
		}
		this.setBounds(new Rectangle(pos.x, pos.y,
								icon.getIconWidth(), icon.getIconHeight()));
		this.repaint();
	}

}

