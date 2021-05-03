package berenyi_kft_GUI;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
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
import berenyi_kft.Coal;
import berenyi_kft.Resource;

public class CoalGraphics extends JButton implements IDrawable{
	
	private static List<CoalGraphics> allCoalGraphics
	= new ArrayList<CoalGraphics>();

	private static final String actionCommand = "Coal";
	
	private static final String iconPath = "src\\berenyi_kft_GUI\\Icons\\coal.png";
	
	private static final int preferredWidth = 50;
	
	private static final int thingTableSize = 3;
	
	private static final int preferredCellWidth = 30;
	
	private static final int queue_pos = 1;	
	
	private static Icon icon;
	
	public static List<CoalGraphics> getCoalGraphics() {
		return allCoalGraphics;
	}
	
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
			icon = new ImageIcon(img, "Coal");
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//TODO number pos
	public static Point getNumberPos(Resource r) {
		return new Point(0,0);
	}
	
	private final Coal coal;
	
	private Point pos = new Point(0, 0);
	
	public CoalGraphics(Coal c, Dimension panelSize) {
		allCoalGraphics.add(this);
		this.coal = c;
		
		this.setActionCommand(actionCommand);
		this.setIcon(icon);
		this.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		
		pos.x = panelSize.width/5 * queue_pos + 20;
		pos.y = panelSize.height/2 - preferredWidth/2;
	}
	
	/**
	 * Visszater a modellbeli szennel.
	 * @return a szen modell-objektuma
	 */
	public Coal getCoal() {
		return this.coal;
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		if (this.coal == null) {
			allCoalGraphics.remove(this);
			return;
		}
		this.setBounds(new Rectangle(pos.x, pos.y,
				icon.getIconWidth(), icon.getIconHeight()));
		this.repaint();
		
	}

}
