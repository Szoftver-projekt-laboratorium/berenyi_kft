package berenyi_kft;

import java.util.Scanner;

/**
 * Jatekos felhasznalo, aki a sajat telepeset iranyitja a jatekban
 * @author berenyi_kft
 */
public class Player {
	
	private String name = null;
	
	private Settler settler = null;
	
	private boolean isAlive = true;
	
	// private Controller controller;
	
	//------------------------------------------
	
	public String getDescription() { 
		
		String str="";
		
		String id=Proto.getId(this);
		str+="Player "+id+"\n";
		
		str+="\tname "+name+"\n";
		
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
		String line = sc.nextLine();
		while (!line.equals("") & sc.hasNextLine()) {
			line = sc.nextLine();
			line = line.stripLeading();
			String[] tokens = line.split("\\s+");
			
			switch (tokens[0]) {
				case "name":
					name = tokens[1];
					break;
					
				case "settler":
					settler = (Settler)Proto.getObject(tokens[1]);
					break;
					
				case "isAlive":
					isAlive = (tokens[1].equals("true"));
					break;
					
				default:
					break;
			}
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
		switch (cmd) {
			case PASS: /* No op */ break;
			case MOVE:
				int dir = Integer.parseInt((String)allParams[1]);
				settler.move(dir); break;
				
			case DRILL: settler.drill(); break;
			case MINE: settler.mine(); break;
			case RESTORE:
				Resource r = (Resource)allParams[1];
				settler.restore(r); break;
			
			case CREATE_ROBOT: settler.createAIRobot(); break;
			case CREATE_GATE_PAIR: settler.createGatePair(); break;
			case RELEASE_GATE: settler.releaseGate(); break;
			
			default: throw new IllegalArgumentException(
					"Invalid PlayerCommand: " + allParams[0]);
		}
	}
	
}
