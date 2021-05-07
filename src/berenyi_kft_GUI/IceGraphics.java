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
import javax.swing.JButton;

import berenyi_kft.Ice;
import berenyi_kft.Resource;

public class IceGraphics extends JButton implements IDrawable{
	
	private static List<IceGraphics> allIceGraphics
	= new ArrayList<IceGraphics>();

	private static final String actionCommand = "Ice";
	
	private static final String iconPath = "src\\berenyi_kft_GUI\\Icons\\ice.png";
	
	private static final int preferredWidth = 70;
	
	private static final int thingTableSize = 3;
	
	private static final int preferredCellWidth = 30;
	
	private static final int queue_pos = 2;
	private static final int element_number = 5;
	
	private static Icon icon;
	
	public static List<IceGraphics> getIceGraphics() {
		return allIceGraphics;
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
			icon = new ImageIcon(img, "Ice");
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//TODO number pos
	public static Point getNumberPos(Resource r) {
		return new Point(0,0);
	}
	
	private final Ice ice;
	
	private Point pos = new Point(0, 0);
	
	public IceGraphics(Ice i, Dimension panelSize) {
		// allIceGraphics.add(this);
		this.ice = i;
		
		this.setActionCommand(actionCommand);
		this.setIcon(icon);
		this.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		this.setBorderPainted(true);
		this.setContentAreaFilled(false);
		
		// pos.x = panelSize.width/element_number * queue_pos + 20;
		// pos.y = panelSize.height/2 - preferredWidth/2;
	}
	
	/**
	 * Visszater a modellbeli szennel.
	 * @return a szen modell-objektuma
	 */
	public Ice getIce() {
		return this.ice;
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		if (this.ice == null) {
			allIceGraphics.remove(this);
			return;
		}
		this.setBounds(new Rectangle(pos.x, pos.y,
				icon.getIconWidth(), icon.getIconHeight()));
		this.repaint();
		
	}

}
