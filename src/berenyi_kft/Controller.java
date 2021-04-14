package berenyi_kft;

import java.util.*;

enum State {
	INIT,
	RUNNING,
	PAUSED,
	WON,
	LOST,
	EXITED;
	
	public static State fromString(String s) {
		switch (s) {
			case "init": return INIT;
			case "running": return RUNNING;
			case "paused": return PAUSED;
			case "won": return WON;
			case "lost": return LOST;
			default: return EXITED;
		}
	}
}

public class Controller {
	
	private Game game;
	
	private ArrayList<Player> playersAlive = new ArrayList<Player>();
	
	private Player actPlayer;
	
	private State state = State.INIT;
	
	
	public void startGame() {
		
	}
	
	
	public void endGame(State state) {
		if(state==State.WON)
			System.out.println("Settlers won");
		else if(state==State.LOST)
			System.out.println("Settlers lost");
		else
			System.out.println("The game has not ended");
	}
	
	public Player getActPlayer() {
		return actPlayer;
	}
	
	public void nextPlayer() {
		int idx=playersAlive.indexOf(actPlayer);
		if(idx==playersAlive.size()-1) {
			actPlayer=playersAlive.get(0);
			
			//Jelenleg itt van a léptetése a steppable-eknek
			game.getTimer().tick();  
		}
		else {
			actPlayer=playersAlive.get(idx+1);
		}
	}
	
	public void removePlayer(Player p) {
		playersAlive.remove(p);
	}
	
	/**
	 * 
	 * @param sc
	 */
	public void load(Scanner sc) {
		String line = sc.nextLine(); // fejlecsor
		line = sc.next();
		while (!line.equals("")) {
			String[] tokens = line.split("\\s");
			
			switch (tokens[0]) {
				case "game":
					game = (Game)Proto.getObject(tokens[1]);
					break;
					
				case "allPlayers":
					for (int i = 1; i < tokens.length; i++) {
						playersAlive.add((Player)Proto.getObject(tokens[i]));
					}
					break;
					
				case "actPlayer":
					actPlayer = (Player)Proto.getObject(tokens[1]);
					break;
					
				case "state":
					state = State.fromString(tokens[1]);
					break;
			}
			line = sc.next();
		}

	}
}
