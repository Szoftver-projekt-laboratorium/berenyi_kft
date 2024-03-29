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

import berenyi_kft.AIRobot;
import berenyi_kft.Asteroid;
import berenyi_kft.Sun;

/**
 * A robotok képernyőre rajzolásáért felelős grafikus csomagoló osztály.
 * A robotok képet tartalmazó címkeként jelennek meg a gamePanel mapPanel-jén.
 * 
 * @author berenyi_kft
 */
public class AIRobotGraphics extends JLabel implements IDrawable {
	
	/**
	 * A játékpanel, amelynek a mapPanel-jén a robotok is megjelennek
	 */
	private static GamePanel gamePanel = null;
	
	/**
	 * A robotok közös képfájljának relatív elérési útja a projektben
	 */
	private static final String iconPath = "src\\berenyi_kft_GUI\\Icons\\aiRobot.png";

	/**
	 * A robot-ikonok kívánt konstans szélessége
	 */
	private static final int preferredWidth = 65;

	/**
	 * A robotok megjelenő ikonja (képe)
	 */
	private static Icon icon;

	/**
	 * Statikus inicializáló blokk a robotok ikonjának betöltéséhez és
	 * beállításához. Ha a képfájlt nem sikerül betölteni, IOException keletkezik,
	 * amit azonnal lekezelünk.
	 */
	static {
		try {
			Image img = ImageIO.read(new File(iconPath));
			img = img.getScaledInstance(preferredWidth, -1, Image.SCALE_DEFAULT);
			icon = new ImageIcon(img, "AIRobot");
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
		AIRobotGraphics.gamePanel = gamePanel;
	}

	/**
	 * A modellbeli robot objektum, amit ki kell rajzolni a képernyőre
	 */
	private final AIRobot aiRobot;

	/**
	 * Új, robotot ábrázoló címke jön létre. A grafikus osztály
	 * konstruktorparaméterben átveszi a kirajzolandó robotot.
	 * 
	 * A robotnak beállítja az ikont, és a nézet-objektum méreteit hozzáigazítja
	 * az ikon méretéhez. A címke hátterét átlátszóra állítja be. Nem rajzoltatja ki
	 * a címke keretét sem.
	 * 
	 * @param air	- a robot, amelyet a képernyőn meg kell jeleníteni
	 */
	public AIRobotGraphics(AIRobot air) {
		this.aiRobot = air;
		
		this.setIcon(icon);
		this.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		this.setBorder(null);
		this.setOpaque(false);
		this.setLocation(new Point(0, 0));
	}

	/**
	 * Visszatér a modellbeli robottal.
	 * 
	 * @return A robot modell-objektuma
	 */
	public AIRobot getAIRobot() {
		return this.aiRobot;
	}

	/**
	 * Frissíti a robot nézetének pozícióját az aszteroidája állapota alapján.
	 */
	@Override
	public void draw() {
		if (aiRobot.isDead()) {
			this.setIcon(null);
			gamePanel.removeDrawable(this);
			gamePanel.removeFromMapPanel(this);
			// this.aiRobot = null;
			return;
		}
		
		// this.setLocation(AsteroidGraphics.getCharacterPos(this.aiRobot));
		Point pos = AsteroidGraphics.getCharacterPos(this.aiRobot);
		
		Asteroid place = aiRobot.getPlace();
		Sun sun = place.getSun();
		if (sun.isCloseToSun(place)
				&& sun.getTimeToSunStorm() < 1 && place.isMined()) {
			this.setIcon(null);
		} else {
			this.setIcon(icon);
		}
		this.setBounds(new Rectangle(pos.x, pos.y,
								icon.getIconWidth(), icon.getIconHeight()));
		this.repaint();
	}

}
