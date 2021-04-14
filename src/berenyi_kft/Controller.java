package berenyi_kft;

import java.util.*;

public class Controller {
	
	private Game game;
	
	private ArrayList<Player> playersAlive=new ArrayList<Player>();
	
	private Player actPlayer;
	
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
