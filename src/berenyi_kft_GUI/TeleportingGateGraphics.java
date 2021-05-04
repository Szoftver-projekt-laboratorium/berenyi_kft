package berenyi_kft_GUI;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import berenyi_kft.TeleportingGate;

/**
 * A teleportkapuk képernyőre rajzolásáért felelős grafikus csomagoló osztály.
 * A teleportkapuk képet tartalmazó címkeként jelennek meg a gamePanel mapPanel-jén.
 * 
 * @author berenyi_kft
 */
public class TeleportingGateGraphics extends JLabel implements IDrawable {
	
	/**
	 * Az összes grafikus teleportkapu-objektum listája
	 */
	private static List<TeleportingGateGraphics> allTeleportingGateGraphics
			= new ArrayList<TeleportingGateGraphics>();
	
	/**
	 * A játékpanel, amelynek a mapPanel-jén a teleportkapuk is megjelennek
	 */
	private static GamePanel gamePanel = null;
	
	/**
	 * A kapuk közös képfájljának relatív elérési útja a projektben
	 */
	private static final String iconPath
		= "src\\berenyi_kft_GUI\\Icons\\teleportingGate.png";
	
	/**
	 * A kapuk közös képfájljának relatív elérési útja a projektben
	 */
	private static final String emphasizedIconPath
		= "src\\berenyi_kft_GUI\\Icons\\teleportingGate_emphasized.png";

	/**
	 * A kapu-ikonok kívánt konstans szélessége
	 */
	private static final int preferredWidth = 60;

	/**
	 * A kapuk megjelenő ikonja (képe)
	 */
	private static Icon icon;
	
	/**
	 * A kiemelt kapuk megjelenő ikonja
	 */
	private static Icon emphasizedIcon;

	/**
	 * Statikus inicializáló blokk a kapuk ikonjának betöltéséhez és
	 * beállításához. Ha a képfájlt nem sikerül betölteni, IOException keletkezik,
	 * amit azonnal lekezelünk.
	 */
	static {
		try {
			Image img = ImageIO.read(new File(iconPath));
			img = img.getScaledInstance(preferredWidth, -1, Image.SCALE_DEFAULT);
			icon = new ImageIcon(img, "TeleportingGate");
			
			Image emphImg = ImageIO.read(new File(emphasizedIconPath));
			emphImg = emphImg.getScaledInstance(preferredWidth, -1, Image.SCALE_DEFAULT);
			emphasizedIcon = new ImageIcon(emphImg, "TeleportingGate emphasized");
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
		TeleportingGateGraphics.gamePanel = gamePanel;
	}
	
	/**
	 * Visszatér a grafikus felület összes teleportkapu-objektumával.
	 * @return - az összes teleportkapu nézet-objektumot tartalmazó lista
	 */
	public static List<TeleportingGateGraphics> getAllTeleportingGateGraphics() {
		return allTeleportingGateGraphics;
	}

	/**
	 * A modellbeli kapu objektum, amit ki kell rajzolni a képernyőre
	 */
	private final TeleportingGate gate;
	
	/**
	 * Új, teleportkaput ábrázoló címke jön létre. A grafikus osztály
	 * konstruktorparaméterben átveszi a kirajzolandó kaput.
	 * 
	 * A kapunak beállítja az ikont, és a nézet-objektum méreteit hozzáigazítja
	 * az ikon méretéhez. A címke hátterét átlátszóra állítja be. Nem rajzoltatja ki
	 * a címke keretét sem.
	 * 
	 * @param tg	- a teleportkapu, amelyet a képernyőn meg kell jeleníteni
	 */
	public TeleportingGateGraphics(TeleportingGate tg) {
		allTeleportingGateGraphics.add(this);
		this.gate = tg;
		
		this.setIcon(icon);
		this.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		this.setBorder(null);
		this.setOpaque(false);
		this.setLocation(new Point(0, 0));
		
		
	}

	/**
	 * Visszatér a modellbeli teleportkapuval.
	 * 
	 * @return A kapu modell-objektuma
	 */
	public TeleportingGate getTeleportingGate() {
		return this.gate;
	}

	/**
	 * Frissíti a teleportkapu nézetének pozícióját az aszteroidája állapota alapján,
	 * feltéve, hogy az már le van rakva egy aszteroidához.
	 */
	@Override
	public void draw() {
		if (gate.isDead()) {
			this.setIcon(null);
			allTeleportingGateGraphics.remove(this);
			gamePanel.removeDrawable(this);
			gamePanel.removeFromMapPanel(this);
			// this.gate = null;
			return;
		}
		
		if (this.gate.getAsteroid() != null) {
			// this.setLocation(AsteroidGraphics.getGatePos(this.gate));
			Point pos = AsteroidGraphics.getGatePos(this.gate);
			if (gate.isEmphasized())
				this.setIcon(emphasizedIcon);
			else
				this.setIcon(icon);
				
			this.setBounds(new Rectangle(pos.x, pos.y,
									icon.getIconWidth(), icon.getIconHeight()));
			this.repaint();
		}
	}

}
