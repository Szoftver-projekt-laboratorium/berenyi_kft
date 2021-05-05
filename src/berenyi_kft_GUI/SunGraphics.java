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

/**
 * A Nap képernyőre rajzolásáért felelős grafikus csomagoló osztály.
 * A Nap képet tartalmazó címkeként jelenik meg a gamePanel mapPanel-jén.
 * 
 * @author berenyi_kft
 */
public class SunGraphics extends JLabel implements IDrawable {

	/**
	 * A Nap képfájljának relatív elérési útja a projektben
	 */
	private static final String iconPath = "src\\berenyi_kft_GUI\\Icons\\sun.png";
	
	/**
	 * A Nap napvihart ábrázoló képfájljának relatív elérési útja a projektben
	 */
	private static final String stormyIconPath
			= "src\\berenyi_kft_GUI\\Icons\\sun_solarstorm.png";

	/**
	 * A Nap-ikonok kívánt konstans szélessége
	 */
	private static final int preferredWidth = 200;

	/**
	 * A Nap alapértelmezetten megjelenő ikonja (képe)
	 */
	private static Icon icon;
	
	/**
	 * A Nap napviharkor megjelenő ikonja
	 */
	private static Icon stormyIcon;

	/**
	 * Statikus inicializáló blokk a Nap ikonjának betöltéséhez és
	 * beállításához. Ha a képfájlt nem sikerül betölteni, IOException keletkezik,
	 * amit azonnal lekezelünk.
	 */
	static {
		try {
			Image img = ImageIO.read(new File(iconPath));
			img = img.getScaledInstance(preferredWidth, -1, Image.SCALE_DEFAULT);
			icon = new ImageIcon(img, "Sun");
			
			Image stormyImg = ImageIO.read(new File(stormyIconPath));
			stormyImg = stormyImg.getScaledInstance(preferredWidth, -1, Image.SCALE_DEFAULT);
			stormyIcon = new ImageIcon(stormyImg, "Sunstorm");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A modellbeli Nap objektum, amit ki kell rajzolni a képernyőre
	 */
	private final Sun sun;
	
	/**
	 * A Nap pozíciója a játékpanelen
	 */
	private Point pos = new Point(0, 0);

	/**
	 * Új, robotot ábrázoló címke jön létre. A grafikus osztály
	 * konstruktorparaméterben átveszi a kirajzolandó robotot.
	 * 
	 * A robotnak beállítja az ikont, és a nézet-objektum méreteit hozzáigazítja
	 * az ikon méretéhez. A címke hátterét átlátszóra állítja be. Nem rajzoltatja ki
	 * a címke keretét sem.
	 * 
	 * @param air	- a játékbeli Nap, amelyet a képernyőn meg kell jeleníteni
	 */
	public SunGraphics(Sun sun, Dimension panelSize) {
		this.sun = sun;
		
		this.setIcon(icon);
		this.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		this.setBorder(null);
		this.setOpaque(false);
		
		// Például helyezhetjük középre a Napot:
		pos.x = (panelSize.width - preferredWidth) / 2;
		pos.y = (panelSize.height - preferredWidth) / 2;
		this.setLocation(pos);
	}

	/**
	 * Visszatér a modellbeli Nappal.
	 * 
	 * @return A Nap modell-objektuma
	 */
	public Sun getSun() {
		return this.sun;
	}

	/**
	 * Frissíti és kirajzolja a Nap ikonját az Nap modell-objektum állapota alapján.
	 */
	@Override
	public void draw() {
		// this.setLocation(pos);
		if (sun.getTimeToSunStorm() < 3) {
			this.setIcon(stormyIcon);
		}
		else {
			this.setIcon(icon);
		}
		
		this.setBounds(new Rectangle(pos.x, pos.y,
								icon.getIconWidth(), icon.getIconHeight()));
		this.repaint();
	}

}
