package berenyi_kft_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import berenyi_kft.Asteroid;

/**
 * Az aszteroidák képernyőre rajzolásáért felelős grafikus csomagoló osztály. Az
 * aszteroidák kattintható gombokként jelennek meg a gamePanel mapPanel-jén.
 * 
 * @author berenyi_kft
 */
public class AsteroidGraphics extends JButton implements IDrawable {
	
	/**
	 * Az összes AsteroidGraphics típusú objektum listája
	 */
	private static List<AsteroidGraphics> allAsteroidGraphics
		= new ArrayList<AsteroidGraphics>();
	
	/**
	 * Az aszteroida-gombok közös akcióparancsa
	 */
	private static final String actionCommand = "Asteroid";
	
	/**
	 * Az aszteroidák közös képfájljának relatív elérési útja a projektben
	 */
	private static final String iconPath = "src\\berenyi_kft_GUI\\Icons\\asteroid.png";

	/**
	 * Az aszteroida ikonok kívánt konstans szélessége (a képek négyzet alakúak)
	 */
	private static final int preferredWidth = 120;

	/**
	 * Az aszteroida-gombokon megjelenő ikon (kép)
	 */
	private static Icon icon;
	
	/**
	 * Átlátszó szín az aszteroidák háttérszínének
	 */
	// private static final Color defaultBgColor = new Color(0, 0, 0, 0);
	
	/**
	 * Élénk szín a kiemelt aszteroidák háttérszínének
	 */
	private static final Color emphasizerBgColor = Color.CYAN;
	
	/**
	 * Visszatér az összes grafikus aszteroidából álló listával.
	 * @return Az aszteroidák nézet-objektumai listája
	 */
	public static List<AsteroidGraphics> getAllAsteroidGraphics() {
		return allAsteroidGraphics;
	}
	
	/**
	 * Visszatér az aszteroida-gombok közös parancsával.
	 * @return Az aszteroidák akcióparancs-szövege
	 */
	public static String getCommand() {
		return actionCommand;
	}
	
	/**
	 * Statikus inicializáló blokk az aszteroidák ikonjának betöltéséhez és
	 * beállításához. Ha a képfájlt nem sikerül betölteni, IOException keletkezik,
	 * amit azonnal lekezelünk.
	 */
	static {
		try {
			Image img = ImageIO.read(new File(iconPath));
			img = img.getScaledInstance(preferredWidth, -1, Image.SCALE_DEFAULT);
			icon = new ImageIcon(img, "Asteroid");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Elrendezi az aszteroida-gombokat a képernyőn.
	 */
	public static void setAsteroidLocations() {
		for (AsteroidGraphics ag : allAsteroidGraphics)
			ag.setRandomLocation();
	}

	
	/**
	 * A modellbeli aszteroida objektum, amit ki kell rajzolni a képernyőre
	 */
	private final Asteroid asteroid;
	
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
	public AsteroidGraphics(Asteroid a, Dimension panelSize) {
		allAsteroidGraphics.add(this);
		this.asteroid = a;
		
		this.setActionCommand(AsteroidGraphics.actionCommand);
		this.setIcon(icon);
		this.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		
		this.setBackground(emphasizerBgColor);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		
		Random random = new Random();
		pos.x = random.nextInt(panelSize.width - icon.getIconWidth());
		pos.y = random.nextInt(panelSize.height - icon.getIconHeight());
		this.setLocation(pos);
	}
	
	/**
	 * Visszatér a modellbeli aszteroidaval.
	 * @return Az aszteroida modell-objektuma
	 */
	public Asteroid getAsteroid() {
		return this.asteroid;
	}
	
	/**
	 * Véletlenszerű helyre állítja be az aszteroida-gombot a paneljén.
	 */
	public void setRandomLocation() {
		Random random = new Random();
		if (this.getParent() != null) {
			pos.x = random.nextInt(
					this.getParent().getWidth() - icon.getIconWidth());
			pos.y = random.nextInt(
					this.getParent().getHeight() - icon.getIconHeight());
		}
		else {
			pos.x = 0;
			pos.y = 0;
		}
		this.setLocation(pos);
	}
	
	/**
	 * Frissíti az aszteroida nézetét a modellbeli állapota alapján.
	 * 
	 * Ha az asteroid modell-objektum éppen ki van emelve (isEmphasized()==true),
	 * akkor átlátszatlanná teszi azzal, hogy kirajzolja a gomb teljes területét
	 * (a setContentAreFilled(true) paranccsal éri el).
	 * Ezzel megjelenik az ikon mögötti emphasizerBgColor kiemelőszínű háttér.
	 * Ha az aszteroida nincs kiemelve, akkor nem rajzoltatja ki a gomb hátterét
	 * (a setContentAreFilled(false) utasítással).
	 */
	@Override
	public void draw() {
		/*
		 * A modellre támaszkodik, hogy az asteroid ki van-e jelölve/emelve, vagy sem.
		 * Az ikon majd változhat, függően a rétegek számától és a nyersanyagtól is.
		 * A karakterek saját maguk rajzolják rá magukat, az nem az aszteroida dolga.
		 */
		this.setLocation(pos);
		if (asteroid.isEmphasized()) {
			this.setContentAreaFilled(true);
		}
		else {
			this.setContentAreaFilled(false);
		}
	}

}
