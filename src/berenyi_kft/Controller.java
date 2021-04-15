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
	private Proto proto;
	
	 public String getDescription() { 
			
			String str="";
			
			String id=Proto.getId(this);
			str+="Controller "+id+"\n";
			
			String gameId=Proto.getId(game);
			str+="\tgame "+gameId+"\n";
			
			if(!playersAlive.isEmpty()) {   // A doksiban allPlayers az attribútum neve
				str+="\tplayersAlive";
				for(Player p : playersAlive) {
					String playerId=Proto.getId(p);
					str+=" "+playerId;
				}
				str+="\n";
			}
			else
				str+="\tplayersAlive null\n";
			
			String actPlayerId=Proto.getId(actPlayer);
			str+="\tactPlayer "+actPlayerId+"\n";
			
			if(state==State.RUNNING)
				str+="\tgameRunning true\n";
			else
				str+="\tgameRunning false\n";
			
			return str;	
		}
	
	public void startGame() {
		// TODO
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
	 * Beolvassa a jatek attributumait az sc Scanner aktualis poziciojatol.
	 * @param sc A beolvasast vegzo Scanner
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
						Player p = (Player)Proto.getObject(tokens[i]);
						// TODO: Kollekciok eseten nem szabad null-t belepakolni!
						// Olyan kollekcio nincs, amelyben szerepelne null elem is.
						// Ha tehat null-t olvasunk be, azt ki kell hagyni.
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
			line = sc.next();
		}
	}
	
}
