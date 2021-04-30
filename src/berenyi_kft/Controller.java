package berenyi_kft;

import java.util.*;

import berenyi_kft_GUI.AsteroidGraphics;
import berenyi_kft_GUI.GamePanel;

/**
 * A jatek foosztalya, a jatek objektumait vezerelve vezenyeli a jatekot
 * @author berenyi_kft
 */
public class Controller {
	
	private Game game  = null;
	
	private ArrayList<Player> playersAlive = new ArrayList<Player>();
	
	private Player actPlayer = null;
	
	private State state = State.INIT;
	
	//---------------------------------------------------------------
	
	/**
	 * Megmondja a jatek jelenlegi allapotat.
	 * @return Az aktualis jatekallapot
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * Visszater a game attributummal.
	 * @return Az aszteroidaovet osszefogo jatek objektum
	 */
	public Game getGame() {
		return game;
	}
	
	/**
	 * Beallitja a state allapotot aktualis jatekallapotkent,
	 * felteve, hogy az allapot nem WON, LOST vagy EXITED.
	 * @param state Az uj jatekallapot
	 */
	public void setState(State state) {
		if (this.state != State.WON & this.state != State.LOST
				& this.state != State.EXITED) {
			Proto.print(Proto.getId(this) + ".setState("
					+ State.toString(state) + ")");
			this.state = state;
			System.out.println(" - " + State.toString(state));
		}
	}

	public String getDescription() {
		String str = "";

		String id = Proto.getId(this);
		str += "Controller " + id + "\n";

		String gameId = Proto.getId(game);
		str += "\tgame " + gameId + "\n";

		if (!playersAlive.isEmpty()) { // A doksiban allPlayers az attributum neve
			str += "\tallPlayers";
			for (Player p : playersAlive) {
				String playerId = Proto.getId(p);
				str += " " + playerId;
			}
			str += "\n";
		} else
			str += "\tallPlayers null\n";

		String actPlayerId = Proto.getId(actPlayer);
		str += "\tactPlayer " + actPlayerId + "\n";

		str += "\tstate " + State.toString(state) + "\n";

		return str;
	}
	
	/**
	 * Bekeri egy felhasznalotol a jatekosok szamat, majd sorban beolvassa
	 * a jatekosok nevet, es beallitja a legfontosabb attributumaikat.
	 * Letrehozza es inicializalja az aszteroidaovet (Game.startGame()),
	 * elozetesen atadva a telepesek listajat.
	 * @param sc Scanner, amellyel a jatekosok adatait beolvassa
	 */
	public void startGame(GamePanel gamePanel, List<String> playerNames) {
		Proto.println(Proto.getId(this) + ".startGame()");
		Proto.incrTabs();
		
		game = new Game();
		Proto.getAllObjects().setGame(game);
		game.setController(this);	// Beallitja a game-ben a controllert.
		game.setGamePanel(gamePanel);
		
		// Sorban beallitja a jatekosokat, es telepest rendel hozzajuk.
		for (String name : playerNames) {
			Player p = new Player();
			Proto.getAllObjects().addPlayer(p);
			playersAlive.add(p);
			
			Settler s = new Settler();
			Proto.getAllObjects().addSettler(s);
			game.addSettler(s);
			
			p.setName(name);
			p.setSettler(s);
			p.setAlive(true);
		}
		
		// Palyakep inicializalasa
		game.startGame();
		
		// Aszteroidak elhelyezese a panelen, majd a palyakep megjelenitese
		AsteroidGraphics.setAsteroidLocations();
		gamePanel.drawAll();
		
		// Az elso jatekos beallitasa actPlayer-nek
		this.nextPlayer();
		
		// TODO timer inditasa
		
		Proto.decrTabs();
	}	
	
	/**
	 * Kiirja a jatek vegeredmenyet a kepernyore, ha valoban veget ert.
	 * @param state A jatek allapota
	 */
	public void endGame(State state) {
		Proto.println(Proto.getId(this) + ".endGame("
				+ State.toString(state) + ")");
		setState(state);
		if (state == State.WON)
			Proto.println("Settlers won the game, the spacebase has been built!");
		else if (state == State.LOST)
			Proto.println("Settlers lost the game, everyone has died.");
		else
			Proto.println("(The game has not yet ended.)");
	}
	
	/**
	 * Visszater az aktualis lepo jatekossal.
	 * @return Az aktualis lepo jatekos
	 */
	public Player getActPlayer() {
		return actPlayer;
	}
	
	/**
	 * Beallitja a soron kovetkezo jatekost az actPlayer helyere.
	 */
	public void nextPlayer() {
		Proto.println(Proto.getId(this) + ".nextPlayer()");
		// TODO: Steppable leptetes
		// game.getTimer().tick();
		
		if (playersAlive.size() == 0)
			actPlayer = null;
		else {
			if (actPlayer == null)
				actPlayer = playersAlive.get(0);
			else {
				int idx = playersAlive.indexOf(actPlayer);
				if (idx == playersAlive.size() - 1)
					actPlayer = playersAlive.get(0);
				else
					actPlayer = playersAlive.get(idx + 1);
			}
		}
	}
	
	/**
	 * Torli a parameterkent kapott Settlerhez tartozo jatekost.
	 * @param s A telepes, akinek a jatekosa szamara a jatek veget er
	 */
	public void removePlayer(Settler s) {
		Proto.println(Proto.getId(this) + ".removePlayer("
				+ Proto.getId(s) + ")");
		Proto.incrTabs();
		if (!this.playersAlive.isEmpty()) {
			for (int i = playersAlive.size() - 1; i >= 0; i--) {
				if (playersAlive.get(i).getSettler() == s) {
					Player p = playersAlive.get(i);
					playersAlive.remove(p);
					Proto.println(p.getName() + ", you died!");
					Proto.getAllObjects().removePlayer(p);
				}
			}
		}
		Proto.decrTabs();
	}
	
	/**
	 * Beolvassa a jatek attributumait az sc Scanner aktualis poziciojatol.
	 * @param sc A beolvasast vegzo Scanner
	 */
	public void load(Scanner sc) {
		String line = sc.nextLine();
		while (!line.equals("") & sc.hasNextLine()) {
			line = sc.nextLine();
			line = line.stripLeading();
			String[] tokens = line.split("\\s+");
			
			switch (tokens[0]) {
				case "game":
					game = (Game)Proto.getObject(tokens[1]);
					break;
					
				case "allPlayers":
					for (int i = 1; i < tokens.length; i++) {
						Player p = (Player)Proto.getObject(tokens[i]);
						if (p != null)
							playersAlive.add(p);
					}
					break;
					
				case "actPlayer":
					actPlayer = (Player)Proto.getObject(tokens[1]);
					break;
					
				case "state":
					state = State.fromString(tokens[1]);
					break;
					
				default:
					break;
			}
		}
	}
	
}
