package berenyi_kft_GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Cards /*implements ItemListener*/ {
	public final static String menuPanelID = "menuPanel";
	public final static String gamePanelID = "gamePanel";
	public final static String addPlayersPanelID = "addPlayersPanel";
	
	private JPanel cardsPanel;
	private MenuPanel menuPanel;
	private AddPlayersPanel addPlayersPanel;
	private GamePanel gamePanel;
	private JPanel endGamePanel; /*pauseGamePanel*/
	
	public void initComponents(Container pane) {		
        menuPanel = new MenuPanel(this);
        addPlayersPanel = new AddPlayersPanel(this);
        gamePanel = new GamePanel();
        
        cardsPanel = new JPanel(new CardLayout());
        cardsPanel.add(menuPanelID, menuPanel);
        cardsPanel.add(addPlayersPanelID, addPlayersPanel);
        cardsPanel.add(gamePanelID, gamePanel);
        pane.add(cardsPanel, BorderLayout.CENTER);
    }
	
	/*public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cardsPanel.getLayout());
        cl.show(cardsPanel, (String)evt.getItem());
    }*/
	
	public void show(String itemID) {
		CardLayout cl = (CardLayout)(cardsPanel.getLayout());
        cl.show(cardsPanel, itemID);
	}
	
}
