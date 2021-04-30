package berenyi_kft_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

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
 * aszteroidák kattintható gombokként jelennek meg a gamePanel mapPanel-jében.
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
	 * A modellbeli aszteroida objektum, amit ki kell rajzolni a képernyőre
	 */
	private final Asteroid asteroid;

	/**
	 * Új, aszteroidát ábrázoló gomb jön létre. A grafikus osztály
	 * konstruktorparaméterben átveszi a kirajzolandó aszteroidát és az
	 * aszteroidamező paneljának méreteit.
	 * 
	 * Az aszteroida-gombnak beállítja az ikont, és a gomb méreteit hozzáigazítja az
	 * ikon méretéhez. Az aszteroidának véletlen pozíciót állít be a leendő paneljén
	 * úgy, hogy a teljes ikon beleférjen.
	 * 
	 * @param a         Az aszteroida, amelyet a képernyőn meg kell jeleníteni
	 * @param panelSize Az aszteroidamezőt megjelenítő panel méretei
	 */
	public AsteroidGraphics(Asteroid a, Dimension panelSize) {
		allAsteroidGraphics.add(this);
		asteroid = a;
		
		this.setActionCommand(AsteroidGraphics.actionCommand);
		this.setIcon(icon);
		this.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		this.setBackground(new Color(0, 0, 0, 0));
		this.setBorderPainted(false);

		Random random = new Random();
		int xPos = random.nextInt(panelSize.width - icon.getIconWidth());
		int yPos = random.nextInt(panelSize.height - icon.getIconHeight());
		this.setLocation(xPos, yPos);
	}
	
	/**
	 * Visszater a modellbeli aszteroidaval.
	 * @return Az aszteroida modell-objektuma
	 */
	public Asteroid getAsteroid() {
		return this.asteroid;
	}
	
	/**
	 * Kirajzolja a képernyőre az aszteroidát a modellbeli állapota alapján.
	 */
	@Override
	public void draw() {
		/*
		 * Egyelőre nem változik semmi lényeges. Az ikon majd változhat, függően a
		 * rétegek számától és a nyersanyagtól is. A karakterek saját maguk rajzolják rá
		 * magukat, az nem az aszteroida dolga.
		 */

		/*
		 * Az aszteroidák a mapPanel minden invalidálódása alkalmával, illetve a Move
		 * gomb lenyomásakor véletlen helyre mozognak a mapPanelen belül, figyelve, hogy
		 * ne lógjanak ki belőle (csak játékból, nem kell majd mozogniuk).
		 */
		Random random = new Random();
		int xPos = random.nextInt(this.getParent().getWidth() - icon.getIconWidth());
		int yPos = random.nextInt(this.getParent().getHeight() - icon.getIconHeight());
		this.setLocation(xPos, yPos);
		
		if (asteroid.isEmphasized()) {
			this.setBackground(Color.RED);
		}
		else {
			this.setBackground(new Color(0, 0, 0, 0));
		}
	}

}
