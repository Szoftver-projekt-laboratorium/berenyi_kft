package berenyi_kft;

import java.util.Scanner;

enum PlayerCommand {
	PASS,
	MOVE,
	DRILL,
	MINE,
	RESTORE,
	CREATE_ROBOT,
	CREATE_GATE_PAIR,
	RELEASE_GATE,
	INVALID;
	
	public static PlayerCommand fromString(String s) {
		switch (s) {
			case "pass": return PASS;
			case "move": return MOVE;
			case "drill": return DRILL;
			case "mine": return MINE;
			case "restore": return RESTORE;
			case "create_robot": return CREATE_ROBOT;
			case "create_gate_pair": return CREATE_GATE_PAIR;
			case "release_gate": return RELEASE_GATE;
			default: return INVALID;
		}
	}
}

public class Player {
	
	private String name;
	
	private Settler settler;
	
	private boolean isAlive=true;
	
	
	public String getDescription() { 
		
		String str="";
		
		String id=Proto.getId(this);
		str+="Player "+id+"\n";
		
		String nameId=Proto.getId(name);
		str+="\tname "+nameId+"\n";
		
		String settlerId=Proto.getId(settler);
		str+="\tsettler "+settlerId+"\n";
		
		if(isAlive)
			str+="\tisAlive true\n";
		else
			str+="\tisAlive false\n";
		
		return str;	
	}
	
	/*
	Player
		name Bela
		settler s1
		isAlive true
	*/
	public void load(Scanner sc) {
		String line = sc.nextLine(); // fejlecsor
		line = sc.nextLine();
		while (!line.equals("")) {
			String[] tokens = line.split("\\s");
			
			switch (tokens[0]) {
				case "name":
					name = (String)Proto.getObject(tokens[1]);
					break;
					
				case "settler":
					settler = (Settler)Proto.getObject(tokens[1]);
					break;
					
				case "isAlive":
					isAlive = (Boolean)Proto.getObject(tokens[1]);
					break;
					
				default:
					break;
			}
			line = sc.nextLine();
		}
	}
	
	
	public void setSettler(Settler s) {
		settler=s;
	}
	
	public Settler getSettler() {
		return settler;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	/**
	 * Vegrehajtja a jatekos telepesevel a cmd muveletet.
	 * @param cmd PlayerCommand tipusu muvelet, amit a telepesnek vegre kell hajtania
	 * @param allParams A parancs argumentumai; allParams[0] maganak a parancsnak a neve
	 */
	public void actOnSettler(PlayerCommand cmd, Object[] allParams) {
		//TODO switch-case a commandoknak
	}
	
}
