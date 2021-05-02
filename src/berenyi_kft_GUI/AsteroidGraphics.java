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
import berenyi_kft.TeleportingGate;

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
	 * Az aszteroidák közös képfájljainak relatív elérési útjai a projektben
	 */
	private static final String iconPath = "src\\berenyi_kft_GUI\\Icons\\asteroid.png";
	private static final String emphasizedIconPath
		= "src\\berenyi_kft_GUI\\Icons\\asteroid_emphasized.png";

	/**
	 * Az aszteroida ikonok kívánt konstans szélessége (a képek négyzet alakúak)
	 */
	private static final int preferredWidth = 120;
	
	/**
	 * Az aszteroidán megjelenő karakter- és teleportkapu ikonokat
	 * tartalmazó négyzet alakú táblázat sorainak (vagy oszlopainak) száma
	 */
	private static final int thingTableSize = 3;
	
	/**
	 * Az aszteroidán megjelenő karakter- és teleportkapu ikonokat
	 * tartalmazó cellák kívánt konstans szélessége
	 */
	private static final int preferredCellWidth = 40;

	/**
	 * Az aszteroida-gombokon megjelenő alapértelmezett ikon
	 */
	private static Icon icon;
	
	/**
	 * Az aszteroida-gombon a kiemelésekor megjelenő ikon
	 */
	private static Icon emphasizedIcon;
	
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
			
			Image emphImg = ImageIO.read(new File(emphasizedIconPath));
			emphImg = emphImg.getScaledInstance(preferredWidth, -1, Image.SCALE_DEFAULT);
			emphasizedIcon = new ImageIcon(emphImg, "Asteroid Emphasized");
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
	 * Kiszámítja a <code>c</code> karakter pozícióját a paneljén, felhasználva
	 * az aszteroidája pozícióját és az aszteroidán levő karakterek listáját.
	 * 
	 * @param c	- a karakter, amelynek a nézetbeli koordinátái meghatározandók
	 * @return	a karakter nézet-objektumának számított pozíciója
	 */
	public static Point getCharacterPos(berenyi_kft.Character c) {
		// TODO Kellenek null check-ek?
		Asteroid a = c.getPlace();
		AsteroidGraphics ag = null;
		for (AsteroidGraphics ag2 : allAsteroidGraphics) {
			if (ag2.getAsteroid() == a) {
				ag = ag2;
				break;
			}
		}
		
		int idx = a.getCharacters().indexOf(c);
		int xPos = ag.getX() + (idx % thingTableSize) * preferredCellWidth;
		int yPos = ag.getY() + (idx / thingTableSize) * preferredCellWidth;
		return new Point(xPos, yPos);
	}
	
	/**
	 * Kiszámítja a <code>tg</code> teleportkapu pozícióját a paneljén, feltéve
	 * hogy már aszteroida körül kering. Ehhez felhasználja az aszteroidája
	 * pozícióját, az aszteroidán levő karakterek, valamint kapuk listáját.
	 * 
	 * @param tg	- A teleportkapu, amelynek a koordinátái meghatározandók
	 * @return	a teleportkapu nézet-objektumának számított pozíciója
	 */
	public static Point getGatePos(TeleportingGate tg) {
		// TODO Kellenek null check-ek?
		Asteroid a = tg.getAsteroid();
		if (a == null)
			return new Point(0, 0);
		
		AsteroidGraphics ag = null;
		for (AsteroidGraphics ag2 : allAsteroidGraphics) {
			if (ag2.getAsteroid() == a) {
				ag = ag2;
				break;
			}
		}
		
		int idx = a.getCharacters().size() + a.getGates().indexOf(tg);
		int xPos = ag.getX() + (idx % thingTableSize) * preferredCellWidth;
		int yPos = ag.getY() + (idx / thingTableSize) * preferredCellWidth;
		return new Point(xPos, yPos);
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
	 * @param a         - az aszteroida, amelyet a képernyőn meg kell jeleníteni
	 * @param panelSize - az aszteroidamezőt megjelenítő panel méretei
	 */
	public AsteroidGraphics(Asteroid a, Dimension panelSize) {
		allAsteroidGraphics.add(this);
		this.asteroid = a;
		
		this.setActionCommand(actionCommand);
		this.setIcon(icon);
		this.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		
		Random random = new Random();
		pos.x = random.nextInt(panelSize.width - icon.getIconWidth());
		pos.y = random.nextInt(panelSize.height - icon.getIconHeight());
		this.setLocation(pos);
	}
	
	/**
	 * Visszatér a modellbeli aszteroidával.
	 * @return az aszteroida modell-objektuma
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
	 * Ehhez először a helyére mozgatja az aszteroidát.
	 * 
	 * Ha az asteroid modell-objektum éppen ki van emelve (<code>isEmphasized()==true</code>),
	 * akkor az <code>emphasizedIcon</code>-t állítja be az ikonjaként, egyébként az
	 * alapértelmezett <code>icon</code> ikont.
	 */
	@Override
	public void draw() {
		/*
		 * A modellre támaszkodik, hogy az asteroid ki van-e jelölve/emelve, vagy sem.
		 * Az ikon majd változhat, függően a rétegek számától és a nyersanyagtól is.
		 * 
		 * A karakterek saját maguk rajzolják rá magukat, az nem az aszteroida dolga,
		 * viszont a karakterek és kapuk le tudják kérdezni tőle a pozíciójukat.
		 * 
		 * (A draw függvényekben mindig beállítjuk a nézet-objektum helyét az elvárt
		 * pos pozícióra, ez automatikusan invalidate()-et hív, és újra fogja rajzolni.)
		 */
		if (asteroid.isEmphasized()) {
			this.setIcon(icon);
		}
		else {
			this.setIcon(emphasizedIcon);
		}
		this.setLocation(pos);
	}

}
