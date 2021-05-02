package berenyi_kft_GUI;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

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
	 * A kapuk közös képfájljának relatív elérési útja a projektben
	 */
	private static final String iconPath
		= "src\\berenyi_kft_GUI\\Icons\\teleportingGate.png";

	/**
	 * A kapu-ikonok kívánt konstans szélessége
	 */
	private static final int preferredWidth = 60;

	/**
	 * A kapuk megjelenő ikonja (képe)
	 */
	private static Icon icon;

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
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	 * Frissíti a teleportkapu pozícióját az aszteroidája állapota alapján,
	 * feltéve, hogy az már le van rakva egy aszteroidához.
	 */
	@Override
	public void draw() {
		if (this.gate.getAsteroid() != null)
			this.setLocation(AsteroidGraphics.getGatePos(this.gate));
	}

}
