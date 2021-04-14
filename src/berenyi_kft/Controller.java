package berenyi_kft;

import java.util.*;

public class Controller {
	
	private Game game;
	
	private ArrayList<Player> playersAlive=new ArrayList<Player>();
	
	private Player actPlayer;
	
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
			
			if(Proto.getState()==Proto.State.RUNNING)
				str+="\tgameRunning true\n";
			else
				str+="\tgameRunning false\n";
			
			return str;	
		}
	
	public void startGame() {
		
	}
	
	
	public void endGame(Proto.State state) {
		if(state==Proto.State.WON)
			System.out.println("Settlers won");
		else if(state==Proto.State.LOST)
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
}
