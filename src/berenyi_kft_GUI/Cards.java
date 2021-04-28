package berenyi_kft_GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Cards {
	
	public final static String menuPanelID = "menuPanel";
	public final static String addPlayersPanelID = "addPlayersPanel";
	public final static String gamePanelID = "gamePanel";
	public final static String endGamePanelID = "endGamePanel";
	
	private JPanel cardsPanel;
	private MenuPanel menuPanel;
	private AddPlayersPanel addPlayersPanel;
	private GamePanel gamePanel;
	private JPanel endGamePanel;
	
	public JPanel getCardsPanel() {
		return cardsPanel;
	}
	
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	public void initComponents(Container pane) {
        menuPanel = new MenuPanel(this);
        addPlayersPanel = new AddPlayersPanel(this);
        gamePanel = new GamePanel(this);
        endGamePanel = new EndGamePanel(this);
        
        cardsPanel = new JPanel(new CardLayout());
        cardsPanel.add(menuPanel, menuPanelID);
        cardsPanel.add(addPlayersPanel, addPlayersPanelID);
        cardsPanel.add(gamePanel, gamePanelID);
        cardsPanel.add(endGamePanel, endGamePanelID);
        cardsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(cardsPanel, BorderLayout.CENTER);
    }
	
	public void show(String itemID) {
		CardLayout cl = (CardLayout)(cardsPanel.getLayout());
        cl.show(cardsPanel, itemID);
	}
	
	public Cards(Container pane) {
		initComponents(pane);
	}
	
}
