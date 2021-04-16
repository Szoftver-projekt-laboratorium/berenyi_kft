package berenyi_kft;

import java.util.Scanner;

public class Player {
	
	enum Command{
		PASS,
		MOVE,
		DRILL,
		MINE,
		RESTORE,
		CREATE_ROBOT,
		CREATE_GATE_PAIR,
		RELEASE_GATE
	}
	
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
	
	public void actOnSettler(Command cmd, Object[] params) {
		//TODO switch-case a commandoknak
	}
	
}
