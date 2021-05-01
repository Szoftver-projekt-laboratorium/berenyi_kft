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

// TODO Átírni a javadoc kommenteket Settler-re.
/**
 * Az aszteroidák képernyőre rajzolásáért felelős grafikus csomagoló osztály. Az
 * aszteroidák kattintható gombokként jelennek meg a gamePanel mapPanel-jén.
 * 
 * @author berenyi_kft
 */
public class SettlerGraphics extends JLabel implements IDrawable {
	
	/**
	 * Az aszteroidák közös képfájljának relatív elérési útja a projektben
	 */
	private static final String iconPath = "src\\berenyi_kft_GUI\\Icons\\settler.png";

	/**
	 * Az aszteroida ikonok kívánt konstans szélessége (a képek négyzet alakúak)
	 */
	private static final int preferredWidth = 30;

	/**
	 * Az aszteroida-gombokon megjelenő ikon (kép)
	 */
	private static Icon icon;
	
	/**
	 * Statikus inicializáló blokk az aszteroidák ikonjának betöltéséhez és
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
	 * A modellbeli aszteroida objektum, amit ki kell rajzolni a képernyőre
	 */
	private final Settler settler;
	
	/**
	 * Az aszteroida-gomb koordinátái a paneljén
	 */
	private Point pos = new Point(0, 0);

	/**
	 * Új, aszteroidát ábrázoló gomb jön létre. A grafikus osztály
	 * konstruktorparaméterben átveszi a kirajzolandó aszteroidát és az
	 * aszteroidamező paneljának méreteit.
	 * 
	 * Az aszteroida-gombnak beállítja az ikont, és a gomb méreteit hozzáigazítja az
	 * ikon méretéhez. Megadja a gomb háttérszínét is, de ezt alapértelmezetten
	 * nem rajzolja ki. Nem rajzoltatja ki a gomb keretét sem.
	 * Az aszteroidának véletlen pozíciót állít be a leendő paneljén
	 * úgy, hogy a teljes kép a panel belsejében legyen.
	 * 
	 * @param a         Az aszteroida, amelyet a képernyőn meg kell jeleníteni
	 * @param panelSize Az aszteroidamezőt megjelenítő panel méretei
	 */
	public SettlerGraphics(Settler s) {
		this.settler = s;
		
		this.setIcon(icon);
		this.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		this.setBorder(null);
		this.setOpaque(false);
		
		this.setLocation(pos);
	}
	
	/**
	 * Visszatér a modellbeli aszteroidaval.
	 * @return Az aszteroida modell-objektuma
	 */
	public Settler getSettler() {
		return this.settler;
	}
	
	/**
	 * Frissíti a telepes nézetét a modellbeli állapota alapján.
	 * Meghívja az invalidate() metódust, hogy a frissített nézet
	 * tényleges képernyőre rajzolása is megtörténjen.
	 */
	@Override
	public void draw() {
		// TODO AsteroidGraphics kommunikáció az elhelyezésről
		this.setLocation(pos);
	}
	
}
