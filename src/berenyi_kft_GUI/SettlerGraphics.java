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

import berenyi_kft.Settler;

/**
 * A telepesek képernyőre rajzolásáért felelős grafikus csomagoló osztály.
 * A telepesek képet tartalmazó címkeként jelennek meg a gamePanel mapPanel-jén.
 * 
 * @author berenyi_kft
 */
public class SettlerGraphics extends JLabel implements IDrawable {

	/**
	 * A telepesek közös képfájljának relatív elérési útja a projektben
	 */
	private static final String iconPath = "src\\berenyi_kft_GUI\\Icons\\settler.png";

	/**
	 * A telepes-ikonok kívánt konstans szélessége
	 */
	private static final int preferredWidth = 60;

	/**
	 * A telepesek megjelenő ikonja (képe)
	 */
	private static Icon icon;

	/**
	 * Statikus inicializáló blokk a telepesek ikonjának betöltéséhez és
	 * beállításához. Ha a képfájlt nem sikerül betölteni, IOException keletkezik,
	 * amit azonnal lekezelünk.
	 */
	static {
		try {
			Image img = ImageIO.read(new File(iconPath));
			img = img.getScaledInstance(preferredWidth, -1, Image.SCALE_DEFAULT);
			icon = new ImageIcon(img, "Settler");
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		this.setLocation(new Point(0, 0));
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
	 * Frissíti a telepes pozícióját az aszteroidája állapota alapján.
	 */
	@Override
	public void draw() {
		this.setLocation(AsteroidGraphics.getCharacterPos(this.settler));
	}

}
